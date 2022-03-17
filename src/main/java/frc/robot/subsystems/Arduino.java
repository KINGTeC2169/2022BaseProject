package frc.robot.subsystems;

import java.util.Timer;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.ActuatorMap;

public class Arduino extends SubsystemBase {
    DigitalOutput ledLeft = new DigitalOutput(ActuatorMap.leftLed);
    DigitalOutput ledRight = new DigitalOutput(ActuatorMap.rightLed);
    
    public void changeLed(boolean isOn) {
        ledLeft.set(isOn);
    }

    public void rightOn() {
        ledRight.set(false);
    }
    
    public void leftOn() {
        ledLeft.set(false);
    }

    public void leftOff() {
        ledLeft.set(true);
    }

    public void rightOff() {
        ledRight.set(true);
    }

    public void off() {
        ledRight.set(true);
        ledLeft.set(true);
    }

    public void on() {
        ledRight.set(false);
        ledLeft.set(false);
    }
}
