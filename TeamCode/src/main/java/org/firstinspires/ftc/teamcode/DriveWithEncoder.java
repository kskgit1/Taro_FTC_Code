// autonomous program that drives bot forward a set distance, stops then
// backs up to the starting point using encoders to measure the distance.
// This example assumes there is one encoder, attached to the left motor.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

@Autonomous(name="DriveWithEncoder", group="Exercises")
public class DriveWithEncoder extends LinearOpMode
{
    private DcMotor flDrive, frDrive, blDrive, brDrive;

    @Override
    public void runOpMode() throws InterruptedException
    {
        flDrive  = hardwareMap.get(DcMotor.class, "fl_drive");
        frDrive  = hardwareMap.get(DcMotor.class, "fr_drive");
        brDrive = hardwareMap.get(DcMotor.class, "br_drive");
        blDrive = hardwareMap.get(DcMotor.class, "bl_drive");

        flDrive.setDirection(DcMotor.Direction.FORWARD);
        frDrive.setDirection(DcMotor.Direction.REVERSE);
        brDrive.setDirection(DcMotor.Direction.REVERSE);
        blDrive.setDirection(DcMotor.Direction.FORWARD);

        // reset encoder count kept by left motor.
        flDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        blDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motors to run to target encoder position and stop with brakes on.
        flDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        blDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // set right motor to run without regard to an encoder.
        //rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        //set left motor to run to 5000 encoder counts.

        //leftMotor.setTargetPosition(5000);

        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && flDrive.isBusy()) {
            telemetry.addData("encoder-fwd", flDrive.getCurrentPosition() + "  busy=" + flDrive.isBusy());
            telemetry.update();
            idle();
        }

        // wait 5 sec so you can observe the final encoder position.

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-fwd-end", flDrive.getCurrentPosition() + "  busy=" + flDrive.isBusy());
            telemetry.update();
            idle();
        }

        // Now back up to starting point. In this example instead of
        // having the motor monitor the encoder, we will monitor the encoder ourselves.

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setPower(-0.25);
        rightMotor.setPower(-0.25);

        while (opModeIsActive() && flDrive.getCurrentPosition() > 0)
        {
            telemetry.addData("encoder-back", flDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // set motor power to zero to stop motors.

        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

        // wait 5 sec so you can observe the final encoder position.

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-back-end", flDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }
}
