import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private int x,y, width, height, speed;
    private KeyManager keyManager;
    private Direction direction;
    private ArrayList<Bullet> bulletList;
    private long lastShootTime;
    private long shootCooldown;

    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        NORTH_EAST,
        NORTH_WEST,
        SOUTH_EAST,
        SOUTH_WEST
    }


    public Player(int x, int y, int width, int height, KeyManager keyManager){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = 2;
        this.keyManager = keyManager;
        this.direction = Direction.SOUTH;
        this.bulletList = new ArrayList<>();
        this.lastShootTime = System.currentTimeMillis();
        this.shootCooldown = 3;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x,y,width,height);
    }

    public void update(){
        int deltaX = 0;
        int deltaY = 0;

        if (keyManager.wPressed)
            deltaY -= speed;
        if (keyManager.sPressed)
            deltaY += speed;
        if (keyManager.aPressed)
            deltaX -= speed;
        if (keyManager.dPressed)
            deltaX += speed;

        // calculate diagonal
        if (deltaX != 0 && deltaY != 0) {
            // normalize so diagonal is not faster
            double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double factor = speed * Math.sqrt(speed) / length;
            deltaX = (int) (deltaX * factor);
            deltaY = (int) (deltaY * factor);
        }

        x += deltaX;
        y += deltaY;

        if (deltaX > 0) {
            if (deltaY > 0)
                direction = Direction.SOUTH_EAST;
            else if (deltaY < 0)
                direction = Direction.NORTH_EAST;
            else
                direction = Direction.EAST; }
        else if (deltaX < 0) {
            if (deltaY > 0)
                direction = Direction.SOUTH_WEST;
            else if (deltaY < 0)
                direction = Direction.NORTH_WEST;
            else
                direction = Direction.WEST; }
        else {
            if (deltaY > 0)
                direction = Direction.SOUTH;
            else if (deltaY < 0)
                direction = Direction.NORTH; }

        if (keyManager.spacePressed && System.currentTimeMillis() - lastShootTime >= shootCooldown) {
            shoot();
            lastShootTime = System.currentTimeMillis();
        }
    }


    public void shoot(){
        int bulletSize = 10;
        int bulletSpeed = 6;
        int bulletX = x + (width / 2) - (bulletSize / 2);
        int bulletY = y + (height / 2) - (bulletSize / 2);

        for (int i = 0; i < 300; i++) {
            Bullet bullet = new Bullet(bulletX, bulletY, bulletSize, bulletSpeed);
            bullet.setDirection(getDirection());
            bulletList.add(bullet);
            System.out.println(bulletList.size());
        }
    }

    public ArrayList<Bullet> getBulletList() {
        return bulletList;
    }

    public Direction getDirection() {
        return direction;
    }
}
