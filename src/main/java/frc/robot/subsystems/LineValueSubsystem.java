package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class LineValueSubsystem extends Subsystem {

	private AnalogInput linereadleft;
	private AnalogInput linereadmiddle;
	private AnalogInput linereadright;
	public static double carpetColor = 3600;
	public static double stopColor = 3700;
	public static AverageValue = (linereadleft + linereadmiddle + linereadright)/3;
	public LineValueSubsystem() {
		super();
		linereadleft = new AnalogInput(RobotMap.LINE_TRACKER_LEFT);
		linereadmiddle = new AnalogInput(RobotMap.LINE_TRACKER_MIDDLE);
		linereadright = new AnalogInput(RobotMap.LINE_TRACKER_RIGHT);

	}

	public void reset() {

	}

	public double getCarpetColor() {
		return carpetColor;
	}
	public double getStopColor() {
		return stopColor;
	}

	public void initDefaultCommand() {
		SmartDashboard.putNumber("Starting linereadleft", linereadleft.getValue());
		SmartDashboard.putNumber("Starting linereadmiddle", linereadmiddle.getValue());
		SmartDashboard.putNumber("Starting linereadright", linereadright.getValue());
		
		reset();
	}

	public void writeValue() {
		SmartDashboard.putNumber("Starting linereadleft", linereadleft.getValue());
		SmartDashboard.putNumber("Starting linereadmiddle", linereadmiddle.getValue());
		SmartDashboard.putNumber("Starting linereadright", linereadright.getValue());
		
	}

	public double getLineValue(){
		double number = linereadleft.getValue();
		double number = linereadmiddle.getValue();
		double number = linereadright.getValue();

		return number;
	}
}
