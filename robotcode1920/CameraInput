package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import frc.robot.RobotContainer;
import frc.robot.command.CameraFeeder;

public Class CameraInput extends SubsystemBase {
    
    public UsbCamera frontCamera, backCamera;
    
    public void CameraInput() {
        frontCamera = CameraServer.getInstance().startAutomaticCapture(0);
        backCamera = CameraServer.getInstance().startAutomaticCapture(1);
    }

    public void CameraOutput(UsbCamera a, String b) {
        a.getVideo();
        a.putVideo(b, 640, 480);
    }

    @Override
    public void periodic() {
    // This method will be called once per scheduler run
        setDefaultCommand(new CameraFeeder);
    }

}
