import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameEngine implements Engine, KeyListener {
    private  DynamicSprite hero;
    private  DynamicSprite boss;
    private Set<Integer> pressedKeys = new HashSet<>();

    public GameEngine(DynamicSprite hero, DynamicSprite boss) {
        this.hero = hero;
        this.boss = boss; // Initialisation du boss
    }

    @Override
    public void update() {
        //Code du héro
        if (pressedKeys.contains(KeyEvent.VK_Z) && pressedKeys.contains(KeyEvent.VK_D)) {
            hero.speed = 5;
            hero.setDirection(Direction.NORTHEAST);
        } else if (pressedKeys.contains(KeyEvent.VK_Z) && pressedKeys.contains(KeyEvent.VK_Q)) {
            hero.speed = 5;
            hero.setDirection(Direction.NORTHWEST);
        } else if (pressedKeys.contains(KeyEvent.VK_S) && pressedKeys.contains(KeyEvent.VK_D)) {
            hero.speed = 5;
            hero.setDirection(Direction.SOUTHEAST);
        } else if (pressedKeys.contains(KeyEvent.VK_S) && pressedKeys.contains(KeyEvent.VK_Q)) {
            hero.speed = 5;
            hero.setDirection(Direction.SOUTHWEST);
        } else if (pressedKeys.contains(KeyEvent.VK_Z)) {
            hero.speed = 5;
            hero.setDirection(Direction.NORTH);
        } else if (pressedKeys.contains(KeyEvent.VK_S)) {
            hero.speed = 5;
            hero.setDirection(Direction.SOUTH);
        } else if (pressedKeys.contains(KeyEvent.VK_Q)) {
            hero.speed = 5;
            hero.setDirection(Direction.WEST);
        } else if (pressedKeys.contains(KeyEvent.VK_D)) {
            hero.speed = 5;
            hero.setDirection(Direction.EAST);
        } else {
            hero.speed = 0;
        }

        // Code du Boss

        boss.setDirection(Direction.SOUTH);
        boss.speed = 1;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Ajouter la touche enfoncée au set des touches pressées
        pressedKeys.add(e.getKeyCode());
        update(); // Mettre à jour la direction en fonction des touches enfoncées
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Retirer la touche relâchée du set des touches pressées
        pressedKeys.remove(e.getKeyCode());
        update(); // Mettre à jour la direction en fonction des touches restantes
    }
}
