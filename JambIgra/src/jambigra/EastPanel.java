package jambigra;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class EastPanel extends JPanel
{
    private JLabel label1,label2,label3,label4,label5,label6,pom;
    private JTextArea polje1,polje2,polje3,polje4,polje5,polje6;
    private Kockica[] niz;
    private JCheckBox box1,box2,box3,box4,box5,box6;
    private JButton dugme;
    private int brojac = 0;
    private int[] pomNiz;
    static int[] prenosniNiz = new int[6];
    private boolean krajPoteza = false;
    private ArrayList<JCheckBox> boxevi;
    private ArrayList<JLabel> labeli;
    private CenterPanel center;
    
    
    public EastPanel() 
    {
        this.setPreferredSize(new Dimension(500,600));
        this.setLayout(new GridLayout(7,1));
        
        
        boxevi = new ArrayList();
        labeli = new ArrayList();
        
        niz = new Kockica[6];
        dugme = new JButton("Bacanje");
        Osluskivac o = new Osluskivac();
        dugme.addActionListener(o);
        CheckListener il = new CheckListener();
        
        for(int i =0;i<niz.length;i++)
        {
            niz[i] = new Kockica();
        }
        this.add(label1 = new JLabel("",SwingConstants.CENTER)); labeli.add(label1);
        this.add(box1=new JCheckBox()); box1.addItemListener(il);
        boxevi.add(box1);
        this.add(label2 = new JLabel("",SwingConstants.CENTER)); labeli.add(label2);
        this.add(box2=new JCheckBox()); box2.addItemListener(il);
        boxevi.add(box2);
        this.add(label3 = new JLabel("",SwingConstants.CENTER)); labeli.add(label3);
        this.add(box3=new JCheckBox()); box3.addItemListener(il);
        boxevi.add(box3);
        this.add(label4 = new JLabel("",SwingConstants.CENTER)); labeli.add(label4);
        this.add(box4=new JCheckBox()); box4.addItemListener(il);
        boxevi.add(box4);
        this.add(label5 = new JLabel("",SwingConstants.CENTER)); labeli.add(label5);
        this.add(box5=new JCheckBox()); box5.addItemListener(il);
        boxevi.add(box5);
        this.add(label6 = new JLabel("",SwingConstants.CENTER)); labeli.add(label6);
        this.add(box6=new JCheckBox()); box6.addItemListener(il);
        boxevi.add(box6);
        this.add(dugme);
        this.add(pom= new JLabel("",SwingConstants.CENTER));
    }
    
    public void bacanje() // bacanje kockice
    {
        for(int i = 0;i<niz.length;i++)
        {
            niz[i].baciKockicu(); 
                
        }
        if(niz[0].isBacaj()==true) postaviText(label1,niz[0]);
        if(niz[1].isBacaj()==true) postaviText(label2,niz[1]);
        if(niz[2].isBacaj()==true) postaviText(label3,niz[2]);
        if(niz[3].isBacaj()==true) postaviText(label4,niz[3]);     
        if(niz[4].isBacaj()==true) postaviText(label5,niz[4]);
        if(niz[5].isBacaj()==true) postaviText(label6,niz[5]);
    }
    
    public void postaviText(JLabel label,Kockica k) // dobijanje broja kockice, postavljanje labela
    {
        
        int broj = k.getBroj();
        switch(broj)
        {
            case 1: label.setText("1"); break;
            case 2: label.setText("2"); break;
            case 3: label.setText("3"); break;
            case 4: label.setText("4"); break;
            case 5: label.setText("5"); break;
            case 6: label.setText("6"); break;
            default:
        }
        label.setFont(new Font("Arial",Font.BOLD,20));
         
    }

    private class CheckListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
            
            if(box1.isSelected()){ niz[0].setBacaj(false); provera(); } 
            else niz[0].setBacaj(true);
            if(box2.isSelected()){ niz[1].setBacaj(false); provera(); } 
            else niz[1].setBacaj(true);
            if(box3.isSelected()){ niz[2].setBacaj(false); provera(); } 
            else niz[2].setBacaj(true);
            if(box4.isSelected()){ niz[3].setBacaj(false); provera(); } 
            else niz[3].setBacaj(true);
            if(box5.isSelected()){ niz[4].setBacaj(false); provera(); } 
            else niz[4].setBacaj(true);
            if(box6.isSelected()){ niz[5].setBacaj(false); provera(); } 
            else niz[5].setBacaj(true);
        }
    }

    private class Osluskivac implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
           if(e.getSource()==dugme)
           {
               
               if((brojac<=2) && krajPoteza==false)
               {
                       center.postaviInfo5();
                       bacanje();
                       postavljanjeBoxeva2();
                       ++brojac;
                       pom.setText("Bacanje "+brojac+"!");
                       checkBoxRucno();
               }
               if(brojac==1) 
               {
                   center.otkljucajNajavu();
                   center.brojacPoteza();
                   center.proveraPoteza();
               }
               else 
               {
                   if(brojac>1 && center.getNajavaBrojac()<1) center.zakljucajNajavu();
               }
               if(brojac>=3)
               {
                   for(Kockica k:niz) k.setBacaj(false);
                   provera();
                   //for(int b:prenosniNiz) System.out.print(b+" ");
                   pom.setText("Iskoristili ste sva bacanja, upisite rezultat.");
                   dugme.setText("Novo bacanje");
                   dugme.setEnabled(false);
               }
            }
        }
    }
    
    public void novoBacanje() // Pocetak novog poteza, metoda koja se aktivira kada se popuni neko polje
    {
        for(JCheckBox box:boxevi) box.setSelected(false);
        krajPoteza=false;
        brojac = 0;
        postavljanjeBoxeva1();
        for(Kockica k:niz) 
        {
            k.setBacaj(true);
        }
        for(Integer a:prenosniNiz) a=0;
        dugme.setEnabled(true);
        pom.setText("Novi potez!");
        for(JLabel l:labeli) l.setText("");
        postaviRucno();
       
    }
    
    public int[] provera() // Provera vrednosti svih kockica u nizu kockica
    {
        pomNiz = new int[6]; // pomocni niz koji sluzi za pamcenje koliko kojih kockica ima
        for (int i = 0; i < niz.length; i++) 
        {
          if(niz[i].isBacaj()==false) // ako se kockica vise ne baca, on proverava koji broj je na kockici, i na osnovu toga povecava poziciju u pomocnom nizu
          {
            int broj = niz[i].getBroj();
            switch(broj)
            {
                case 1: pomNiz[0]++; break;
                case 2: pomNiz[1]++; break;
                case 3: pomNiz[2]++; break;
                case 4: pomNiz[3]++; break;
                case 5: pomNiz[4]++; break;
                case 6: pomNiz[5]++; break;
                default:
            }
          }
        }
        System.arraycopy(pomNiz, 0, prenosniNiz, 0, prenosniNiz.length);
        
        return prenosniNiz;
    }
   
    public int vratiBroj(int l) // Vracanje vrednosti za kockicu
    {
        if(l==1) {if(prenosniNiz[0]==6) return prenosniNiz[0]-1; 
        else return prenosniNiz[0];}
        if(l==2) {if(prenosniNiz[1]==6) return prenosniNiz[1]-1; 
        else return prenosniNiz[1];}
        if(l==3) {if(prenosniNiz[2]==6)return prenosniNiz[2]-1; 
        else return prenosniNiz[2];}
        if(l==4) {if(prenosniNiz[3]==6) return prenosniNiz[3]-1; 
        else return prenosniNiz[3];}
        if(l==5) {if(prenosniNiz[4]==6)return prenosniNiz[4]-1; 
        else return prenosniNiz[4];}
        if(l==6) {if(prenosniNiz[5]==6)return prenosniNiz[5]-1;
        else return prenosniNiz[5];}
        
        return 0;
     }
    
    public int maximum() // metoda za izacunavanje maxima tog poteza (potez == tri bacanja )
    {
        int min = niz[0].getBroj(); 
        int max = 0;
        for(int i = 0;i<niz.length;i++) 
        {
            max += niz[i].getBroj();
            if(niz[i].getBroj()<min) min = niz[i].getBroj();
        }
        max -= min;
        return max;
    }
    
     public int minimum() // metoda za izacunavanje manimuma tog poteza (potez == tri bacanja )
    {
        int max = niz[0].getBroj(); 
        int min = 0;
        for(int i = 0;i<niz.length;i++)
        {
            min += niz[i].getBroj();
            if(niz[i].getBroj()>max) max = niz[i].getBroj();
        }
        min -= max;
        return min;
    }
     
     public int kenta() // provera kente
     {
         if(((prenosniNiz[1]>=1) && (prenosniNiz[2]>=1) && (prenosniNiz[3]>=1) && (prenosniNiz[4]>=1)) && ((prenosniNiz[0]>=1)||(prenosniNiz[5]>=1)))
         {
             if(brojac == 1) return 1;
             else if(brojac == 2) return 2;
             else if(brojac == 3) return 3;
         }   
         return 0;    
     }
     
     public int triling() // provera trilinga
     {
         for(int i=prenosniNiz.length-1;i>=0;i--)
         {
             if(prenosniNiz[i]>=3) return i+1;
         }
         return 0;
     }
     
     public int[] full() //provera fula
     {
         
         int[] full = new int[2];
        
         for(int i=prenosniNiz.length-1;i>=0;i--) // prva petlja proverava da li ima tri iste kocke
         {
             if(prenosniNiz[i]>=3) // prva petlja proverava da li ima tri iste kocke
             {
                 for(int j = prenosniNiz.length-1;j>=0;j--) //ako ima neke tri iste kocke, onda druga petlja proverava da li ima 2 iste kocke koje nisu iste kao prethodne
                 {
                     if(i==j) continue; // uslov da se ne bi pokazivale iste kocke
                     if(prenosniNiz[j]>=2)
                     {
                         full[0]=i+1; 
                         full[1]=j+1;
                     }
                 }
             }
         }
         
         return full;
     }
     
     public int poker() // provera pokera
     {
         for(int i=0;i<prenosniNiz.length;i++)
         {
             if(prenosniNiz[i]>=4) return i+1;
         }
         return 0;
     }
     
     public int YAMB() // provera jamba
     {
         for(int i=0;i<prenosniNiz.length;i++)
         {
             if(prenosniNiz[i]>=5) return i+1;
         }
         return 0;
     }
     public void postavljanjeBoxeva1() // postavljanje checkboxeva da ne mogu da se cekiraju
     {
         if(brojac==0)
             for(JCheckBox c:boxevi) c.setEnabled(false);        
     }
     
     public void postavljanjeBoxeva2() // postavljanje checkboxeva da mogu da se cekiraju
     {
          for(JCheckBox c:boxevi) c.setEnabled(true);
     }
     
     public int vratiBrojac()
     {
         return brojac;
     }
     public void postaviCenterPanel(CenterPanel c)
     {
         center = c;
     }
    
     public void postaviRucno()
     {
         for(int i = 0;i<niz.length;i++)
             niz[i].setRucno(true);
     }
     
     public boolean jesteRucno()
     {
         for(int i = 0;i<niz.length;i++)
             if(niz[i].isRucno()==false) return false;
         
         return true;
     }
     
     public void checkBoxRucno()
     {
         if(!box1.isSelected()) niz[0].setRucno(true);
         else niz[0].setRucno(false);
         if(!box2.isSelected()) niz[1].setRucno(true);
         else niz[1].setRucno(false);
         if(!box3.isSelected()) niz[2].setRucno(true);
         else niz[2].setRucno(false);
         if(!box4.isSelected()) niz[3].setRucno(true);
         else niz[3].setRucno(false);
         if(!box5.isSelected()) niz[4].setRucno(true);
         else niz[4].setRucno(false);
         if(!box6.isSelected()) niz[5].setRucno(true);
         else niz[5].setRucno(false);
     }
     
     public void setDugmeFalse(boolean f)
     {
         dugme.setEnabled(f);
     }
     
     public JLabel postaviPom()
     {
         return pom;
     }
}
