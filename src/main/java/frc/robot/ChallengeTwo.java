package frc.robot;

import javax.crypto.Cipher;

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



public class ChallengeTwo {

    //put constants here
    private static final double FEEDER_SPEED = -0.4;
    private static final double ZERO = 0;

    private static final double MAX_DRIVE_SPEED = 0.8;
    private static final double INNER_TURN_DRIVE_SPEED = 0; 
    private static final double OUTER_TURN_DRIVE_SPEED = 0; 

    private static final double CIRCLE_RADIUS = 20.0;
    private static final double DISTANCE_PIVOT_TO_WHEEL = 12;
    //put variables here

    private int segment = 1; 
    private int path = 1;
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
    public ChallengeTwo(Controller cIn, int p) {
        controller = cIn; 
        path = p;
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
        // Display useful information
        SmartDashboard.putNumber("Current Segment", segment);
        SmartDashboard.putNumber("Total distance travelled (in)", getDistanceTravelled());
        SmartDashboard.putNumber("Angle Facing (deg)", controller.getAngleFacing());
        if (path == 1) {
            if (segment == 1) {
                controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED);
            } else if (segment == 2) {
                controller.setDriveSpeed(OUTER_TURN_DRIVE_SPEED, INNER_TURN_DRIVE_SPEED); //clockwise
            } else if (segment == 3) {
                controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED);
            } else if (segment == 4) {
                controller.setDriveSpeed(INNER_TURN_DRIVE_SPEED, OUTER_TURN_DRIVE_SPEED); //counter clockwise
            } else if (segment == 5) {
                controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED);
            } else if (segment == 6) {
                controller.setDriveSpeed(INNER_TURN_DRIVE_SPEED, OUTER_TURN_DRIVE_SPEED); //counter clockwise
            } else if (segment == 7) {
                controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED);
            } else if (segment == 8) {
                controller.setDriveSpeed(0, 0);
            }
        } else if (path == 2) {
            if      (segment == 1) { controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED); }
            else if (segment == 2) { controller.setDriveSpeed(OUTER_TURN_DRIVE_SPEED, INNER_TURN_DRIVE_SPEED); } // clockwise
            else if (segment == 3) { controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED); }
            else if (segment == 4) { controller.setDriveSpeed(OUTER_TURN_DRIVE_SPEED, INNER_TURN_DRIVE_SPEED); } // clockwise
            else if (segment == 5) { controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED); }
            else if (segment == 6) { controller.setDriveSpeed(INNER_TURN_DRIVE_SPEED, OUTER_TURN_DRIVE_SPEED); } //ccw
            else if (segment == 7) { controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED);}
            else if (segment == 8) { controller.setDriveSpeed(OUTER_TURN_DRIVE_SPEED, INNER_TURN_DRIVE_SPEED); }
            else if (segment == 9) { controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED);}
            else if (segment == 10) { controller.setDriveSpeed(OUTER_TURN_DRIVE_SPEED, INNER_TURN_DRIVE_SPEED); }
            else if (segment == 11) { controller.setDriveSpeed(MAX_DRIVE_SPEED, MAX_DRIVE_SPEED); } 
            else if (segment == 12) { controller.setDriveSpeed(0, 0); }
        } else if (path == 3) {
            if      (segment == 1) { }
            else if (segment == 2) { }
            else if (segment == 3) { }
            else if (segment == 4) { }
            else if (segment == 5) { }
            else if (segment == 6) { }
            else if (segment == 7) { }
            else if (segment == 8) { }
            else if (segment == 9) { }
            else if (segment == 10) { }
        } else {
            SmartDashboard.putBoolean("SOMETHING WENT WRONG", true);
        }
        
        checkSegmentIncrement();
    }


    //this is called every 20 milliseconds during teleop (manually controlled by human with xboxcontroller)
    public void UpdateTeleop() {

        /* 
        
            Explain your controls here, so the driver knows what to do. 
                > Drive the robot using the left and right joysticks to control the speed of their respective wheels. 
        
        */

        // This is a very basic way of driving using two joysticks. Think about other ways the robot can be driven. Which would be the easiest and/or most efficient for the driver?
        controller.setDriveSpeed(xController.getY(Hand.kLeft), xController.getY(Hand.kRight));


    }

    private void checkSegmentIncrement() {
        if (path == 1) {
                 if (segment == 1 && getDistanceTravelled() >= getIntendedDistanceOne()) segment++;
            else if (segment == 2 && getDistanceTravelled() >= getIntendedDistanceTwo() && getAngleFacing() >= getIntendedAngleTwo()) segment++;
            else if (segment == 3 && getDistanceTravelled() >= getIntendedDistanceThree()) segment++;
            else if (segment == 4 && getDistanceTravelled() >= getIntendedDistanceFour() &&  getAngleFacing() <= getIntendedAngleFour()) segment++;
            else if (segment == 5 && getDistanceTravelled() >= getIntendedDistanceFive()) segment++;
            else if (segment == 6 && getDistanceTravelled() >= getIntendedDistanceSix() && getAngleFacing() <= getIntendedAngleSix()) segment++;
            else if (segment == 7 && getDistanceTravelled() >= getIntendedDistanceSeven()) segment++;
        } else if (path == 2) {
                 if (segment == 1 && getDistanceTravelled() >= getIntendedDistanceOne()) segment++;
            else if (segment == 2 && getDistanceTravelled() >= getIntendedDistanceTwo() && getAngleFacing() <= getIntendedAngleTwo()) segment++;
            else if (segment == 3 && getDistanceTravelled() >= getIntendedDistanceThree()) segment++;
            else if (segment == 4 && getDistanceTravelled() >= getIntendedDistanceFour() && getAngleFacing() <= getIntendedAngleFour()) segment++;
            else if (segment == 5 && getDistanceTravelled() >= getIntendedDistanceFive()) segment++;
            else if (segment == 6 && getDistanceTravelled() >= getIntendedDistanceSix() && getAngleFacing() >= getIntendedAngleSix()) segment++;
            else if (segment == 7 && getDistanceTravelled() >= getIntendedDistanceSeven()) segment++;
            else if (segment == 8 && getDistanceTravelled() >= getIntendedDistanceEight() && getAngleFacing() <= getIntendedAngleEight()) segment++;
            else if (segment == 9 && getDistanceTravelled() >= getIntendedDistanceNine()) segment++;
            else if (segment == 10 && getDistanceTravelled() >= getIntendedDistanceTen() && getAngleFacing() <= getIntendedAngleTen()) segment++;
            else if (segment == 11 && getDistanceTravelled() >= getIntendedDistanceEleven()) segment++; 
        } else if (path == 3) {
                 if (segment == 1) segment++;
            else if (segment == 2) segment++;
            else if (segment == 3) segment++;
            else if (segment == 4) segment++;
            else if (segment == 5) segment++;
            else if (segment == 6) segment++;
            else if (segment == 7) segment++;
            else if (segment == 8) segment++;
            else if (segment == 9) segment++;
            else if (segment == 10) segment++;
        }
    }

    private double getIntendedDistanceOne() {
        if (path == 1) return 90;
        if (path == 2) {
            double theta = Math.asin( (20.0 + CIRCLE_RADIUS) / 60.0  );
            return ( 60.0 * CIRCLE_RADIUS / ( 20.0 + CIRCLE_RADIUS ) * Math.cos( theta )) + ( ( 1200 ) / (20 + CIRCLE_RADIUS) ) / Math.cos(theta) + 16 * Math.tan(theta);

        } else {
            return 0.0;
        }
    }

    private double getIntendedAngleTwo() {
        if (path == 1) return (2.50 * Math.PI) - Math.acos(CIRCLE_RADIUS / (15.0 * Math.sqrt(13.0))) - Math.atan(60.0/90.0);
        if (path == 2)  {
            return  -1 * Math.asin( (20.0 + CIRCLE_RADIUS) / 60.0  );
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceTwo() {
        if (path == 1) return getIntendedDistanceOne() + getIntendedAngleTwo() * (CIRCLE_RADIUS + DISTANCE_PIVOT_TO_WHEEL);
        if (path == 2)  {
            return getIntendedDistanceOne() + Math.asin( (20.0 + CIRCLE_RADIUS) / 60.0  ) * (CIRCLE_RADIUS + DISTANCE_PIVOT_TO_WHEEL);
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceThree() {
        if (path == 1) return getIntendedDistanceTwo() + (2.0 * Math.sqrt(2925 - Math.pow(CIRCLE_RADIUS, 2.0)));
        if (path == 2)  {
            return getIntendedDistanceTwo() + 120.0;
        } else {
            return 0.0;
        }
    }

    private double getIntendedAngleFour() {
        if (path == 1) return 0.25 * Math.PI;
        if (path == 2)  {
            return getIntendedAngleTwo() - Math.asin( CIRCLE_RADIUS / 30.0  );
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceFour() {
        if (path == 1) return getIntendedDistanceThree() + (- Math.PI/4 + getIntendedAngleTwo()) * (CIRCLE_RADIUS - DISTANCE_PIVOT_TO_WHEEL);
        if (path == 2)  {
            return getIntendedDistanceThree() + Math.asin( CIRCLE_RADIUS / 30.0  ) * (CIRCLE_RADIUS + DISTANCE_PIVOT_TO_WHEEL);
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceFive() {
        if (path == 1) return getIntendedDistanceFour() + 60.0 * Math.sqrt(2);
        if (path == 2)  {
            return getIntendedDistanceFour() + 2 * Math.sqrt(900.0 - Math.pow(CIRCLE_RADIUS, 2));
        } else {
            return 0.0;
        }
    }

    private double getIntendedAngleSix() {
        if (path == 1) return -1 * Math.PI;
        if (path == 2)  {
            return getIntendedAngleFour() + 2 * Math.PI - 2 * Math.acos( CIRCLE_RADIUS / 30.0  );
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceSix() {
        if (path == 1) return getIntendedDistanceFive() + (1.25 * Math.PI * (CIRCLE_RADIUS - DISTANCE_PIVOT_TO_WHEEL));
        if (path == 2)  {
            return getIntendedDistanceFive() + (2 * Math.PI - 2 * Math.acos( CIRCLE_RADIUS / 30.0  )) * (CIRCLE_RADIUS - DISTANCE_PIVOT_TO_WHEEL);
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceSeven() {
        //803 
        if (path == 1) return getIntendedDistanceSix() + 260; 
        if (path == 2)  {
            return getIntendedDistanceSix() + 2 * Math.sqrt(900.0 - Math.pow(CIRCLE_RADIUS, 2));
        } else {
            return 0.0;
        }
    }

    private double getIntendedAngleEight() {
        if (path == 2) {
            return getIntendedAngleSix()  - Math.asin( CIRCLE_RADIUS / 30.0  );
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceEight() {
        if (path == 2)  {
            return getIntendedDistanceSeven() + Math.asin( CIRCLE_RADIUS / 30.0  ) * (CIRCLE_RADIUS + DISTANCE_PIVOT_TO_WHEEL);
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceNine() {
        if (path == 2) {
            return getIntendedDistanceEight() + 120.0;
        } else {
            return 0.0;
        }
    }

    private double getIntendedAngleTen() {
        if (path == 2) {
            return getIntendedAngleEight()  - Math.asin( (20.0 + CIRCLE_RADIUS) / 60.0  );
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceTen() {
        if (path == 2) {
            return getIntendedDistanceNine() + Math.asin( (20.0 + CIRCLE_RADIUS) / 60.0  ) * (CIRCLE_RADIUS + DISTANCE_PIVOT_TO_WHEEL);
        } else {
            return 0.0;
        }
    }

    private double getIntendedDistanceEleven() {
        if (path == 2) {
            return getIntendedDistanceTen() + getIntendedDistanceOne();
        } else {
            return 0.0;
        }
    }

    private double getDistanceTravelled() {
        return controller.getDistanceTravelled("fL") * 12.0;
    }

    private double getAngleFacing() {
        if (path == 1) {
            return -1 * Math.toRadians(controller.getAngleFacing());
        } else {
            return Math.toRadians(controller.getAngleFacing());
        }
        
    }
}
