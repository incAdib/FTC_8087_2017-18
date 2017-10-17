package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Emmanuel on 8/30/2017.
 */

@TeleOp (name = "Mecanum Drive", group = "Tele Test ")
//@Disabled
public class Mecanum_Drive_Math extends OpMode {

    Hardware_333 robot = new Hardware_333();

    private final double DRIVE_SPEED = 1.0;

    /* code to run once the driver hits INIT */
    @Override
    public void init(){

        //Initialize the hardware variables
        robot.init(hardwareMap);

        //send message to drivers station
        telemetry.addData("Driver Station:", "I'm waiting...");
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

        double r          = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX     = gamepad1.right_stick_x;

        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        robot.Lefty1.setPower(v1);
        robot.Righty1.setPower(v2);
        robot.Lefty2.setPower(v3);
        robot.Righty2.setPower(v4);
    }

    /* Code to run once when the driver hits STOP */
    @Override
    public void stop(){}
}
