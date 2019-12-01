package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

//This is the Official TeleOp for Team Taro. 
//This program also moves the Linear Slides, which turn rotational motion into linear motion, either vertically or horizontally. 
//The Linear Slides in this program is sliding vertically.

@TeleOp(name = "MainDriver", group = "Linear OpMode")
public class MainDriver extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor flDrive, frDrive, blDrive, brDrive, fly_Wheel, back_Slide, left_Slide, top_Slide; // initialize all motors
    //these below servos are named to mimic a human body. the comment next to it explains the role/ position
    //intialize all servos
    Servo head_servo, hair_servo;
    //Servo arm_servo, hand_servo;
    //currentpositions for arm, hand, head, and hair servos
    double currentposition_arm = 0; //arm: small arm on the side
    double currentposition_hand = 0; //hand: servo on the top of the arm servo
    double currentposition_head = 0; //head: the final top block twisting servo
    double currentposition_hair = 0; //hair: clamps to secure the block
    boolean slow_mode = false;
    boolean xControl = false;
    //double speed_power;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //to 'get' must correspond to the names assigned during robot config       
        //arm_servo = hardwareMap.get(Servo.class, "arm");
        //hand_servo = hardwareMap.get(Servo.class, "hand");
        head_servo = hardwareMap.get(Servo.class, "head");
        hair_servo = hardwareMap.get(Servo.class, "hair");


        flDrive = hardwareMap.get(DcMotor.class, "fl_drive"); //motor 0 
        frDrive = hardwareMap.get(DcMotor.class, "fr_drive"); //motor 1
        brDrive = hardwareMap.get(DcMotor.class, "br_drive"); //motor 2
        blDrive = hardwareMap.get(DcMotor.class, "bl_drive"); //motor 3
        //fly_Wheel = hardwareMap.get(DcMotor.class, "fly_Wheel");
        back_Slide = hardwareMap.get(DcMotor.class, "back_Slide");
        left_Slide = hardwareMap.get(DcMotor.class, "left_Slide");
        top_Slide = hardwareMap.get(DcMotor.class, "top_Slide");

        //set the direction of the motors
        flDrive.setDirection(DcMotor.Direction.FORWARD); //motor 0
        frDrive.setDirection(DcMotor.Direction.REVERSE); //motor 1
        brDrive.setDirection(DcMotor.Direction.REVERSE); //motor 2
        blDrive.setDirection(DcMotor.Direction.FORWARD); //motor 3
        //fly_Wheel.setDirection(DcMotor.Direction.FORWARD);// fly wheel motor
        back_Slide.setDirection(DcMotor.Direction.FORWARD);
        left_Slide.setDirection(DcMotor.Direction.FORWARD);
        top_Slide.setDirection(DcMotor.Direction.FORWARD);

        //wait for the game to start, wherein the driver will press PLAY
        waitForStart();
        runtime.reset();

        //run until the end of the match, wherein the driver will press STOP
        while (opModeIsActive()) {

            double Speed = -gamepad1.left_stick_y;
            double Turn = gamepad1.right_stick_x;
            double Strafe = gamepad1.left_stick_x;
            double Catch = gamepad1.right_trigger;  // using flywheel
            double Lift = gamepad2.left_stick_y;    // using linear slides
            double Place = gamepad2.right_stick_x;  // using top slide
            double Release = gamepad1.left_trigger; // release block

            //initialize the variables
            double f_left;
            double f_right;
            double b_left;
            double b_right;
            double fly_wheel; 
            double back_slide;
            double left_slide;
            double top_slide;
            double fly_wheel_release;

            f_left = Speed + Turn + Strafe;
            f_right = Speed - Turn + Strafe;
            b_right = Speed - Turn - Strafe;
            b_left = Speed + Turn - Strafe;
            fly_wheel = Catch;
            fly_wheel_release = Release;
            back_slide = Lift;
            left_slide = Lift;
            top_slide = Place;

            if(slow_mode){
                f_left/=10;
                f_right/=10;
                b_left/=10;
                b_right/=10;

            }

            flDrive.setPower(Range.clip(f_left, -1.0, 1.0));
            frDrive.setPower(Range.clip(f_right, -1.0, 1.0));
            brDrive.setPower(Range.clip(b_right, -1.0, 1.0));
            blDrive.setPower(Range.clip(b_left, -1.0, 1.0));
            //fly_Wheel.setPower(Range.clip(fly_wheel, -1.0, 1.0));
            //fly_Wheel.setPower(Range.clip(fly_wheel_release, 1.0, -1.0));
            back_Slide.setPower(Range.clip(back_slide, -1.0, 1.0));
            left_Slide.setPower(Range.clip(left_slide, -1.0, 1.0));
            top_Slide.setPower(Range.clip(top_slide, -1.0, 1.0));

            if(gamepad1.x && xControl){
                slow_mode = !slow_mode;
            }

            xControl = !gamepad1.x;


           /* if (gamepad1.x){
                if (slow_mode){
                    speed_power = 0.1;
                }
                if (slow_mode == false){
                    speed_power = 1.0;
                }

            }


            if(gamepad1.a) // reduce servo position for arm
            {
                currentposition_arm = currentposition_arm - 0.1;
                arm_servo.setPosition(currentposition_arm);
            }

            if(gamepad1.x)  // increase servo position for arm
            {
                currentposition_arm = currentposition_arm + 0.1;
                arm_servo.setPosition(currentposition_arm);
            }

            //if the Right Bumper on Gamepad 1 is pressed, then the servo position will increase
            if(gamepad1.left_bumper)  // reduce servo position for hand
            {
                currentposition_hand = currentposition_hand - 0.1;
                hand_servo.setPosition(currentposition_hand);
            }

            //servos in action: hand
            if(gamepad1.right_bumper) // increase servo position for hand
            {
                currentposition_hand = currentposition_hand + 0.1;
                hand_servo.setPosition(currentposition_hand);
            }
            */


            //if the Left Bumper on Gamepad 2 is pressed, then the servo will turn counterclockwise
            //servos in action: head
            if(gamepad2.left_bumper)
            {
                currentposition_head = currentposition_head - 0.1;
                head_servo.setPosition(currentposition_head);
            }
            
            //if the Right Bumper on Gamepad 2 is pressed, then the servo will turn clockwise
            //servos in action: head
            if(gamepad2.right_bumper)  
            {
                currentposition_head = currentposition_head + 0.1;
                head_servo.setPosition(currentposition_head);
                
            }

            //if A on Gamepad 2 is pressed, then both servos will be clamped at the same time 
            //servos in action: hair1 and hair2
            if(gamepad2.a)   
            {
                if(currentposition_hair == 0)
                {
                    currentposition_hair = 0.5;
                    hair_servo.setPosition(currentposition_hair);
                }
                else
                {
                    currentposition_hair = 0;
                    hair_servo.setPosition(currentposition_hair);
            }
                

            }
            
            //display the wheel power and elapsed run time
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", f_left, f_right, b_left, b_right);
            telemetry.addData("flDrive",Double.toString(flDrive.getPower()));
            telemetry.addData("flDrive",Double.toString(frDrive.getPower()));
            telemetry.addData("flDrive",Double.toString(blDrive.getPower()));
            telemetry.addData("flDrive",Double.toString(brDrive.getPower()));

            telemetry.update();
        }


    }
}

