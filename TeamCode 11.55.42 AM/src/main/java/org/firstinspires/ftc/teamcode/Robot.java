package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//By Akash

public abstract class Robot {

    public static double getInvertPerspective() {
        return invertPerspective;
    }

    public static void setInvertPerspective(double invertPerspective) {
        Robot.invertPerspective = invertPerspective;
    }

    protected static double invertPerspective = 1;

    public Robot () {}

    public abstract void init(HardwareMap hardwareMap);
    public abstract void move(Gamepad gamepad1, Gamepad gamepad2);

}

class DrivetrainTeleOp extends Robot {

    private DcMotor fl, fr, bl, br;
    
    private double robotAngle = 0;

    private double speedFactor = 1;

    public double getSpeedFactor() {
        return speedFactor;
    }

    public void setSpeedFactor(double speedFactor) {
        this.speedFactor = speedFactor;
    }

    public DrivetrainTeleOp(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br) {
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;
    }

    @Override
    public void init (HardwareMap hardwareMap) {
        this.fl  = hardwareMap.get(DcMotor.class, "fL");
        this.fr = hardwareMap.get(DcMotor.class, "fR");
        this.bl  = hardwareMap.get(DcMotor.class, "bL");
        this.br = hardwareMap.get(DcMotor.class, "bR");
    }

    @Override
    public void move (Gamepad gamepad1, Gamepad gamepad2) {

//         fr.setPower(gamepad1.left_stick_y * invertPerspective * speedFactor);
//         fl.setPower(gamepad1.left_stick_y * invertPerspective * speedFactor);
//         br.setPower(gamepad1.left_stick_y * invertPerspective * speedFactor);
//         bl.setPower(gamepad1.left_stick_y * invertPerspective * speedFactor);

//         fr.setPower(gamepad1.left_stick_x * invertPerspective * speedFactor);
//         fl.setPower(-gamepad1.left_stick_x * invertPerspective * speedFactor);
//         br.setPower(-gamepad1.left_stick_x * invertPerspective * speedFactor);
//         bl.setPower(gamepad1.left_stick_x * invertPerspective * speedFactor);
        
        //disclaimer: the code below is officially goated ACCORDING TO MY FRC TEAM (LIKE WHATTT) and a huge flex on deja/astro/endgame.
        //basically what the code does is the robot will move in the direction the joystick is pointing NO MATTER THE ANGLE OF THE ROBOT
        //yee
        
        this.robotAngle = get angle from gyro (its called heading) from the gyro built in the REV hub called IMU; //yea so someone do this 
        
        double desiredAngle = getAngle(gamepad1.x, gamepad1.y);
        
        fr.setPower(getPowerRed(desiredAngle - robotAngle));
        bl.setPower(getPowerRed(desiredAngle - robotAngle));
        
        fl.setPower(getPowerBlue(desiredAngle - robotAngle));
        br.setPower(getPowerBlue(desiredAngle - robotAngle));
            

    }
    
    public static double getAngle(double x, double y) {
		double angle = 0;
		//c = square root of x squared plus y squared
		double hyp = Math.pow(((x*x) + (y*y)), 0.5);
		
		angle = Math.asin(y/hyp);
		
		angle = Math.toDegrees(angle);
		
		return angle;
	}
    
    public static double getPowerRed(double angle) {
		double power = 0;
		angle = Math.toRadians(angle);
		power = Math.sin(angle - (Math.PI * 0.25));
		if(power > 1) return 0;
		return power;
		
	}
	
	public static double getPowerBlue(double angle) {
		double power = 0;
		angle = Math.toRadians(angle);
		power = Math.sin(angle + (Math.PI * 0.25));
		if(power > 1) return 0;
		return power;
		
	}
}

class Servos extends Robot {

    //Declare servos
    private Servo arm_servo, hand_servo, head_servo, hair1_servo, hair2_servo;

    // currentpositions for arm, hand, and head servos
    private double currentposition_arm = 0;
    private double currentposition_hand = 0;
    private double currentposition_head = 0;
    private double currentposition_hair1 = 0;
    private double currentposition_hair2 = 0;

    public Servos(Servo arm_servo, Servo hand_servo, Servo head_servo, Servo hair1_servo, Servo hair2_servo) {
        this.arm_servo = arm_servo;
        this.hand_servo = hand_servo;
        this.head_servo = head_servo;
        this.hair1_servo = hair1_servo;
        this.hair2_servo = hair2_servo;
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        this.arm_servo = hardwareMap.get(Servo.class, "arm");
        this.hand_servo = hardwareMap.get(Servo.class, "hand");
        this.head_servo = hardwareMap.get(Servo.class, "head");
        this.hair1_servo = hardwareMap.get(Servo.class, "hair1");
        this.hair2_servo = hardwareMap.get(Servo.class, "hair2");
    }

    @Override
    public void move(Gamepad gamepad1, Gamepad gamepad2) {
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
        }


    }
}

class Arms extends Robot {

    private DcMotor back_Slide, left_Slide, top_Slide, fly_Wheel;

    double Catch;
    double Lift;    // using linear slides
    double Place; // using top slide

    double fly_wheel;
    double back_slide;
    double left_slide;
    double top_slide;

    public Arms(Gamepad gamepad1, Gamepad gamepad2, DcMotor back_Slide, DcMotor left_Slide, DcMotor top_Slide, DcMotor fly_Wheel) {

        this.back_Slide = back_Slide;
        this.left_Slide = left_Slide;
        this.top_Slide = top_Slide;
        this.fly_Wheel = fly_Wheel;

         Catch = gamepad1.right_trigger;
         Lift = gamepad2.left_stick_y;    // using linear slides
         Place = gamepad2.right_stick_x;  // using top slide

    }

    @Override
    public void init(HardwareMap hardwareMap) {
        fly_Wheel.setDirection(DcMotor.Direction.FORWARD);
        back_Slide.setDirection(DcMotor.Direction.FORWARD);
        left_Slide.setDirection(DcMotor.Direction.FORWARD);
        top_Slide.setDirection(DcMotor.Direction.FORWARD);
        back_Slide = hardwareMap.get(DcMotor.class, "back_Slide");
        left_Slide = hardwareMap.get(DcMotor.class, "left_Slide");
        top_Slide = hardwareMap.get(DcMotor.class, "top_Slide");
    }

    @Override
    public void move(Gamepad gamepad1, Gamepad gamepad2) {

        fly_Wheel.setPower(Range.clip(fly_wheel, -1.0, 1.0));
        back_Slide.setPower(Range.clip(back_slide, -1.0, 1.0));
        left_Slide.setPower(Range.clip(left_slide, -1.0, 1.0));
        top_Slide.setPower(Range.clip(top_slide, -1.0, 1.0));

    }
}
