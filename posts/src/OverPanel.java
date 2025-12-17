
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class OverPanel extends JPanel{
    private SpringLayout layout = new SpringLayout();
    private JButton toMenu = new JButton("Back to Menu");
    private JLabel GameOver = new JLabel("Game Over");
    private JLabel score = new JLabel("Score: ");


    public OverPanel(){
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        layout.putConstraint(SpringLayout.WEST, toMenu, 300, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, toMenu, 300, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, GameOver,300, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, GameOver, 200, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.WEST, score,300, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, score, 250, SpringLayout.NORTH, this);

        

        Font customFont = new Font("Comic Sans", Font.BOLD, 50);
        GameOver.setFont(customFont);
        GameOver.setForeground(Color.WHITE);
        score.setForeground(Color.WHITE);
        toMenu.setPreferredSize(new Dimension(200,75));

        this.add(toMenu);
        this.add(GameOver);
        this.add(score);
    }
        
    public JButton getMenuButton(){
        return toMenu;
    }

    public void scoreText(String s){
        score.setText(s);
    }



}
