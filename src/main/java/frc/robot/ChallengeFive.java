package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

//uncomment ones you need
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.SPI;
// import com.kauailabs.navx.frc.AHRS;
// import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.Servo;
// import edu.wpi.first.wpilibj.Timer;
//a

//faiufoahuifahifhe
public class ChallengeFive {

    //put constants here
    
    private static double ZERO = 0;

    //put variables here
    private static double negativeIntake = -0.4;
    private static double positiveIntake = 0.4;
    private static double highFlywheelSpeed = 0.7;
    private static double flywheelSpeed = 0.5;
    
    //this is the main controller class (which we have written before), which will call the update methods below. This is NOT an Xbox Controller
    private Controller controller;   
    
    /* 
        Instructions on how to get data from the robot:
            controller.getAngleFacing() returns a double indicating which angle (in degrees) the robot is facing
            controller.getUltraSonicReading() returns a double indicating the ultrasonic sensor reading in INCHES
            controller.getDistanceTravelled("fL") returns a double indicating the distance travelled by the front left wheel in FEET

        Instruction on how to control the robot:
            controller.setDriveSpeed(double leftSpeed, double rightSpeed) sets the speed of the right and left wheels. Only the wheels in the back will be powered. 
                                                                          The values MUST be between (0.9 and -0.9) (negative is backwards).
            controller.setIntakeSpeed(double speed) sets the speed of the intake. Values should be between (-0.9 and 0.9) 
//Dillon note: set a button to toggle the speed of intake? The button will not move the intake, the joystick will
            The shooter works in two seperate parts. 
            First, there is a big and strong wheel which actually launches the balls. This wheel must be charged up for 0.5 - 1.5 seconds before shooting.
            controller.setShooterSpeed(double speed) controls the speed of the big shooter wheel. This value should be between (0.4 and 0.9).             
            Second, there is a small, rubber wheel, which feeds the balls into the shooter.
            controller.setFeederSpeed(double speed) controls its speed. In order to feed balls, set it to -0.4 (FEEDER_SPEED). Otherwise, set it to ZERO.

    */

    //this is the xbox controller which will be plugged into the drive laptop to control the robot
    private XboxController xController;

    /* 
        The documentation for the xbox controller can be found here: https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/XboxController.html
        Example implementation can be seen in Controller.java
    
    */



    // Methods

    //constructor
    public ChallengeFive(Controller cIn) {
        controller = cIn; 
        xController = controller.xcontroller;
    }


    /*
        Unfortunately, you cannot rapidly test your code since we don't have a physical robot to expirement with. 
        Please try to make sure your code works in theory. 
        If there are any ways in which it could go wrong, please write comments detailing how. 

    
        You should consider putting as much useful information as possible on the smart dashboard.
        Documentation can be found here: https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/smartdashboard/SmartDashboard.html
        For example: SmartDashboard.putNumber("ultra sonic reading", controller.getUltraSonicReading());

    */

    //this is called every 20 milliseconds during autonomous
    public void UpdateAutonomous() {
        
    }


    //this is called every 20 milliseconds during teleop (manually controlled by human with xboxcontroller)
    public void UpdateTeleop() {

       
           
    
//this if statement does not descirbe what the controls will be, I have this as an example to use as reference.
       


    

         if(xController.getXButtonPressed())
         {
             //will activate inverse. I need the other part of the code done first however.
         }
         if(xController.getYButtonPressed())
         {
           flywheelSpeed = highFlywheelSpeed;
           //sets the flywheel speed to the "high value"
         }
         

        if(xController.getY(Hand.kRight) > 0)
        {
            controller.setIntakeSpeed(positiveIntake);
            //Intake goes up. I'll fix this if I reversed the controls by accident.
        }
        else
        {
              controller.setIntakeSpeed(ZERO);
              //to set value to zero when no one is touching joystick.
        }
        if(xController.getY(Hand.kRight) < 0)
        {
            controller.setIntakeSpeed(negativeIntake);
             //Intake goes down. I'll fix this if I reversed the controls by accident.
        }
        else
        {
              controller.setIntakeSpeed(ZERO);
              //to set value to zero when no one is touching joystick.
        }


    }
 


}
