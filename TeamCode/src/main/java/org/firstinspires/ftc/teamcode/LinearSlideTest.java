package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "LinearSlideTest", group = "Linear OpMode")
public class LinearSlideTest extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftslide, rightslide;


    @Override
    public void runOpMode()
    {

        leftslide = hardwareMap.get(DcMotor.class, "leftslide");
        rightslide = hardwareMap.get(DcMotor.class, "rightslide");

        leftslide.setDirection(DcMotor.Direction.REVERSE);
        rightslide.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive())
        {

            double liftpower = gamepad1.left_stick_y;
            int liftincrement = 500;

            if(gamepad1.left_stick_y>0)
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

            if(gamepad1.left_stick_y<0 && ((leftslide.getCurrentPosition() - liftincrement) < 0))
            {
                leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            else if(gamepad1.left_stick_y<0)
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
        }
    }
}
