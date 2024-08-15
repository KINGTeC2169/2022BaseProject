package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.ActuatorMap;
import frc.robot.utils.Constants;
import frc.robot.utils.PID;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.configs.ClosedLoopRampsConfigs;
import com.ctre.phoenix6.configs.OpenLoopRampsConfigs;
import com.ctre.phoenix6.hardware.TalonFX;

public class Shooter extends SubsystemBase {
    TalonFX shooter = new TalonFX(ActuatorMap.shooter);
    Orchestra music = new Orchestra();

    double currentPower;
    double intstagrill;
    double previousPower = 0.0;
    double previousError = 0.0;
    PID rpmLoop = new PID(.00025, .0003, 0);
    

    public Shooter() {
        OpenLoopRampsConfigs openLoopRampConfigs = new OpenLoopRampsConfigs().withDutyCycleOpenLoopRampPeriod(.5);
        ClosedLoopRampsConfigs closedLoopRampConfigs = new ClosedLoopRampsConfigs().withDutyCycleClosedLoopRampPeriod(0);
        shooter.getConfigurator().apply(openLoopRampConfigs);
        shooter.getConfigurator().apply(closedLoopRampConfigs);
    }

    /**Sets shooter motor to 'power' value */
    public void shoot(double power) {
        CompressorTank.disable();
        shooter.set(power);
    }

    /**Calculates RPM value of flywheel */
    public double getRPM() {
        return (600 * shooter.getVelocity().getValueAsDouble() / Constants.TalonFXCPR)  * (24.0/18.0);
    }

    /**Original PID loop for RPM setting, don't use this unless there is an unknown error with RPM setting. 
     * It's basically the same thing but worse */
    public void setCoolerRPM(double rpm) {
        CompressorTank.disable();
        double error = rpm - getRPM(); // Error = Target - Actual
        //double power = previousPower + (error* .00000125);
        double power = previousPower + (error* .0000014);
       shooter.set(power);
       previousPower = power;
       previousError = error;
       if(previousPower > 1){
        previousPower = 1;
      }
      else if (previousPower < -1){
        previousPower = -1;
      }
    }

    /**Good PID loop for RPM setting. This is the one we actually use */
    public void setCoolerestRPM(double rpm) {
        if(rpm > 4500) {
            rpm = 4500;
        }
        else if (rpm < -4500) {
            rpm = -4500;
        }
        CompressorTank.disable();
        rpmLoop.setSetpoint(rpm);
        rpmLoop.calculate(getRPM());
        shooter.set(rpmLoop.getOutput());
    }
    /**Turns off flywheel */
    public void stopShooter() {
        //shooter.set(ControlMode.PercentOutput, 0);
        rpmLoop.resetI();
        shooter.set(0);
    }

    public boolean isShootingLeft() {
        return shooter.getDutyCycle().getValueAsDouble() > 0;
    }
    public boolean isShootingRight() {
        return shooter.getDutyCycle().getValueAsDouble() < 0;
    }
    public double getCurrent() {
        return shooter.getSupplyCurrent().getValueAsDouble();
    }
    public double getVoltage() {
        return shooter.getMotorVoltage().getValueAsDouble();
    }
    public void playMusic() {
        music.loadMusic("file.chrp");
        music.addInstrument(shooter);
        music.play();
    }
}