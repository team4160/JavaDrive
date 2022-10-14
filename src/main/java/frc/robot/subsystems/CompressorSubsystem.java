package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CompressorSubsystem extends SubsystemBase{
    Compressor p_compressor;
    Solenoid intake_piston;
    public CompressorSubsystem(){
        p_compressor = new Compressor(Constants.CompressorConstants.compressorID, PneumaticsModuleType.CTREPCM);
        intake_piston = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.CompressorConstants.solenoidChannel);
        p_compressor.disable();
    }

    public void fire(boolean on){
        intake_piston.set(on);
    }

    public void setCompressor(boolean enable){
        if(enable){p_compressor.enableDigital();} else{p_compressor.disable();}
    }
}
