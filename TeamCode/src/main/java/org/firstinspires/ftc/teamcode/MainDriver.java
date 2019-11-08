package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "MecanumDriver", group = "Linear OpMode")
public class MecanumDriver extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor flDrive, frDrive, blDrive, brDrive, flyDrive;
    Servo arm_bottom_servo;
    int increment = 0;

    /*public void release(double currentposition)
    {
        arm_bottom_servo.setPosition(currentposition);
    }
    public void grab(double currentposition)
    {    // if arm is open, make it closed
    }*/


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        arm_servo = hardwareMap.get(Servo.class, "arm");
        hand_servo = hardwareMap.get(Servo.class, "hand");
        clamp_servo = hardwareMap.get(Servo.class, "clamp");

        flDrive = hardwareMap.get(DcMotor.class, "fl_drive");
        frDrive = hardwareMap.get(DcMotor.class, "fr_drive");
        brDrive = hardwareMap.get(DcMotor.class, "br_drive");
        blDrive = hardwareMap.get(DcMotor.class, "bl_drive");
        flyWheel = hardwareMap.get(DcMotor.class, "fly_Wheel");
        backSlide = hardwareMap.get(DcMotor.class, "back_Slide");
        leftSlide = hardwareMap.get(DcMotor.class, "left_Slide");
        topSLide = hardwareMap.get(DcMotor.class, "top_Slide");

        flDrive.setDirection(DcMotor.Direction.FORWARD);
        frDrive.setDirection(DcMotor.Direction.REVERSE);
        brDrive.setDirection(DcMotor.Direction.REVERSE);
        blDrive.setDirection(DcMotor.Direction.FORWARD);
        flyWheel.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double Speed = -gamepad1.left_stick_y;
            double Turn = gamepad1.right_stick_x;
            double Strafe = gamepad1.left_stick_x;

            double f_left;
            double f_right;
            double b_left;
            double b_right;

            f_left = Speed + Turn + Strafe;
            f_right = Speed - Turn - Strafe;
            b_right = Speed - Turn + Strafe;
            b_left = Speed + Turn - Strafe;

            flDrive.setPower(Range.clip(f_left, -1.0, 1.0));
            frDrive.setPower(Range.clip(f_right, -1.0, 1.0));
            brDrive.setPower(Range.clip(b_right, -1.0, 1.0));
            blDrive.setPower(Range.clip(b_left, -1.0, 1.0));
            

            if(gamepad1.left_bumper)
            {
                increment -= - 0.1;
                arm_bottom_servo.setPosition(increment);
            }

            if(gamepad1.right_bumper)
            {
                increment += 0.1;
                arm_bottom_servo.setPosition(increment);
            }

           /* if(gamepad2.x)
            {
               flydrive.setPower(0.7); 
            }
            
             if(gamepad2.b)
            {
               flydrive.setPower(0.0); 
            }*/

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", f_left, f_right, b_left, b_right);
            telemetry.update();
        }


    }
}
