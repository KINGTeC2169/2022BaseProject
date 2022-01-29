package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ColorSensorTestCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LLDistanceCommand;
import frc.robot.commands.NavXTestCommand;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.NavX;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final DriveCommand m_teleopCommand = new DriveCommand(new DriveTrain());
  private SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    //Add chooser options here
    chooser.setDefaultOption("ColorSensor Test", new ColorSensorTestCommand(new ColorSensor()));
    chooser.addOption("NavX test", new NavXTestCommand(new NavX()));
    chooser.addOption("LimeLight_Distance", new LLDistanceCommand(new LimeLight()));
  
   
    SmartDashboard.putData(chooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
  public Command getTeleopCommand() {
    return chooser.getSelected();
  }
}
