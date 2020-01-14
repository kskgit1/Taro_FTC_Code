package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MecanumDriver", group = "Linear OpMode")
public class MecanumDriver extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor fldrive, frdrive, bldrive, brdrive; //flywheel;
    //Servo arm_bottom_servo;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        fldrive = hardwareMap.get(DcMotor.class, "fl_drive");
        frdrive = hardwareMap.get(DcMotor.class, "fr_drive");
        brdrive = hardwareMap.get(DcMotor.class, "br_drive");
        bldrive = hardwareMap.get(DcMotor.class, "bl_drive");
        //flywheel = hardwareMap.get(DcMotor.class, "fly_wheel");

        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);
        //flywheel.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {


            double drivepower;
            //double flypower = 1;

            if(gamepad1.a)
                drivepower = 0.3;
            else
                drivepower = 1;

            double DriveSpeed = -gamepad1.left_stick_y;
            double Rotate = gamepad1.right_stick_x;
            double Strafe = gamepad1.left_stick_x;
            //double FlySpeed = gamepad1.right_trigger;

            double f_left;
            double f_right;
            double b_left;
            double b_right;
            //double fly_wheel;

            f_left = DriveSpeed + Rotate + Strafe;
            f_right = DriveSpeed - Rotate - Strafe;
            b_right = DriveSpeed - Rotate + Strafe;
            b_left = DriveSpeed + Rotate - Strafe;
            //fly_wheel = FlySpeed;


            fldrive.setPower(Range.clip(f_left, -drivepower, drivepower));
            frdrive.setPower(Range.clip(f_right, -drivepower, drivepower));
            brdrive.setPower(Range.clip(b_right, -drivepower, drivepower));
            bldrive.setPower(Range.clip(b_left, -drivepower, drivepower));
            //flywheel.setPower(Range.clip(fly_wheel, -flypower, flypower));


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", f_left, f_right, b_left, b_right);
            telemetry.update();
        }


    }
}
