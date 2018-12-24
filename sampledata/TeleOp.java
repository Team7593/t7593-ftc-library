package org.team7593.t7593_ftc_library;

import java.util.ArrayList;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "teleop")
public class TeleOp extends AutonStepOpmode {

    //any steps you put in here will run at the very beginning when you press play
    @Override
    public ArrayList<AutonStep> createAutonSteps() {
        return null;
    }

    @Override
    public void init(){
        super.init(); //wont create steps without this

        //your init code here
    }

    @Override
    public void loop(){
        super.loop(); //driver assists won't work with this

        //teleop code here such as actions related to the game pad etc.

        ArrayList<AutonStep> assists = new ArrayList<>();
        assists.add(new RunMotor(400));

        //if a is pressed run the driver assists
        if(gamepad1.a){
            this.newDriverAssist(assists);
            //or this.newDriverAssist(assists, true); if you want it to append instead of replace
        }
    }
}
