package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/14/2017.
 */

@Autonomous(name = "Auto 8088", group = "8088")
@Disabled
public class Auto_8088 extends LinearOpMode {

    /* Declare OpMode members */
    Hardware_8088       robot   = new Hardware_8088();
    private ElapsedTime runtime = new ElapsedTime();

    private final double DRIVE_SPEED = 0.7;
    private final double TURN_SPEED    = 0.5;

    @Override
    public void runOpMode(){

        /* Initialize the drive system variables. */
        robot.init(hardwareMap);

        //send status message to driver station
        telemetry.addData("Status:", "Ready to WIN!");
        telemetry.update();

        //wait for game to start (driver presses PLAY)
        waitForStart();

        //Step1 : Drive forwards for 3 seconds
        robot.Lefty1.setPower(DRIVE_SPEED);
        robot.Lefty2.setPower(DRIVE_SPEED);
        robot.Righty1.setPower(DRIVE_SPEED);
        robot.Righty2.setPower(DRIVE_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)){
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        //Step 2: Turn right for 1.4 seconds
        robot.Lefty1.setPower(TURN_SPEED);
        robot.Lefty2.setPower(TURN_SPEED);
        robot.Righty1.setPower(-TURN_SPEED);
        robot.Righty2.setPower(-TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.4)){
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        //Step 3: Drive backwards for 2 seconds
        robot.Lefty1.setPower(-DRIVE_SPEED);
        robot.Lefty2.setPower(-DRIVE_SPEED);
        robot.Righty1.setPower(-DRIVE_SPEED);
        robot.Righty2.setPower(-DRIVE_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)){
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
