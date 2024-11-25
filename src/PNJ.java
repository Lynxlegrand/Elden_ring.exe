import java.awt.image.BufferedImage;

public class PNJ extends DynamicSprite{
    protected double champDeVision;
    protected boolean fight;

    // Constructeurs
    public PNJ (BufferedImage image, double x, double y, double width, double height){
        super(image, x, y, width, height);
        this.champDeVision = 250;
        this.fight = false;
        this.spriteSheetNumberOfColumn = 6;
        this.timeBetweenFrame = 300;
        this.attack = 50;
        this.range = 10;
    }

    //Setters
    public void setChampDeVision(double champDeVision) {
        this.champDeVision = champDeVision;
    }

    // Méthodes

    public void iSeeHero(DynamicSprite hero){
        double d = this.distance(hero);
        if (d<this.champDeVision){
            this.fight = true;
        }
        else{
            this.fight = false;
        }
    }

    // Déplace l'entité vers une autre position
    public void moveTo(double targetX, double targetY) {
        double dx = targetX - this.x;
        double dy = targetY - this.y;
        double distance =this.distance(targetX,targetY);

        // Normaliser le vecteur de déplacement
        if (distance > 70) {
            switch ((dx == 0 ? 4 : (dx > 0 ? 1 : 0)) + (dy == 0 ? 8 : (dy > 0 ? 2 : 0))) {
                case 4: // dx = 0, dy < 0
                    this.setDirection(Direction.NORTH);
                    break;
                case 6: // dx > 0, dy = 0
                    this.setDirection(Direction.EAST);
                    break;
                case 8: // dx = 0, dy > 0
                    this.setDirection(Direction.SOUTH);
                    break;
                case 10: // dx < 0, dy = 0
                    this.setDirection(Direction.WEST);
                    break;
                case 0: // dx < 0, dy < 0
                    this.setDirection(Direction.NORTHWEST);
                    break;
                case 1: // dx > 0, dy < 0
                    this.setDirection(Direction.NORTHEAST);
                    break;
                case 2: // dx < 0, dy > 0
                    this.setDirection(Direction.SOUTHWEST);
                    break;
                case 3: // dx > 0, dy > 0
                    this.setDirection(Direction.SOUTHEAST);
                    break;
            }
        }
        else{
            this.setDirection(Direction.EAST);

        }
    }


}
