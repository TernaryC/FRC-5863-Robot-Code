import wpilib
import wpilib.drive
from wpilib.interfaces.generichid import GenericHID
import os

class MyRobot(wpilib.IterativeRobot):
	def robotInit(self):
		wpilib.CameraServer.launch("vision.py:main")
		self.timer = wpilib.Timer()
		
		###Init XBOX
		self.gamepad = wpilib.XboxController(0)
		
		###Assign Motors to variables
		self.motor = []
		self.motor.append(wpilib.Spark(0))
		self.motor.append(wpilib.Spark(1))
		self.motor.append(wpilib.DMC60(2))
		self.motor.append(wpilib.DMC60(3))
		self.motor.append(wpilib.DMC60(4))
		###
		# 0 - Front Left (Practice Left)
		# 1 - Front Right (Practice Right)
		# 2 - Rear Left
		# 3 - Rear Right
		# 4 - Arm Motor
		###
		
		self.driveLeft = wpilib.SpeedControllerGroup(self.motor[0], self.motor[2])
		self.driveRight = wpilib.SpeedControllerGroup(self.motor[1], self.motor[3])
		
		self.drive = wpilib.drive.DifferentialDrive(self.driveLeft, self.driveRight)
		
		self.compressor = wpilib.Compressor(0)
		self.compressor.setClosedLoopControl(True)
		self.solenoid = wpilib.DoubleSolenoid(0, 1)
		
		# DIO PORTS:
		self.encoder = wpilib.Encoder(0,1,False)
		self.lift_limit_up = wpilib.DigitalInput(2)
		self.lift_limit_down = wpilib.DigitalInput(3)
		
		self.arm_tick = 0
		
		self.arm_open = wpilib.DoubleSolenoid.Value.kForward
		self.arm_closed = wpilib.DoubleSolenoid.Value.kReverse
		self.arm_off = wpilib.DoubleSolenoid.Value.kOff
		self.motor[0].set(0)
		self.platform_tick = 0
		self.ds = wpilib.DriverStation.getInstance()
		self.alliance = self.ds.getAlliance()
		self.data = []
		# AUTONOMOUS INSTRUCTIONS!
		self.timerAction = [None, None, None, None, None, None]
		self.timerAction[0] = {
			3.5: {
				"n": 0.0,
				"f": 0.7,
				"t": 0
				},
			3.6: {
				"n": 0.0,
				"f": 0,
				"t": 0,
				}
		}
		self.platformTrain = wpilib.drive.DifferentialDrive(self.motor[4], self.motor[4])
	
	def autonomousInit(self):
		self.timer.reset()
		self.timer.start()
		self.f = 0
		self.t = 0
		self.currentAction = self.timerAction[0]
		#######################################################################
		self.encoder.reset()
		self.data = self.ds.getGameSpecificMessage()
	
	def autonomousPeriodic(self):		
		#time = self.time
		#print(count)
		pass
	def teleopInit(self):
		""" DriveStation reference:
		ds = wpilib.DriverStation.getInstance()
		log = self.logFILE
		log.write("FRC Competition 2017-2018\n")
		log.write(" -- {}\n".format(ds.getEventName()))
		log.write(" -- {}\n".format(ds.getGameSpecificMessage()))
		log.write("-" * 80)
		log.write("\n Driver Station: {}\n".format(ds.getInstance()))
		log.write("-" * 80)
		log.write("\n Alliance: {}\n".format(ds.getAlliance()))
		log.write(" Location: {}\n".format(ds.getLocation()))
		log.write(" Match Number {}\n".format(ds.getMatchNumber()))
		log.write("-" * 80)
		log.write("\n")
		"""
		#self.motor[4].set(0.8)
		#print("STUFF!")
	
	def teleopPeriodic(self):
		#print(self.motor[4].get())
		
		### MAP CONTROLLER
		lefth = GenericHID.Hand.kLeft		
		righth = GenericHID.Hand.kRight

		stk_LX = self.gamepad.getX(lefth)
		stk_LY = self.gamepad.getY(lefth)
		stk_RY = self.gamepad.getY(righth)
		btn_B = self.gamepad.getBButton()
		btn_X = self.gamepad.getXButton()
		btn_Y = self.gamepad.getYButton()
		btn_A = self.gamepad.getAButton()
		bmp_L = self.gamepad.getBumper(lefth)
		bmp_R = self.gamepad.getBumper(righth)
		###
		
		#drive_modifier = (stk_RY + 1) / 2
		##print(":",drive_modifier)
		speed = 1
		if bmp_L:
			#logOut.append("Slow Driving Activated")
			speed = 0.65
		#else:
			#logOut.append("Slow Driving Off")
		speed *= -1
		self.drive.arcadeDrive(stk_LX * .7, stk_LY * speed)
		
		## Grabbing Arm
		
		if (btn_B or btn_X) and not (btn_B and btn_X):
			#logOut.append("Grabbing Arm Activated")
			if self.arm_tick == 1000:
				self.solenoid.set(self.arm_off)
			elif not btn_X:
				#logOut.append("		-- Open!")
				self.solenoid.set(self.arm_open)
				self.arm_tick += 1
			elif not btn_B:
				#logOut.append("		-- Close!")
				self.solenoid.set(self.arm_closed)
				self.arm_tick += 1
		else:
			#logOut.append("Grabbing Arm Deactivated")
			self.solenoid.set(self.arm_off)
			self.arm_tick = 0
		
		## Raising Platform
		#platform = self.motor[4]
		platform_speed = 0
		
		#self.motor[4].set(stk_RY)
		
		if btn_Y and not btn_A:
			#if not self.lift_limit_up.get():
				platform_speed = -1
		elif btn_A and not btn_Y:
			#if not self.lift_limit_down.get():
				platform_speed = 1
			
		#self.platformTrain.arcadeDrive(0, platform_speed)  # RY Doesn't work???
	def testPeriodic(self):
		wpilib.LiveWindow.run()
		
		
if __name__ == "__main__":
    wpilib.run(MyRobot)