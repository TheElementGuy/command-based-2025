package frc.robot.subsystem;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HornSubsystem extends SubsystemBase {
    
    private PWMVictorSPX hornMotor;

    public HornSubsystem() {

        hornMotor = new PWMVictorSPX(2);

    }

    public void honk() {
        hornMotor.set(1);
    }

    public void unhonk() {
        hornMotor.set(0);
    }
}
