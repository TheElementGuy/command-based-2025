package frc.robot.subsystem;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

	private VictorSP rightMotor;
	private VictorSP leftMotor;
	private DifferentialDrive drive;

	public DriveSubsystem() {
		rightMotor = new VictorSP(0);
		leftMotor = new VictorSP(1);
		rightMotor.setInverted(true);
		drive = new DifferentialDrive(rightMotor::set, leftMotor::set);
	}
	
	public void arcadeDrive(double toAndFro, double rotation) {
		drive.arcadeDrive(toAndFro, rotation);
	}

}
