// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.*;
import frc.robot.subsystem.*;

public class RobotContainer {

	private DriveSubsystem driveTrain;
	private DriveCommand driver;
	private FeederSubsystem feeder;
	private ShooterSubsystem shooter;
	private HoodSubsystem hoodControl;
	private WindUpAndShootCommand shootBrain;
	private LEDStripSubsystem ledStrip;
	private LEDAnimate animator;
	private HornSubsystem horn;
	private HonkOrNot toHonk;
	private AutoDriveCommand autoDrive;
	private AutoShootCommand autoShootBrain;
	private AutoHonkOn honkOn;
	private AutoDriveUnhonk noHonk;
	private AutoCeaseDrive noDrive;
	private AutoCeaseShoot noShoot;
	private AutoHonkOn honkOnPt2;

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
		horn = new HornSubsystem();
		driveTrain.setDefaultCommand(driver);
		shooter.setDefaultCommand(shootBrain);
		hoodControl.setDefaultCommand(shootBrain);
		feeder.setDefaultCommand(shootBrain);
		ledStrip.setDefaultCommand(animator);
		toHonk = new HonkOrNot(horn, controller);
		horn.setDefaultCommand(toHonk);
		autoDrive = new AutoDriveCommand(driveTrain);
		autoShootBrain = new AutoShootCommand(shooter, hoodControl, feeder);
		honkOn = new AutoHonkOn(horn);
		noHonk = new AutoDriveUnhonk(horn);
		noDrive = new AutoCeaseDrive(driveTrain);
		noShoot = new AutoCeaseShoot(shooter, hoodControl, feeder);
		honkOnPt2 = new AutoHonkOn(horn);
	}

	private void configureBindings() {
	}

	public Command getAutonomousCommand() {
		return autoDrive.withTimeout(Seconds.of(3)).andThen(autoShootBrain.withTimeout(Seconds.of(5)).andThen(honkOn.withTimeout(Seconds.of(0.25)).andThen(noHonk.withTimeout(Seconds.of(0.5)).andThen(honkOnPt2.withTimeout(Seconds.of(0.25))))));
	}
}
