package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CompressorSubsystem;

public class SetCompressor extends CommandBase{
    private final CompressorSubsystem compressorSubsystem;
    public boolean enable;

    public SetCompressor(CompressorSubsystem subsystem, boolean enable){
        compressorSubsystem = subsystem;
        addRequirements(compressorSubsystem);
        this.enable = enable;
    }

    @Override
    public void initialize(){
        compressorSubsystem.setCompressor(enable);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
