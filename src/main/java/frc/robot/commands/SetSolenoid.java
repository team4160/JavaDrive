package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CompressorSubsystem;

public class SetSolenoid extends CommandBase{
    private final CompressorSubsystem compressorSubsystem;
    public boolean on;

    public SetSolenoid(CompressorSubsystem subsystem, boolean fire){
        subsystem.setCompressor(true);
        compressorSubsystem = subsystem;
        addRequirements(compressorSubsystem);
        this.on = fire;
    }

    @Override
    public void initialize(){
        compressorSubsystem.fire(on);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}