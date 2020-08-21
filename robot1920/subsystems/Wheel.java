package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import frc.robot.commands.ColorWheelSpinner;
import frc.robot.RobotContainer;

public class Wheel extends SubsystemBase {

  private WPI_TalonSRX spinMotor;

  
    public Wheel() {
      //Creates a new motor object.
      spinMotor = new WPI_TalonSRX(RobotContainer.spinner);
      //Reset any pre-programmed settings.
      spinMotor.configFactoryDefault();
      //Assigns the Magnetic Shaft Encoder as one of this motor's input device.
      spinMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    //Simple command that causes the motor to activate.
    public void wheelMove(double a) {
      spinMotor.set(a);
    }
    
    @Override
    public void periodic(){
         setDefaultCommand(new ColorWheelSpinner());
    }
}
