package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RaiseSubsystem;

public class ToggleManualArm extends CommandBase{
    private final RaiseSubsystem raiseSubsystem;

    public ToggleManualArm(RaiseSubsystem subsystem){
        raiseSubsystem = subsystem;
        addRequirements(raiseSubsystem);
    }

    @Override
    public void initialize(){
        raiseSubsystem.manual = !raiseSubsystem.manual;
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}