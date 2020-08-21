package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.*;

/**
 * An example command that uses an example subsystem.
 */
public class Climb extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  
  double grabmotor;
  double liftmotor;
  double multiplier;
  /**.
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Climb() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_lift);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    multiplier = Robot.m_input.testController.getTriggerAxis(Hand.kRight);

    if (Robot.m_input.testController.getXButton() == true) {
      Robot.m_lift.grabMove(multiplier); 
    }

    if (Robot.m_input.testController.getXButton() == true && Robot.m_input.testController.getBumper(Hand.kRight) == true) {
      Robot.m_lift.grabMove(-multiplier);
    }

    if (Robot.m_input.testController.getXButton() == false) {
      Robot.m_lift.grabMove(0); 
    }
    
    if (Robot.m_input.testController.getYButton() == true) {
      Robot.m_lift.liftMove(-multiplier);
    }
    
    if (Robot.m_input.testController.getYButton() == true && Robot.m_input.testController.getBumper(Hand.kRight) == true) {
      Robot.m_lift.liftMove(multiplier);
    }

    if (Robot.m_input.testController.getYButton() == false) {
      Robot.m_lift.liftMove(0); 
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
