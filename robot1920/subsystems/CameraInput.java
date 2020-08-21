package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import frc.robot.RobotContainer;

public class CameraInput extends SubsystemBase {
    
    public UsbCamera frontCamera, backCamera;
    
    public CameraInput() {
        //frontCamera = CameraServer.getInstance().startAutomaticCapture(RobotContainer.frontCamera);
        CameraThread cameraThread = new CameraThread();
        new Thread(cameraThread).start();
        //backCamera = CameraServer.getInstance().startAutomaticCapture(RobotContainer.backCamera);
    }

    public CvSource cameraOutput(UsbCamera a, String b) {
        CvSink c = CameraServer.getInstance().getVideo(a);
        
        return CameraServer.getInstance().putVideo(b, 640, 480);
    }

    @Override
    public void periodic() {
    // This method will be called once per scheduler run
        //setDefaultCommand(new CameraFeeder());
    //Someone needs to write a proper stop() method.
    }
    private class CameraThread implements Runnable {
        CvSource frontOutput;
        public CameraThread() {
            frontCamera = CameraServer.getInstance().startAutomaticCapture(RobotContainer.frontCamera);
        }

        public void run() {
                frontOutput = cameraOutput(frontCamera, "Front Camera");
        }

       /* public void stop() {

        }*/
    }
}