package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "MotorsTest", group = "Linear OpMode")
public class MotorsTest extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor flywheel, leftslide, rightslide, arm;
    double flypower, armpower;
    double liftpower = 0.7;
    int liftincrement = 500;


    @Override
    public void runOpMode()
    {
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        leftslide = hardwareMap.get(DcMotor.class, "leftslide");
        rightslide = hardwareMap.get(DcMotor.class, "rightslide");
        arm = hardwareMap.get(DcMotor.class, "arm");

        flywheel.setDirection(DcMotor.Direction.FORWARD);
        leftslide.setDirection(DcMotor.Direction.FORWARD);
        rightslide.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive())
        {

            flypower = gamepad1.right_trigger;

            if (gamepad1.right_bumper)
            {
                flypower = 0.5;
            }

            flywheel.setPower(Range.clip(flypower, -flypower, flypower));


            if(gamepad1.dpad_up)
            {
                leftslide.setTargetPosition(leftslide.getCurrentPosition() + liftincrement);
                rightslide.setTargetPosition(leftslide.getCurrentPosition() + liftincrement);

                leftslide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightslide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftslide.setPower(liftpower);
                rightslide.setPower(liftpower);

                leftslide.setPower(0);
                rightslide.setPower(0);

                leftslide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightslide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            if(gamepad1.dpad_down && ((leftslide.getCurrentPosition() - liftincrement) < 0))
            {
                leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            else if(gamepad1.dpad_down)
            {
                leftslide.setTargetPosition(leftslide.getCurrentPosition() - liftincrement);
                rightslide.setTargetPosition(leftslide.getCurrentPosition() - liftincrement);

                leftslide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightslide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftslide.setPower(-liftpower);
                rightslide.setPower(-liftpower);

                leftslide.setPower(0);
                rightslide.setPower(0);

                leftslide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightslide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }


            armpower = gamepad1.left_stick_y;

            arm.setPower(Range.clip(armpower, -armpower, armpower));
        }


    }

}
