package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.*;

/**
 * An example command that uses an example subsystem.
 */
public class DoorOpen extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  
  double doorMotor;
  /**.
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DoorOpen() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_door);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double multiplier = Robot.m_input.testController.getTriggerAxis(Hand.kRight);
    if (Robot.m_input.testController.getBButton() == true) {
      Robot.m_door.doorMove(multiplier*10);
    } 
    if (Robot.m_input.testController.getBButton() == false) {
      Robot.m_door.doorMove(0);
    } 
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
