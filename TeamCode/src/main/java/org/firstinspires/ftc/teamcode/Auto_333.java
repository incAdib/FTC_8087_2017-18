package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 9/13/2017.
 */

@Autonomous (name = "Auto 333", group = "Auto Tests")
//@Disabled
public class Auto_333 extends LinearOpMode {

    Hardware_333 brobot = new Hardware_333();
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED = -0.8;
    static final double TURN_SPEED  = 0.6;

    @Override
    public void runOpMode(){

        brobot.init(hardwareMap);

        telemetry.addData("Status:", "Ready to Run!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            driveForwards();
            turn();
            driveForwards();
        }
    }

    public void driveForwards(){

        brobot.Lefty1.setPower(DRIVE_SPEED);
        brobot.Lefty2.setPower(DRIVE_SPEED);
        brobot.Righty1.setPower(DRIVE_SPEED);
        brobot.Righty2.setPower(DRIVE_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }

    public void turn(){
        //Step 2: Turn right for 1.4 seconds
        brobot.Lefty1.setPower(TURN_SPEED);
        brobot.Lefty2.setPower(TURN_SPEED);
        brobot.Righty1.setPower(-TURN_SPEED);
        brobot.Righty2.setPower(-TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.4)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }
}
