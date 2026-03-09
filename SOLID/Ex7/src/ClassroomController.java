public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        SmartClassroomDevice pj = reg.getFirstOfType("Projector");
        ((PowerOnOff)pj).powerOn();
        ((Input)(pj)).connectInput("HDMI-1");

        SmartClassroomDevice lights = reg.getFirstOfType("LightsPanel");
        ((Brightness)lights).setBrightness(60);

        SmartClassroomDevice ac = reg.getFirstOfType("AirConditioner");
        ((Temperature)ac).setTemperatureC(24);

        SmartClassroomDevice scan = reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + ((Attendance)scan).scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");

        ((PowerOnOff) reg.getFirstOfType("Projector")).powerOff();
        ((PowerOnOff) reg.getFirstOfType("LightsPanel")).powerOff();
        ((PowerOnOff) reg.getFirstOfType("AirConditioner")).powerOff();
    }
}
