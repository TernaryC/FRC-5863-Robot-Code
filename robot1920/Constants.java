/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;

public final class Constants {
    //measurements in inches
    static double wheelRatio = 1/14.39;
    static double susanDiameter = 32;
    static double compliantWheel = 2.2;
    
    public static Color kBlueTarget = ColorMatch.makeColor(0.164, 0.458, 0.379);
    public static Color kGreenTarget = ColorMatch.makeColor(0.203, 0.555, 0.241);
    public static Color kRedTarget = ColorMatch.makeColor(0.420, 0.400, 0.170);
    public static Color kYellowTarget = ColorMatch.makeColor(0.306, 0.548, 0.145);
}
