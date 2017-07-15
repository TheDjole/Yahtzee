package jambigra;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class SouthPanel extends JPanel
{
    private JLabel label;
    private CenterPanel center;
    
    public SouthPanel()
    {
        this.setBackground(Color.white);
        
        
        label = new JLabel("",SwingConstants.CENTER);
        this.add(label);
        
        
        
    }
    
    public void postaviLabel(String s)
    {
        label.setText(s);
        label.setFont(new Font("Arial",Font.BOLD,30));
    }
}
