import java.awt.EventQueue;

/**
 * Launch the application.
 */
public class Test24Game {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Start start = new Start();
                	start.setVisible(true);
                    //GameGUI Game = new GameGUI();
                    //Game.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

