package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;

//Don't worry about this file. This is a failed project that needs to be developed independently.

public class DualStick extends GenericHID {
    
    public enum RightHandButtons {
      kTriggerR(1),
      kDownThumbR(2),
      kLeftThumbR(3),
      kRightThumbR(4),
      kLeftMacroOneR(5),
      kLeftMacroTwoR(6),
      kLeftMacroThreeR(7),
      kLeftMacroFourR(10),
      kLeftMacroFiveR(9),
      kLeftMacroSixR(8),
      kRightMacroOneR(11),
      kRightMacroTwoR(12),
      kRightMacroThreeR(13),
      kRightMacroFourR(16),
      kRightMacroFiveR(15),
      kRightMacroSixR(14);
  
      @SuppressWarnings({"MemberName", "PMD.SingularField"})
      public final int value;
      public Hand hand = Hand.kRight; 
  
      RightHandButtons(int value) {
        this.value = value;
      }
    }

    public enum LeftHandButtons {
      kTriggerL(1),
      kDownThumbL(2),
      kLeftThumbL(3),
      kRightThumbL(4),
      kLeftMacroOneL(11),
      kLeftMacroTwoL(12),
      kLeftMacroThreeL(13),
      kLeftMacroFourL(16),
      kLeftMacroFiveL(15),
      kLeftMacroSixL(14),
      kRightMacroOneL(5),
      kRightMacroTwoL(6),
      kRightMacroThreeL(7),
      kRightMacroFourL(10),
      kRightMacroFiveL(9),
      kRightMacroSixL(8);
  
      @SuppressWarnings({"MemberName", "PMD.SingularField"})
      public final int value;
  
      LeftHandButtons(int value) {
        this.value = value;
      }
    }

    public enum RightAxis {
      kRX(0),
      kRY(1),
      kSliderR(2);
  
      @SuppressWarnings({"MemberName", "PMD.SingularField"})
      public final int value;
  
      RightAxis(int value) {
        this.value = value;
      }
    }
    
    public enum LeftAxis {
      kLX(0),
      kLY(1),
      kSliderL(2);
  
      @SuppressWarnings({"MemberName", "PMD.SingularField"})
      public final int value;
      public Hand hand = Hand.kRight;
  
      LeftAxis(int value) {
        this.value = value;
      }
    }
    
    public DualStick(final int portOne) {
      super(portOne);
            
      HAL.report(tResourceType.kResourceType_Joystick, portOne + 1);
      HAL.report(tResourceType.kResourceType_Joystick, portOne + 2);
    }
  
