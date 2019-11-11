package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.TetrixMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "DriverControl", group = "Linear OpMode")
public class DriverControl extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor flDrive, frDrive, blDrive, brDrive, fly_Wheel, back_Slide, left_Slide, top_Slide; // declare all motors
    private Servo arm_servo, hand_servo, head_servo, hair1_servo, hair2_servo; // declare all servos


    @Override
    public void runOpMode() {

        //Make and init a new drivetrain
        Robot drivetrain = new DrivetrainTeleOp(flDrive, frDrive, blDrive, brDrive);
        drivetrain.init(hardwareMap);

//        Robot servos = new Servos(arm_servo, hand_servo, head_servo, hair1_servo, hair2_servo);
//        servos.init(hardwareMap);
//
//        Robot arms = new Arms(gamepad1, gamepad2, back_Slide, left_Slide, top_Slide, fly_Wheel);
//        arms.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            drivetrain.move(gamepad1, gamepad2);
//            servos.move(gamepad1, gamepad2);
//            arms.move(gamepad1, gamepad2);


            double f_left;
            double f_right;
            double b_left;
            double b_right;

            double Speed = -gamepad1.left_stick_y;
            double Turn = gamepad1.right_stick_x;
            double Strafe = gamepad1.left_stick_x;

            f_left = Speed + Turn + Strafe;
            f_right = Speed - Turn - Strafe;
            b_right = Speed - Turn + Strafe;
            b_left = Speed + Turn - Strafe;


            telemetry.addData("Status", "Run Time: " + runtime);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", f_left, f_right, b_left, b_right);
            telemetry.update();

        }


    }
}

