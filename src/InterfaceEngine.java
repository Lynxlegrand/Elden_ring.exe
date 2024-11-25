import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InterfaceEngine implements KeyListener {

    private JFrame displayZoneFrame;

    public InterfaceEngine(JFrame displayZoneFrame) {
        this.displayZoneFrame = displayZoneFrame;
        configureKeyBindings();
    }

    private void configureKeyBindings() {
        displayZoneFrame.addKeyListener(this);
    }

    private void toggleFullscreenMode() {
        if (displayZoneFrame.isUndecorated()) {
            displayZoneFrame.dispose(); // nécessaire pour appliquer le changement
            displayZoneFrame.setUndecorated(false);
            displayZoneFrame.setVisible(true); // rendre visible à nouveau après modification
        } else {
            displayZoneFrame.dispose();
            displayZoneFrame.setUndecorated(true);
            displayZoneFrame.setVisible(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Vérifie si la touche Échap est pressée
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            toggleFullscreenMode();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Implémentation vide ou ajouter une logique si nécessaire
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implémentation vide ou ajouter une logique si nécessaire
    }
}
