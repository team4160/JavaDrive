package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RaiseSubsystem;

public class ZeroRaiseMotors extends CommandBase{
    private final RaiseSubsystem raiseSubsystem;

    public ZeroRaiseMotors(RaiseSubsystem subsystem){
        raiseSubsystem = subsystem;
        addRequirements(raiseSubsystem);
    }

    @Override
    public void initialize(){
        raiseSubsystem.resetRaiseMotorEncoders(0);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}