// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import static com.revrobotics.CANSparkMax.IdleMode.*;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  private final CANSparkMax leftRearDrive = new CANSparkMax(1, kBrushless);
  private final CANSparkMax leftFrontDrive = new CANSparkMax(4, kBrushless);
  private final CANSparkMax rightRearDrive = new CANSparkMax(2, kBrushless);
  private final CANSparkMax rightFrontDrive = new CANSparkMax(3, kBrushless);

  private double slowSpeedModifier = 0.4;
  private double normalSpeedModifier = 0.8;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    //makes motors never pull more than 50 amps at a time to prevent breaker trips and wheel slippage
    leftRearDrive.setSmartCurrentLimit(50);
    leftFrontDrive.setSmartCurrentLimit(50);
    rightRearDrive.setSmartCurrentLimit(50);
    rightFrontDrive.setSmartCurrentLimit(50);

    //sets motors to coast mode. use kBrake for brake mode
    leftRearDrive.setIdleMode(kCoast);
    leftFrontDrive.setIdleMode(kCoast);
    rightRearDrive.setIdleMode(kCoast);
    rightFrontDrive.setIdleMode(kCoast);

    //makes front motors follow rear. uncomment the true argument if the motors fight each other
    leftFrontDrive.follow(leftRearDrive/*, true*/);
    rightFrontDrive.follow(rightRearDrive/*, true*/);
  }


  /**
   * drives the robot and uses a button to modify the speed to slow
   * @param leftPower left input
   * @param rightPower right input
   * @param slow slow button
   */
  public void drive(double leftPower, double rightPower, boolean slow){
    if (slow == true){
      leftFrontDrive.set(leftPower * slowSpeedModifier);
      rightFrontDrive.set(rightPower * slowSpeedModifier);
    } else if (slow == false){
      leftFrontDrive.set(leftPower * normalSpeedModifier);
      rightFrontDrive.set(rightPower * normalSpeedModifier);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
