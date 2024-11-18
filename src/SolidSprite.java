import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite {
    private double hitboxWidth;  // largeur de la hitbox
    private double hitboxHeight; // hauteur de la hitbox

    public SolidSprite(BufferedImage image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
        this.hitboxWidth = width;  // par défaut, la hitbox est la même que le sprite
        this.hitboxHeight = height;
    }

    // Méthodes pour ajuster la hitbox sans modifier les objets
    public void setHitboxSize(double width, double height) {
        this.hitboxWidth = width;
        this.hitboxHeight = height;
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x, y, hitboxWidth, hitboxHeight); // Utilise les dimensions de la hitbox
    }

    public boolean intersect(Rectangle2D.Double hitBox) {
        return this.getHitBox().intersects(hitBox);
    }

    public void drawHitbox(Graphics g){
        g.setColor(Color.RED);
        g.drawRect((int)x,(int)y,(int)hitboxWidth,(int)hitboxHeight);
    }
}
