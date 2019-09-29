package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="right", group="Linear OpMode")

public class right extends LinearOpMode
{

    private ElapsedTime runtime = new ElapsedTime();

    DcMotor fldrive;
    DcMotor frdrive;
    DcMotor brdrive;
    DcMotor bldrive;

    double power= 0.5;

    public void runOpMode() throws InterruptedException
    {
        telemetry.addData("Status", "Intialized");
        telemetry.update();

        fldrive = hardwareMap.dcMotor.get("fl_drive");
        frdrive = hardwareMap.dcMotor.get("fr_drive");
        brdrive = hardwareMap.dcMotor.get("br_drive");
        bldrive = hardwareMap.dcMotor.get("bl_drive");


        bldrive.setDirection(DcMotorSimple.Direction.FORWARD);
        brdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        fldrive.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();
        runtime.reset();

        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);

        sleep(2000);

        power= 0.0;

        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);



    }

}
