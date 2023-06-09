// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveForward;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;

import java.sql.Driver;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain s_Drivetrain = new Drivetrain();

  final SendableChooser<Command> autoChooser = new SendableChooser<Command>();
  final SendableChooser<Command> autoDelay = new SendableChooser<Command>();


  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    
    autoDelay.setDefaultOption("none", new WaitCommand(0.0));
    autoDelay.addOption("1 second", new WaitCommand(1.0));
    autoDelay.addOption("2 seconds", new WaitCommand(2.0));
    autoDelay.addOption("3 seconds", new WaitCommand(3.0));
    autoDelay.addOption("4 seconds", new WaitCommand(4.0));
    autoDelay.addOption("5 seconds", new WaitCommand(5.0));
    autoDelay.addOption("6 seconds", new WaitCommand(6.0));
    autoDelay.addOption("7 seconds", new WaitCommand(7.0));
    autoDelay.addOption("8 seconds", new WaitCommand(8.0));
    autoDelay.addOption("9 seconds", new WaitCommand(9.0));
    autoDelay.addOption("10 seconds", new WaitCommand(10.0));


    /*autoChooser.setDefaultOption("Do Nothing", new WaitCommand(1));
    autoChooser.addOption("Drive back for 2.5 seconds", new DriveForward(s_Drivetrain)
        .deadlineWith(new WaitCommand(2.5)));
    autoChooser.addOption("Drive back for 4 seconds", new DriveForward(s_Drivetrain)
        .deadlineWith(new WaitCommand(4.0)));
    autoChooser.addOption("Drive back for 6 seconds", new DriveForward(s_Drivetrain)
        .deadlineWith(new WaitCommand(6.0)));*/
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    new InstantCommand(() -> s_Drivetrain.drive(
        m_driverController.getLeftY(), 
        m_driverController.getRightY(), 
        m_driverController.getLeftBumperPressed()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new WaitCommand(0);// c_DriveForward;
  }
}
