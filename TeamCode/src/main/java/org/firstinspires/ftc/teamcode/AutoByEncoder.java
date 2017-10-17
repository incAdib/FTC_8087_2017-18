package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/14/2017.
 */

@Autonomous(name = "Auto By Encoder", group = "Auto Tests")
@Disabled
public class AutoByEncoder extends LinearOpMode {

    Hardware_333       robot    = new Hardware_333();
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED   = 0.6;
    static final double TURN_SPEED    = 0.5;

    static final double COUNTS_PER_MOTOR_REV  = 1120;
    static final double DRIVE_GEAR_REDUCTION  = 2.0;
    static final double WHEEL_DIAMETER_INCHES = 4;
    static final double COUNTS_PER_INCH       = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/
                                                (WHEEL_DIAMETER_INCHES * 3.1415);
    @Override
    public void runOpMode(){

        //get mapping from hardware class
        robot.init(hardwareMap);

        //send message to driver station
        telemetry.addData("Status:", "Resetting Encoders");
        telemetry.update();

        robot.Lefty1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Lefty2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Righty1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Righty2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        //send massage stating successful encoder reset
        telemetry.addData("Path 0:", "Starting @ %7d :%7d",
                          robot.Lefty1.getCurrentPosition(), robot.Lefty2.getCurrentPosition(),
                          robot.Righty1.getCurrentPosition(), robot.Righty2.getCurrentPosition());
        telemetry.update();

        //wait for START button to be pushed
        waitForStart();

        encoderDrive(DRIVE_SPEED, 48, 48, 5.0);  //1: Drive forwards 47" with a 5 sec timeout
        encoderDrive(TURN_SPEED, 12, -12, 4.0);  //2: Turn right 4" with a 4 sec timeout
        encoderDrive(DRIVE_SPEED, -24, -24, 4.0);//3: Reverse 24" with a 4 sec timeout

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS){
        int newLeftTarget1;
        int newLeftTarget2;
        int newRightTarget1;
        int newRightTarget2;

        //Ensure the OpMode is still active
        if(opModeIsActive()){

            //determine new target position, and pass to motor controller
            newLeftTarget1 = robot.Lefty1.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newLeftTarget2 = robot.Lefty2.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget1 = robot.Righty1.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newRightTarget2 = robot.Righty2.getTargetPosition() + (int)(rightInches * COUNTS_PER_INCH);

            //reset the timeout time and start motion
            runtime.reset();
            robot.Lefty1.setPower(Math.abs(speed));
            robot.Lefty2.setPower(Math.abs(speed));
            robot.Righty1.setPower(Math.abs(speed));
            robot.Righty2.setPower(Math.abs(speed));

            //keep looping while we are still active, and there is time left, and both motors are running
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.Lefty1.isBusy() && robot.Lefty2.isBusy() &&
                     robot.Righty1.isBusy() && robot.Righty2.isBusy())){
                //display it to the driver
                telemetry.addData("Path 1", "Running to %7d", newLeftTarget1, newLeftTarget2, newRightTarget1, newRightTarget2);
                telemetry.addData("Path 2", "Running to %7d",
                                             robot.Lefty1.getCurrentPosition(),
                                             robot.Lefty2.getCurrentPosition(),
                                             robot.Righty1.getCurrentPosition(),
                                             robot.Righty2.getCurrentPosition());
                telemetry.update();
            }

            //stop motors
            robot.Lefty1.setPower(0);
            robot.Lefty2.setPower(0);
            robot.Righty1.setPower(0);
            robot.Righty2.setPower(0);

            sleep(250);
        }
    }
}
