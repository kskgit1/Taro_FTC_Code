package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name="Color-Sensor", group="Linear OpMode")

public class Color extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    DcMotor fldrive;
    DcMotor frdrive;
    DcMotor brdrive;
    DcMotor bldrive;
    ColorSensor color_sensor;

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Intialized");
        telemetry.update();

        fldrive = hardwareMap.dcMotor.get("fl_drive");
        frdrive = hardwareMap.dcMotor.get("fr_drive");
        brdrive = hardwareMap.dcMotor.get("br_drive");
        bldrive = hardwareMap.dcMotor.get("bl_drive");
        color_sensor = hardwareMap.colorSensor.get("color");

        fldrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        brdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        bldrive.setDirection(DcMotorSimple.Direction.REVERSE);




        waitForStart();
        runtime.reset();

        color_sensor.red();   // Red channel value
        color_sensor.green(); // Green channel value
        color_sensor.blue();  // Blue channel value

        color_sensor.alpha(); // Total luminosity
        color_sensor.argb();  // Combined color value

        double margin = 20f;
        double perfectred = 175f;
        double perfectgreen = 125f;
        double perfectblue = 10f;




        if  ((Math.abs(perfectred - (color_sensor.red())) < margin) & (Math.abs(perfectgreen - (color_sensor.green())) < margin) & (Math.abs(perfectblue - (color_sensor.blue())) < margin))
        {
            straferight(0.5, 2000);
        }
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
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }
    public void straferight(double power, long wait_time) {
        fldrive.setPower(power);
        frdrive.setPower(-power);
        brdrive.setPower(power);
        bldrive.setPower(-power);
        sleep(wait_time);
        power = 0.0;
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }

}
