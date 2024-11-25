import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameEngine implements Engine, KeyListener {
    private  DynamicSprite hero;
    protected  PNJ boss;
    private Set<Integer> pressedKeys = new HashSet<>();

    public GameEngine(DynamicSprite hero, PNJ boss) {
        this.hero = hero;
        this.boss = boss; // Initialisation du boss
    }

    @Override
    public void update() {
        //Code du héro
        int slow = 3;
        int fast = 6;
        int speed = pressedKeys.contains(KeyEvent.VK_SHIFT) ? fast : slow; // Vitesse en fonction de Shift
        if (pressedKeys.contains(KeyEvent.VK_Z) && pressedKeys.contains(KeyEvent.VK_D)) {
            hero.speed = speed;
            hero.setDirection(Direction.NORTHEAST);
        } else if (pressedKeys.contains(KeyEvent.VK_Z) && pressedKeys.contains(KeyEvent.VK_Q)) {
            hero.speed = speed;
            hero.setDirection(Direction.NORTHWEST);
        } else if (pressedKeys.contains(KeyEvent.VK_S) && pressedKeys.contains(KeyEvent.VK_D)) {
            hero.speed = speed;
            hero.setDirection(Direction.SOUTHEAST);
        } else if (pressedKeys.contains(KeyEvent.VK_S) && pressedKeys.contains(KeyEvent.VK_Q)) {
            hero.speed = speed;
            hero.setDirection(Direction.SOUTHWEST);
        } else if (pressedKeys.contains(KeyEvent.VK_Z)) {
            hero.speed = speed;
            hero.setDirection(Direction.NORTH);
        } else if (pressedKeys.contains(KeyEvent.VK_S)) {
            hero.speed = speed;
            hero.setDirection(Direction.SOUTH);
        } else if (pressedKeys.contains(KeyEvent.VK_Q)) {
            hero.speed = speed;
            hero.setDirection(Direction.WEST);
        } else if (pressedKeys.contains(KeyEvent.VK_D)) {
            hero.speed = speed;
            hero.setDirection(Direction.EAST);
        } else {
            hero.speed = 0;
        }

        // Code du Boss
        boss.iSeeHero(hero);
        if (boss.fight==true){
            boss.speed = 3;
            double d = boss.distance(hero);
            boss.moveTo(hero.x,hero.y);
        }
        else {
            boss.speed = 0;
        }

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
