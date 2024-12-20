/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveTrain;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain = new DriveTrain();

  private final Joystick m_joystick = new Joystick(0);

  // create a singleton that allows us to access the single instance from multiple places
  private static RobotContainer m_instance;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  private RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    if (m_joystick != null) {
      m_driveTrain.setDefaultCommand(new Drive(
        m_driveTrain,
        () -> m_joystick.getRawAxis(1),
        () -> m_joystick.getRawAxis(2),
        () -> m_joystick.getRawAxis(3),
        () -> m_joystick.getRawButton(1)
        ));
    }

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * 
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new Autonomous();
  }


  public DriveTrain getDriveTrain() {
    return m_driveTrain;
  }

  public Joystick getJoystick() {
    return m_joystick;
  }

  public static RobotContainer getInstance() {
    if (m_instance == null) {
      m_instance = new RobotContainer();
    }
    return m_instance;
  }
}