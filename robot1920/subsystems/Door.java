package frc.robot.subsystems;


//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.DoorOpen;

public class Door extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  VictorSP doorMotor;

  public Door() {
    doorMotor = new VictorSP(RobotContainer.doorMotor);
  }

  public void doorMove (double a) {
    doorMotor.setSpeed(a); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new DoorOpen());
    }
}
