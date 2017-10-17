package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/11/2017.
 */

public class Hardware_8088 {

    public DcMotor Lefty1 = null;
    public DcMotor Lefty2 = null;

    public DcMotor Righty1 = null;
    public DcMotor Righty2 = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public void init (HardwareMap ahwMap){
        hwMap = ahwMap;

        /* Initiation */
        Lefty1 = hwMap.get(DcMotor.class, "Lefty1");
        Lefty2 = hwMap.get(DcMotor.class, "Lefty2");

        Righty1 = hwMap.get(DcMotor.class, "Righty1");
        Righty2 = hwMap.get(DcMotor.class, "Righty2");

        //Set direction of motors
        Lefty1.setDirection(DcMotor.Direction.REVERSE);
        Lefty2.setDirection(DcMotor.Direction.REVERSE);

        Righty1.setDirection(DcMotor.Direction.FORWARD);
        Righty2.setDirection(DcMotor.Direction.FORWARD);

        //Set motor power to zero
        Lefty1.setPower(0);
        Lefty2.setPower(0);

        Righty1.setPower(0);
        Righty2.setPower(0);
    }

    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
