package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Emmanuel & MD on 11/16/2017.
 */

@TeleOp(name = "Tele 9384", group = "9384")
@Disabled
public class Tele_9384 extends OpMode {
    /* Declare OpMode members */
    Hardware_9384 robot = new Hardware_9384();

    static final double DRIVE_SPEED   = 1.0;
    static final double GLYPHER_SPEED = 0.3;
    static final int    MAX_POSITION  = 1;
    static final int    MIN_POSITION  = 0;

    /* Code to run once ,when the driver hits INIT */
    @Override
    public void init(){

        /* Initialize the hardware variables */
        robot.init(hardwareMap);

        //send message to signify robot is waiting
        telemetry.addData("Say", "I'm waiting...");
    }

    /* Code to run repeatedly after the driver hits INIT, but before they hit PLAY */
    @Override
    public void init_loop(){

    }

    /* Code to run once when the driver hits PLAY */
    @Override
    public void start(){}

    /* Code to run repeatedly after the driver hits PLAY, but before they hit STOP */
    @Override
    public void loop(){

        if (gamepad1.dpad_up){
            forwards();
        } else if (gamepad1.dpad_down){
            backwards();
        } else if (gamepad1.dpad_left){
            leftDrive();
        } else if (gamepad1.dpad_right){
            rightDrive();
        } else {
            robot.Lefty1.setPower(0);
            robot.Lefty2.setPower(0);
            robot.Righty1.setPower(0);
            robot.Righty2.setPower(0);
        }

        if (gamepad1.right_bumper){
            robot.Glypher.setPower(GLYPHER_SPEED);
        } else if (gamepad1.left_bumper){
            robot.Glypher.setPower(-GLYPHER_SPEED);
        } else {
            robot.Glypher.setPower(0);
        }

        if (gamepad1.a){
            robot.leftGlyphGrabber.setPosition(0.7);
            robot.rightGlyphGrabber.setPosition(0.3);
        } else if (gamepad1.b){
            robot.leftGlyphGrabber.setPosition(0.3);
            robot.rightGlyphGrabber.setPosition(0.7);
        }

        telemetry.addData("Servo Position: ", robot.leftGlyphGrabber.getPosition());
        telemetry.addData("Servo Position: ", robot.rightGlyphGrabber.getPosition());
        telemetry.update();
    }

    /* Code to run once when the driver hits STOP */
    @Override
    public void stop(){}

    public void forwards(){
        robot.Lefty1.setPower(DRIVE_SPEED);
        robot.Lefty2.setPower(DRIVE_SPEED);
        robot.Righty1.setPower(DRIVE_SPEED);
        robot.Righty2.setPower(DRIVE_SPEED);
    }
    public void backwards(){
        robot.Lefty1.setPower(-DRIVE_SPEED);
        robot.Lefty2.setPower(-DRIVE_SPEED);
        robot.Righty1.setPower(-DRIVE_SPEED);
        robot.Righty2.setPower(-DRIVE_SPEED);
    }
    public void leftDrive(){
        robot.Lefty1.setPower(-DRIVE_SPEED);
        robot.Lefty2.setPower(-DRIVE_SPEED);
        robot.Righty1.setPower(DRIVE_SPEED);
        robot.Righty2.setPower(DRIVE_SPEED);
    }
    public void rightDrive(){
        robot.Lefty1.setPower(DRIVE_SPEED);
        robot.Lefty2.setPower(DRIVE_SPEED);
        robot.Righty1.setPower(-DRIVE_SPEED);
        robot.Righty2.setPower(-DRIVE_SPEED);
    }
}
