import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JComponent implements Runnable {

    private Player p1;
    private Thread gameThread;
    private KeyManager keyManager;
    private int FPS;
    private Image img;

    public GameCanvas() {
        keyManager = new KeyManager();
        p1 = new Player(100,100,50,50,keyManager);
        this.FPS = 60;
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyManager);
        this.img = Toolkit.getDefaultToolkit().createImage("src/background.jpeg");
    }

    public void startFPS() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double nextDraw = System.nanoTime() + drawInterval;

        while (gameThread != null){
            updateCanvas();
            repaint();

            try {
                double timeLeft = nextDraw - System.nanoTime();
                timeLeft = timeLeft / 1000000;

                if (timeLeft < 0)
                    timeLeft = 0;
                Thread.sleep((long) timeLeft);
                nextDraw += drawInterval; }

            catch (InterruptedException e) {
                throw new RuntimeException(e);}
        }
    }

    public void updateCanvas(){
        p1.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.drawImage(img, 0, 0, getWidth(),getHeight(),null);

        p1.draw(g2d);
        for (Bullet bullet : p1.getBulletList()) {
            bullet.update();
            bullet.draw(g2d); }

    }
}

