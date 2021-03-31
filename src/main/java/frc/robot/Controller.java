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

      
  
        // fL, fR, bL, bR
        wheels = new Wheels(6, 8, 3, 1);
        xcontroller = new XboxController(0);



    }
    
    public void UpdateTeleop() {

        wheels.drive(xcontroller.getY(Hand.kLeft), xcontroller.getX(Hand.kLeft), xcontroller.getX(Hand.kRight));

    }    

    public void UpdateAutonomous() { 


    }


    





    public void inverseWheels() {
        wheels.inverse();
    }
}


