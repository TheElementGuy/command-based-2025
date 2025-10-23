package frc.robot.commands;

import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.FeederSubsystem;
import frc.robot.subsystem.HoodLevel;
import frc.robot.subsystem.HoodSubsystem;
import frc.robot.subsystem.ShooterSubsystem;

public class WindUpAndShootCommand extends Command {

    private ShooterSubsystem shoot;
    private HoodSubsystem hood;
    private FeederSubsystem feed;

    private XboxController controller;

    public WindUpAndShootCommand(ShooterSubsystem shooter, HoodSubsystem hoodControl, FeederSubsystem feeder,
            XboxController xboxController) {
        shoot = shooter;
        hood = hoodControl;
        feed = feeder;
        controller = xboxController;
        addRequirements(shoot, hood, feed);
    }

    public void doShoot(boolean run) {
        if (run) {
            hood.setLevel(HoodLevel.UP);
            if (shoot.setFlywheelWithPID(ShooterSubsystem.TARGET_RPM) && hood.isUp()) {
                feed.run();
            } else {
                feed.stop();
            }
        } else {
            hood.setLevel(HoodLevel.DOWN);
            shoot.stop();
            feed.stop();
        }
        hood.moveTowardsLevel();
    }

    @Override
    public void execute() {
        doShoot(controller.getAButton());
    }

}
