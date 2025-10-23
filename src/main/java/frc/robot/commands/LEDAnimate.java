package frc.robot.commands;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.LEDPattern.GradientType;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.LEDStripSubsystem;

public class LEDAnimate extends Command {

    public static final LEDPattern RAINBOW_TRAIL = LEDPattern.rainbow(255, 255).scrollAtAbsoluteSpeed(MetersPerSecond.of(0.05), LEDStripSubsystem.SPACING);
    
    private LEDStripSubsystem leds;

    public LEDAnimate(LEDStripSubsystem ledsubsys) {
        leds = ledsubsys;
        addRequirements(leds);
    }

    @Override
    public void execute() {
        leds.applyPattern(RAINBOW_TRAIL);
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

}
