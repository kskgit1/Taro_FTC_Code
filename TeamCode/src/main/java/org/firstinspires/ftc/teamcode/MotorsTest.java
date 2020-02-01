package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "MotorsTest", group = "Linear OpMode")
public class MotorsTest extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor flywheel1, flywheel2, leftslide, rightslide;
    double flypower;
    double liftpower = gamepad1.left_stick_y;
    int liftincrement = 500;


    @Override
    public void runOpMode()
    {
        flywheel1 = hardwareMap.get(DcMotor.class, "flywheel1");
        flywheel2 = hardwareMap.get(DcMotor.class, "flywheel2");

        flywheel1.setDirection(DcMotor.Direction.FORWARD);
        flywheel2.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();


        while (opModeIsActive())
        {

            flypower = gamepad1.right_trigger;

            if (gamepad1.right_bumper)
            {
                flypower = 0.5;
            }

            flywheel1.setPower(Range.clip(flypower, -flypower, flypower));
            flywheel2.setPower(Range.clip(flypower,-flypower,flypower));



        }


    }

}

