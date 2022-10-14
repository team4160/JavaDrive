package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class SetIntake extends CommandBase{
    private final IntakeSubsystem intakeSubsystem;
    public boolean status;
    public double direction;
    public boolean forward;

    public SetIntake(IntakeSubsystem subsystem, boolean status, boolean forward){
        intakeSubsystem = subsystem;
        addRequirements(intakeSubsystem);
        this.status = status;
        this.forward = forward;
    }

    @Override
    public void initialize(){
        intakeSubsystem.setIntake(status ? (forward ? Constants.IntakeConstants.percentOutput : -Constants.IntakeConstants.percentOutput) : 0);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}