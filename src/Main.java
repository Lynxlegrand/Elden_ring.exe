import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception{
        // Paramètres de la fenêtre
        displayZoneFrame = new JFrame("Elden_ring.exe");
        displayZoneFrame.setSize(1366,768);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        displayZoneFrame.getContentPane().setBackground(Color.BLACK);
        displayZoneFrame.setIconImage(ImageIO.read(new File("./img/icon.png")));
        displayZoneFrame.setUndecorated(true);

        //Création du personnage
        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")),200,300, 48,50);
        hero.setHitboxSize(48,50); // Réglage de la hitbox

        //Création du boss
        PNJ boss = new PNJ(ImageIO.read(new File("./img/boss.png")),1000,300, 212,140);
        boss.setHitboxSize(212,140); // Réglage de la hitbox

        //Initialisation des moteurs
        RenderEngine renderEngine = new RenderEngine(displayZoneFrame);
        PhysicEngine physicEngine = new PhysicEngine();
        GameEngine gameEngine = new GameEngine(hero, boss);
        SoundEngine soundEngine = new SoundEngine();
        InterfaceEngine interfaceEngine = new InterfaceEngine(displayZoneFrame);



        //Initialisation des timers
        Timer renderTimer = new Timer(10,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(10,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(10,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        //Render Engine
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        //Création du menu


        //Sons/musique
        soundEngine.playSound("./sound/music.wav");



        //Création du niveau
        Playground level = new Playground("./data/level1.txt");
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);

        renderEngine.addToRenderList(boss);
        physicEngine.addToMovingSpriteList(boss);

        // Environnement
        physicEngine.setEnvironment(level.getSolidSpriteList());
        physicEngine.addToEnvironmentList(boss); // ajoute le boss à l'environnement


        displayZoneFrame.addKeyListener(gameEngine);


    }

    public static void main (String[] args) throws Exception {
        // write your code here
        Main main = new Main();

    }
}
