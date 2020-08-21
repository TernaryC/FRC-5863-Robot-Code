package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import frc.robot.RobotContainer;
import frc.robot.commands.ColorManipulate;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class ColorDetect extends SubsystemBase {
    
    //The color sensor and its reference data cache are initiated.
    public ColorSensorV3 colorSensor;
    public ColorMatch colorMatcher;
    
    //Variables that convert read from the sensor to human-understood data values are initiated.
    public String colorString;
    public Color detectedColor;
    public ColorMatchResult match;
    public Color lastColor;
    int firstRun = 0;
    
    public ColorDetect() {
        //Colors are detected and a new compute thread is created to distribute the computing load.
        colorSensor =  new ColorSensorV3(RobotContainer.colorSensorPort);
        colorMatcher = new ColorMatch();
        ColorThread colorThread = new ColorThread();
        new Thread(colorThread).start();
    }

    public Color colorCounter() {
      return match.color;
    }
    
    @Override
    public void periodic() {
    // This method will be called once per scheduler run
        setDefaultCommand(new ColorManipulate());
    }

    private class ColorThread implements Runnable {
        public ColorThread() {
          //Creates the set of colors that can be used to match.
          colorMatcher.addColorMatch(Constants.kBlueTarget);
          colorMatcher.addColorMatch(Constants.kGreenTarget);
          colorMatcher.addColorMatch(Constants.kRedTarget);
          colorMatcher.addColorMatch(Constants.kYellowTarget); 
        }
  
        public void run() {
          
          //Algorithm used to match colors to human output
          while (true) {
            detectedColor = colorSensor.getColor();
            match = colorMatcher.matchClosestColor(detectedColor);
            if (match.color == Constants.kBlueTarget) {
              colorString = "Blue";
            } else if (match.color == Constants.kRedTarget) {
              colorString = "Red";
            } else if (match.color == Constants.kGreenTarget) {
              colorString = "Green";
            } else if (match.color == Constants.kYellowTarget) {
              colorString = "Yellow";
            } else {
              colorString = "Unknown";
            }
            SmartDashboard.putNumber("Red", detectedColor.red);
            SmartDashboard.putNumber("Green", detectedColor.green);
            SmartDashboard.putNumber("Blue", detectedColor.blue);
            SmartDashboard.putNumber("Confidence", match.confidence);
            SmartDashboard.putString("Detected Color", colorString);
        }
      }
    }
}