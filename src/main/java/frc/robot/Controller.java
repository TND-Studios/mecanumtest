package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
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


    public void controllerInit()
    {

      
  
        // fL, bL, fR, bR
        wheels = new Wheels(33, 31, 30, 34);
        xcontroller = new XboxController(0);



    }
    
    public void UpdateTeleop() {
        double m = 0.5;
        double y = xcontroller.getY(Hand.kLeft) * -m;
        double x = xcontroller.getY(Hand.kLeft) * m;
        double r = xcontroller.getX(Hand.kRight) * m;
        // if (Math.abs(y) < 0.05) y = 0; 
        // if (Math.abs(x) < 0.05) x = 0; 
        // if (Math.abs(r) < 0.05) r = 0;
        wheels.drive(x, y, r);

    }    

    public void UpdateAutonomous() { 


    }


    





    public void inverseWheels() {
        wheels.inverse();
    }
}


