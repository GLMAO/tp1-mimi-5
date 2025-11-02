package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {

    private String name;
    private int valeurRestante;
    private TimerService timerService;
    private boolean termine = false;

    public CompteARebours(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.valeurRestante = valeurInitiale;
        this.timerService = timerService;

        // Abonnement
        timerService.addTimeChangeListener(this);

        System.out.println("CompteARebours " + name + " démarré à " + valeurRestante);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // On agit uniquement sur changement de SECONDE
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName()) && !termine) {
            valeurRestante--;

            if (valeurRestante > 0) {
                System.out.println(name + " : " + valeurRestante);
            } else {
                System.out.println(name + " terminé !");
                termine = true;

                // Désabonnement du timer
                timerService.removeTimeChangeListener(this);
            }
        }
    }
}
