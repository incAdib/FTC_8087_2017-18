package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/12/2017.
 */

@Autonomous(name = "Auto 8087", group = "8087")
@Disabled
public class Auto_8087_Red extends LinearOpMode {

    /* Declare OpMode members */
    Hardware_8087       robot   = new Hardware_8087();
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED   = 0.7;
    static final double TURN_SPEED    = 0.5;

    @Override
    public void runOpMode(){

        /* Initialize the drive system variables */
        robot.init(hardwareMap);

        //Give driver station a status update
        telemetry.addData("Status:", "Ready to WIN!");
        telemetry.update();

        //wait for game to start (driver presses PLAY)
        waitForStart();

        //Step1 : Drive forwards for 3 seconds
        driveForwards();
        while (opModeIsActive() && (runtime.seconds() < 2.5)){
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        stayStill();

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(2000);
    }

    public void driveForwards(){
        robot.Lefty1.setPower(DRIVE_SPEED);
        robot.Lefty2.setPower(DRIVE_SPEED);
        robot.Righty1.setPower(DRIVE_SPEED);
        robot.Righty2.setPower(DRIVE_SPEED);
        runtime.reset();
    }
    public void turnRight(){
        robot.Lefty1.setPower(DRIVE_SPEED);
        robot.Lefty1.setPower(DRIVE_SPEED);
        robot.Righty1.setPower(-DRIVE_SPEED);
        robot.Righty2.setPower(-DRIVE_SPEED);
        runtime.reset();
    }
    public void turnLeft(){
        robot.Lefty1.setPower(-DRIVE_SPEED);
        robot.Lefty2.setPower(-DRIVE_SPEED);
        robot.Righty1.setPower(DRIVE_SPEED);
        robot.Righty2.setPower(DRIVE_SPEED);
        runtime.reset();
    }
    public void driveBackwards(){
        robot.Lefty1.setPower(-DRIVE_SPEED);
        robot.Lefty2.setPower(-DRIVE_SPEED);
        robot.Righty1.setPower(-DRIVE_SPEED);
        robot.Righty2.setPower(-DRIVE_SPEED);
        runtime.reset();
    }
    public void stayStill(){
        robot.Lefty1.setPower(0);
        robot.Lefty2.setPower(0);
        robot.Righty1.setPower(0);
        robot.Righty2.setPower(0);
        sleep(1000);
    }
}
