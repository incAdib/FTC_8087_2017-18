package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Emmanuel on 11/21/2017.
 */

@Autonomous(name = "Optical Distance Example", group = "Auto Tests")
@Disabled
public class Auto_Optical_Test extends LinearOpMode {

    Hardware_333 brobot = new Hardware_333();

    public void runOpMode(){

        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("Raw", brobot.Distance.getRawLightDetected());
            telemetry.addData("Normal", brobot.Distance.getLightDetected());
            telemetry.update();
        }
    }
}
