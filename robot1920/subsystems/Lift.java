package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.Climb;

public class Lift extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  VictorSP grabMotorFront, grabMotorRear, liftMotor;

  public Lift() {
    grabMotorRear = new VictorSP(RobotContainer.grabMotorRear);
    grabMotorFront = new VictorSP(RobotContainer.grabMotorFront);
    liftMotor = new VictorSP(RobotContainer.liftMotor);
  }

  public void grabMove(double a) {
    grabMotorRear.setSpeed(a);
    grabMotorFront.setSpeed(-a);
  }

  public void liftMove(double b) {
    liftMotor.setSpeed(b);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new Climb());
  }
}
