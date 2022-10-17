// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.HelicopterConstants;
import frc.robot.commands.SetCompressor;
import frc.robot.commands.SetIntake;
import frc.robot.commands.SetShooterAngle;
import frc.robot.commands.SetSolenoid;
import frc.robot.commands.ToggleDriveMode;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ToggleShooterMotor;
import frc.robot.subsystems.CompressorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it contains the code
 * necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  Constants constants;

  TalonFX frontLeft = new TalonFX(Constants.DriveConstants.frontLeftID);

  TalonFX backLeft = new TalonFX(Constants.DriveConstants.backLeftID);

  TalonFX frontRight = new TalonFX(Constants.DriveConstants.frontRightID);
  TalonFX backRight = new TalonFX(Constants.DriveConstants.backRightID);

  TalonFX winchMotor = new TalonFX(Constants.WinchConstants.winchID);

  TalonFX intake = new TalonFX(Constants.IntakeConstants.intakeID);

  private Joystick driverController;
  private Joystick operatorController;
  private XboxController thirdController;

  private JoystickButton intakeButton;
  private JoystickButton compressorShutoff;
  private JoystickButton fireButton;
  private JoystickButton retractButton;
  private JoystickButton compressorStart;
  private JoystickButton toggleIntake;
  private JoystickButton toggleDriveMode;
  private JoystickButton increaseShooterAngle;
  private JoystickButton decreaseShooterAngle;
  private JoystickButton toggleShooterMotor;

  DriveSubsystem driveSubsystem;
  CompressorSubsystem compressorSubsystem;
  IntakeSubsystem intakeSubsystem;
  ShooterSubsystem shooterSubsystem;

  public void configureButtons(){
    intakeButton = new JoystickButton(operatorController, Constants.HelicopterConstants.triggerPort);
    intakeButton.whenPressed(new SetIntake(intakeSubsystem, true)).whenReleased(new SetIntake(intakeSubsystem, false));

    compressorStart = new JoystickButton(operatorController, Constants.HelicopterConstants.B8);
    compressorStart.whenPressed(new SetCompressor(compressorSubsystem, true));

    compressorShutoff = new JoystickButton(operatorController, Constants.HelicopterConstants.B9);
    compressorShutoff.whenPressed(new SetCompressor(compressorSubsystem, false));

    fireButton = new JoystickButton(operatorController, Constants.HelicopterConstants.B4);
    fireButton.whenPressed(new SetSolenoid(compressorSubsystem, true));

    retractButton = new JoystickButton(operatorController, Constants.HelicopterConstants.B5);
    retractButton.whenPressed(new SetSolenoid(compressorSubsystem, false));

    toggleIntake = new JoystickButton(operatorController, Constants.HelicopterConstants.B2);
    toggleIntake.whenPressed(new ToggleIntake(intakeSubsystem));

    toggleDriveMode = new JoystickButton(driverController, Constants.HelicopterConstants.B6);
    toggleDriveMode.whenPressed(new ToggleDriveMode(driveSubsystem));

    //increaseShooterAngle = new JoystickButton(operatorController, Constants.HelicopterConstants.B4);
    //increaseShooterAngle.whenPressed(new SetShooterAngle(shooterSubsystem, true));

    //decreaseShooterAngle = new JoystickButton(operatorController, Constants.HelicopterConstants.B5);
    //decreaseShooterAngle.whenPressed(new SetShooterAngle(shooterSubsystem, false));

    //toggleShooterMotor = new JoystickButton(operatorController, HelicopterConstants.B6);
    //toggleShooterMotor.whenPressed(new ToggleShooterMotor(shooterSubsystem));
  }

  @Override
  public void robotInit() {
    driverController = new Joystick(Constants.Joysticks.driverJoystick);
    operatorController = new Joystick(Constants.Joysticks.operatorJoystick);
    thirdController = new XboxController(Constants.Joysticks.backupController);

    intakeSubsystem = new IntakeSubsystem();
    compressorSubsystem = new CompressorSubsystem();
    //shooterSubsystem = new ShooterSubsystem();
    driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);

    configureButtons();
    
    winchMotor.setNeutralMode(NeutralMode.Brake);
    winchMotor.setInverted(true);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    if(Constants.Globals.driveMode){
      driveSubsystem.driveTank(driverController.getY(), operatorController.getY(), frontLeft, frontRight);
    }
    else{
      driveSubsystem.driveArcade(driverController.getX(), driverController.getY(), frontLeft, frontRight);
      winchMotor.set(ControlMode.PercentOutput, operatorController.getY());
      //intake.set(ControlMode.PercentOutput, operatorController.getY());
    }
  }

}

