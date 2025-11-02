package org.emp.gl.gui;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HorlogeGraphique extends JFrame implements TimerChangeListener {

    private final JLabel heureLabel = new JLabel("", SwingConstants.CENTER);
    private final TimerService timerService;

    public HorlogeGraphique(TimerService timerService) {
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);

        setTitle("Horloge graphique");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(250, 240, 255));

        heureLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        heureLabel.setForeground(new Color(120, 60, 160));

        add(heureLabel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }

    private void afficherHeure() {
        String heureTexte = String.format("%02d:%02d:%02d",
                timerService.getHeures(),
                timerService.getMinutes(),
                timerService.getSecondes());
        heureLabel.setText(heureTexte);
    }
}
