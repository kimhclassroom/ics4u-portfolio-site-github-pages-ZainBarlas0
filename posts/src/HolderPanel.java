
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HolderPanel extends  JPanel {
    private  BorderLayout border = new BorderLayout();
    private SubPanel  sub = new SubPanel();
    private  JPanel buttonPanel = new JPanel(new FlowLayout());
    private  JButton signup = new JButton("SignUp");
    private JButton login = new JButton("Login");
    
    public HolderPanel() throws FileNotFoundException{
        this.setLayout(border);
        this.add(sub, BorderLayout.CENTER);
        
        
        buttonPanel.add(signup);
        buttonPanel.add(login);
        
    

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getSign(){
        return signup;
    }

    public JButton getLog(){
        return login;
    }

    public SubPanel getSubPanel(){
        return sub;
    }
    
}
