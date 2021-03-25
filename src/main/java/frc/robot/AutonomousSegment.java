package frc.robot;

public class AutonomousSegment {
    public double length;
    public double angleChange; //radians
    public double totalLength; 
    public double totalAngleChange;
    public double[] speeds; 
    // left, right, intake

    public boolean distanceGreater; 
    public boolean angleGreater; 

    public boolean ignoreAngle = false; 
    public AutonomousSegment(double len, double ang, AutonomousSegment prev, double[] s, boolean distanceGreater, boolean angleGreater) {
        length = len;
        angleChange = ang;

        if (prev != null) {
            totalLength = prev.totalLength + length;
            totalAngleChange = prev.angleChange + angleChange;
        } else {
            totalLength = length;
            totalAngleChange = angleChange;
        }
        

        speeds = s;

        this.distanceGreater = distanceGreater;
        this.angleGreater = angleGreater;

        if (speeds[0] == speeds[1]) ignoreAngle = true;
    }




    public void SetSpeeds(Controller c) {
        c.setDriveSpeed(speeds[0], speeds[1]);
        c.setIntakeSpeed(speeds[2]);
    }

    public Boolean IsSegmentComplete(double dist, double angle) {
        if (distanceGreater) {
            if (dist < totalLength) return false;
        } else {
            if (dist > totalLength) return false;
        }

        if (ignoreAngle) return true; 

        if (angleGreater) {
            if (angle < totalAngleChange) return false;
        } else {
            if (angle > totalAngleChange) return false;
        }

        return true; 
    }
}
