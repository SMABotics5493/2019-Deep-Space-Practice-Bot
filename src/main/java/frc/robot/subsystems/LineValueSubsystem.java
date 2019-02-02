package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class LineValueSubsystem extends Subsystem {

	private AnalogInput ai;
	public static double carpetColor = 3600;
	public static double stopColor = 3700;

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
		SmartDashboard.putNumber("Starting ai", ai.getValue());
		
		reset();
	}

	public void writeValue() {
		SmartDashboard.putNumber("Running ai", ai.getValue());

	}

	public double getLineValue(){
		double number = ai.getValue();
		return number;
	}
}
