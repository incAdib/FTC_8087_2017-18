package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/12/2017.
 */

public class Hardware_9385 {

    /* Public OpMode members */
    public DcMotor Lefty1 = null;
    public DcMotor Lefty2 = null;

    public DcMotor Righty1 = null;
    public DcMotor Righty2 = null;

    /* Local OpMode members */
    HardwareMap hwMap;
    private ElapsedTime period = new ElapsedTime();

    /* Initialize standard hardware interfaces */
    public void init(HardwareMap ahwMap){

        //save reference to hw map
        hwMap = ahwMap;

        //Define and Initialize motors
        Lefty1 = hwMap.get(DcMotor.class, "Lefty1");
        Lefty2 = hwMap.get(DcMotor.class, "Lefty1");

        Righty1 = hwMap.get(DcMotor.class, "Righty1");
        Righty2 = hwMap.get(DcMotor.class, "Righty2");

        //set drive train motor powers to zero
        Lefty1.setPower(0);
        Lefty2.setPower(0);

        Righty1.setPower(0);
        Righty2.setPower(0);

        //set motors to run with encoders
        Lefty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lefty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Righty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Righty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Lefty1.setDirection(DcMotor.Direction.FORWARD);
        Lefty2.setDirection(DcMotor.Direction.FORWARD);

        Righty1.setDirection(DcMotor.Direction.REVERSE);
        Righty2.setDirection(DcMotor.Direction.REVERSE);
    }

    /***
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs Length of wait cycle in mSec.
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
