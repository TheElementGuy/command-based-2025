package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.DriveSubsystem;

public class DriveCommand extends Command {

    private DriveSubsystem driveTrain;
    private XboxController controller;

    public DriveCommand(DriveSubsystem driveSubsystem, XboxController controller) {
        this.controller = controller;
        driveTrain = driveSubsystem;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        driveTrain.arcadeDrive(-controller.getLeftY(), controller.getRightX());
    }

}
