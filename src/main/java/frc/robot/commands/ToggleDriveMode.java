package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class ToggleDriveMode extends CommandBase{
    private final DriveSubsystem driveSubsystem;

    public ToggleDriveMode(DriveSubsystem subsystem){
        driveSubsystem = subsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize(){
        Constants.Globals.driveMode = !Constants.Globals.driveMode;
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}