package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.RaiseSubsystem;

public class SetRaiseMotorPosition extends CommandBase{
    private final RaiseSubsystem raiseSubsystem;
    public boolean raise;

    public SetRaiseMotorPosition(RaiseSubsystem subsystem, boolean raise){
        raiseSubsystem = subsystem;
        addRequirements(raiseSubsystem);
        this.raise = raise;
    }

    @Override
    public void initialize(){
        raiseSubsystem.setMotorPosition(raise ? Constants.RaiseConstants.RAISE_MAX : Constants.RaiseConstants.RAISE_MIN);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}