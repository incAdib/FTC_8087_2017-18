package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 9/13/2017.
 */

@Autonomous (name = "Auto 333", group = "Auto Tests")
@Disabled
public class Auto_333 extends LinearOpMode {

    Hardware_333 brobot = new Hardware_333();
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED =  0.8;
    static final double TURN_SPEED  = 0.6;

    @Override
    public void runOpMode(){

        brobot.init(hardwareMap);

        telemetry.addData("Status:", "Ready to Run!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            driveForwardsForHalfSecond();
            pause();

            driveBackwardsForHalfSecond();
            pause();

            turnRight();
            pause();

            driveForwardsFor2Seconds();
            pause();

            turnLeft();
            pause();
        }
    }

    public void driveForwardsFor2Seconds(){

        brobot.Lefty1.setPower(DRIVE_SPEED);
        brobot.Lefty2.setPower(DRIVE_SPEED);
        brobot.Righty1.setPower(DRIVE_SPEED);
        brobot.Righty2.setPower(DRIVE_SPEED);
        sleep(1300);
    }

    public void driveForwardsForHalfSecond(){
        brobot.Lefty1.setPower(DRIVE_SPEED);
        brobot.Lefty2.setPower(DRIVE_SPEED);
        brobot.Righty1.setPower(DRIVE_SPEED);
        brobot.Righty2.setPower(DRIVE_SPEED);
        sleep(200);
    }

    public void driveBackwardsForHalfSecond(){
        brobot.Lefty1.setPower(-TURN_SPEED);
        brobot.Lefty2.setPower(-TURN_SPEED);
        brobot.Righty1.setPower(-TURN_SPEED);
        brobot.Righty2.setPower(-TURN_SPEED);
        sleep(300);
    }

    public void turnRight(){
        //Step 2: Turn right for ___ seconds
        brobot.Lefty1.setPower(TURN_SPEED);
        brobot.Lefty2.setPower(TURN_SPEED);
        brobot.Righty1.setPower(-TURN_SPEED);
        brobot.Righty2.setPower(-TURN_SPEED);
        sleep(1000);
    }

    public void turnLeft(){
        brobot.Lefty1.setPower(-TURN_SPEED);
        brobot.Lefty2.setPower(-TURN_SPEED);
        brobot.Righty1.setPower(TURN_SPEED);
        brobot.Righty2.setPower(TURN_SPEED);
        sleep(1000);
    }

    public void pause(){
        brobot.Lefty1.setPower(0);
        brobot.Lefty2.setPower(0);
        brobot.Righty1.setPower(0);
        brobot.Righty2.setPower(0);
        sleep(1000);

    }
}
