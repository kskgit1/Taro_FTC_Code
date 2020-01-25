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
    private DcMotor fldrive, frdrive, bldrive, brdrive; //flywheel, leftslide, rightslide, arm;
    //private Servo leftextender, rightextender, leftrotater, rightrotater, centerrotater, grabber1, grabber2;

    double left_trigger_value = 0;
    /*
    double leftextenderposition = 0;
    double rightextenderposition = 0;
    double leftrotaterposition = 0;
    double leftrotaterincrement = 0.01;
    double rightrotaterposition = 0;
    double rightrotaterincrement = 0.01;
    double centerrotaterposition = 0;
    double centerrotaterincrement = 0.01;
    double grabber1position = 0;
    double grabber2position = 0;
    double grabberincrement = 0.01;
    */

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        fldrive = hardwareMap.get(DcMotor.class, "fl_drive");
        frdrive = hardwareMap.get(DcMotor.class, "fr_drive");
        brdrive = hardwareMap.get(DcMotor.class, "br_drive");
        bldrive = hardwareMap.get(DcMotor.class, "bl_drive");
        //flywheel = hardwareMap.get(DcMotor.class, "fly_wheel");
        //leftslide = hardwareMap.get(DcMotor.class, "left_slide");
        //rightslide = hardwareMap.get(DcMotor.class, "right_slide");
        //arm = hardwareMap.get(DcMotor.class, "arm");
        /*
        leftextender = hardwareMap.get(Servo.class, "left_extender");
        rightextender = hardwareMap.get(Servo.class, "right_extender");
        leftrotater = hardwareMap.get(Servo.class, "left_rotater");
        rightrotater = hardwareMap.get(Servo.class, "right_rotater");
        centerrotater = hardwareMap.get(Servo.class, "center_rotater");
        grabber1 = hardwareMap.get(Servo.class, "grabber1");
        grabber2 = hardwareMap.get(Servo.class, "grabber2");
        
         */

        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);
        //flywheel.setDirection(DcMotor.Direction.REVERSE);
        //leftslide.setDirection(DcMotor.Direction.FORWARD);
        //rightslide.setDirection(DcMotor.Direction.REVERSE);
        //arm.setDirection(DcMotor.Direction.FORWARD);



        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {


            double drivepower;
            //double flypower = 1;
            //double liftpower = 1;
            //double armpower = 1;


           left_trigger_value = gamepad1.left_trigger-0.5;
           drivepower = 0.5-left_trigger_value;

            double DriveSpeed = -gamepad1.left_stick_y;
            double Rotate = gamepad1.right_stick_x;
            double Strafe = gamepad1.left_stick_x;
            //double FlySpeed = gamepad1.right_trigger;
            //double Lift = gamepad2.left_stick_y;
            //double MoveArm = gamepad2.right.stick_y;

            double f_left;
            double f_right;
            double b_left;
            double b_right;
            //double fly_wheel;
            //double left_slide;
            //double right_slide;
            //double arm;

            f_left = DriveSpeed + Rotate + Strafe;
            f_right = DriveSpeed - Rotate - Strafe;
            b_right = DriveSpeed - Rotate + Strafe;
            b_left = DriveSpeed + Rotate - Strafe;
            //fly_wheel = FlySpeed;
            //left_slide = Lift;
            //right_slide = Lift;
            //arm = MoveArm;


            fldrive.setPower(Range.clip(f_left, -drivepower, drivepower));
            frdrive.setPower(Range.clip(f_right, -drivepower, drivepower));
            brdrive.setPower(Range.clip(b_right, -drivepower, drivepower));
            bldrive.setPower(Range.clip(b_left, -drivepower, drivepower));
            //flywheel.setPower(Range.clip(fly_wheel, -flypower, flypower));
            //leftslide.setPower(Range.clip(left_slide, -liftpower, liftpower));
            //rightslide.setPower(Range.clip(right_slide, -liftpower, liftpower));
            //arm.setPower(Range.clip(arm, -armpower, armpower));
            
            
            /*
            leftextenderposition = leftextender.getPosition() - (gamepad2.right_trigger * 0.1);
            leftextender.setPosition(leftextenderposition);

            rightextenderposition = rightextender.getPosition() + (gamepad2.right_trigger * 0.1);
            rightextender.setPosition(rightextenderposition);

            if(gamepad2.x)
            {
                leftrotaterposition = leftrotater.getPosition() - leftrotaterincrement;
                rightrotaterposition = rightrotater.getPosition() + rightrotaterincrement;
                leftrotater.setPosition(leftrotaterposition);
                rightrotater.setPosition(rightrotaterposition);

            }

            if(gamepad2.y)
            {
                leftrotaterposition = leftrotater.getPosition() + leftrotaterincrement;
                rightrotaterposition = rightrotater.getPosition() - rightrotaterincrement;
                leftrotater.setPosition(leftrotaterposition);
                rightrotater.setPosition(rightrotaterposition);

            }

            if(gamepad2.left_bumper)
            {
                centerrotaterposition = centerrotater.getPosition() - centerrotaterincrement;
                centerrotater.setPosition(centerrotaterposition);
            }

            if(gamepad2.right_bumper)
            {
                centerrotaterposition = centerrotater.getPosition() + centerrotaterincrement;
                centerrotater.setPosition(centerrotaterposition);
            }

            if(gamepad2.a)
            {
                grabber1position = grabber1.getPosition() - grabberincrement;
                grabber2position = grabber2.getPosition() + grabberincrement;
                grabber1.setPosition(grabber1position);
                grabber2.setPosition(grabber2position);
            }
            */
            
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", f_left, f_right, b_left, b_right);
            telemetry.update();
        }


    }
}
