package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.FeederSubsystem;
import frc.robot.subsystem.HoodLevel;
import frc.robot.subsystem.HoodSubsystem;
import frc.robot.subsystem.ShooterSubsystem;

public class AutoCeaseShoot extends Command {
    private ShooterSubsystem shoot;
    private HoodSubsystem hood;
    private FeederSubsystem feed;

    public AutoCeaseShoot(ShooterSubsystem shooter, HoodSubsystem hoodControl, FeederSubsystem feeder) {
        shoot = shooter;
        hood = hoodControl;
        feed = feeder;
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
        doShoot(false);
    }
}
