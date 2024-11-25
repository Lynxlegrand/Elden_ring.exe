import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private Direction direction;
    public double speed;
    protected int timeBetweenFrame;
    private boolean isWalking;
    protected int spriteSheetNumberOfColumn;
    protected int PV;
    protected boolean isLiving;
    protected int range;
    protected int attack;

    // Constructeurs
    public DynamicSprite(BufferedImage image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
        this.speed = 0;
        this.timeBetweenFrame = 100;
        this.isWalking = true;
        this.spriteSheetNumberOfColumn = 10;
        this.direction = Direction.SOUTH;
        this.PV = 100;
        this.isLiving = true;
        this.range = 50;
        this.attack = 25;

    }

    // Setters
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void setCombat(int range, int attack) {
        this.range = range;
        this.attack = attack;
    }

    public void setPV(int PV) {
        if (this.isLiving == false){
            this.PV = 0;
        }
        this.PV = PV;
    }

    // Méthodes
    @Override
    public void draw(Graphics g) {
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);
        int attitude = direction.getFrameLineNumber();

        // Logique pour ajuster l'attitude
        if (attitude == 4 || attitude == 5) {
            attitude = 2;
        } else if (attitude == 6 || attitude == 7) {
            attitude = 0;
        }

        // Affichage de l'image
        if (speed != 0) {
            // Calcul des coordonnées source pour drawImage
            int srcX1 = index * (int) width;
            int srcY1 = attitude * (int) height;
            int srcX2 = (index + 1) * (int) width;
            int srcY2 = (attitude + 1) * (int) height;

            // Affichage de l'image avec drawImage
            g.drawImage(image, (int) x, (int) y, (int) x + (int) width, (int) y + (int) height, srcX1, srcY1, srcX2, srcY2, null);
        } else {
            // Calcul des coordonnées source pour drawImage lorsque le héros ne bouge pas
            int srcX1 = 0;
            int srcY1 = attitude * (int) height;
            int srcX2 = (int) width;
            int srcY2 = (attitude + 1) * (int) height;

            // Affichage de l'image avec drawImage
            g.drawImage(image, (int) x, (int) y, (int) x + (int) width, (int) y + (int) height, srcX1, srcY1, srcX2, srcY2, null);
        }
    }

    protected void move() {
        switch (direction) {
            case NORTH -> this.y -= speed;
            case SOUTH -> this.y += speed;
            case EAST -> this.x += speed;
            case WEST -> this.x -= speed;
            case NORTHEAST -> {
                this.x += speed / 2;
                this.y -= speed / 2;
            }
            case NORTHWEST -> {
                this.x -= speed / 2;
                this.y -= speed / 2;
            }
            case SOUTHEAST -> {
                this.x += speed / 2;
                this.y += speed / 2;
            }
            case SOUTHWEST -> {
                this.x -= speed / 2;
                this.y += speed / 2;
            }
        }
    }

    protected boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch (direction) {
            case EAST -> moved.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case WEST -> moved.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case NORTH -> moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case SOUTH -> moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case NORTHEAST -> moved.setRect(super.getHitBox().getX() + speed / Math.sqrt(2),
                    super.getHitBox().getY() - speed / Math.sqrt(2),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case NORTHWEST -> moved.setRect(super.getHitBox().getX() - speed / Math.sqrt(2),
                    super.getHitBox().getY() - speed / Math.sqrt(2),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case SOUTHEAST -> moved.setRect(super.getHitBox().getX() + speed / Math.sqrt(2),
                    super.getHitBox().getY() + speed / Math.sqrt(2),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case SOUTHWEST -> moved.setRect(super.getHitBox().getX() - speed / Math.sqrt(2),
                    super.getHitBox().getY() + speed / Math.sqrt(2),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
        }

        for (Sprite s : environment) {
            if (((s instanceof SolidSprite)|(s instanceof DynamicSprite)) && (s != this)) {
                if (((SolidSprite) s).intersect(moved)) {
                    return false;
                }
            }
        }
        return true;
    }

    protected void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
    }

    protected double distance(DynamicSprite dynamicSprite){
        return Math.sqrt(Math.pow(dynamicSprite.x - this.x,2)+Math.pow(dynamicSprite.y - this.y,2));
    }
    protected double distance(double x, double y){
        return Math.sqrt(Math.pow(x - this.x,2)+Math.pow(y - this.y,2));
    }



}
