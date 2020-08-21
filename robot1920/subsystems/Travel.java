package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.Traveller;

public class Travel extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  VictorSP travelMotor;

  public Travel() {
   travelMotor = new VictorSP(RobotContainer.travelMotor);

   SpeedControllerGroup leftSide = new SpeedControllerGroup(travelMotor);
  }

  public void TravelMove(double a) {
    travelMotor.setSpeed(a);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new Traveller());
  }
}
