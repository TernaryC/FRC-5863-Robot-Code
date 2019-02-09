#!/usr/bin/env python3

# Import wpilib and related libraries
import wpilib
import wpilib.drive
from wpilib.interfaces.generichid import GenericHID

# Other Imports
import math

### Functions ###

def stickFunc(sticks, deadzone, check):
	x = 0 if abs(sticks[0]) < deadzone else sticks[0]
	y = 0 if abs(sticks[1]) < deadzone else sticks[1]
	
	if check:
		theta = math.atan2(y, x)
		
		if (x == y == 0):
			nx = 0
			ny = 0
		else:
			nx = math.cos(theta)
			ny = math.sin(theta)
	else:
		if x == abs(x): nx = 1
		else: nx = -1
		if y == abs(y): ny = 1
		else: ny = -1
		
		if (x == y == 0):
			nx = 0
			ny = 0
		
	return [nx, ny]
	
			
### Robot Code ###

class MyRobot(wpilib.IterativeRobot):

	def robotInit(self):
		""" Robot Initialization. Runs on turn-on. """	

		### Xnabled? ###
		self.true_run = wpilib.SendableChooser()
		self.true_run.addDefault("On", True)
		self.true_run.addObject("Off", False)
		
		### Setup Camera ###
		wpilib.CameraServer.launch("vision.py:main")
		
		### Drive Train Initialization ###
		
		# Motors
		self.pwm = [None,None,None,None,None,None,None,None,None,None]
		self.pwm[0] = wpilib.Victor(0)
		self.pwm[1] = wpilib.Victor(1)
		self.pwm[2] = wpilib.Spark(2)
		self.pwm[3] = wpilib.Spark(3)
		self.pwm[7] = wpilib.Servo(7)
		self.pwm[8] = wpilib.Victor(8)
		self.pwm[9] = wpilib.Victor(9)
		
		# Drive Train
		self.driveLeft = wpilib.SpeedControllerGroup(self.pwm[0], self.pwm[2])
		self.driveRight = wpilib.SpeedControllerGroup(self.pwm[1], self.pwm[3])
		
		self.driveTrain = wpilib.drive.DifferentialDrive(self.driveLeft, self.driveRight)
		
		### Initialize Gamepad ###
		self.gamepad = wpilib.XboxController(0)
		
		### Other Inputs ###
		self.finderA = wpilib.AnalogInput(0)
		self.finderB = wpilib.AnalogInput(1)
		
		### Variables ###
		
		self.allignment = True
		self.direction = -1
		
		
	def autonomousInit(self):
		pass
	def autonomousPeriodic(self):
		pass
	def teleopInit(self):
		pass
	def teleopPeriodic(self):
		""" Robot Teleoperation. Runs every millisecond when robot is on """
		
		### Xnabled? ###
		xnabled = self.true_run.getSelected()
		
		### Controller Setup ###
		leftH = GenericHID.Hand.kLeft
		rightH = GenericHID.Hand.kRight
		
		# Left Stick
		leftX = self.gamepad.getX(leftH)
		leftY = self.gamepad.getY(leftH) #         v Deadzone
		[leftX, leftY] = stickFunc([leftX, leftY], 0.16, self.allignment)
		
		# Right Stick
		rightX = self.gamepad.getX(rightH)
		rightY = self.gamepad.getY(rightH) #           v Deadzone
		[rightX, rightY] = stickFunc([rightX, rightY], 0.08, self.allignment)
		
		# Triggers
		leftT = self.gamepad.getTriggerAxis(leftH) # These don't work in the robotsim!
		rightT = self.gamepad.getTriggerAxis(rightH)
		#print("Left: {}\nRight: {}".format(leftT, rightT))
		
		# Buttons
		togY = self.gamepad.getYButtonPressed() # Was 'Y' Pressed (not held)?
		togA = self.gamepad.getAButtonPressed() # Was 'A' Pressed (not held)?
		holdB = self.gamepad.getBButton() # Is 'B' Held? Need to Inverse 
		holdLB = self.gamepad.getBumper(leftH) # Is "Bumper L" Held?
		holdRB = self.gamepad.getBumper(rightH) # Is "Bumper R" Held?
		
		### Other Inputs ###
		
		#print(self.finderA.getValue(), self.finderB.getValue())
		
		### Controling the Robot ###
		
		# Driving Toggles
		if togY: self.direction *= -1
		if togA and self.allignment: self.allignment = False
		elif togA: self.allignment = True
		
		# Speed Multiplier
		driveM = abs(rightT) * 0.8
		
		# Brake
		brake = 0 if leftT > .5 else 1
		
		# Driving the Robot
		if (xnabled):
			self.driveTrain.arcadeDrive(leftX * driveM * brake,
										leftY * driveM * self.direction * brake) 
		
		# Ball Retriever
		if holdLB: self.pwm[8].set(-1)
		else: self.pwm[8].set(0)
		
		# Ball Shooter
		if holdRB: self.pwm[9].set(-1)
		else: self.pwm[9].set(0)
		
		# Ramp
		if holdB: self.pwm[7].set(0.5)
		else: self.pwm[7].set(0)
		
		### Smart Dashboard ###
		
		#TODO: Round Motor Outputs
		wpilib.SmartDashboard.putNumber("Modifier", driveM)
		wpilib.SmartDashboard.putNumber("Drive FL", self.pwm[0].get())
		wpilib.SmartDashboard.putNumber("Drive FR", self.pwm[1].get())
		wpilib.SmartDashboard.putNumber("Drive BL", self.pwm[2].get())
		wpilib.SmartDashboard.putNumber("Drive BR", self.pwm[3].get())
		wpilib.SmartDashboard.putNumber("Retrieve", self.pwm[8].get())
		wpilib.SmartDashboard.putNumber("Launcher", self.pwm[9].get())
		wpilib.SmartDashboard.putNumber("Pin Servo", self.pwm[7].get())
		wpilib.SmartDashboard.putNumber("Pin Angle", self.pwm[7].getAngle())
		
		wpilib.SmartDashboard.putNumber("Left Stick X", leftX)
		wpilib.SmartDashboard.putNumber("Left Stick Y", leftY)
		wpilib.SmartDashboard.putNumber("Right Stick X", rightX)
		wpilib.SmartDashboard.putNumber("Right Stick Y", rightY)
		wpilib.SmartDashboard.putNumber("Left Trigger", leftT)
		wpilib.SmartDashboard.putNumber("Right Trigger", rightT)
		wpilib.SmartDashboard.putBoolean(" LB", self.gamepad.getBumper(leftH))
		wpilib.SmartDashboard.putBoolean(" RB", self.gamepad.getBumper(rightH))
		wpilib.SmartDashboard.putBoolean(" A", self.gamepad.getAButton())
		wpilib.SmartDashboard.putBoolean(" Y", self.gamepad.getYButton())
		wpilib.SmartDashboard.putBoolean(" B", self.gamepad.getBButton())
		
		#wpilib.SmartDashboard.putNumber("RF0", self.finderA.getValue())
		#wpilib.SmartDashboard.putNumber("RF1", self.finderB.getValue())
		
		wpilib.SmartDashboard.putData("Xnabled?", self.true_run)
		wpilib.SmartDashboard.putBoolean(" Brake", brake == 0)
		wpilib.SmartDashboard.putBoolean(" Driving", self.allignment)
		wpilib.SmartDashboard.putBoolean(" Turning", not self.allignment)
		wpilib.SmartDashboard.putBoolean(" Front", self.direction == -1)
		wpilib.SmartDashboard.putBoolean(" Back", self.direction == -1)
		
	def testPeriodic(self):
		leftH = GenericHID.Hand.kLeft
		rightH = GenericHID.Hand.kRight
		leftT = self.gamepad.getTriggerAxis(leftH) # These don't work in the robotsim!
		rightT = self.gamepad.getTriggerAxis(rightH)
		pass

