package frc.robot.subsystem;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.MutLinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

    private final SwerveDriveKinematics kinematics;

    public static final Distance WHEEL_OFFSET = Inches.of(11.55);

    public DriveSubsystem() {
        double offsetInMeters = WHEEL_OFFSET.in(Meters);
        Translation2d frontLeft = new Translation2d(offsetInMeters, offsetInMeters);
        Translation2d frontRight = new Translation2d(offsetInMeters, -offsetInMeters);
        Translation2d backLeft = new Translation2d(-offsetInMeters, offsetInMeters);
        Translation2d backRight = new Translation2d(-offsetInMeters, -offsetInMeters);
        kinematics = new SwerveDriveKinematics(frontLeft, frontRight, backLeft, backRight);
    }

    public void swerveDrive(double forward, double left, double turn) {
        MutLinearVelocity vel;
    }
    
}
