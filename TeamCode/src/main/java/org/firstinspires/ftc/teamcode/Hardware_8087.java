package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/11/2017.
 */

public class Hardware_8087 {

    /* Public OpMode members */
    public DcMotor Lefty1;
    public DcMotor Lefty2;

    public DcMotor Righty1;
    public DcMotor Righty2;

    public DcMotor Test;


    public DcMotor slide1;
    public DcMotor slide2;

    public Servo raise1;
    public Servo raise2;

    public ColorSensor Color;
    

    /* Local OpMode members */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Initialize standard hardware interfaces */

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;

        //Define and initialize motors and sensors
        Lefty1 = hwMap.get(DcMotor.class, "Lefty1");
        Lefty2 = hwMap.get(DcMotor.class, "Lefty2");

        Righty1 = hwMap.get(DcMotor.class, "Righty1");
        Righty2 = hwMap.get(DcMotor.class, "Righty2");

        Test = hwMap.get(DcMotor.class, "test");

        raise1 = hwMap.get(Servo.class, "raise1");
        raise2 = hwMap.get(Servo.class, "raise2");

       slide2 = hwMap.get(DcMotor.class, "intake2");
       slide1 = hwMap.get(DcMotor.class, "intake1");


    //Color = hwMap.get(ColorSensor.class, "color");

        //Set motor powers to zero
        Lefty1.setPower(0);
        Lefty2.setPower(0);

        Righty1.setPower(0);
        Righty2.setPower(0);
        Lefty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lefty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Righty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Righty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Lefty1.setDirection(DcMotorSimple.Direction.REVERSE);
        Lefty2.setDirection(DcMotorSimple.Direction.REVERSE);

        Righty1.setDirection(DcMotorSimple.Direction.FORWARD);
        Righty2.setDirection(DcMotorSimple.Direction.FORWARD);
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
