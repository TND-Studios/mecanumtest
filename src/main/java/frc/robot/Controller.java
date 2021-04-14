package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class Controller{
    // THE VALUES FOR THE DOUBLES BELOW NEED TO BE CONFIGURED MANUALLY

    //main controller
    public XboxController xcontroller;
    private Wheels wheels;
    private AHRS navx; 
    private double offset; 
    private Timer t; 
    private boolean isOffsetting = false;
    private boolean accountForAngle = false; 

    public void controllerInit()
    {

      
  
        // fL, bL, fR, bR
        wheels = new Wheels(33, 31, 30, 34);
        xcontroller = new XboxController(0);
        navx = new AHRS(SPI.Port.kMXP);
        t = new Timer();
        t.start();


    }
    
    public void UpdateTeleop() {
        double m = 0.5;
        offset =  Math.round(t.get() * 3000.0) / 1000.0 / 1000.0; 
        if (!isOffsetting) offset = 0; 

        double forwardSpeed = xcontroller.getY(Hand.kLeft) * -m;
        double rightSpeed = xcontroller.getX(Hand.kLeft) * m;
        double rotationSpeed = xcontroller.getX(Hand.kRight) * m * 0.5;

        if (Math.abs(forwardSpeed) < 0.075) { forwardSpeed = 0; }
        if (Math.abs(rightSpeed) < 0.075) { rightSpeed = 0; }
        if (Math.abs(rotationSpeed) < 0.075) { rotationSpeed = 0; }

        SmartDashboard.putNumber("forward speed", forwardSpeed);
        SmartDashboard.putNumber("right speed", rightSpeed);
        SmartDashboard.putNumber("rotation speed", rotationSpeed);
        SmartDashboard.putNumber("navx reading", navx.getAngle() + offset);
        SmartDashboard.putNumber("navx offset", offset);

        if (accountForAngle) wheels.drive(forwardSpeed, rightSpeed, rotationSpeed, navx.getAngle() + offset);
        else wheels.drive(forwardSpeed, rightSpeed, rotationSpeed);

        //calibrate
        if (xcontroller.getAButtonReleased()) { navx.calibrate(); navx.zeroYaw(); t.reset(); t.start(); offset = 0; }

        //no more auto adjustment
        if (xcontroller.getBButtonReleased()) { isOffsetting = !isOffsetting;}
        
        //dont use gyro at all to drive
        if (xcontroller.getXButtonReleased()) { accountForAngle = !accountForAngle; }

        SmartDashboard.putBoolean("navx calibrating?", navx.isCalibrating());
        SmartDashboard.putBoolean("auto offset?", isOffsetting);
        SmartDashboard.putBoolean("accounting for angle?", accountForAngle);
    }    

    public void UpdateAutonomous() { 


    }


    





    public void inverseWheels() {
        wheels.inverse();
    }
}


