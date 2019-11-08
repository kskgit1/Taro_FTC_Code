//NOTES WHEN I WAS PUTTING THIS CODE TOGETHER
//A quick summary: this program is just moving forward with encoders and computer vision, we will add servos in later
//Goal: to move forward from the square on the mat and recognize a stone block
//please fix margins when putting into android studio!!!!!
//A lot of things are commented out because if we need them, we don't have to go back searching and can just un-comment
//NOTES ENDED

//let's first import all the packages and variables (there's a lot of them)
//idk if we can move these to their corresponding parts so i'm going to name them here. if we can, we can move them back.

//COMPUTER VISION VARIABLES
package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";

    private ElapsedTime runtime = new ElapsedTime();
   // private DcMotor fldrive, frdrive, bldrive, brdrive;
    //Servo servo;

   // we are not using these double currentposition;
   // double currentposition2;
   
//ENCODER VARIABLES THAT WEREN'T ALREADY SPECIFIED

import com.qualcomm.robotcore.hardware.DcMotorController;

   
//ENCODER PART
//we are just moving forward, not all of them. 

// autonomous program that drives bot forward a set distance, stops then
// backs up to the starting point using encoders to measure the distance.
// This example assumes there is one encoder, attached to the left motor.


@Autonomous(name="ForwardCv", group="Exercises")
public class ForwardCv extends LinearOpMode
{
    private DcMotor fldrive, frdrive, bldrive, brdrive;
    
    @Override
    public void runOpMode() throws InterruptedException {
        fldrive = hardwareMap.get(DcMotor.class, "fl_drive");
        frdrive = hardwareMap.get(DcMotor.class, "fr_drive");
        brdrive = hardwareMap.get(DcMotor.class, "br_drive");
        bldrive = hardwareMap.get(DcMotor.class, "bl_drive");

        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);

        // set right motor to run without regard to an encoder.
        //rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();
        //you might have to increase the distance parameter in forward
        forward(0.5, 1);

        telemetry.addData("Mode", "running");
        telemetry.update();
        
    }
    public void forward(double power, int distance) {
        fldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sets Target Position
        fldrive.setTargetPosition(distance);
        frdrive.setTargetPosition(distance);
        bldrive.setTargetPosition(distance);
        brdrive.setTargetPosition(distance);

        //sets to a RUN_TO_POSITION mode
        fldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
        
        while (fldrive.isBusy() && frdrive.isBusy() && brdrive.isBusy() && bldrive.isBusy()) {
            //Wait until target position is reached
        }
        
        power = 0.0;
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    

//COMPUTER VISION PART


//when putting this in android studio, please unmargin everything. it's a little messy, and i can't unmargin on my chromebook.
    private static final String VUFORIA_KEY =
            "ARZSVjv/////AAABmbFVYMvEBEg1i14c/gYXU1QSxd7JrRov3nzCufUBnZaW+W4Brt9ma44NYvvCTPjEI6yKKvf+tHXE21sApVu52FWz2R7IXb3dUcnL8omF+I3/kH2tmCp+Ps+dygy16NJo2ZPwJe5RNTui0EJ9x6AvNz8z9UdZjMpCwTCWwZ57NW3iXWjFYRRU8sEb0Yb31uQUeN7612t8inQXvPVzTeJAW2A5u48T2alsmVw/Z9vKDmUATi1LgVNwuKa8krGlGBa2joNnt81JqOSuWotWTICmC6W6ZEqiYedlAcCTAZWSDmekIXa8m+espguXv16FzkcJAiq62FxMUFGw+aNKjnyCsubOoIRRwq2k1RZ8K9FveEUF";


    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        
        /**
        //We do not need this, as we are not having the motors do anything in this program, only CV detection.
        fldrive  = hardwareMap.get(DcMotor.class, "fl_drive");
        frdrive  = hardwareMap.get(DcMotor.class, "fr_drive");
        brdrive = hardwareMap.get(DcMotor.class, "br_drive");
        bldrive = hardwareMap.get(DcMotor.class, "bl_drive");
        //servo = hardwareMap.get(Servo.class, "arm");

        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);

        */
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                strafeleft(0.05, 1000);
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                      telemetry.addData("# Object Detected", updatedRecognitions.size());
                      // step through the list of recognitions and display boundary info.
                      int i = 0;
                      //actions to complete after recognized
                       //THIS IS WHERE SERVOS GO 
                       //REPLACE WITH SERVO CODE forward(0.1, 500);
                       //REPLACE WITH SERVO CODE grab();
                       //REPLACE WITH SERVO CODE backward(0.1, 500);
                       //REPLACE WITH SERVO CODE right(0.1, 200);
                      for (Recognition recognition : updatedRecognitions) {
                        telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                          recognition.getLeft(), recognition.getTop());
                        telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
                      }
                      telemetry.update();
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}
