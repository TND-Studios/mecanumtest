package frc.robot;


import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.SpeedController;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.*;
//import java.lang.Math;

public class Wheels {


    private WPI_TalonFX frontLeft;
    private WPI_TalonFX backLeft;
    private WPI_TalonFX frontRight;
    private WPI_TalonFX backRight;
    private MecanumDrive wheels;
    private boolean inverseState;

    public Wheels(int fL, int bL, int fR, int bR) {
        //initialize motor objects
        frontLeft = new WPI_TalonFX(fL);
        backLeft = new WPI_TalonFX(bL);
        frontRight = new WPI_TalonFX(fR);
        backRight = new WPI_TalonFX(bR);
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
            backRight);

        wheels.setDeadband(0.025);
        
    }

    // Negative speed turns wheels backwards
    public void drive(double forwardSpeed, double rightSpeed, double rotationSpeed) {

        wheels.driveCartesian(rightSpeed, forwardSpeed, rotationSpeed);
        SmartDashboard.putNumber("front left", frontLeft.get());
        SmartDashboard.putNumber("back left", backLeft.get());
        SmartDashboard.putNumber("front right", frontRight.get());
        SmartDashboard.putNumber("back right", backRight.get());
    }

   

    public void inverse()
    {
        inverseState = !inverseState;
    }       //reverses inverse everytime pressed
}
