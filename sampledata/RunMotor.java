package org.team7593.t7593_ftc_library;

import com.qualcomm.robotcore.hardware.DcMotor;

public class RunMotor implements AutonStep {

    int encoderVal;

    RunMotor(int val){
        encoderVal = val;
    }

    @Override
    public String name() {
        return "run motor";
    }

    @Override
    public void start(AutonStepOpmode opmode) {
        opmode.hardwareMap.get(DcMotor.class, "motor").setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //or if you have a hardware class opmode.robot.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop(AutonStepOpmode opmode) {
        opmode.hardwareMap.get(DcMotor.class, "motor").setPower(.5);
        //or if you have a hardware class opmode.robot.motor.setPower(.5);
    }

    @Override
    public boolean isDone(AutonStepOpmode opmode) {
        //you can replace opmode.hardwareMap.get(DcMotor.class, "motor") with opmode.robot.motor if you made a hardware class
        if(opmode.hardwareMap.get(DcMotor.class, "motor").getCurrentPosition() > encoderVal){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void updateTelemetry(AutonStepOpmode opmode) {

        //you can replace opmode.hardwareMap.get(DcMotor.class, "motor") with opmode.robot.motor if you made a hardware class
        opmode.telemetry.addData("curr encoder value", opmode.hardwareMap.get(DcMotor.class, "motor").getCurrentPosition());
    }
}
