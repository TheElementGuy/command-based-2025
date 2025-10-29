package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.DriveSubsystem;

public class AutoCeaseDrive extends Command {
    private DriveSubsystem drive;

    public AutoCeaseDrive(DriveSubsystem sub) {
        drive = sub;
    }

    @Override
    public void execute() {
        drive.runAt(0);
    }
}
