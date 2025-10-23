package frc.robot.subsystem;

import org.opencv.core.Mat.Tuple2;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HoodSubsystem extends SubsystemBase {

    public static final int TOP_HOOD_POSITION = 50;
    public static final double HOOD_POWER = 0.2;
    
    private SparkMax hoodMotor;
    private HoodLevel level;
    private PIDController pid;

    private DoubleEntry kPEntry;
    private DoubleEntry kIEntry;
    private DoubleEntry kDEntry;
    private DoubleEntry hoodPos;

    public HoodSubsystem() {
        hoodMotor = new SparkMax(8, MotorType.kBrushless);
        hoodMotor.getEncoder().setPosition(0);
        hoodMotor.configure(new SparkMaxConfig().inverted(true), ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        pid = new PIDController(0, 0, 0);

        NetworkTable table = NetworkTableInstance.getDefault().getTable("Hood PID");
        kPEntry = table.getDoubleTopic("kP").getEntry(0);
        kIEntry = table.getDoubleTopic("kI").getEntry(0);
        kDEntry = table.getDoubleTopic("kD").getEntry(0);

        kPEntry.set(0.08);
        kIEntry.set(0);
        kDEntry.set(0.0015);

        hoodPos = table.getDoubleTopic("hoodPos").getEntry(0);
    }

    public void setLevel(HoodLevel level) {
        this.level = level;
    }

    public boolean isUp() {
        return hoodMotor.getEncoder().getPosition() > 40;
    }

    public boolean moveTowardsLevel() {

        pid.setD(kDEntry.get());
        pid.setI(kIEntry.get());
        pid.setP(kPEntry.get());

        hoodMotor.set(pid.calculate(hoodMotor.getEncoder().getPosition(), currentPosGoal()));

        return Math.abs((hoodMotor.getEncoder().getPosition() - currentPosGoal())) < 2;

    }

    public double currentPosGoal() {
        return (level == HoodLevel.UP) ? TOP_HOOD_POSITION : 0;
    }

    public double getPos() {
        return hoodMotor.getEncoder().getPosition();
    }

    @Override
    public void periodic() {
        hoodPos.set(getPos());
    }

}
