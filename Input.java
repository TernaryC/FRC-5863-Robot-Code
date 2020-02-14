package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class Input {
    public XboxController testController = null;
    
    public Input() {
        testController =  new XboxController(RobotContainer.testController);
    }

}