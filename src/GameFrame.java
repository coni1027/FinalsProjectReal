import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    private JFrame frame;
    private GameCanvas gc;

    public GameFrame(){
        frame = new JFrame();
        gc = new GameCanvas();
    }

    public void setUpGUI(){
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Final Project");


        gc.setPreferredSize(new Dimension(900, 700));
        gc.startFPS();


        frame.add(gc);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
