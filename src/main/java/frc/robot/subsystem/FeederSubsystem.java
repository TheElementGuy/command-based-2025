package frc.robot.subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {

    public static final double DEFAULT_RUN_SPEED = 0.6;
    
    private SparkMax feederMotor;

    public FeederSubsystem() {
        feederMotor = new SparkMax(2, MotorType.kBrushless);

        feederMotor.configure(new SparkMaxConfig().inverted(true), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public void run(double speed) {
        feederMotor.set(speed);
    }

    public void run() {
        run(DEFAULT_RUN_SPEED);
    }

    public void stop() {
        run(0);
    }

}
