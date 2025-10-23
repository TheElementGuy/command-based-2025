package frc.robot.subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    public static final int TARGET_RPM = 2000;
    public static final double DEFAULT_RUN_SPEED = 2000;
    
    private SparkMax shooterMotorA;
    private SparkMax shooterMotorB;
    private PIDController pid;

    private DoubleEntry kPEntry;
    private DoubleEntry kIEntry;
    private DoubleEntry kDEntry;
    private DoubleEntry kFFEntry;
    private DoubleEntry flywheelVel;

    public ShooterSubsystem() {

        shooterMotorA = new SparkMax(1, MotorType.kBrushless);
        shooterMotorB = new SparkMax(7, MotorType.kBrushless);

        //Configurations, because Spark people are annoying
        shooterMotorA.configure(new SparkMaxConfig(), ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        shooterMotorB.configure(new SparkMaxConfig().follow(shooterMotorA, true), ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

        pid = new PIDController(0, 0, 0);

        NetworkTable table = NetworkTableInstance.getDefault().getTable("Shooter PID");
        kPEntry = table.getDoubleTopic("kP").getEntry(0);
        kIEntry = table.getDoubleTopic("kI").getEntry(0);
        kDEntry = table.getDoubleTopic("kD").getEntry(0);
        kFFEntry = table.getDoubleTopic("kFF").getEntry(0);

        flywheelVel = table.getDoubleTopic("flywheelVel").getEntry(0);

        kPEntry.set(0.00001);
        kIEntry.set(0);
        kDEntry.set(0);
        kFFEntry.set(0.000185);
    }

    public void runMotors(double speed) {
        shooterMotorA.set(speed);
    }

    public void runMotors() {
        runMotors(DEFAULT_RUN_SPEED);
    }

    public boolean flywheelUpToSpeed() {
        return shooterMotorA.getEncoder().getVelocity() > TARGET_RPM;
    }

    public boolean setFlywheelWithPID(double speed) {

        pid.setP(kPEntry.get());
        pid.setD(kDEntry.get());
        pid.setI(kIEntry.get());

        shooterMotorA.set((pid.calculate(shooterMotorA.getEncoder().getVelocity(), speed)) + (kFFEntry.get() * speed));
        return Math.abs(speed - shooterMotorA.getEncoder().getVelocity()) < 2;

    }

    public void stop() {
        runMotors(0);
    }

    public double getVelocity() {
        return shooterMotorA.getEncoder().getVelocity();
    }

    @Override
    public void periodic() {
        flywheelVel.set(getVelocity());
    }

}
