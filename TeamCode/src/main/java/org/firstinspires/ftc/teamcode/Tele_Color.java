package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name = "Auto Color Test", group = "Auto Tests")
//@Disabled
/**
 * Created by Emmanuel on 8/16/2017.
 */

public class Tele_Color extends LinearOpMode {

    Hardware_333 brobot = new Hardware_333();
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED = 1.0;

    public void runOpMode(){

        brobot.init(hardwareMap);

        telemetry.addData("Status:", "Ready to Run...");
        telemetry.update();

        float hsvValues[] = {0F,0F,0F};

        final float values[] = hsvValues;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        boolean bPrevState = false;
        boolean bCurrState = false;

        boolean bLedOn = true;

        brobot.Color = hardwareMap.get(ColorSensor.class, "sensor_color");

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
}
//}
