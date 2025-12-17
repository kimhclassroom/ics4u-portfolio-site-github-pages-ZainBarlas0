
import javax.swing.JFrame;

public class Main {
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  public static void main(String[] args) throws Exception {

     
        JFrame frame = new JFrame("Space Crusaders");
        MainPanel main = new MainPanel();//Main panel stores everything login/signup pages, menu and game

        //frame configuratoin
        frame.setContentPane(main);
        frame.setSize(WIDTH,HEIGHT);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocusInWindow();
    }  
}
