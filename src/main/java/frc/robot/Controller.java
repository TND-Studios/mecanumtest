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
    //private int intakeVal;
    //    CONVEYOR CODE DELETED (????)

    private Intake intake;
    private int intakePort = 2;
    
    private int hookPort = 9;
    private int hookPortTwo = 10;
    private int shooterPortOne = 7;
    private int shooterPortTwo = 4;


    private Shooter shooter;

    private AHRS ahrs;
    private AnalogInput m_ultrasonic;

    private Boolean hookUp; 
    private HookExtension hook;

    private VisionComp visionComp;

    private DigitalInput dioOne, dioTwo;


    private ChallengeOne cOne;
    private ChallengeTwo cTwo;
    private ChallengeThree cThree;
    private ChallengeFour cFour;
    private ChallengeFive cFive;
    //-1 = testing, 1 = challenge one, 2 = challenge 2... 5 = challenge 5
    private int challengeNumber = -1; 



    public void controllerInit()
    {
        ahrs = new AHRS(SPI.Port.kMXP);
        m_ultrasonic = new AnalogInput(0);
      
  
        // fL, fR, bL, bR
        wheels = new Wheels(6,8,3,1);
        xcontroller = new XboxController(0);

        shooter = new Shooter(shooterPortOne, shooterPortTwo);
        visionComp = new VisionComp();


        dioOne = new DigitalInput(0);
        dioTwo = new DigitalInput(1);

        intake = new Intake(intakePort);

        hook = new HookExtension(hookPort, hookPortTwo);

        hookUp = false;

        cOne = new ChallengeOne(this);
        cTwo = new ChallengeTwo(this);
        cThree = new ChallengeThree(this);
        cFour = new ChallengeFour(this);
        cFive = new ChallengeFive(this);

    }
    
    public void UpdateTeleop() {
     // get values from the encoder. every 1 represents 1 rotation which is 2 pi r (6 pi) inches
        //double frontLeftRotations = wheels.getRotations("fL");
        //double backLeftRotations = wheels.getRotations("bR");

        //get values from the navx gyro to see which angle we are facing
        //double angleFacing = ahrs.getAngle();

        //get value from the ultrasonic sensor mounted in the front of the robot
        //double ultrasonicReading = getUltraSonicReading();




        //logic code below

        switch(challengeNumber) {
            case -1: break;
            case 1: cOne.UpdateTeleop(); break;
            case 2: cTwo.UpdateTeleop();  break;
            case 3: cThree.UpdateTeleop();  break;
            case 4: cFour.UpdateTeleop();  break;
            case 5: cFive.UpdateTeleop();  break;
        }
        // SmartDashboard.putNumber("ultra sonic reading", getUltraSonicReading());
        // SmartDashboard.putNumber("angle facing", getAngleFacing());
        
        // setDriveSpeed(xcontroller.getY(Hand.kLeft), xcontroller.getY(Hand.kRight));
        

        // //drum in 
        // if(xcontroller.getYButtonPressed())
        // {
        //     wheels.inverse();

        // }
        // if(xcontroller.getBButtonPressed())
        // {
        //     if(hookUp)
        //     {
        //         hook.lower();
        //         hookUp = false;
        //     }
        //     if(!hookUp)
        //     {
        //         hook.raise();
        //         hookUp = true;
        //     }
        // }


        // if (xcontroller.getBButtonReleased()) {
        //     hook.stop();
        // }
     
        

        
        // if(xcontroller.getBumperPressed(Hand.kLeft))
        // {
        //     intake.drive(0.85);
        // }
        // if(xcontroller.getBumperReleased(Hand.kLeft))
        // {
        //     intake.drive(0);
        // }
  
        // if(xcontroller.getBumperPressed(Hand.kRight))
        // {
        //     intake.drive(-0.85);
        // }
        // if(xcontroller.getBumperReleased(Hand.kRight))
        // {
        //     intake.drive(0);
        // }
        // //inverse wheels
     
        // //left trigger; revs up shooter
        // if(xcontroller.getTriggerAxis(Hand.kLeft)>.1)
        // {
        //     shooter.charge(0.8); //big wheel
        // } else {
        //     shooter.charge(0);
        // }
        // // right trigger; controls shooter
        
        // if(xcontroller.getTriggerAxis(Hand.kRight)>0)
        // {
        //     shooter.fire(-0.4); //blue wheel
        // } else {
        //     shooter.fire(0);
        // }



    }    

    public void UpdateAutonomous() {
        // code for all situations - DO NOT COMMENT OUT
        SmartDashboard.putNumber("Front Left Encoder Distance Travelled (ft)", getDistanceTravelled("fL"));
        SmartDashboard.putNumber("Back Right Encoder Distance Travelled (ft)", getDistanceTravelled("bR"));
        SmartDashboard.putNumber("Angle Facing From Gyro (degrees)", getAngleFacing());
        SmartDashboard.putNumber("Ultrasonic Reading (feet):", getUltraSonicReading() / 12);
        
        
        switch(challengeNumber) {
            case -1: break;
            case 1: cOne.UpdateAutonomous(); break;
            case 2: cTwo.UpdateAutonomous();  break;
            case 3: cThree.UpdateAutonomous();  break;
            case 4: cFour.UpdateAutonomous();  break;
            case 5: cFive.UpdateAutonomous();  break;
        }


    }

    public int AutonomousTurnCheck() {
        if (!dioOne.get() && !dioTwo.get()) {
            visionComp.compute(2); // straight
            return 2;
        }
        
        if (!dioOne.get()) {
            visionComp.compute(0); // left
            return 0;
        }
        if (!dioTwo.get()) {
            visionComp.compute(1); //  right
            return 1;
        } 

        if (dioOne.get() && dioTwo.get()) 
        {
            visionComp.compute(3); // none
            return 3;
        }

        return -1; 
    }

    public double getUltraSonicReading()
    {
        //                return value is in inches
        return m_ultrasonic.getValue()*0.125f/2.54f;
    }

    public double getAngleFacing() 
    {
        // return value is in degrees
        return ahrs.getAngle();
    }
    
    public double getDistanceTravelled(String pos) {
        // return             rotations * 2 * pi           * r * (1 foot / 12 inches)
        return wheels.getRotations(pos) * 2 * 3.1415926535 * 3  * 1 / 12;
    }

    public void setDriveSpeed(double l, double r) {
        wheels.drive(l, r);
    }

    public void setIntakeSpeed(double v) {
        intake.drive(v);
    }

    public void setShooterSpeed(double v) {
        shooter.charge(v);
    }

    public void setFeederSpeed(double v) {
        shooter.fire(v);
    }
   

}