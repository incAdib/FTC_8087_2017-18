 package org.firstinspires.ftc.teamcode;

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

    private final double DRIVE_SPEED = 1.0;

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
        left  = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        robot.Lefty1.setPower(left);
        robot.Lefty2.setPower(left);
        robot.Righty1.setPower(right);
        robot.Righty2.setPower(right);
/*
        if (gamepad1.dpad_left){
            robot.Lefty1.setPower(-DRIVE_SPEED);
            robot.Lefty2.setPower(DRIVE_SPEED);
            robot.Righty1.setPower(DRIVE_SPEED);
            robot.Righty2.setPower(-DRIVE_SPEED);
        } else if (gamepad1.dpad_right){
            robot.Lefty1.setPower(DRIVE_SPEED);
            robot.Lefty2.setPower(-DRIVE_SPEED);
            robot.Righty1.setPower(-DRIVE_SPEED);
            robot.Righty2.setPower(DRIVE_SPEED);
        } else{
            robot.Lefty1.setPower(0);
            robot.Lefty2.setPower(0);
            robot.Righty1.setPower(0);
            robot.Righty2.setPower(0);
        }*/
    }

    /* Code to run once when the driver hits STOP */
    @Override
    public void stop(){}
}
