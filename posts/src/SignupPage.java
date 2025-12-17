
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class SignupPage extends JPanel{
    
    public SignupPage(){
        
        this.setPreferredSize(new Dimension(400,400 ));
        
      
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        //making labels and names
        JLabel name  = new JLabel("User ID: ");
        JLabel email = new JLabel("Email: ");
        JLabel password = new JLabel("New Password: ");
        JLabel confirmation = new JLabel("Confirm Password: ");
        JLabel error = new JLabel("");
         JLabel title = new JLabel("SPACE CRUSADER: SignUp ");

        JTextField insertName = new JTextField();
        JTextField insertEmail = new JTextField();
        JTextField insertPassword = new JTextField();
        JTextField confirmPassword = new JTextField();

        JButton submit = new JButton("Submit");

        insertName.setColumns(15);
        insertEmail.setColumns(15);
        insertPassword.setColumns(15);
        confirmPassword.setColumns(15);


        //setting up constraints
        layout.putConstraint(SpringLayout.NORTH, email, 100,SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, email, 100,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, insertEmail, 100, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, insertEmail, 150,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, name, 150,SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, name, 100,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, insertName, 150, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, insertName, 150,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, password, 200,SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, password, 100,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, insertPassword, 200, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, insertPassword, 200,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.WEST, title,100, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, title,40 , SpringLayout.NORTH,this);

        layout.putConstraint(SpringLayout.NORTH, confirmation, 250,SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, confirmation, 100,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, confirmPassword, 250, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, confirmPassword, 210,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH,error , 270, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, error, 100,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH,submit , 300, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, submit, 100,SpringLayout.WEST, this);


        //button functionality
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String userEmail = insertEmail.getText();
                String userName = insertName.getText();
                String userPassword = insertPassword.getText();
                String confirm = confirmPassword.getText();
            
                if(confirm.trim().equals(userPassword.trim()) == false){
                    error.setText("both passwords must match");
                } else{
                    String information = userEmail.trim() + "," + userName.trim()+ "," + userPassword.trim();
                    String content = "Content.txt";
                   
                    try{
    
                    FileWriter writer  = new FileWriter(content,true);
                    writer.write(information+"\n");
                    writer.close();
                    System.out.println("Data has been added to file: "+ information);
                    }catch(IOException f){
                        System.out.println("Error");
                    }


                }
                

            }

        });

        Font customFont = new Font("Comic Sans", Font.BOLD, 40);
        title.setFont(customFont);
       
        this.add(name);
        this.add(insertName);
        this.add(email);
        this.add(insertEmail);
        this.add(password);
        this.add(insertPassword);
        this.add(confirmation);
        this.add(confirmPassword);
        this.add(submit);
        this.add(error);
        this.add(title);
         



    }
    
}
