package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Emmanuel on 8/30/2017.
 */

@Autonomous (name = "Auto Touch Test", group = "Auto Tests")
//@Disabled
public class Auto_Touch extends LinearOpMode {

    Hardware_333 brobot = new Hardware_333();
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED = 0.8;

    @Override
    public void runOpMode(){

        brobot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()){
            if(brobot.Touchy.isPressed())
                telemetry.addData("Touchy", "was touched...");
             else
                telemetry.addData("Touchy", "was not touched...");

             telemetry.update();
        }
    }
}
