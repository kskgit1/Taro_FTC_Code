package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="MecanumAuto", group="Linear OpMode")

public class MecanumAuto extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    DcMotor fldrive;
    DcMotor frdrive;
    DcMotor brdrive;
    DcMotor bldrive;

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Intialized");
        telemetry.update();

        fldrive = hardwareMap.dcMotor.get("fl_drive");
        frdrive = hardwareMap.dcMotor.get("fr_drive");
        brdrive = hardwareMap.dcMotor.get("br_drive");
        bldrive = hardwareMap.dcMotor.get("bl_drive");

        fldrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        brdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        bldrive.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        forward(0.5, 2000);
        backward(0.5, 2000);
        right(0.5, 1000);


    }
    public void forward(double power, long wait_time) {
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
        sleep(wait_time);
        power = 0.0;
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }

    public void backward(double power, long wait_time) {
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
        sleep(wait_time);
        power = 0.0;
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }

    public void left(double power, long wait_time) {
        fldrive.setPower(-power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(-power);
        sleep(wait_time);
        power = 0.0;
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }

    public void right(double power, long wait_time) {
        fldrive.setPower(power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(power);
        sleep(wait_time);
        power = 0.0;
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }
    public void strafeleft(double power, long wait_time) {
        fldrive.setPower(-power);
        frdrive.setPower(power);
        brdrive.setPower(-power);
        bldrive.setPower(power);
        sleep(wait_time);
        power = 0.0;
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }
    public void straferight(double power, long wait_time) {
        fldrive.setPower(power);
        frdrive.setPower(-power);
        brdrive.setPower(power);
        bldrive.setPower(-power);
        sleep(wait_time);
        power = 0.0;
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }

}
