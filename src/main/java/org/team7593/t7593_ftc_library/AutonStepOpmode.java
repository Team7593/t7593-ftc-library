package org.team7593.t7593_ftc_library;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class AutonStepOpmode extends OpMode {

    //an array list to hold your auton steps
    private ArrayList<AutonStep> steps = null;

    private int currentStep = -1;

    //protected Hardware robot = new Hardware();

    /*
    Uncomment this line ^ and add your own robot using your own hardware class
        Here's a link to a tutorial on how to make your own hardware class
        https://www.youtube.com/watch?v=iXucv7pgUn0

        Or if you don't want to make a hardware class, you can call hardwareMap.get in your auton step
    */

    //hashmap to hold info that you want to share between steps
    private HashMap<String, Object> sharedInfo = new HashMap<>();

    /** This function will need to be implemented in your auton and teleop classes that extend this opmode
     * (as it is abstract). This method is where you create an array list that contains the steps you'd like to use.
     *
     * */
    public abstract ArrayList<AutonStep> createAutonSteps();

    /**
     * Do not forget to call super.init() if you're overriding this method in your code
     */
    public void init() {

        //this.robot.init(hardwareMap);

        /* Uncomment this ^ once you've added your hardware above (if you did)
           this will initialize your robot and find all your hardware
        */

        //getting the steps from your teleop or auton
        steps = this.createAutonSteps();

        if (steps != null) {
            telemetry.log().add("INIT: Steps are there");
        }else{
            telemetry.log().add("INIT: Steps are NOT there");
        }
    }

    /**
     * Do not forget to call super.loop() if you are overriding this method
     */
    public void loop() {

        //telemetry that shows what is in shared information
        for (String name : sharedInfo.keySet()) {
            telemetry.addLine().addData(name, sharedInfo.get(name));
        }

        //no more steps
        if (steps == null) {
            telemetry.addLine().addData("Step:", "No more Steps to run");
            return;
        }

        //start the steps (from 0)
        if (currentStep == -1) {
            currentStep = 0;
            steps.get(currentStep).start(this);
        }


        steps.get(currentStep).loop(this);

        //telemetry that shows the current step and gets the telemetry in the step
        telemetry.addLine().addData("Step:", steps.get(currentStep).name());
        steps.get(currentStep).updateTelemetry(this);

        //check if the steps are done
        if (steps.get(currentStep).isDone(this)) {
            //telemetry that will show what steps are done (this telemetry will persist unlike regular .addData)
            telemetry.log().add("Step %s is done.", steps.get(currentStep).name());

            //go to the next step since the current one is done
            currentStep += 1;

            //making sure there are more steps to run; if there aren't we're resetting the current step to -1
            if (currentStep >= steps.size()) {
                steps = null;
                currentStep = -1;
            } else {
                //starting the next step
                steps.get(currentStep).start(this);
            }

        }
    }

    /**
     * This method stops the steps that are in queue
     */
    public void stopSteps(){
        steps = null;
        currentStep = -1;
    }

    /**
     * This method will put information into the sharedInfo hashmap
     * Simply give what you want to share a name and pass the object
     *
     * @param name - the name of whatever object you want to share between steps e.g. a color from a sensor
     * @param value - the object e.g. data from a sensor
     */
    public void shareInfo(String name, Object value) {
        this.sharedInfo.put(name, value);
    }

    /**
     *
     * method to get info and use it
     *
     * @param name - the name you gave it when you shared it using the shareInfo method
     * @return returns whatever object you put in it
     */
    public Object getSharedInfo(String name) {
        return this.sharedInfo.get(name);
    }

    /**
     * you can call this method in your teleop or autonsteps
     * because append is false, it will write over any steps that are in queue
     * instead of adding to them.
     *
     * @param driverAssistSteps - Steps to be added as driver assists
     *
     * */
    public void newDriverAssist(ArrayList<AutonStep> driverAssistSteps)
    {
        this.newDriverAssist(driverAssistSteps, false);
    }

    /**
     * this method creates new driver assists (drivers assists is another name for autonsteps basically)
     *  if append is true it will add steps to what's already in queue
     *
     * @param append - whether to add to an existing list of steps or replace them
     *  */
    public void newDriverAssist(ArrayList<AutonStep> driverAssistSteps, boolean append){
        if (append) {
            if(steps != null) steps.addAll(driverAssistSteps);
            else {
                steps = driverAssistSteps;
                currentStep = -1;
            }
        } else {
            steps = driverAssistSteps;
            currentStep = -1;
        }
    }
}
