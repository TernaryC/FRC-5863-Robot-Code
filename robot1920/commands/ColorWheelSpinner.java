package frc.robot.commands;

import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.CANCoderFaults;
import com.ctre.phoenix.sensors.CANCoderStickyFaults;
import com.ctre.phoenix.sensors.MagnetFieldStrength;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.*;

/**
 * An example command that uses an example subsystem.
 */
public class ColorWheelSpinner extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  
  double spinMotor;
  double multiplier;
  
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ColorWheelSpinner() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_wheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //This code an be optimized. 
    if (Robot.m_input.testController.getAButton() == true) {
      multiplier = Robot.m_input.testController.getTriggerAxis(Hand.kRight);
      Robot.m_wheel.wheelMove(multiplier); 
    }
    
    if (Robot.m_input.testController.getAButton() == true && Robot.m_input.testController.getBumper(Hand.kLeft) == true) {
      multiplier = Robot.m_input.testController.getTriggerAxis(Hand.kRight);
      Robot.m_wheel.wheelMove(-multiplier);
    }

    if (Robot.m_input.testController.getAButton() == false) {
        Robot.m_wheel.wheelMove(0); 
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
