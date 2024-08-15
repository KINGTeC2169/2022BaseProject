package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NavX;

public class ExampleCommand extends Command {
    @SuppressWarnings("unused")
    private NavX navX;
    public  ExampleCommand(NavX subsystem) {
        navX = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}
}
