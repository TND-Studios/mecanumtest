package frc.robot;


import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.SpeedController;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix.motorcontrol.can.*;
//import java.lang.Math;

public class Wheels {


    private WPI_TalonSRX frontLeft;
    private WPI_TalonSRX backLeft;
    private WPI_TalonSRX frontRight;
    private WPI_TalonSRX backRight;
    private MecanumDrive wheels;
    private boolean inverseState;

    public Wheels(int fL, int bL, int fR, int bR) {
        //initialize motor objects
        frontLeft = new WPI_TalonSRX(fL);
        backLeft = new WPI_TalonSRX(bL);
        frontRight = new WPI_TalonSRX(fR);
        backRight = new WPI_TalonSRX(bR);
        //so motors brake when speed is 0
        frontRight.setNeutralMode(NeutralMode.Brake);
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        
        inverseState = false;
        wheels = new MecanumDrive(
            frontLeft, 
            backLeft, 
            frontRight,
            backLeft);
    }

    // Negative speed turns wheels backwards
    public void drive(double xSpeed, double ySpeed, double rotationSpeed) {

        wheels.driveCartesian(ySpeed, xSpeed, rotationSpeed);
    }

   

    public void inverse()
    {
        inverseState = !inverseState;
    }       //reverses inverse everytime pressed
}
