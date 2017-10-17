package org.firstinspires.ftc.teamcode;

// import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.hardware.motors.NeveRest60Gearmotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/16/2017.
 */

public class Hardware_333 {

    public DcMotor Lefty1 = null; //left drive train motors
    public DcMotor Lefty2 = null;

    public DcMotor Righty1 = null; //right drive train motors
    public DcMotor Righty2 = null;

    public ColorSensor Color  = null; //color sensor
    public TouchSensor Touchy = null; //touch sensor


    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public void init (HardwareMap ahwMap){
        hwMap = ahwMap;

        /* Initiation */
        Lefty1 = hwMap.get(DcMotor.class, "Lefty1");
        Lefty2 = hwMap.get(DcMotor.class, "Lefty2");

        Righty1 = hwMap.get(DcMotor.class, "Righty1");
        Righty2 = hwMap.get(DcMotor.class, "Righty2");

        //Color  = hwMap.get(ColorSensor.class, "color");
        //Touchy = hwMap.get(TouchSensor.class, "touchy");

        //Set direction of motors
        Lefty1.setDirection(DcMotor.Direction.FORWARD); //set left motors to move forwards
        Lefty2.setDirection(DcMotor.Direction.FORWARD);

        Righty1.setDirection(DcMotor.Direction.REVERSE); //set right motors to move backwards
        Righty2.setDirection(DcMotor.Direction.REVERSE);

        //have the robot run with encoders
        Lefty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //run all motors using the
        Lefty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Righty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Righty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
