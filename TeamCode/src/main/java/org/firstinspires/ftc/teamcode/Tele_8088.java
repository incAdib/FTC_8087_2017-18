package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Emmanuel on 8/14/2017.
 */

@TeleOp(name = "Tele 8088", group = "8088")

//@Disabled
public class Tele_8088 extends OpMode {
    /* Declare OpMode members */
    Hardware_8088 robot = new Hardware_8088();

    static final double HOME_POSITION = 0.1;
    static final double MAX_POSITION  = 0.7;
    static final double MIN_POSITION  = 0.1;

    static final double GLYPHER_SPEED = 0.6;
    static final double BALANCE_POWER = 0.6;

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
    public void init_loop(){}

    /* Code to run once when the driver hits PLAY */
    @Override
    public void start(){
    }

    /* Code to run repeatedly after the driver hits PLAY, but before they hit STOP */
    @Override
    public void loop(){
        if(gamepad1 != null && robot != null) {
            //robot.GlyphGrabber1.setPosition(HOME_POSITION);
            //robot.GlyphGrabber2.setPosition(HOME_POSITION);
            //robot.GlyphPusher.setPosition(1.0);

            double left;
            double right;

            //run drive train in tank mode
            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;

            robot.Lefty1.setPower(left);
            robot.Lefty2.setPower(left);
            robot.Righty1.setPower(right);
            robot.Righty2.setPower(right);


            if (gamepad1.a) {
                robot.GlyphGrabber1.setPosition(MAX_POSITION);
                robot.GlyphGrabber2.setPosition(MIN_POSITION);
            } else if (gamepad1.b) {
                robot.GlyphGrabber1.setPosition(MIN_POSITION);
                robot.GlyphGrabber2.setPosition(MAX_POSITION);
            }
            if(!gamepad1.a && !gamepad1.b){
                //robot.GlyphGrabber1.setPosition(HOME_POSITION);
            }

            if(gamepad1.left_bumper){
                robot.JewelKnocker.setPosition(MAX_POSITION);
            } else if (gamepad1.right_bumper){
                robot.JewelKnocker.setPosition(MIN_POSITION);
            }

            if (gamepad1.x) {
                robot.GlyphPusher.setPosition(1);
            } else if (gamepad1.y) {
                robot.GlyphPusher.setPosition(0);
            }

            if (gamepad1.left_trigger>0) {
                robot.Glypher.setPower(-GLYPHER_SPEED);
            } else if (gamepad1.right_trigger>0) {
                robot.Glypher.setPower(GLYPHER_SPEED);
            }
            if(gamepad1.left_trigger <= 0 && gamepad1.right_trigger <= 0){
                robot.Glypher.setPower(0.125);
            }
        }
    }

    /* Code to run once when the driver hits STOP */
    @Override
    public void stop(){}
}
