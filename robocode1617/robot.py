import wpilib
from wpilib.interfaces.generichid import GenericHID

class MyRobot(wpilib.IterativeRobot):
	def robotInit(self):
		###Assign Talons to variables
		self.motor = []
		for i in range(0,4): self.motor.append(wpilib.Talon(i))
		self.winch_motor = wpilib.Talon(6)
		###
		# 0 - Front Left
		# 1 - Front Right
		# 2 - Rear Left
		# 3 - Rear Right
		###
		
		###Init XBOX
		self.gamepad = wpilib.XboxController(0)
		
	def teleopInit(self):
		wpilib.CameraServer.launch("vision.py:main")
	def teleopPeriodic(self):
	
		### MAP CONTROLLER
		left = GenericHID.Hand.kLeft
		right = GenericHID.Hand.kRight

		btn_B = self.gamepad.getBButton()
		btn_X = self.gamepad.getXButton()
		btn_L = self.gamepad.getStickButton(left)
		stk_LX = self.gamepad.getX(left) 
		stk_RY = self.gamepad.getY(right)
		triggers = self.gamepad.getRawAxis(2)
		
		###Winch Modifier
		##Winch deadzone
		winch_mod = stk_RY / 2 if stk_RY / 2 > -0.1 or stk_RY / 2 < 0.1 else 0
			
		###Winch
		winch_speed = 0
		if btn_B:
			##Run winch backwards
			winch_speed = -0.6 - winch_mod if not btn_X else 0
		if btn_X:
			##Run winch forwards
			winch_speed = 0.6 + winch_mod if not btn_B else 0
		
		self.winch_motor.set(winch_speed)
		
		###Drive
		##Turning deadzone
		turning = 0 if stk_LX <= 0.2 and stk_LX >= -0.2 else stk_LX
		
		##Forward and Reverse
		speedmod = -triggers if abs(triggers) >= 0.1 else 0
		#print(speedmod)
		##Turning
		l_speed = r_speed = speedmod
		r_speed += turning if turning < 0 else turning / 2
		l_speed += turning if turning >= 0 else turning / 2
		if btn_L: ##SHUT DOWN self.motorS
			l_speed = 0
			r_speed = 0
		##Set drivetrain self.motors
		for i in range(0,2): self.motor[i].set(l_speed)
		for i in range(2,4): self.motor[i].set(r_speed)
		
		
if __name__ == "__main__":
    wpilib.run(MyRobot)