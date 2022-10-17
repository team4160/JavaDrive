package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class SetIntake extends CommandBase{
    private final IntakeSubsystem intakeSubsystem;
    public boolean status;

    public SetIntake(IntakeSubsystem subsystem, boolean status){
        intakeSubsystem = subsystem;
        addRequirements(intakeSubsystem);
        this.status = status;
    }

    @Override
    public void initialize(){
        intakeSubsystem.setIntake(status ? Constants.IntakeConstants.percentOutput : 0);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}