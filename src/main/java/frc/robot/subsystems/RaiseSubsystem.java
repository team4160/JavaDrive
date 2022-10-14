package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RaiseSubsystem extends SubsystemBase{
    public TalonFX raiseMotor1;
    public TalonFX raiseMotor2;
    boolean fineTuningMode;
    public boolean manual;

    public RaiseSubsystem(){
        raiseMotor1 = new TalonFX(Constants.RaiseConstants.RAISE_1_MOTOR_ID);
        raiseMotor2 = new TalonFX(Constants.RaiseConstants.RAISE_2_MOTOR_ID);

        manual = false;

        fineTuningMode = true;

        raiseMotor1.setNeutralMode(NeutralMode.Brake);
        raiseMotor1.setInverted(false);
        raiseMotor1.configFactoryDefault();

        raiseMotor2.setNeutralMode(NeutralMode.Brake);
        raiseMotor2.configFactoryDefault();

        raiseMotor2.set(ControlMode.Follower, raiseMotor1.getDeviceID());
        raiseMotor2.setInverted(InvertType.FollowMaster);

        raiseMotor1.config_kP(0, 0.005, Constants.ShooterConstants.FEEDBACK_DEVICE_TIMEOUT_MS);
        raiseMotor1.config_kI(0, 0.00000025, Constants.ShooterConstants.FEEDBACK_DEVICE_TIMEOUT_MS);
        raiseMotor1.config_kD(0, 2, Constants.ShooterConstants.FEEDBACK_DEVICE_TIMEOUT_MS);
        raiseMotor1.config_kF(0, 0, Constants.ShooterConstants.FEEDBACK_DEVICE_TIMEOUT_MS);
        raiseMotor1.configClearPositionOnLimitR(true, Constants.ShooterConstants.FEEDBACK_DEVICE_TIMEOUT_MS);
    }

    public void resetRaiseMotorEncoders(int pidIndex) {
        raiseMotor1.setSelectedSensorPosition(0, pidIndex, Constants.ShooterConstants.FEEDBACK_DEVICE_TIMEOUT_MS);
        raiseMotor2.setSelectedSensorPosition(0, pidIndex, Constants.ShooterConstants.FEEDBACK_DEVICE_TIMEOUT_MS);
    }

    public double getMotorPosition(){
        return raiseMotor1.getSelectedSensorPosition();
    }

    public void setMotorPercentage(double percentage) {
        raiseMotor1.set(ControlMode.PercentOutput, fineTuningMode ? percentage * 0.2 : percentage);
    }

    public void setMotorPosition(double position) {
        raiseMotor1.set(ControlMode.Position, position);
    }

    public void toggleFineTuningMode() {
        fineTuningMode = !fineTuningMode;
    }
}
