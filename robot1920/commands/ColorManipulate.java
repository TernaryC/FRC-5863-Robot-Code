package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example command that uses an example subsystem.
 */
public class ColorManipulate extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  
  int spinCount = 0;
  int fullRotationCount = 0;
  int fiveCount = 0;
  int DPad = -1;
  Color colorNeeded;
  boolean DPressed = false;
  boolean spinActive = false;
  int sawColor = 0;
  int fiveSpins = 0;

  public ColorManipulate() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_colorDetect);
    //addRequirements(Robot.m_wheel);
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   DPad = Robot.m_input.testController.getPOV();
   SmartDashboard.putNumber("D-Pad", DPad);
    switch(DPad) {
      case 0:
        colorNeeded = Constants.kGreenTarget;
        //SmartDashboard.putString("ColorD", "Green");
        DPressed = true;
        break;
      case 90:
        colorNeeded = Constants.kBlueTarget;
        //SmartDashboard.putString("ColorD", "Blue");
        DPressed = true;
        break;
      case 180:
        colorNeeded = Constants.kYellowTarget;
        //SmartDashboard.putString("ColorD", "Yellow");
        DPressed = true;
        break;
      case 270:
        colorNeeded = Constants.kRedTarget;
        //SmartDashboard.putString("ColorD", "Red");
        DPressed = true;
        break;
    }

    if (Robot.m_colorDetect.colorCounter() != colorNeeded && DPressed == true) {
      Robot.m_wheel.wheelMove(0.3);
      if (Robot.m_colorDetect.colorCounter() == colorNeeded) {
        Robot.m_wheel.wheelMove(0);
        DPressed = false;
      }
    }

    if (Robot.m_input.testController.getStickButton(Hand.kRight)) {
      spinActive = true;
    } 
    
    if (spinActive == true) {
      if (Robot.m_colorDetect.colorCounter() == colorNeeded) {
        sawColor += 1;
      }
      if (sawColor % 2 == 0) {
        fiveSpins += 1;
      }
      if (fiveSpins < 5) {
        Robot.m_wheel.wheelMove(0.5);
      }
      if (fiveSpins >= 5) {
        Robot.m_wheel.wheelMove(-0.5);
      }
      else if (fiveSpins == 10) {
        Robot.m_wheel.wheelMove(0);
        spinActive = false;
        sawColor = 0;
        fiveSpins = 0;
        }
      }

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished(){
    return false;
    }
    
}
