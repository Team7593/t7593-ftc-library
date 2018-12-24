package org.team7593.t7593_ftc_library;

/**
 * Created by NDHSB-Emma on 9/26/17.
 *
 *The basis of our Auton Steps
 */

public interface AutonStep {

    //use this to return the name of the class that you're using. (eg. DriveX)
    public String name();

    //this is where you would do initial actions such as setting the mode of a motor
    public void start(AutonStepOpmode opmode);

    //this is the loop where all the code is executed (this code will be repeated until the isdone condition is
    //satisfied such as motor.setPower(.5)
    public void loop(AutonStepOpmode opmode);

    /*
    the condition that ends the loop

    for example:
    if (time > 1){
        return true;
    }else{
        return false;
    }
    */
    public boolean isDone(AutonStepOpmode opmode);

    /* add any telemetry here that you want to see during the duration of the step
    you can also add telemetry in the start, loop, and isdone methods but it's easier if you put all your telemetry
    in one place */
    public void updateTelemetry(AutonStepOpmode opmode); //method that will update the telemetry

}

