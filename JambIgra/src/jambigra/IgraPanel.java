package jambigra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class IgraPanel extends JPanel 
{
    
    

    public IgraPanel() 
    {
        SouthPanel jug = new SouthPanel();
        EastPanel istok = new EastPanel();
        CenterPanel center = new CenterPanel(istok,jug);
        istok.postaviCenterPanel(center);
        
       
       
        
        this.setLayout(new BorderLayout());
        
        this.add(istok,"East");
        this.add(jug,"South");
        this.add(center,"Center");
       
        
        this.setPreferredSize(new Dimension(1500,500));
        this.setBackground(Color.green);
        
    }
    
}