if __name__ == "__main__":
	wpilib.run(MyRobot)
	
"""
### Programming Tips ###

# When titling functions, use triple quotes.
# When titling sections of code, use triple pounds (see above).
# When titling lines of codes, use regular comments.

# When writing comments, use a pound sign followed by a space.
# When removing lines of code, use a pound sign without a space.
# When writing comments on an existing line of code, place them at the end.
	Seperate the code and the comment with a space.
# When writing TODOs, use "#TODO: " at the front of it.
# Use a full line of 80 pound signs to create a horizontal line to seperate,
	code, if you want.

# Variables that start with "self." are usable in the entire class.
# Variables without "self." are only usable in that function.
# "self." variables should only be initialized in the robotInit() function.
# Variables that do not need to be used in more than one function should not be 
	made "self." variables, for the sanity of the programmer.

# Variable names should be pascalCased, CamelCased, or kebab-cased.
# Commas should always be followed by a space.
# Equals signs and other math symbols should be surrounded by spaces.
# Indents should be made using the Tab key, not using spaces.
# Blank lines should be used to seperate areas of code into more orginized
	sections.
# Three blank lines should be used to seperate functions from one another.
# Similar lines of code next to one another should have blank space added to
	keep them consistent.
	
	(i.e.)
	x = (20.2 + 10  )
	y = (1.5  + 10.2)
	
# If a line is longer than 80 characters (the blue line), it should be seperated
	into shorter lines using valid methods.

# Valid methods for seperating a line into shorter lines are as follows:
	# Lines that contain commas can be split at those commas. New lines created
		should be intended such that each piece of text that the commas seperate
		starts at the same place.
		
		list = ["this",
				"is",
				"a",
				"list"]
				
	# Long conditional statements can be spit at logic operands (and, or, not).
		New lines created should start at one indent further than the following
		lines.
		
		if (((this == long) and (length > 80)) or (OCD.active == True)
				or (!you.care)):
			doConditional()
			
	# Long comments should be split after the last word that ends within the
		80-character limit. New lines created should also be commented.
			
# Functions, classes, and multi-line strings (triple quotes) can be collapsed or
	extended by clicking the [+] or [-] to the left of the start of the block.

"""