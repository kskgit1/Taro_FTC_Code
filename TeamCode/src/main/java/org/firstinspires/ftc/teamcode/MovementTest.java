package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "MovementTest", group = "Linear OpMode")
public class MovementTest extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    //initiate motor variables and driving power variable
    private DcMotor fldrive, frdrive, bldrive, brdrive;
    double drivepower, slowmodepower;

    @Override
    public void runOpMode()
    {
        //classify each motor
        fldrive = hardwareMap.get(DcMotor.class, "fldrive");
        frdrive = hardwareMap.get(DcMotor.class, "frdrive");
        brdrive = hardwareMap.get(DcMotor.class, "brdrive");
        bldrive = hardwareMap.get(DcMotor.class, "bldrive");

        //set direction of each motor
        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive())
        {
            // set driving power based on left trigger
            drivepower = 0.5 - (gamepad1.left_trigger - 0.5);

            //set driving power to slow power when left bumper button pressed
            slowmodepower = 0.3;
            if(gamepad1.left_bumper)
            {
                drivepower = slowmodepower;
            }

            double Drive = -gamepad1.left_stick_y;
            double Rotate = gamepad1.right_stick_x;
            double Strafe = gamepad1.left_stick_x;

            //set range of power and current power to each motor
            fldrive.setPower(Range.clip((Drive + Rotate + Strafe), -drivepower, drivepower));
            frdrive.setPower(Range.clip((Drive - Rotate - Strafe), -drivepower, drivepower));
            brdrive.setPower(Range.clip((Drive - Rotate + Strafe), -drivepower, drivepower));
            bldrive.setPower(Range.clip((Drive + Rotate - Strafe), -drivepower, drivepower));
        }
    }
}
