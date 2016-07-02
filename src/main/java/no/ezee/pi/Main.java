package no.ezee.pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Testing my Rasberry Pi
 *
 */
public class Main {
    static final GpioController gpio = GpioFactory.getInstance();

    public static void main(String[] args) throws InterruptedException {
        GpioPinDigitalInput myButton =
                gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, "MyButton", PinPullResistance.PULL_DOWN);

        myButton.addListener(new GpioUsageExampleListener());
        System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");
        for (;;) {
            Thread.sleep(500);
        }
    }

    public static class GpioUsageExampleListener implements GpioPinListenerDigital {
        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            // display pin state on console
            System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
        }
    }

}
