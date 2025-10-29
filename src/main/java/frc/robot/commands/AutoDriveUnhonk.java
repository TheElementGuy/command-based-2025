package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.HornSubsystem;

public class AutoDriveUnhonk extends Command {
    
    private HornSubsystem horn;

    public AutoDriveUnhonk(HornSubsystem sub) {
        horn = sub;

        addRequirements(horn);
    }

    @Override
    public void execute() {
        horn.unhonk();
    }

}
