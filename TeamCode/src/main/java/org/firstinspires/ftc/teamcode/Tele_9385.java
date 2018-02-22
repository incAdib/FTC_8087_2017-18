 package org.firstinspires.ftc.teamcode;

import com.google.blocks.ftcrobotcontroller.util.ProjectsUtil;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

 /**
 * Created by Emmanuel on 8/14/2017.
 */

@TeleOp (name = "Tele 9385", group = "9385")
@Disabled

public class Tele_9385 extends OpMode {
    /* Declare OpMode members */
    Hardware_9385 robot = new Hardware_9385();

    private final double RAISER_POWER  = 0.8;
    private final double MIN_POSITION  = 0.3;
    private final double MAX_POSITION  = 0.9;
    private final double HOME_POSITION = 0.5;
    private final double DRIVE_SPEED   = 1.0;

    /* Code to run once ,when the driver hits INIT */
    @Override
    public void init(){

        /* Initialize the hardware variables */
        robot.init(hardwareMap);

        //send message to signify robot is waiting
        telemetry.addData("Say", "I'm waiting...");
        telemetry.update();
    }

    /* Code to run repeatedly after the driver hits INIT, but before they hit PLAY */
    @Override
    public void init_loop(){}

    /* Code to run once when the driver hits PLAY */
    @Override
    public void start(){}

    /* Code to run repeatedly after the driver hits PLAY, but before they hit STOP */
    @Override
    public void loop(){
        double left;
        double right;

        //run drive train in tank mode
        left  = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
/*
        robot.Lefty1.setPower(left);
        robot.Lefty2.setPower(left);
        robot.Righty1.setPower(right);
        robot.Righty2.setPower(right);
*/

        if (gamepad1.dpad_up){
            driveForwards();
        } else if (gamepad1.dpad_down){
            driveBackwards();
        } else if (gamepad1.dpad_left){
            turnLeft();
        } else if (gamepad1.dpad_right){
            turnRight();
        } else {
            robot.Lefty1.setPower(0);
            robot.Lefty2.setPower(0);
            robot.Righty1.setPower(0);
            robot.Righty2.setPower(0);
        }

        if (gamepad1.left_bumper){
            robot.Raise.setPower(RAISER_POWER);
        } else if (gamepad1.right_bumper){
            robot.Raise.setPower(-RAISER_POWER);
        } else {
            robot.Raise.setPower(0);
        }

        if (gamepad1.a){
            robot.Grab1.setPosition(MIN_POSITION);
            robot.Grab2.setPosition(MIN_POSITION);
        } else if (gamepad1.b){
            robot.Grab1.setPosition(MAX_POSITION);
            robot.Grab2.setPosition(MAX_POSITION);
        }
    }

    /* Code to run once when the driver hits STOP */
    @Override
    public void stop(){}

    public void driveForwards(){
        robot.Lefty1.setPower(DRIVE_SPEED);
        robot.Lefty2.setPower(DRIVE_SPEED);
        robot.Righty1.setPower(DRIVE_SPEED);
        robot.Righty2.setPower(DRIVE_SPEED);
    }

    public void driveBackwards(){
        robot.Lefty1.setPower(-DRIVE_SPEED);
        robot.Lefty2.setPower(-DRIVE_SPEED);
        robot.Righty1.setPower(-DRIVE_SPEED);
        robot.Righty1.setPower(-DRIVE_SPEED);
    }

    public void turnLeft(){
        robot.Lefty1.setPower(-DRIVE_SPEED);
        robot.Lefty2.setPower(-DRIVE_SPEED);
        robot.Righty1.setPower(DRIVE_SPEED);
        robot.Righty2.setPower(DRIVE_SPEED);
    }

    public void turnRight(){
        robot.Lefty1.setPower(DRIVE_SPEED);
        robot.Lefty2.setPower(DRIVE_SPEED);
        robot.Righty1.setPower(-DRIVE_SPEED);
        robot.Righty2.setPower(-DRIVE_SPEED);
    }
}
