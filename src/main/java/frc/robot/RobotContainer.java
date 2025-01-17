// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {
  private final SwerveSubsystem m_SwerveSubsystem = new SwerveSubsystem();
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.k_driverController);
  
  public RobotContainer() {
    configureBindings();
    m_SwerveSubsystem.setDefaultCommand(driveFieldOrientedAngularVelocity);
  }

  private void configureBindings() {}
  Command driveFieldOrientedAngularVelocity = m_SwerveSubsystem.driveCommand(
    () -> MathUtil.applyDeadband(m_driverController.getLeftY() * DriveConstants.k_driveSpeed, DriveConstants.k_driveDeadBand),
    () -> MathUtil.applyDeadband(m_driverController.getLeftX() * DriveConstants.k_driveSpeed, DriveConstants.k_driveDeadBand),
    () -> m_driverController.getRightX() * DriveConstants.k_turnRate);
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
