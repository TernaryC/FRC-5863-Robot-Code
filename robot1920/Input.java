package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.DualStick;

public class Input {
    //This is self-explanatory. The controller is being initiated from a class and created into an object.
    public XboxController testController = null;
    
    public Input() {
        testController =  new XboxController(RobotContainer.testController);
    }

}