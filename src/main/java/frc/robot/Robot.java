// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.HelicopterConstants;
import frc.robot.commands.SetCompressor;
import frc.robot.commands.SetIntake;
import frc.robot.commands.SetRaiseMotorPercentage;
import frc.robot.commands.SetRaiseMotorPosition;
import frc.robot.commands.SetSolenoid;
import frc.robot.commands.ToggleDriveMode;
import frc.robot.commands.ToggleManualArm;
import frc.robot.commands.ZeroRaiseMotors;
import frc.robot.subsystems.CompressorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RaiseSubsystem;

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

  TalonFX intake = new TalonFX(Constants.IntakeConstants.intakeID);

  public boolean manual = false;

  private Joystick driverController;
  private Joystick operatorController;
  private XboxController thirdController;

  private JoystickButton compressorShutoff;
  private JoystickButton fireButton;
  private JoystickButton retractButton;
  private JoystickButton compressorStart;
  private JoystickButton toggleDriveMode;
  private JoystickButton forwardIntakeButton;
  private JoystickButton reverseIntakeButton;
  private JoystickButton decreaseRaiseMotor;
  private JoystickButton increaseRaiseMotor;
  private JoystickButton increaseRaiseMotorPercentage;
  private JoystickButton decreaseRaiseMotorPercentage;
  private JoystickButton raiseMotorShutoff;
  private JoystickButton resetEncoders;
  private JoystickButton toggleManualArm;



  DriveSubsystem driveSubsystem;
  CompressorSubsystem compressorSubsystem;
  IntakeSubsystem intakeSubsystem;
  RaiseSubsystem raiseSubsystem;

  public void configureButtons(){
    //Driver Controller
    compressorStart = new JoystickButton(driverController, Constants.HelicopterConstants.B8);
    compressorStart.whenPressed(new SetCompressor(compressorSubsystem, true));

    compressorShutoff = new JoystickButton(driverController, Constants.HelicopterConstants.B9);
    compressorShutoff.whenPressed(new SetCompressor(compressorSubsystem, false));

    toggleDriveMode = new JoystickButton(driverController, Constants.HelicopterConstants.B6);
    toggleDriveMode.whenPressed(new ToggleDriveMode(driveSubsystem));

    resetEncoders = new JoystickButton(driverController, Constants.HelicopterConstants.B9);
    resetEncoders.whenPressed(new ZeroRaiseMotors(raiseSubsystem));

    //Operator Controller
    //Intake Subsystem
    forwardIntakeButton = new JoystickButton(operatorController, Constants.HelicopterConstants.triggerPort);
    forwardIntakeButton.whenPressed(new SetIntake(intakeSubsystem, true, true)).whenReleased(new SetIntake(intakeSubsystem, false, true));

    reverseIntakeButton = new JoystickButton(operatorController, Constants.HelicopterConstants.B2);
    reverseIntakeButton.whenPressed(new SetIntake(intakeSubsystem, true, false)).whenReleased(new SetIntake(intakeSubsystem, false, false));

    //Compressor Subsystem
    fireButton = new JoystickButton(operatorController, Constants.HelicopterConstants.B8);
    fireButton.whenPressed(new SetSolenoid(compressorSubsystem, true));

    retractButton = new JoystickButton(operatorController, Constants.HelicopterConstants.B9);
    retractButton.whenPressed(new SetSolenoid(compressorSubsystem, false));

    //Winch / Raise Motor Subsystem
    increaseRaiseMotor = new JoystickButton(operatorController, Constants.HelicopterConstants.B6);
    increaseRaiseMotor.whenPressed(new SetRaiseMotorPosition(raiseSubsystem, true));

    decreaseRaiseMotor = new JoystickButton(operatorController, Constants.HelicopterConstants.B7);
    decreaseRaiseMotor.whenPressed(new SetRaiseMotorPosition(raiseSubsystem, false));

    increaseRaiseMotorPercentage = new JoystickButton(operatorController, Constants.HelicopterConstants.B10);
    increaseRaiseMotorPercentage.whenPressed(new SetRaiseMotorPercentage(raiseSubsystem, true, false));

    decreaseRaiseMotorPercentage = new JoystickButton(operatorController, Constants.HelicopterConstants.B11);
    decreaseRaiseMotorPercentage.whenPressed(new SetRaiseMotorPercentage(raiseSubsystem, false, false));

    raiseMotorShutoff = new JoystickButton(operatorController, Constants.HelicopterConstants.B4);
    raiseMotorShutoff.whenPressed(new SetRaiseMotorPercentage(raiseSubsystem, true, true));

    toggleManualArm = new JoystickButton(operatorController, HelicopterConstants.B3);
    toggleManualArm.whenPressed(new ToggleManualArm(raiseSubsystem));

  }

  @Override
  public void robotInit() {
    driverController = new Joystick(Constants.Joysticks.driverJoystick);
    operatorController = new Joystick(Constants.Joysticks.operatorJoystick);
    thirdController = new XboxController(Constants.Joysticks.backupController);

    intakeSubsystem = new IntakeSubsystem();
    compressorSubsystem = new CompressorSubsystem();
    raiseSubsystem = new RaiseSubsystem();
    driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);

    configureButtons();
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
      //make a variable/function to toggle if the operator can control the arm or not
      if(raiseSubsystem.manual){
        raiseSubsystem.raiseMotor1.set(ControlMode.PercentOutput, operatorController.getY());
      }
    }
  }

}

