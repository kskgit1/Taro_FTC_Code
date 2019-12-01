package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

//this is the official Autonomous Code for Team Taro.
//this uses Mecanum wheels. They are a bit different from normal wheels: not only can they turn and move forward/backward,
//but they can also strafe left and strafe right.

@Autonomous(name="MecanumAuto", group="Linear OpMode")

public class MecanumAuto extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    //define motors
    DcMotor fldrive; //motor 0
    DcMotor frdrive; //motor 1
    DcMotor brdrive; //motor 2
    DcMotor bldrive; //motor 3
    
    //functions we will call into the main program
    //move forward function
    public void forward(double power, long wait_time) {
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
        sleep(wait_time);
        power = 0.0;
        //"stop"
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }

    //move backward function
    public void backward(double power, long wait_time) {
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
        sleep(wait_time);
        power = 0.0;
        //"stop"
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }

    //turn left function
    public void left(double power, long wait_time) {
        fldrive.setPower(-power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(-power);
        sleep(wait_time);
        power = 0.0;
        //"stop"
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }

    //strafe right function
    public void right(double power, long wait_time) {
        fldrive.setPower(power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(power);
        sleep(wait_time);
        power = 0.0;
        //"stop"
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }
    
    //strafe left function
    public void strafeleft(double power, long wait_time) {
        fldrive.setPower(-power);
        frdrive.setPower(power);
        brdrive.setPower(-power);
        bldrive.setPower(power);
        sleep(wait_time);
        power = 0.0;
        //"stop"
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }
    
    //strafe right function
    public void straferight(double power, long wait_time) {
        fldrive.setPower(power);
        frdrive.setPower(-power);
        brdrive.setPower(power);
        bldrive.setPower(-power);
        sleep(wait_time);
        power = 0.0;
        //"stop"
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }
    
    //main program
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Intialized");
        telemetry.update();

        //to 'get' means to configure the robot according to the motor names
        fldrive  = hardwareMap.get(DcMotor.class, "fl_drive");
        frdrive  = hardwareMap.get(DcMotor.class, "fr_drive");
        brdrive = hardwareMap.get(DcMotor.class, "br_drive");
        bldrive = hardwareMap.get(DcMotor.class, "bl_drive");

        //set the direction of each of the motors
        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);

        //wait for the START button to be pressed
        waitForStart();
        runtime.reset();

        //call the functions: power, distance
        forward(0.5, 300);
        right(0.5, 500);
        forward(0.5, 2500);
        //right(0.5, 1000);
        //strafeleft(0.5,1000);
        //straferight(0.5,1000);


    }
   

}
