package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Emmanuel on 8/14/2017.
 */

@TeleOp(name = "Tele 8087", group = "8087")
@Disabled
public class Tele_8087 extends OpMode {

    /* Declare OpMode members */
    Hardware_8087 robot = new Hardware_8087();

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
    public void start(){}

    /* Code to run repeatedly after the driver hits PLAY, but before they hit STOP */
    @Override
    public void loop(){
        double left;
        double right;

        //run drive train in tank mode
        left  = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        robot.Lefty1.setPower(left);
        robot.Lefty2.setPower(left);
        robot.Righty1.setPower(right);
        robot.Righty2.setPower(right);

        if (gamepad1.a){
            robot.slide1.setPower(1.0);
        } else if (gamepad1.b) {
            robot.slide1.setPower(0);
        }

        if (gamepad1.x){
            robot.raise1.setPosition(1);
            robot.raise2.setPosition(0);
        } else if (gamepad1.y){
            robot.raise1.setPosition(0);
            robot.raise2.setPosition(1);
        }

        if (gamepad1.left_bumper){
            robot.slide1.setPower(1);
            robot.slide2.setPower(1);
        } else if (gamepad1.right_bumper){
            robot.slide1.setPower(-1);
            robot.slide2.setPower(-1);
        } else {
            robot.slide1.setPower(0);
            robot.slide2.setPower(0);
        }
    }

    /* Code to run once when the driver hits STOP */
    @Override
    public void stop(){}
}
