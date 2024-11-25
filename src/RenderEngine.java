import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    private void drawControlsInfo(Graphics g) {
        g.setColor(Color.WHITE); // Couleur du texte
        g.setFont(new Font("Arial", Font.PLAIN, 20)); // Police et taille du texte

        String controlsInfo = "Contrôles :";
        String moveKeys = "ZQSD : Déplacement";
        String sprintKey = "Maj gauche : Sprint";

        // Dessiner les textes
        g.drawString(controlsInfo, 10, 20); // Coordonnées (10, 20)
        g.drawString(moveKeys, 10, 40);     // Coordonnées (10, 40)
        g.drawString(sprintKey, 10, 60);    // Coordonnées (10, 60)
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);
            if (renderObject instanceof DynamicSprite){         //Affiche les hitboxs
                ((SolidSprite)renderObject).drawHitbox(g);
            }
        }
        drawControlsInfo(g);
    }

    @Override
    public void update(){
        this.repaint();
    }



}
