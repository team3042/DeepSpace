/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3042.lib.pixy2api.Pixy2;
import org.usfirst.frc.team3042.lib.pixy2api.Pixy2.LinkType;
import org.usfirst.frc.team3042.lib.pixy2api.Pixy2Line.Vector;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LineTracker extends Subsystem {

  private Pixy2 pixy;
  
  public LineTracker()
  {
      SmartDashboard.putString("linePrinter", "Starting...");

      this.pixy = Pixy2.createInstance(LinkType.SPI);
      pixy.init(1);
      
      // change to the line_tracking program.  Note, changeProg can use partial strings, so for example,
      // you can change to the line_tracking program by calling changeProg("line") instead of the whole
      // string changeProg("line_tracking")
      this.pixy.changeProg("line".toCharArray());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /** Command Controls ******************************************************/

  public void printLines()
  {        
      this.pixy.getLine().getAllFeatures();

      if (this.pixy.getLine().getVectors() != null)  {
         for (Vector vector : this.pixy.getLine().getVectors()) {
             SmartDashboard.putString("linePrinter", vector.toString());
         }
      }
  }

  public void followLine() {

    // Follows the code from https://github.com/charmedlabs/pixy2/blob/master/src/host/arduino/libraries/Pixy2/examples/line_zumo_demo/line_zumo_demo.ino
    // Will need to put put our own looping control in here since the Arduino example code
    // loops already.  The key will be determining when to stop
  }
}