package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "MainDriver", group = "Linear OpMode")
public class MainDriver extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor flDrive, frDrive, blDrive, brDrive, flyWheel, back_Slide, left_Slide, top_Slide;
    Servo arm_servo, hand_servo, head_servo, hair_1, hair_2;
    double currentposition_arm = 0;
    double currentposition_hand = 0;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        arm_servo = hardwareMap.get(Servo.class, "arm");
        hand_servo = hardwareMap.get(Servo.class, "hand");
        head_servo = hardwareMap.get(Servo.class, "head");
        hair_1 = hardwareMap.get(Servo.class, "hair1");
        hair_2 = hardwareMap.get(Servo.class, "hair2");

        flDrive = hardwareMap.get(DcMotor.class, "fl_drive");
        frDrive = hardwareMap.get(DcMotor.class, "fr_drive");
        brDrive = hardwareMap.get(DcMotor.class, "br_drive");
        blDrive = hardwareMap.get(DcMotor.class, "bl_drive");
        flyWheel = hardwareMap.get(DcMotor.class, "fly_Wheel");
        back_Slide = hardwareMap.get(DcMotor.class, "back_Slide");
        left_Slide = hardwareMap.get(DcMotor.class, "left_Slide");
        top_Slide = hardwareMap.get(DcMotor.class, "top_Slide");

        flDrive.setDirection(DcMotor.Direction.FORWARD);
        frDrive.setDirection(DcMotor.Direction.REVERSE);
        brDrive.setDirection(DcMotor.Direction.REVERSE);
        blDrive.setDirection(DcMotor.Direction.FORWARD);
        flyWheel.setDirection(DcMotor.Direction.FORWARD);
        back_Slide.setDirection(DcMotor.Direction.FORWARD);
        left_Slide.setDirection(DcMotor.Direction.FORWARD);
        top_Slide.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double Speed = -gamepad1.left_stick_y;
            double Turn = gamepad1.right_stick_x;
            double Strafe = gamepad1.left_stick_x;
            double Lift = gamepad2.left_stick_y;
            double Place = gamepad2.right_stick_x;
          

            double f_left;
            double f_right;
            double b_left;
            double b_right;
            double back_slide;
            double left_slide;
            double top_slide;

            f_left = Speed + Turn + Strafe;
            f_right = Speed - Turn - Strafe;
            b_right = Speed - Turn + Strafe;
            b_left = Speed + Turn - Strafe;
            back_slide = Lift;
            left_slide = Lift;
            top_slide = Place;

            flDrive.setPower(Range.clip(f_left, -1.0, 1.0));
            frDrive.setPower(Range.clip(f_right, -1.0, 1.0));
            brDrive.setPower(Range.clip(b_right, -1.0, 1.0));
            blDrive.setPower(Range.clip(b_left, -1.0, 1.0));
            back_Slide.setPower(Range.clip(back_slide, -1.0, 1.0));
            left_Slide.setPower(Range.clip(left_slide, -1.0, 1.0));
            top_Slide.setPower(Range.clip(top_slide, -1.0, 1.0));
            

            if(gamepad1.left_bumper)
            {
                currentposition_arm = currentposition_arm - 0.1;
                arm_servo.setPosition(currentposition_arm);
            }

            if(gamepad1.right_bumper)
            {
                currentposition_arm = currentposition_arm + 0.1;
                arm_servo.setPosition(currentposition_arm);
            }

            if(gamepad1.dpad_down)
            {
                currentposition_hand = currentposition_hand - 0.1;
                arm_servo.setPosition(currentposition_hand);
            }

            if(gamepad1.dpad_up)
            {
                currentposition_hand = currentposition_hand + 0.1;
                arm_servo.setPosition(currentposition_hand);
            }

            while(gamepad2.x)
            {
               flyWheel.setPower(0.7);
            }
            
             if(gamepad2.y)
            {
               flyWheel.setPower(0.0);
            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", f_left, f_right, b_left, b_right);
            telemetry.update();
        }


    }
}
