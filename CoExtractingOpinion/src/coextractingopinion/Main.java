
package coextractingopinion;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        System.out.println("Here");
        try
        {                    
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");                         
            
            HomePage hp=new HomePage();
            hp.setResizable(false);
            hp.setTitle("Home Page");
            hp.setVisible(true);  
        }
	catch (Exception ex)
	{
            System.out.println("Failed loading L&F: ");
            System.out.println(ex);
	}        
    }
}
