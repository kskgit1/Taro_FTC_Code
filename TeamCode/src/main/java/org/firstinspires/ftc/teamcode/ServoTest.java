package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ServoTest", group = "Linear OpMode")
@Disabled
public class ServoTest extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private Servo leftextender, rightextender, leftrotater, rightrotater, centerrotater, grabber;
    double leftextenderposition, rightextenderposition, leftrotaterposition, rightrotaterposition, centerrotaterposition, grabberposition;

    @Override
    public void runOpMode()
    {
        leftextender = hardwareMap.get(Servo.class, "leftextender");
        rightextender = hardwareMap.get(Servo.class, "rightextender");
        leftrotater = hardwareMap.get(Servo.class, "leftrotater");
        rightrotater = hardwareMap.get(Servo.class, "rightrrotater");
        centerrotater = hardwareMap.get(Servo.class, "centerrotater");
        grabber = hardwareMap.get(Servo.class, "grabber");


        waitForStart();
        runtime.reset();
        while (opModeIsActive())
        {

        }
    }
}
