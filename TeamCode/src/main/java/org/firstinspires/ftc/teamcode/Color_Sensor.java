package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;


//This is the official Color Sensor code for Team Taro.
//it serves as a backup to the CV code, in case the CV fails.
@Autonomous(name="Color_Sensor", group="Linear OpMode")

public class Color_Sensor extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //functions 
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

    //turn right function
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
    
    //turn StrafeLeft function
    public void strafeleft(double power, long wait_time) {
        fldrive.setPower(-power);
        frdrive.setPower(power);
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
    
    //turn StrafeRight function
    public void straferight(double power, long wait_time) {
        fldrive.setPower(power);
        frdrive.setPower(-power);
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
    
    //define motors
    DcMotor fldrive; //motor 0
    DcMotor frdrive; //motor 1
    DcMotor brdrive; //motor 2
    DcMotor bldrive; //motor 3
    
    ColorSensor color_sensor; //color sensor

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Intialized");
        telemetry.update();

        //to "get" is to edit the robot config according to the motor name
        fldrive = hardwareMap.dcMotor.get("fl_drive");
        frdrive = hardwareMap.dcMotor.get("fr_drive");
        brdrive = hardwareMap.dcMotor.get("br_drive");
        bldrive = hardwareMap.dcMotor.get("bl_drive");
        color_sensor = hardwareMap.colorSensor.get("color");

        //set the directions of the motors
        fldrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        brdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        bldrive.setDirection(DcMotorSimple.Direction.REVERSE);

        //wait for START button to be pressed
        waitForStart();
        runtime.reset();

        color_sensor.red();   // Red channel value
        color_sensor.green(); // Green channel value
        color_sensor.blue();  // Blue channel value

        color_sensor.alpha(); // Total luminosity
        color_sensor.argb();  // Combined color value

        //initiliaze the variables
        double margin = 20f; //a threshold value 
        //perfect color values
        double perfectred = 175f;
        double perfectgreen = 125f;
        double perfectblue = 10f;
    
        //if the perfectcolor - color is within the threshold value, then it is a stone block
        if  ((Math.abs(perfectred - (color_sensor.red())) < margin) & (Math.abs(perfectgreen - (color_sensor.green())) < margin) & (Math.abs(perfectblue - (color_sensor.blue())) < margin))
        {
            straferight(0.5, 2000);
        }
        
        //move forward, backward, and right
        forward(0.5, 2000);
        backward(0.5, 2000);
        right(0.5, 1000);
        
    }
}
