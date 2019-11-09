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
    private DcMotor flDrive, frDrive, blDrive, brDrive, fly_Wheel, back_Slide, left_Slide, top_Slide; // initialize all motors
    Servo arm_servo, hand_servo, head_servo, hair1_servo, hair2_servo; // initialize all servos
    double currentposition_arm = 0;  // currentpositions for arm, hand, and head servos
    double currentposition_hand = 0;
    double currentposition_head = 0;
    double currentposition_hair1 = 0;
    double currentposition_hair2 = 0;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        arm_servo = hardwareMap.get(Servo.class, "arm");
        hand_servo = hardwareMap.get(Servo.class, "hand");
        head_servo = hardwareMap.get(Servo.class, "head");
        hair1_servo = hardwareMap.get(Servo.class, "hair1");
        hair2_servo = hardwareMap.get(Servo.class, "hair2");

        flDrive = hardwareMap.get(DcMotor.class, "fl_drive");
        frDrive = hardwareMap.get(DcMotor.class, "fr_drive");
        brDrive = hardwareMap.get(DcMotor.class, "br_drive");
        blDrive = hardwareMap.get(DcMotor.class, "bl_drive");
        fly_Wheel = hardwareMap.get(DcMotor.class, "fly_Wheel");
        back_Slide = hardwareMap.get(DcMotor.class, "back_Slide");
        left_Slide = hardwareMap.get(DcMotor.class, "left_Slide");
        top_Slide = hardwareMap.get(DcMotor.class, "top_Slide");

        flDrive.setDirection(DcMotor.Direction.FORWARD);
        frDrive.setDirection(DcMotor.Direction.REVERSE);
        brDrive.setDirection(DcMotor.Direction.REVERSE);
        blDrive.setDirection(DcMotor.Direction.FORWARD);
        fly_Wheel.setDirection(DcMotor.Direction.FORWARD);
        back_Slide.setDirection(DcMotor.Direction.FORWARD);
        left_Slide.setDirection(DcMotor.Direction.FORWARD);
        top_Slide.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();
    

        while (opModeIsActive()) {

            double Speed = -gamepad1.left_stick_y;
            double Turn = gamepad1.right_stick_x;
            double Strafe = gamepad1.left_stick_x;
            double Catch = gamepad1.right_trigger;
            double Lift = gamepad2.left_stick_y;    // using linear slides
            double Place = gamepad2.right_stick_x;  // using top slide


            double f_left;
            double f_right;
            double b_left;
            double b_right;
            double fly_wheel;
            double back_slide;
            double left_slide;
            double top_slide;

            f_left = Speed + Turn + Strafe;
            f_right = Speed - Turn - Strafe;
            b_right = Speed - Turn + Strafe;
            b_left = Speed + Turn - Strafe;
            fly_wheel = Catch;
            back_slide = Lift;
            left_slide = Lift;
            top_slide = Place;

            flDrive.setPower(Range.clip(f_left, -1.0, 1.0));
            frDrive.setPower(Range.clip(f_right, -1.0, 1.0));
            brDrive.setPower(Range.clip(b_right, -1.0, 1.0));
            blDrive.setPower(Range.clip(b_left, -1.0, 1.0));
            fly_Wheel.setPower(Range.clip(fly_wheel, -1.0, 1.0));
            back_Slide.setPower(Range.clip(back_slide, -1.0, 1.0));
            left_Slide.setPower(Range.clip(left_slide, -1.0, 1.0));
            top_Slide.setPower(Range.clip(top_slide, -1.0, 1.0));


            if (gamepad1.a) // reduce servo position for arm
            {
                currentposition_arm = currentposition_arm - 0.1;
                arm_servo.setPosition(currentposition_arm);
            }

            if (gamepad1.x)  // increase servo position for arm
            {
                currentposition_arm = currentposition_arm + 0.1;
                arm_servo.setPosition(currentposition_arm);
            }

            if (gamepad1.left_bumper)  // reduce servo position for hand
            {
                currentposition_hand = currentposition_hand - 0.1;
                hand_servo.setPosition(currentposition_hand);
            }

            if (gamepad1.right_bumper) // increase servo position for hand
            {
                currentposition_hand = currentposition_hand + 0.1;
                hand_servo.setPosition(currentposition_hand);
            }

            if (gamepad2.left_bumper)  // turn head servo one way
            {
                currentposition_head = currentposition_head - 0.1;
                head_servo.setPosition(currentposition_hand);
            }

            if (gamepad2.right_bumper)  // turn head servo other way
            {
                currentposition_head = currentposition_head + 0.1;
                head_servo.setPosition(currentposition_head);

            }

            if (gamepad2.a)   // clamp both servos at same time
            {
                if (currentposition_hair1 == 0) {
                    currentposition_hair1 = 0.5;
                    hair1_servo.setPosition(currentposition_hair1);
                } else {
                    currentposition_hair1 = 0;
                    hair1_servo.setPosition(currentposition_hair1);
                }
                if (gamepad2.a) {
                    if (currentposition_hair2 == 0) {
                        currentposition_hair2 = 0.5;
                        hair2_servo.setPosition(currentposition_hair2);
                    } else {
                        currentposition_hair2 = 0;
                        hair2_servo.setPosition(currentposition_hair2);
                    }

                }


                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.addData("Motors", "left (%.2f), right (%.2f)", f_left, f_right, b_left, b_right);
                telemetry.update();
            }


        }
    }
}
