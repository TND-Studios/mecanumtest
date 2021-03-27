package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
public class Test {
    
    private Controller controller;   
    private XboxController xController;

    private WPI_TalonFX talon;
    private double shooterSpeed = 0;

    private int port = 0; 
    public Test(Controller cIn) {
        controller = cIn; 
        xController = controller.xcontroller;
        talon = new WPI_TalonFX(port);
        talon.setNeutralMode(NeutralMode.Brake);
    }

    public void UpdateTeleop() {
        /* CONTROL SCHEMA
        | X - Raise motor speed
        | Y - Lower motor speed 
        | Left Trigger - If pressed, set speed to motor speed, otherwise set to 0
        | A - Reconstruct talon with higher port
        | B - Reconstruct talon with lower port
        | Information displayed - Encoder position, absolute position, and velocity. Gyro X Y Z velocity. Gyro, mag, and fused reading. Talon port. Current speed. 
        */
        SmartDashboard.putNumber("getIntegratedSensorPosition", talon.getSensorCollection().getIntegratedSensorPosition());
        SmartDashboard.putNumber("getIntegratedSensorAbsolutePosition", talon.getSensorCollection().getIntegratedSensorAbsolutePosition());
        SmartDashboard.putNumber("getIntegratedSensorVelocity", talon.getSensorCollection().getIntegratedSensorVelocity());
        SmartDashboard.putNumber("gyro velocity X", controller.ahrs.getVelocityX());
        SmartDashboard.putNumber("gyro velocity Y", controller.ahrs.getVelocityY());
        SmartDashboard.putNumber("gyro velocity Z", controller.ahrs.getVelocityZ());
        SmartDashboard.putNumber("fused heading", controller.ahrs.getFusedHeading());
        SmartDashboard.putNumber("gyro heading", controller.ahrs.getAngle());
        SmartDashboard.putNumber("mag reading", controller.ahrs.getCompassHeading());
        SmartDashboard.putNumber("Talon Port", port);
        SmartDashboard.putNumber("Current Speed", shooterSpeed);
        // Use X/Y buttons to increase/decrease launcher speed
        if(xController.getXButtonPressed()) {
            shooterSpeed += 0.1;
        }
        if(xController.getYButtonPressed()) {
            shooterSpeed -= 0.1;
        }

        // Use triggers to control shooter
        // Left trigger 
        if(xController.getTriggerAxis(Hand.kLeft) > 0) {
            talon.set(shooterSpeed);
        }
        else {
            talon.set(0);
        }

        if(xController.getAButtonPressed()) {
            port++; 
            talon = new WPI_TalonFX(port);
            talon.setNeutralMode(NeutralMode.Brake);
        }
        if(xController.getBButtonPressed()) {
            port--; 
            talon = new WPI_TalonFX(Math.abs(port));
            talon.setNeutralMode(NeutralMode.Brake);
       }
    }

}
