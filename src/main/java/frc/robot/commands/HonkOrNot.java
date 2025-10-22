package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.HornSubsystem;

public class HonkOrNot extends Command {

    private HornSubsystem horn;
    private XboxController controller;

    public HonkOrNot(HornSubsystem sub, XboxController cont) {
        horn = sub;
        controller = cont;
    }

    public void toHonkOrNot() {
        
    }
    
}
