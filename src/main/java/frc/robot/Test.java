package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
public class Test {
    
    private Controller controller;   
    private XboxController xController;

    private WPI_TalonFX talon;
    private double shooterSpeed = 0;

    public Test(Controller cIn) {
        controller = cIn; 
        xController = controller.xcontroller;
        talon = new WPI_TalonFX(0);
        talon.setNeutralMode(NeutralMode.Brake);
    }

    public void UpdateTeleop() {
        SmartDashboard.putNumber("getIntegratedSensorPosition", talon.getSensorCollection().getIntegratedSensorPosition());
        SmartDashboard.putNumber("getIntegratedSensorAbsolutePosition", talon.getSensorCollection().getIntegratedSensorAbsolutePosition());
        SmartDashboard.putNumber("getIntegratedSensorVelocity", talon.getSensorCollection().getIntegratedSensorVelocity());
        SmartDashboard.putNumber("gyro velocity X", controller.ahrs.getVelocityX());
        SmartDashboard.putNumber("gyro velocity Y", controller.ahrs.getVelocityY());
        SmartDashboard.putNumber("gyro velocity Z", controller.ahrs.getVelocityZ());
        SmartDashboard.putNumber("fused heading", controller.ahrs.getFusedHeading());
        SmartDashboard.putNumber("gyro heading", controller.ahrs.getAngle());
        SmartDashboard.putNumber("mag reading", controller.ahrs.getCompassHeading());
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
    }

}
