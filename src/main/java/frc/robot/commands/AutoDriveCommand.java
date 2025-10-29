package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.DriveSubsystem;

public class AutoDriveCommand extends Command {

	private DriveSubsystem drive;

	public AutoDriveCommand(DriveSubsystem driveSubsystem) {
		drive = driveSubsystem;
		addRequirements(driveSubsystem);
	}

	@Override
	public void execute() {
		drive.runAt(0.50);
	}

}
