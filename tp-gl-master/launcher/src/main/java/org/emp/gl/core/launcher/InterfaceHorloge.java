package org.emp.gl.core.launcher;



import org.emp.gl.gui.HorlogeGraphique;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

public class InterfaceHorloge {
    public static void main(String[] args) {
        TimerService service = new DummyTimeServiceImpl();
        new HorlogeGraphique(service);
    }
}
