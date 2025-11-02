package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeListener ;
import java.beans.PropertyChangeEvent;


public class Horloge implements TimerChangeListener {

    String name;
    TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;

        // inscription à l'observable
        timerService.addTimeChangeListener(this);

        System.out.println("Horloge " + name + " initialisée !");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }


    public void afficherHeure() {
        if (timerService != null)
            System.out.println(name + " affiche " + timerService.getHeures()
                    + ":" + timerService.getMinutes()
                    + ":" + timerService.getSecondes());
    }
}
