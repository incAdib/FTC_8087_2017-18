package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/11/2017.
 */

public class Hardware_8088 {

    public DcMotor Lefty1; //left drive train motorz
    public DcMotor Lefty2;

    public DcMotor Righty1; //right drive train motorz
    public DcMotor Righty2;

    public DcMotor Glypher; //glyph raiser mech
    //public DcMotor Relic; //motor to get on the balancing stone

    public Servo GlyphGrabber1; //glyph grabber mech
    public Servo GlyphGrabber2;
    public Servo GlyphPusher;
    public Servo JewelKnocker;

    public ColorSensor    color; //jewel mech
    //public DistanceSensor distance;
    public NavxMicroNavigationSensor nav;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public void init (HardwareMap ahwMap){
        hwMap = ahwMap;

        /* Initiation */
        Lefty1 = hwMap.get(DcMotor.class, "Lefty1");
        Lefty2 = hwMap.get(DcMotor.class, "Lefty2");

        Righty1 = hwMap.get(DcMotor.class, "Righty1");
        Righty2 = hwMap.get(DcMotor.class, "Righty2");

        Glypher = hwMap.get(DcMotor.class, "glypher");
        //Relic = hwMap.get(DcMotor.class, "relic");

        GlyphGrabber1 = hwMap.get(Servo.class, "grabber1");
        GlyphGrabber2 = hwMap.get(Servo.class, "grabber2");
        GlyphPusher   = hwMap.get(Servo.class, "pusher");
        JewelKnocker  = hwMap.get(Servo.class, "knocker");

        color    = hwMap.get(ColorSensor.class, "color");
        //distance = hwMap.get(DistanceSensor.class, "distance");
        //nav      = hwMap.get(NavxMicroNavigationSensor.class, "navX");

        //Set direction of motors
        Lefty1.setDirection(DcMotor.Direction.FORWARD);
        Lefty2.setDirection(DcMotor.Direction.FORWARD);

        Righty1.setDirection(DcMotor.Direction.REVERSE);
        Righty2.setDirection(DcMotor.Direction.REVERSE);

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
