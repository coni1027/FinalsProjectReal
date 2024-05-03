import java.awt.*;

public class Bullet {
    private int x, y, size, speed;
    private Player.Direction direction;

    public Bullet(int x, int y, int size, int speed){
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillOval(this.x,y,size,size);
    }

    public void setDirection(Player.Direction direction){
        this.direction = direction;
    }
    
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        if (this.direction == Player.Direction.NORTH)
            deltaY -= speed;
        else if (this.direction == Player.Direction.SOUTH)
            deltaY += speed;
        if (this.direction == Player.Direction.WEST)
            deltaX -= speed;
        else if (this.direction == Player.Direction.EAST)
            deltaX += speed;

        // calculate diagonal
        if (this.direction == Player.Direction.NORTH_EAST) {
            deltaX += speed / Math.sqrt(2);
            deltaY -= speed / Math.sqrt(2);
        } else if (this.direction == Player.Direction.NORTH_WEST) {
            deltaX -= speed / Math.sqrt(2);
            deltaY -= speed / Math.sqrt(2);;
        } else if (this.direction == Player.Direction.SOUTH_EAST) {
            deltaX += speed / Math.sqrt(2);;
            deltaY += speed / Math.sqrt(2);;
        } else if (this.direction == Player.Direction.SOUTH_WEST) {
            deltaX -= speed / Math.sqrt(2);;
            deltaY += speed / Math.sqrt(2);;
        }

        x += deltaX;
        y += deltaY;
    }
}
