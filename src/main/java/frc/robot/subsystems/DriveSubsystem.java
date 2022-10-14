package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase{
    
    public DriveSubsystem(TalonFX frontLeft, TalonFX frontRight, TalonFX backLeft, TalonFX backRight){
        frontRight.configFactoryDefault();
        backRight.configFactoryDefault();
        frontLeft.configFactoryDefault();
        backLeft.configFactoryDefault();

        frontRight.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);

        backRight.set(ControlMode.Follower, frontRight.getDeviceID());
        backLeft.set(ControlMode.Follower, frontLeft.getDeviceID());

        frontRight.setInverted(true);
        backRight.setInverted(InvertType.FollowMaster);
        frontLeft.setInverted(false);
        backLeft.setInverted(InvertType.FollowMaster);
    }

    public void driveTank(double driveLeft, double driveRight, TalonFX frontLeft, TalonFX frontRight){
        driveLeft = Constants.Globals.isSlowModeActive ? driveLeft / Constants.Globals.slowModeFactor : driveLeft;
        driveRight = Constants.Globals.isSlowModeActive ? driveRight / Constants.Globals.slowModeFactor : driveRight;

        frontLeft.set(ControlMode.PercentOutput, driveLeft);
        frontRight.set(ControlMode.PercentOutput, driveRight);
    }

    public void driveArcade(double directionHorizontal, double throttle, TalonFX frontLeft, TalonFX frontRight){
        throttle = Constants.Globals.isSlowModeActive ? throttle / Constants.Globals.slowModeFactor : throttle;
        double turnSpeed = (
            Constants.DriveConstants.TURN_SENSITIVITY * directionHorizontal * directionHorizontal * directionHorizontal
            ) / (Constants.Globals.isSlowModeActive ? Constants.Globals.slowModeFactor : 1);

        double clampedLeft = (throttle + turnSpeed) > 1 ? 1 : ((throttle + turnSpeed) < -1 ? -1 : (throttle + turnSpeed));
        double clampedRight = (throttle - turnSpeed) > 1 ? 1 : ((throttle - turnSpeed) < -1 ? -1 : (throttle - turnSpeed));

        frontLeft.set(ControlMode.PercentOutput, clampedLeft);
        frontRight.set(ControlMode.PercentOutput, clampedRight);
    } 
    
}