    public double getX(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawAxis(LeftAxis.kLY.value);
      } else {
        return getRawAxis(RightAxis.kRY.value);
      }
    }
    
    public double getY(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawAxis(LeftAxis.kLY.value);
      } else {
        return getRawAxis(RightAxis.kRY.value);
      }
    }
  
    public double getSliderAxis(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawAxis(LeftAxis.kSliderL.value);
      } else {
        return getRawAxis(RightAxis.kSliderR.value);
      }
    }

    public boolean getTrigger(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kTriggerL.value);
      } else {
        return getRawButton(RightHandButtons.kTriggerR.value);
      }
    }
    
    public boolean getTriggerPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kTriggerL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kTriggerR.value);
      }
    }
    
    public boolean getTriggerReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kTriggerL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kTriggerR.value);
      }
    }
    
    public boolean getLeftThumb(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kLeftThumbL.value);
      } else {
        return getRawButton(RightHandButtons.kLeftThumbR.value);
      }
    }
   
    public boolean getLeftThumbPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kLeftThumbL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kLeftThumbR.value);
      }
    }
    
    public boolean getLeftThumbReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kLeftThumbL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kLeftThumbR.value);
      }
    }

    public boolean getRightThumb(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kRightThumbL.value);
      } else {
        return getRawButton(RightHandButtons.kRightThumbR.value);
      }
    }
   
    public boolean getRightThumbPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kRightThumbL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kRightThumbR.value);
      }
    }
    
    public boolean getRightThumbReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kRightThumbL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kRightThumbR.value);
      }
    }

    public boolean getDownThumb(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kDownThumbL.value);
      } else {
        return getRawButton(RightHandButtons.kDownThumbR.value);
      }
    }
   
    public boolean getDownThumbPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kDownThumbL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kDownThumbR.value);
      }
    }
    
    public boolean getDownThumbReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kDownThumbL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kDownThumbR.value);
      }
    }

    public boolean getLeftMacroOne(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kLeftMacroOneL.value);
      } else {
        return getRawButton(RightHandButtons.kLeftMacroOneR.value);
      }
    }
   
    public boolean getLeftMacroOnePressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kLeftMacroOneL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kLeftMacroOneR.value);
      }
    }
    
    public boolean getLeftMacroOneReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kLeftMacroOneL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kLeftMacroOneR.value);
      }
    }
    
    public boolean getLeftMacroTwo(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kLeftMacroTwoL.value);
      } else {
        return getRawButton(RightHandButtons.kLeftMacroTwoR.value);
      }
    }
   
    public boolean getLeftMacroTwoPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kLeftMacroTwoL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kLeftMacroTwoR.value);
      }
    }
    
    public boolean getLeftMacroTwoReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kLeftMacroTwoL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kLeftMacroTwoR.value);
      }
    }

    public boolean getLeftMacroThree(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kLeftMacroThreeL.value);
      } else {
        return getRawButton(RightHandButtons.kLeftMacroThreeR.value);
      }
    }
   
    public boolean getLeftMacroThreePressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kLeftMacroThreeL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kLeftMacroThreeR.value);
      }
    }
    
    public boolean getLeftMacroThreeReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kLeftMacroThreeL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kLeftMacroThreeR.value);
      }
    }

    public boolean getLeftMacroFour(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kLeftMacroFourL.value);
      } else {
        return getRawButton(RightHandButtons.kLeftMacroFourR.value);
      }
    }
   
    public boolean getLeftMacroFourPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kLeftMacroFourL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kLeftMacroFourR.value);
      }
    }
    
    public boolean getLeftMacroFourReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kLeftMacroFourL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kLeftMacroFourR.value);
      }
    }

    public boolean getLeftMacroFive(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kLeftMacroFiveL.value);
      } else {
        return getRawButton(RightHandButtons.kLeftMacroFiveR.value);
      }
    }
   
    public boolean getLeftMacroFivePressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kLeftMacroFiveL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kLeftMacroFiveR.value);
      }
    }
    
    public boolean getLeftMacroFiveReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kLeftMacroFiveL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kLeftMacroFiveR.value);
      }
    }
  
    public boolean getLeftMacroSix(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kLeftMacroSixL.value);
      } else {
        return getRawButton(RightHandButtons.kLeftMacroSixR.value);
      }
    }
   
    public boolean getLeftMacroSixPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kLeftMacroSixL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kLeftMacroSixR.value);
      }
    }
    
    public boolean getLeftMacroSixReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kLeftMacroSixL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kLeftMacroSixR.value);
      }
    }
    //end copy/pase
    public boolean getRightMacroOne(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kRightMacroOneL.value);
      } else {
        return getRawButton(RightHandButtons.kRightMacroOneR.value);
      }
    }
   
    public boolean getRightMacroOnePressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kRightMacroOneL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kRightMacroOneR.value);
      }
    }
    
    public boolean getRightMacroOneReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kRightMacroOneL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kRightMacroOneR.value);
      }
    }
    
    public boolean getRightMacroTwo(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kRightMacroTwoL.value);
      } else {
        return getRawButton(RightHandButtons.kRightMacroTwoR.value);
      }
    }
   
    public boolean getRightMacroTwoPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kRightMacroTwoL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kRightMacroTwoR.value);
      }
    }
    
    public boolean getRightMacroTwoReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kRightMacroTwoL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kRightMacroTwoR.value);
      }
    }

    public boolean getRightMacroThree(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kRightMacroThreeL.value);
      } else {
        return getRawButton(RightHandButtons.kRightMacroThreeR.value);
      }
    }
   
    public boolean getRightMacroThreePressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kRightMacroThreeL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kRightMacroThreeR.value);
      }
    }
    
    public boolean getRightMacroThreeReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kRightMacroThreeL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kRightMacroThreeR.value);
      }
    }

    public boolean getRightMacroFour(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kRightMacroFourL.value);
      } else {
        return getRawButton(RightHandButtons.kRightMacroFourR.value);
      }
    }
   
    public boolean getRightMacroFourPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kRightMacroFourL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kRightMacroFourR.value);
      }
    }
    
    public boolean getRightMacroFourReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kRightMacroFourL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kRightMacroFourR.value);
      }
    }

    public boolean getRightMacroFive(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kRightMacroFiveL.value);
      } else {
        return getRawButton(RightHandButtons.kRightMacroFiveR.value);
      }
    }
   
    public boolean getRightMacroFivePressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kRightMacroFiveL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kRightMacroFiveR.value);
      }
    }
    
    public boolean getRightMacroFiveReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kRightMacroFiveL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kRightMacroFiveR.value);
      }
    }
  
    public boolean getRightMacroSix(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButton(LeftHandButtons.kRightMacroSixL.value);
      } else {
        return getRawButton(RightHandButtons.kRightMacroSixR.value);
      }
    }
   
    public boolean getRightMacroSixPressed(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonPressed(LeftHandButtons.kRightMacroSixL.value);
      } else {
        return getRawButtonPressed(RightHandButtons.kRightMacroSixR.value);
      }
    }
    
    public boolean getRightMacroSixReleased(Hand hand) {
      if (hand.equals(Hand.kLeft)) {
        return getRawButtonReleased(LeftHandButtons.kRightMacroSixL.value);
      } else {
        return getRawButtonReleased(RightHandButtons.kRightMacroSixR.value);
      }
    }
}