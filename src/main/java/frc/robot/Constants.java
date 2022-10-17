package frc.robot;

public final class Constants {
    public static final class Globals{
        public static boolean isSlowModeActive = true;
        public static boolean isShooterMotorActive = false;
        public static boolean isIntakeActive = false;
        public static boolean driveMode = false; //true is tank, false is arcade
        public static double shooterMotorPercentOutput = 0.4;
    }

    public static final class Joysticks{
        public static final int driverJoystick = 0;
        public static final int externalKeyboard = 1;
        public static final int operatorJoystick = 2;
        public static final int backupController = 3;
    }

    public static final class DriveConstants{
        public static final int frontLeftID = 0;
        public static final int frontRightID = 3;
        public static final int backLeftID = 2;
        public static final int backRightID = 4;

        public static final double TURN_SENSITIVITY = 0.55;
        public static final double DEFAULT_TIME_TO_FULL_SPEED = 1.452;
        public static final double SLOW_MODE_PERCENTAGE = 1.4;
    }

    public static final class IntakeConstants{
        public static final int intakeID = 8;
        public static double percentOutput = .8;
    }

    public static final class WinchConstants{
        public static final int winchID = 10;
    }

    public static final class ShooterConstants{
        public static final int SHOOTER_TRACK_CONTROLLER_CANIFIER_ID = 8;
        public static final int SHOOTER_TRACK_CONTROLLER_MOTOR_ID = 8;
        public static final int SHOOTER_MOTOR_ID = 10;
        public static final int SHOOTER_TRACK_MAX_ANGLE = 90;
        public static final int SHOOTER_TRACK_MIN_ANGLE = 0;
        public static final int SHOOTER_TRACK_ANGLE_INCREMENT_DECREMENT = 15;
    
        public static final int SHOOTER_ZERO_DEGREE_ANGLE = 400;
        public static final int SHOOTER_NINTY_DEGREE_ANGLE = 1900;
        public static final double CANIFIER_UNITS_PER_DEGREE = 16.666667;
        public static final int FEEDBACK_DEVICE_TIMEOUT_MS = 30;

    }

    public static final class CompressorConstants{
        public static final int compressorID = 0;
        public static final int solenoidChannel = 6;
    }

    public static final class HelicopterConstants{
        public static final int triggerPort = 1;
        public static final int B2 = 2;
        public static final int B3 = 3;
        public static final int B4 = 4;
        public static final int B5 = 5;
        public static final int B6 = 6;
        public static final int B7 = 7;
        public static final int B8 = 8;
        public static final int B9 = 9;
        public static final int B10 = 10;
        public static final int B11 = 11;
    }

    public static final class PS4Constants{
        public static final int Square = 1;
        public static final int Cross = 2;
        public static final int Circle = 3;
        public static final int Triangle = 4;
        public static final int L1 = 5;
        public static final int R1 = 6;
        public static final int L2 = 7;
        public static final int R2 = 8;
        public static final int Share = 9;
        public static final int Options = 10;
        public static final int L3 = 11;
        public static final int R3 = 12;
        public static final int PS = 13;
        public static final int Pad = 14;
        public static final int PSLeftStickRight = 0;
        public static final int PSLeftStickDown = 1;
        public static final int PSRightStickRight = 2;
        public static final int L2In = 3; // start at -1 and goes to 1 ((L2In)+1)/2 0 to 100%
        public static final int R2In = 4; // start at -1 and goes to 1
        public static final int PSRightStickDown = 5;
        // POV up is 0 none is -1
    }

    public static final class XBoxConstants{
        public static final int A = 1;
        public static final int B = 2;
        public static final int X = 3;
        public static final int Y = 4;
        public static final int LB = 5;
        public static final int RB = 6;
        public static final int View = 7;
        public static final int Menu = 8;
        public static final int LS = 9;
        public static final int RS = 10;
        public static final int XBLeftStickRight = 0;
        public static final int XBLeftStickDown = 1;
        public static final int LIn = 2; // 0 to 1
        public static final int RIn = 3; // 0 to 1
        public static final int XBRightStickRight = 4;
        public static final int XBRightStickDown = 5;
        // POV up is 0 none is -1
    }
}
