package jambigra;

import javax.swing.JFrame;

public class JambIgra {
    public static void main(String[] args) 
    {
        
        JFrame frame = new JFrame("Jamb");
        frame.setDefaultCloseOperation(3);
        
        frame.getContentPane().add(new IgraPanel());
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
