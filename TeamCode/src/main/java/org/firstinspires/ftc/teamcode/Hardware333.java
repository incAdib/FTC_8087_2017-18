package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Shamily on 9/15/2017.
 */

public class Hardware333 {
    public DcMotor righty1= null;
    public DcMotor lefty1= null;

    public TouchSensor touch= null;
    public ColorSensor color= null;

    HardwareMap hwmap=null;

    public void init(HardwareMap ahwmap){

        hwmap = ahwmap;

        righty1 = hwmap.dcMotor.get("righty1");
        lefty1 = hwmap.dcMotor.get("lefty1");


        righty1.setDirection(DcMotor.Direction.FORWARD);
        lefty1.setDirection(DcMotor.Direction.FORWARD);

        righty1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        lefty1.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);

        righty1.setPower(0);
        lefty1.setPower(0);
    }



}
