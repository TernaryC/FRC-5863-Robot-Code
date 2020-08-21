/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.*;

/**
 * An example command that uses an example subsystem.
 */
public class ArcadeDrive extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  
  double leftStickX;
  double leftStickY;
  double leftTrigger;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by thi s command.
   */
  public ArcadeDrive() {
    // Use addRequirements() here to declare subsystem depe ndencies.
    addRequirements(Robot.m_driveTrain);
  }

  // Called when the command is initially sch eduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftStickX = Robot.m_input.testController.getX(Hand.kLeft);
    leftStickY  = Robot.m_input.testController.getY(Hand.kLeft);
    leftTrigger = Robot.m_input.testController.getTriggerAxis(Hand.kLeft)*0.8;
    Robot.m_driveTrain.controllerPassthrough(leftStickY*leftTrigger, -leftStickX*leftTrigger);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
