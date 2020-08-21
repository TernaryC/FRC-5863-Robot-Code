/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.ArcadeDrive;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  Spark leftFrontMotor, rightFrontMotor;
  VictorSP leftBackMotor, rightBackMotor;
  DifferentialDrive drive;
  
  public DriveTrain() {
    //Creates motor objects for robot movement.
    leftFrontMotor = new Spark(RobotContainer.frontLeftMotor);
    rightFrontMotor = new Spark(RobotContainer.frontRightMotor);
    leftBackMotor = new VictorSP(RobotContainer.backLeftMotor);
    rightBackMotor = new VictorSP(RobotContainer.backRightMotor);

    //Creates objects containing movement motors. Here, the motors are grouped by the side of the robot they occupy.
    SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
    SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);
    drive = new DifferentialDrive(leftSide, rightSide);
  }

  /*Motors are moved by this command. 
  controllerPassthrough is used as an intuitive command due to motors
  being reversed from the expected direction*/
  public void controllerPassthrough(double a, double b) {
    drive.arcadeDrive(-a,-b);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new ArcadeDrive());
  }
}
