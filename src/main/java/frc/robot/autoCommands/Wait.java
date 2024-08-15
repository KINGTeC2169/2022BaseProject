package frc.robot.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class Wait extends Command {
    private Timer timer = new Timer();
    private int seconds;
    
    public Wait(int seconds) {
        seconds = this.seconds;
    }

    @Override
    public void initialize() {
        timer.start();
    }
    
    @Override
    public boolean isFinished() {
        return timer.get() >= seconds;
    }
}
