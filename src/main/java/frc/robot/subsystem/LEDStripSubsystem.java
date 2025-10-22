package frc.robot.subsystem;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDStripSubsystem extends SubsystemBase {

    public static final int LED_NUM = 240;
    public static final Distance SPACING = Meters.of(1.0 / 60);
    
    private AddressableLED strip;
    private AddressableLEDBuffer buffer;

    public LEDStripSubsystem() {
        strip = new AddressableLED(9);
        buffer = new AddressableLEDBuffer(LED_NUM);
        strip.setLength(LED_NUM);
        strip.start();
    }

    public void applyPattern(LEDPattern pattern) {
        pattern.applyTo(buffer);
        strip.setData(buffer);
    }

}
