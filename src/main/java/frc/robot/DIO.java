package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
public class DIO{
    DigitalInput p;
    public DIO (int p){
        this.p = new DigitalInput(p);
        
    }
    public boolean getDIOstatus(){
        return p.get();
    }
}