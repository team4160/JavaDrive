package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.RaiseSubsystem;

public class SetRaiseMotorPercentage extends CommandBase{
    private final RaiseSubsystem raiseSubsystem;
    public boolean forward;
    public boolean stop;

    public SetRaiseMotorPercentage(RaiseSubsystem subsystem, boolean forward, boolean stop){
        raiseSubsystem = subsystem;
        addRequirements(raiseSubsystem);
        this.forward = forward;
        this.stop = stop;
    }

    @Override
    public void initialize(){
        if(stop){
            raiseSubsystem.setMotorPercentage(0);
            System.out.println(raiseSubsystem.getMotorPosition());
        }
        else{
            raiseSubsystem.setMotorPercentage(forward ? -Constants.RaiseConstants.RAISE_MOTOR_PERCENTAGE : Constants.RaiseConstants.RAISE_MOTOR_PERCENTAGE);
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}