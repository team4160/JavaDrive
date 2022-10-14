
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
public class IntakeSubsystem extends SubsystemBase {
    TalonSRX intake; 

    public IntakeSubsystem(){
        intake = new TalonSRX(Constants.IntakeConstants.intakeID);
        intake.setNeutralMode(NeutralMode.Brake);
        intake.setInverted(false);
        intake.clearStickyFaults();
        intake.configFactoryDefault();
    }

    public void setIntake(double percentOutput) {
        intake.set(ControlMode.PercentOutput, percentOutput);
    }
}
