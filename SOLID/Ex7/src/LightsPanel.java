public class LightsPanel implements PowerOnOff , Brightness , SmartClassroomDevice {
    @Override public void powerOn() { /* always on */ }
    @Override public void powerOff() { System.out.println("Lights OFF"); }

    @Override public void setBrightness(int pct) { System.out.println("Lights set to " + pct + "%"); }
    
}
