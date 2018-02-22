
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/12/2017.
 */

public class Hardware_9384 {

    /* Public OpMode members */
    public DcMotor Lefty1;
    public DcMotor Lefty2;

    public DcMotor Righty1;
    public DcMotor Righty2;

    public DcMotor Glypher;

    public Servo leftGlyphGrabber;
    public Servo rightGlyphGrabber;
    public Servo JewlKnocker;

    public ColorSensor Color;

    /* Local OpMode */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Initialize standard interfaces */
    public void init(HardwareMap ahwMap){

        //save reference to hw map
        hwMap = ahwMap;

        //Define and Initialize motors
        Lefty1 = hwMap.get(DcMotor.class, "leftFront");
        Lefty2 = hwMap.get(DcMotor.class, "leftBack");

        Righty1 = hwMap.get(DcMotor.class, "rightFront");
        Righty2 = hwMap.get(DcMotor.class, "rightBack");

        Glypher = hwMap.get(DcMotor.class, "glypher");

        Color = hwMap.get(ColorSensor.class, "color");

        leftGlyphGrabber  = hwMap.get(Servo.class, "leftGrabber");
        rightGlyphGrabber = hwMap.get(Servo.class, "rightGrabber");
        JewlKnocker       = hwMap.get(Servo.class, "knocker");

        //Set motor powers to zero
        Lefty1.setPower(0);
        Lefty2.setPower(0);

        Righty1.setPower(0);
        Righty2.setPower(0);

        //Set all motors to run without encoders
        Lefty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lefty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Righty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Righty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //set direction of motors
        Lefty1.setDirection(DcMotor.Direction.REVERSE);
        Lefty2.setDirection(DcMotor.Direction.REVERSE);
        Righty1.setDirection(DcMotor.Direction.FORWARD);
        Righty2.setDirection(DcMotor.Direction.FORWARD);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */

    public void waitForTick(long periodMs) throws InterruptedException {

        long remaining = periodMs - (long) period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();

    }
}
