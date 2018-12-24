package org.team7593.t7593_ftc_library;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

@Autonomous (name = "test auton")
public class Auton extends AutonStepOpmode {

    //add all your steps here
    //the loop in the superclass AutonStepOpmode will automatically run these steps unless you override the loop()
    //method without calling super.loop()
    @Override
    public ArrayList<AutonStep> createAutonSteps() {
        ArrayList<AutonStep> steps = new ArrayList<>();
        steps.add(new RunMotor(500));

        return steps;
    }

    @Override
    public void init(){
        super.init(); //DONT forget your super.init

        hardwareMap.get(DcMotor.class, "motor").setPower(0);
        //or if you made a hardware class robot.motor.setPower(0);
    }
}
