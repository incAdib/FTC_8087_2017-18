package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Auto Color Test", group = "Auto Tests")
@Disabled
/**
 * Created by Emmanuel on 8/16/2017.
 */

public class Auto_Color extends LinearOpMode {

    Hardware_9385 brobot = new Hardware_9385();
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED    = 1.0;
    private final double TURN_SPEED    = 0.5;

    private final double HOME_POSITION   = 0.1;
    private final double ATTACK_POSITION = 0.9;

    static final double COUNTS_PER_MOTOR_REV  = 1120;
    static final double DRIVE_GEAR_REDUCTION  = 2.0;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH       = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public void runOpMode(){

        brobot.init(hardwareMap);

        telemetry.addData("Status:", "Ready to Run...");
        telemetry.update();

        reset();

        brobot.Lefty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        brobot.Lefty2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brobot.Righty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        brobot.Righty2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Path 0", "Starting @ %7d :%7d",
                brobot.Lefty1.getCurrentPosition(),
                brobot.Lefty2.getCurrentPosition(),
                brobot.Righty1.getCurrentPosition(),
                brobot.Righty2.getCurrentPosition());
        telemetry.update();

        float hsvValues[] = {0F,0F,0F};

        final float values[] = hsvValues;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        boolean bPrevState = false;
        boolean bCurrState = false;

        boolean bLedOn = true;

        boolean blueSensed = true;

        brobot.Color.enableLed(bLedOn);

        waitForStart();

        while (opModeIsActive()) {

            bCurrState = gamepad1.x;

            if (bCurrState && (bCurrState != bPrevState)){
                bLedOn = !bLedOn;
                brobot.Color.enableLed(bLedOn);
            }

            bPrevState = bCurrState;

            Color.RGBToHSV(brobot.Color.red() * 8, brobot.Color.green() * 8, brobot.Color.blue() * 8, hsvValues);

            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", brobot.Color.alpha());
            telemetry.addData("Red  ", brobot.Color.red());
            telemetry.addData("Green", brobot.Color.green());
            telemetry.addData("Blue ", brobot.Color.blue());
            telemetry.addData("Hue", hsvValues[0]);

            relativeLayout.post(new Runnable() {
                @Override
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

            telemetry.update();

            if (brobot.Color.blue() >= 5){
                //drive();
                encoderDrive(TURN_SPEED, 6, -6, 1.0);
                pause();
                //slideLeft();
                pause();
            } else  if(brobot.Color.red() >= 5){
                encoderDrive(TURN_SPEED, -6, 6, 1.0);
                pause();
                //slideRight();
                pause();
            } else {
                pause();
            }

        }

        relativeLayout.post(new Runnable() {
            @Override
            public void run() {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        });
    }
    public void drive(){

        brobot.Lefty1.setPower(DRIVE_SPEED);
        brobot.Lefty1.setPower(DRIVE_SPEED);
        brobot.Righty1.setPower(DRIVE_SPEED);
        brobot.Righty2.setPower(DRIVE_SPEED);
        sleep(1000);
        idle();

        //while //(opModeIsActive() && runtime < 2.0){

    }

    public void pause(){
        brobot.Lefty1.setPower(0);
        brobot.Lefty2.setPower(0);
        brobot.Righty1.setPower(0);
        brobot.Righty2.setPower(0);
        sleep(1000);
    }

    public void slideLeft(){
        brobot.Lefty1.setPower(-DRIVE_SPEED);
        brobot.Lefty2.setPower(DRIVE_SPEED);
        brobot.Righty1.setPower(DRIVE_SPEED);
        brobot.Righty2.setPower(-DRIVE_SPEED);
        sleep(1000);
    }

    public void slideRight(){
        brobot.Lefty1.setPower(DRIVE_SPEED);
        brobot.Lefty2.setPower(-DRIVE_SPEED);
        brobot.Righty1.setPower(-DRIVE_SPEED);
        brobot.Righty2.setPower(DRIVE_SPEED);
        sleep(1000);
    }

    public void turnLeft(){
        brobot.Lefty1.setPower(DRIVE_SPEED);
    }

    private void encoderDrive(double speed,
                              double leftInches, double rightInches,
                              double timeoutS){
        int newLeftTarget, newRightTarget;

        if (opModeIsActive()){
            newLeftTarget = brobot.Lefty1.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newLeftTarget = brobot.Lefty2.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = brobot.Righty1.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newRightTarget = brobot.Righty2.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            brobot.Lefty1.setTargetPosition(newLeftTarget);
            brobot.Lefty2.setTargetPosition(newLeftTarget);
            brobot.Righty1.setTargetPosition(newRightTarget);
            brobot.Righty2.setTargetPosition(newRightTarget);

            runToPosition();
            runtime.reset();

            brobot.Lefty1.setPower(Math.abs(speed));
            brobot.Lefty2.setPower(Math.abs(speed));
            brobot.Righty1.setPower(Math.abs(speed));
            brobot.Righty2.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    brobot.Lefty1.isBusy() && brobot.Lefty2.isBusy() &&
                    brobot.Righty1.isBusy() && brobot.Righty2.isBusy()){
                telemetry.addData("Path", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path", "Running at %7d :%7d",
                        brobot.Lefty1.getCurrentPosition(),
                        brobot.Lefty2.getCurrentPosition(),
                        brobot.Righty1.getCurrentPosition(),
                        brobot.Righty2.getCurrentPosition());
                telemetry.update();
            }

            brobot.Lefty1.setPower(0);
            brobot.Lefty2.setPower(0);
            brobot.Righty1.setPower(0);
            brobot.Righty2.setPower(0);

            brobot.Lefty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            brobot.Lefty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            brobot.Righty1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            brobot.Righty2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private void runToPosition(){
        brobot.Lefty1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brobot.Lefty2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brobot.Righty1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brobot.Righty2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void reset(){
        brobot.Lefty1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brobot.Lefty2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brobot.Righty1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brobot.Righty2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }



}
//}
