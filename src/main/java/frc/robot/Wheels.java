package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
//import java.lang.Math;

public class Wheels {

    public enum DriveType { TANK, ARCADE }

    private WPI_TalonSRX frontLeft, backLeft, frontRight, backRight;
    private DifferentialDrive wheels;
    private boolean inverseState;

    public Wheels(int fL, int bL, int fR, int bR) {
        frontLeft = new WPI_TalonSRX(fL);
        backLeft = new WPI_TalonSRX(bL);
        frontRight = new WPI_TalonSRX(fR);
        backRight = new WPI_TalonSRX(bR);
       
        inverseState = false;
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

        wheels = new DifferentialDrive(new SpeedControllerGroup(frontLeft, backLeft), new SpeedControllerGroup(frontRight, backRight));
    }

    public double getRotations(String location) { 
        if (location == "fL") {
            double temp = frontLeft.getSelectedSensorPosition();
            double val = temp / 4096;
            return val;
        }
        if (location == "bR") {
            double temp = backRight.getSelectedSensorPosition();
            double val = temp / 4096;
            return val;
            
        }
        return 0;

    }

    public void resetRotations() {
        frontLeft.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);
    }
    // Negative speed turns wheels backwards
    public void drive(double leftSpeed, double rightSpeed) {

        leftSpeed *= 0.6;
        rightSpeed *= 0.6;
        if(inverseState)
        {
            //if inversState is true, reverse the speeds and call drive 
            frontLeft.set(leftSpeed);
            backLeft.set(leftSpeed);
            frontRight.set(-rightSpeed);
            backRight.set(-rightSpeed);

        }
        else
        {
            //makes sure speeds are positive
            //wheels.tankDrive(Math.abs(leftSpeed), Math.abs(rightSpeed));
            frontLeft.set(-leftSpeed);
            backLeft.set(-leftSpeed);
            frontRight.set(rightSpeed);
            backRight.set(rightSpeed);
       
        }
    }

    public void diffDrive(double speed1, double speed2, DriveType dType) {
        switch(dType) {
            case ARCADE:
                wheels.arcadeDrive(speed1 * 0.8, speed2 * 0.8); // speed scaling may need to be adjusted as we can't test in person right now
                break;
            case TANK:
                wheels.tankDrive(speed1 * 0.9, speed2 * 0.9);
                break;
        }
    }

    public void inverse()
    {
        inverseState = !inverseState;
    }       //reverses inverse everytime pressed
}
