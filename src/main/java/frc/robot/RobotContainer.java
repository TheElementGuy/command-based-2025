// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LEDAnimate;
import frc.robot.commands.WindUpAndShootCommand;
import frc.robot.subsystem.DriveSubsystem;
import frc.robot.subsystem.FeederSubsystem;
import frc.robot.subsystem.HoodSubsystem;
import frc.robot.subsystem.LEDStripSubsystem;
import frc.robot.subsystem.ShooterSubsystem;

public class RobotContainer {

  private DriveSubsystem driveTrain;
  private DriveCommand driver;
  private FeederSubsystem feeder;
  private ShooterSubsystem shooter;
  private HoodSubsystem hoodControl;
  private WindUpAndShootCommand shootBrain;
  private LEDStripSubsystem ledStrip;
  private LEDAnimate animator;

  public RobotContainer() {
    configureBindings();
    XboxController controller = new XboxController(0);
    ledStrip = new LEDStripSubsystem();
    driveTrain = new DriveSubsystem();
    driver = new DriveCommand(driveTrain, controller);
    shooter = new ShooterSubsystem();
    feeder = new FeederSubsystem();
    hoodControl = new HoodSubsystem();
    shootBrain = new WindUpAndShootCommand(shooter, hoodControl, feeder, controller);
    animator = new LEDAnimate(ledStrip);
    driveTrain.setDefaultCommand(driver);
    shooter.setDefaultCommand(shootBrain);
    hoodControl.setDefaultCommand(shootBrain);
    feeder.setDefaultCommand(shootBrain);
    ledStrip.setDefaultCommand(animator);
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
