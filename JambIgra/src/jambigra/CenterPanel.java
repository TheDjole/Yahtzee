package jambigra;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CenterPanel extends JPanel
{
    private JButton[][] matrica;
    private TextArea prostor;
    private EastPanel ep;
    private int[] pom;
    private SouthPanel south;
    private JLabel info,info2,info3,info4,info5,info6;
    private int najavaBrojac = 0;
    private int brojPotezaNaj = 0;
    private int brojacPoteza = 0;
    
    
    public CenterPanel(EastPanel ep,SouthPanel s)
    {
        this.setLayout(new GridLayout(18,6));
        this.setBackground(Color.red);
        
        this.ep = ep;
        this.south = s;
        matrica = new JButton[17][6];
        Osluskivac listener = new Osluskivac();
        for(int i = 0;i<matrica.length;i++)
            for(int j = 0;j<matrica[i].length;j++)  
            {
                matrica[i][j] = new JButton("");
                matrica[i][j].addActionListener(listener);
                matrica[i][j].setBackground(Color.white);
                matrica[i][j].setEnabled(false);
                if(j==2 && i>0) matrica[i][j].setEnabled(true);
                if(i==1 && j==1) matrica[i][j].setEnabled(true);
                if(j==3 && i==15) matrica[i][j].setEnabled(true);
                
                if((j==0)&&(i==7) || (j==0)&&(i==10) || (j==0)&&(i==16)) 
                {
                    postavi(matrica[i][j],"SUMA");
                    matrica[i][j].setBackground(Color.CYAN);
                }
                
                if((j==0 && ((i>j)&& (i<7)))) postavi(matrica[i][j],""+i);
                if(j==0 && i==8) postavi(matrica[i][j],"MAX ");
                if(j==0 && i==9) postavi(matrica[i][j],"MIN ");
                if(j==0 && i==11) postavi(matrica[i][j],"KENTA ");
                if(j==0 && i==12) postavi(matrica[i][j],"TRILING");
                if(j==0 && i==13) postavi(matrica[i][j],"FULL");
                if(j==0 && i==14) postavi(matrica[i][j],"POKER");
                if(j==0 && i==15) postavi(matrica[i][j],"YAMB");
                
                if(i==0 && j==1) postavi(matrica[i][j],"DOLE");
                if(i==0 && j==2) postavi(matrica[i][j],"PROIZVOLJNO");
                if(i==0 && j==3) postavi(matrica[i][j],"GORE");
                if(i==0 && j==4) postavi(matrica[i][j],"NAJAVA");
                if(i==0 && j==5) postavi(matrica[i][j],"RUCNO");
               
                if(i==16 && j>0) matrica[i][j].setEnabled(true);
                
                if(i<17 && i>0 && j==5) matrica[i][j].setEnabled(true);
                if(i==7 || i==10 || i==16) matrica[i][j].setEnabled(false);
                    
                postavi(matrica[0][0],"YAMB");
                
                this.add(matrica[i][j]);
            }
        info = new JLabel(); info2 = new JLabel(); info3 = new JLabel(); info4 = new JLabel(); info5 = new JLabel(); info6 = new JLabel("",SwingConstants.CENTER);
        this.add(info); this.add(info2); this.add(info3); this.add(info4); this.add(info5); this.add(info6);
        
        this.setPreferredSize(new Dimension(250,100));
        this.setBackground(Color.yellow);
    }
    
    public void postavi(JButton dugme,String s) // Metoda za postavljanje teksa prilikom kreiranja izgleda
    {
       dugme.setText(s);
       dugme.setFont(new Font("Arial",Font.BOLD,20));
     
    }

    private class Osluskivac implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Object pom = e.getSource();
            // PRVA KOLONA
            if(pom==matrica[1][1]) { postaviDugme(matrica[1][1],matrica[2][1],1); }
            else if(pom==matrica[2][1]) { postaviDugme(matrica[2][1],matrica[3][1],2); suma(matrica[1][1],matrica[2][1],matrica[3][1],matrica[4][1],matrica[5][1],matrica[6][1],matrica[7][1],matrica[8][1]);}
            else if(pom==matrica[3][1]) { postaviDugme(matrica[3][1],matrica[4][1],3); suma(matrica[1][1],matrica[2][1],matrica[3][1],matrica[4][1],matrica[5][1],matrica[6][1],matrica[7][1],matrica[8][1]);}
            else if(pom==matrica[4][1]) { postaviDugme(matrica[4][1],matrica[5][1],4); suma(matrica[1][1],matrica[2][1],matrica[3][1],matrica[4][1],matrica[5][1],matrica[6][1],matrica[7][1],matrica[8][1]);}
            else if(pom==matrica[5][1]) { postaviDugme(matrica[5][1],matrica[6][1],5); suma(matrica[1][1],matrica[2][1],matrica[3][1],matrica[4][1],matrica[5][1],matrica[6][1],matrica[7][1],matrica[8][1]);}
            else if(pom==matrica[6][1]) { postaviDugme(matrica[6][1],matrica[8][1],6); suma(matrica[1][1],matrica[2][1],matrica[3][1],matrica[4][1],matrica[5][1],matrica[6][1],matrica[7][1],matrica[8][1]);}
            else if(pom==matrica[8][1]) { postaviMaximum(matrica[8][1],matrica[9][1]); razlika(matrica[8][1],matrica[9][1],matrica[11][1],matrica[10][1],matrica[1][1]);}
            else if(pom==matrica[9][1]) { postaviMinimum(matrica[9][1],matrica[11][1]); razlika(matrica[8][1],matrica[9][1],matrica[11][1],matrica[10][1],matrica[1][1]);}
            else if(pom==matrica[11][1]) { postaviKentu(matrica[11][1],matrica[12][1]); postaviZbir3(matrica[11][1],matrica[12][1],matrica[13][1],matrica[14][1],matrica[15][1],matrica[16][1]);}
            else if(pom==matrica[12][1]) { postaviTriling(matrica[12][1],matrica[13][1]); postaviZbir3(matrica[11][1],matrica[12][1],matrica[13][1],matrica[14][1],matrica[15][1],matrica[16][1]);}
            else if(pom==matrica[13][1]) { postaviFull(matrica[13][1],matrica[14][1]); postaviZbir3(matrica[11][1],matrica[12][1],matrica[13][1],matrica[14][1],matrica[15][1],matrica[16][1]);}
            else if(pom==matrica[14][1]) { postaviPoker(matrica[14][1],matrica[15][1]); postaviZbir3(matrica[11][1],matrica[12][1],matrica[13][1],matrica[14][1],matrica[15][1],matrica[16][1]);}
            else if(pom==matrica[15][1]) { postaviJAMB(matrica[15][1],matrica[16][1]); postaviZbir3(matrica[11][1],matrica[12][1],matrica[13][1],matrica[14][1],matrica[15][1],matrica[16][1]); krajIgre();}
                        
            // DRUGA KOLONA
            else if(pom==matrica[1][2]) { postaviDugme(matrica[1][2],matrica[2][2],1); suma(matrica[1][2],matrica[2][2],matrica[3][2],matrica[4][2],matrica[5][2],matrica[6][2],matrica[7][2],matrica[8][2]); razlika(matrica[8][2],matrica[9][2],matrica[11][2],matrica[10][2],matrica[1][2]); krajIgre();}
            else if(pom==matrica[2][2]) { postaviDugme(matrica[2][2],matrica[3][2],2); suma(matrica[1][2],matrica[2][2],matrica[3][2],matrica[4][2],matrica[5][2],matrica[6][2],matrica[7][2],matrica[8][2]); krajIgre();}
            else if(pom==matrica[3][2]) { postaviDugme(matrica[3][2],matrica[4][2],3); suma(matrica[1][2],matrica[2][2],matrica[3][2],matrica[4][2],matrica[5][2],matrica[6][2],matrica[7][2],matrica[8][2]); krajIgre();}
            else if(pom==matrica[4][2]) { postaviDugme(matrica[4][2],matrica[5][2],4); suma(matrica[1][2],matrica[2][2],matrica[3][2],matrica[4][2],matrica[5][2],matrica[6][2],matrica[7][2],matrica[8][2]); krajIgre();}
            else if(pom==matrica[5][2]) { postaviDugme(matrica[5][2],matrica[6][2],5); suma(matrica[1][2],matrica[2][2],matrica[3][2],matrica[4][2],matrica[5][2],matrica[6][2],matrica[7][2],matrica[8][2]); krajIgre();}
            else if(pom==matrica[6][2]) { postaviDugme(matrica[6][2],matrica[7][2],6); suma(matrica[1][2],matrica[2][2],matrica[3][2],matrica[4][2],matrica[5][2],matrica[6][2],matrica[7][2],matrica[8][2]); krajIgre();}
            else if(pom==matrica[8][2]) { postaviMaximum(matrica[8][2],matrica[9][2]); razlika(matrica[8][2],matrica[9][2],matrica[11][2],matrica[10][2],matrica[1][2]); krajIgre();}
            else if(pom==matrica[9][2]) { postaviMinimum(matrica[9][2],matrica[10][2]); razlika(matrica[8][2],matrica[9][2],matrica[11][2],matrica[10][2],matrica[1][2]); krajIgre();}
            else if(pom==matrica[11][2]) { postaviKentu(matrica[11][2],matrica[12][2]); postaviZbir3(matrica[11][2],matrica[12][2],matrica[13][2],matrica[14][2],matrica[15][2],matrica[16][2]); krajIgre();}
            else if(pom==matrica[12][2]) { postaviTriling(matrica[12][2],matrica[13][2]); postaviZbir3(matrica[11][2],matrica[12][2],matrica[13][2],matrica[14][2],matrica[15][2],matrica[16][2]); krajIgre();}
            else if(pom==matrica[13][2]) { postaviFull(matrica[13][2],matrica[14][2]); postaviZbir3(matrica[11][2],matrica[12][2],matrica[13][2],matrica[14][2],matrica[15][2],matrica[16][2]); krajIgre();}
            else if(pom==matrica[14][2]) { postaviPoker(matrica[14][2],matrica[15][2]); postaviZbir3(matrica[11][2],matrica[12][2],matrica[13][2],matrica[14][2],matrica[15][2],matrica[16][2]); krajIgre();}
            else if(pom==matrica[15][2]) { postaviJAMB(matrica[15][2],matrica[16][2]); postaviZbir3(matrica[11][2],matrica[12][2],matrica[13][2],matrica[14][2],matrica[15][2],matrica[16][2]); krajIgre();}
                      
            // TRECA KOLONA
            else if(pom==matrica[15][3]) { postaviJAMB(matrica[15][3],matrica[14][3]); postaviZbir3(matrica[11][3],matrica[12][3],matrica[13][3],matrica[14][3],matrica[15][3],matrica[16][3]); }
            else if(pom==matrica[14][3]) { postaviPoker(matrica[14][3],matrica[13][3]); postaviZbir3(matrica[11][3],matrica[12][3],matrica[13][3],matrica[14][3],matrica[15][3],matrica[16][3]); }
            else if(pom==matrica[13][3]) { postaviFull(matrica[13][3],matrica[12][3]); postaviZbir3(matrica[11][3],matrica[12][3],matrica[13][3],matrica[14][3],matrica[15][3],matrica[16][3]); }
            else if(pom==matrica[12][3]) { postaviTriling(matrica[12][3],matrica[11][3]); postaviZbir3(matrica[11][3],matrica[12][3],matrica[13][3],matrica[14][3],matrica[15][3],matrica[16][3]); }
            else if(pom==matrica[11][3]) { postaviKentu(matrica[11][3],matrica[9][3]); postaviZbir3(matrica[11][3],matrica[12][3],matrica[13][3],matrica[14][3],matrica[15][3],matrica[16][3]); }
            else if(pom==matrica[9][3]) { postaviMinimum(matrica[9][3],matrica[8][3]); razlika(matrica[8][3],matrica[9][3],matrica[8][3],matrica[10][3],matrica[1][3]); }
            else if(pom==matrica[8][3]) { postaviMaximum(matrica[8][3],matrica[6][3]); razlika(matrica[8][3],matrica[9][3],matrica[8][3],matrica[10][3],matrica[1][3]); }
            else if(pom==matrica[6][3]) { postaviDugme(matrica[6][3],matrica[5][3],6); suma(matrica[1][3],matrica[2][3],matrica[3][3],matrica[4][3],matrica[5][3],matrica[6][3],matrica[7][3],matrica[10][3]); }
            else if(pom==matrica[5][3]) { postaviDugme(matrica[5][3],matrica[4][3],5); suma(matrica[1][3],matrica[2][3],matrica[3][3],matrica[4][3],matrica[5][3],matrica[6][3],matrica[7][3],matrica[10][3]); }
            else if(pom==matrica[4][3]) { postaviDugme(matrica[4][3],matrica[3][3],4); suma(matrica[1][3],matrica[2][3],matrica[3][3],matrica[4][3],matrica[5][3],matrica[6][3],matrica[7][3],matrica[10][3]); }
            else if(pom==matrica[3][3]) { postaviDugme(matrica[3][3],matrica[2][3],3); suma(matrica[1][3],matrica[2][3],matrica[3][3],matrica[4][3],matrica[5][3],matrica[6][3],matrica[7][3],matrica[10][3]); }
            else if(pom==matrica[2][3]) { postaviDugme(matrica[2][3],matrica[1][3],2); suma(matrica[1][3],matrica[2][3],matrica[3][3],matrica[4][3],matrica[5][3],matrica[6][3],matrica[7][3],matrica[10][3]); }
            else if(pom==matrica[1][3]) { postaviDugme(matrica[1][3],matrica[7][3],1); suma(matrica[1][3],matrica[2][3],matrica[3][3],matrica[4][3],matrica[5][3],matrica[6][3],matrica[7][3],matrica[1][3]); razlika(matrica[8][3],matrica[9][3],matrica[8][3],matrica[10][3],matrica[1][3]); krajIgre();}
            
            // Kolona najave
            else if(pom==matrica[1][4]) { klikniNajavu(matrica[1][4],1); suma2();}
            else if(pom==matrica[2][4]) klikniNajavu(matrica[2][4],2);
            else if(pom==matrica[3][4]) klikniNajavu(matrica[3][4],3);
            else if(pom==matrica[4][4]) klikniNajavu(matrica[4][4],4);
            else if(pom==matrica[5][4]) klikniNajavu(matrica[5][4],5);
            else if(pom==matrica[6][4]) klikniNajavu(matrica[6][4],6);
            else if(pom==matrica[7][4]) najavaSuma();
            else if(pom==matrica[8][4]) NajavaMax();
            else if(pom==matrica[9][4]) NajavaMin();
            else if(pom==matrica[10][4]) suma2();
            else if(pom==matrica[11][4]) najavaKenta();
            else if(pom==matrica[12][4]) najavaTriling();
            else if(pom==matrica[13][4]) najavaFull();
            else if(pom==matrica[14][4]) najavaPoker();
            else if(pom==matrica[15][4]) najavaJamb();
            // Kolona rucna
            else if(pom==matrica[1][5]) 
            {
                if(ep.jesteRucno()==true) { postaviDugme(matrica[1][5],matrica[2][5],1); south.postaviLabel(""); }
                else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[1][5]);
                if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
                sumaRucno();
                sumaRucno2();
                krajIgre();
            }
            else if(pom==matrica[2][5]) 
            {
                if(ep.jesteRucno()==true) { postaviDugme(matrica[2][5],matrica[3][5],2);  south.postaviLabel(""); }
                else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[2][5]);
                if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
                sumaRucno();
                krajIgre();
            }
            else if(pom==matrica[3][5]) 
            {
                if(ep.jesteRucno()==true) { postaviDugme(matrica[3][5],matrica[4][5],3);  south.postaviLabel(""); }
                else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[3][5]);
                if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
                sumaRucno();
                krajIgre();
            }
            else if(pom==matrica[4][5]) 
            {
                if(ep.jesteRucno()==true) { postaviDugme(matrica[4][5],matrica[5][5],4);  south.postaviLabel(""); }
                else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[4][5]);
                if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
                sumaRucno();
                krajIgre();
            }
            else if(pom==matrica[5][5]) 
            {
                if(ep.jesteRucno()==true) { postaviDugme(matrica[5][5],matrica[6][5],5);  south.postaviLabel(""); }
                else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[5][5]);
                if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
                sumaRucno();
                krajIgre();
            }
            else if(pom==matrica[6][5]) 
            {
                if(ep.jesteRucno()==true) { postaviDugme(matrica[6][5],matrica[6][5],6);  south.postaviLabel(""); }
                else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[6][5]);
                if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
                sumaRucno();
                krajIgre();
            }
            else if(pom==matrica[8][5])
            {
                if(ep.jesteRucno()==true) { postaviMaximum(matrica[8][5],matrica[9][5]);  south.postaviLabel(""); }
                else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[8][5]);
                if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
                sumaRucno2();
                krajIgre();
            }
            else if(pom==matrica[9][5])
            {
                if(ep.jesteRucno()==true) { postaviMinimum(matrica[9][5],matrica[9][5]);  south.postaviLabel(""); }
                else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[9][5]);
                if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
                sumaRucno2();
                krajIgre();
            }
            else if(pom==matrica[11][5])
            {
               if(ep.jesteRucno()==true) { postaviKentu(matrica[11][5],matrica[11][5]);  south.postaviLabel(""); }
               else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[11][5]);
               if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
               postaviZbir3(matrica[11][5],matrica[12][5],matrica[13][5],matrica[14][5],matrica[15][5],matrica[16][5]);
               krajIgre();
            }
            else if(pom==matrica[12][5])
            {
               if(ep.jesteRucno()==true) { postaviTriling(matrica[12][5],matrica[12][5]);  south.postaviLabel(""); }
               else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[12][5]);
               if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
               postaviZbir3(matrica[11][5],matrica[12][5],matrica[13][5],matrica[14][5],matrica[15][5],matrica[16][5]);
               krajIgre();
            }
            else if(pom==matrica[13][5])
            {
               if(ep.jesteRucno()==true) { postaviFull(matrica[13][5],matrica[13][5]);  south.postaviLabel(""); }
               else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[13][5]);
               if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
               postaviZbir3(matrica[11][5],matrica[12][5],matrica[13][5],matrica[14][5],matrica[15][5],matrica[16][5]);
               krajIgre();
            }
             else if(pom==matrica[14][5])
            {
               if(ep.jesteRucno()==true) { postaviPoker(matrica[14][5],matrica[14][5]);  south.postaviLabel(""); }
               else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[14][5]);
               if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
               postaviZbir3(matrica[11][5],matrica[12][5],matrica[13][5],matrica[14][5],matrica[15][5],matrica[16][5]);
               krajIgre();
            }
            else if(pom==matrica[15][5])
            {
               if(ep.jesteRucno()==true) { postaviJAMB(matrica[15][5],matrica[15][5]);  south.postaviLabel(""); }
               else if(ep.jesteRucno()==false && ep.vratiBrojac()>=3) postaviNulu(matrica[15][5]);
               if(ep.jesteRucno()==false) south.postaviLabel("Kockice nisu rucno bacene.Ukoliko kockice nisu bacene rucno, nakon 3 bacanja rezultat ce biti 0.");
               postaviZbir3(matrica[11][5],matrica[12][5],matrica[13][5],matrica[14][5],matrica[15][5],matrica[16][5]);
               krajIgre();
            }
        }
    }
    
    public void postaviDugme(JButton a,JButton b,int i) // Postavljanje brojeva na dugme, zavisi od polja koje se otvori
    { 
        if(ep.vratiBrojac()==0) a.setText("");
        else
        {
            String broj = "";
            broj = ""+(ep.vratiBroj(i))*i;
            a.setText(broj); a.setFont(new Font("Arial",Font.BOLD,20));
            a.setEnabled(false);
            if(b.getText().equalsIgnoreCase("")) b.setEnabled(true);
            ep.novoBacanje();   
           
        }
    }
    
    public void suma(JButton a,JButton b,JButton c,JButton d,JButton e,JButton f,JButton suma,JButton next) // zbir od 1 do 7 vrste u svim kolonama
    {
        
        if(!a.getText().equals("") && !b.getText().equals("") && !c.getText().equals("") && !d.getText().equals("") && !e.getText().equals("") && !f.getText().equals(""))
        {
            int rezultat = (Integer.parseInt(a.getText()) + Integer.parseInt(b.getText()) + Integer.parseInt(c.getText()) + Integer.parseInt(d.getText()) + Integer.parseInt(e.getText()) + Integer.parseInt(f.getText()));
            if(rezultat>=60)
            {
                rezultat+=30;
                suma.setText(""+rezultat);
            }
            else suma.setText(""+rezultat);
            suma.setFont(new Font("Arial",Font.BOLD,20));
            if(next.getText().equalsIgnoreCase("")) next.setEnabled(true);
            suma.setEnabled(false);
        }
        else
        {
           south.postaviLabel("");
        }
    }
    
    public void postaviMaximum(JButton a, JButton b) // metoda za postavljanje maximuma
    {
        if(ep.vratiBrojac()==0) a.setText("");
        else
        {
            int broj = ep.maximum();
            String broj1 = Integer.toString(broj);
            a.setText(broj1);
            a.setFont(new Font("Arial",Font.BOLD,20));
            a.setEnabled(false);
            if(b.getText().equalsIgnoreCase("")) b.setEnabled(true);
            ep.novoBacanje();
           
        }
    }
    
    public void postaviMinimum(JButton a, JButton b) // metoda za postavljanje minimuma
    {
        if(ep.vratiBrojac()==0) a.setText("");
        else
        {
            int min = ep.minimum();
            String min1 = Integer.toString(min);
            a.setText(min1);
            a.setFont(new Font("Arial",Font.BOLD,20));
            a.setEnabled(false);
            if(b.getText().equalsIgnoreCase("")) b.setEnabled(true);
            ep.novoBacanje();
           
        }
    }
    public void razlika(JButton max1,JButton min1,JButton next,JButton ova,JButton jedinice1) // metoda za racunanje razlike minimuma i maximuma
    {
        if(!max1.getText().equals("") && !min1.getText().equals("") && !jedinice1.getText().equals(""))
        {
            int max = Integer.parseInt(max1.getText());
            int min = Integer.parseInt(min1.getText());
            int jedinice = Integer.parseInt(jedinice1.getText());
            int raz = (max - min)*jedinice;
            String razl = Integer.toString(raz);
            ova.setText(razl);
            ova.setFont(new Font("Arial",Font.BOLD,20));
            ova.setEnabled(false);
            if(next.getText().equalsIgnoreCase("")) next.setEnabled(true);
        }
        else
        {
            south.postaviLabel("");
        }
    }
    public void postaviKentu(JButton ovo,JButton next) // POSTAVLJANJE KENTE
    {
        if(ep.vratiBrojac()==0) ovo.setText("");
        else
        {
            int broj = ep.kenta(); 
            String text = "";
            switch(broj)
            {
                case 1: text = "66";  break;
                case 2: text = "56"; break;
                case 3: text = "46"; break;
                case 0: text = "0"; break;
                default:     
            }
            ovo.setText(text); 
            ovo.setFont(new Font("Arial",Font.BOLD,20));
            ovo.setEnabled(false);
            if(next.getText().equalsIgnoreCase("")) next.setEnabled(true);
            ep.novoBacanje();
            
        }
    }
    public void postaviTriling(JButton ovo,JButton next) //POSTAVLJANJE TRILINGA
    {
        if(ep.vratiBrojac()==0) ovo.setText("");
        else
        {
            int broj = ep.triling()*3; 
            String text = "";
            if(broj!=0)
            {
                broj+=20;
                text = Integer.toString(broj);
            }
            else
            {
                text = Integer.toString(broj);
            } 
            ovo.setText(text);
            ovo.setFont(new Font("Arial",Font.BOLD,20));
            ovo.setEnabled(false);
            if(next.getText().equalsIgnoreCase("")) next.setEnabled(true);
            ep.novoBacanje();
            
        }
    }
    public void postaviFull(JButton ovo,JButton next) // POSTAVLJANJE FULA
    {
        if(ep.vratiBrojac()==0) ovo.setText("");
        else
        {
            int[] pom = ep.full();
            String text;
            int ful = pom[0]*3+pom[1]*2;
            if(ful!=0)
            {
                ful +=30;
                text = Integer.toString(ful);
            }
            else
            {
                text = Integer.toString(ful);
            }
            ovo.setText(text);
            ovo.setFont(new Font("Arial",Font.BOLD,20));
            ovo.setEnabled(false);
            if(next.getText().equalsIgnoreCase("")) next.setEnabled(true);
            ep.novoBacanje();
           
        }
    }
    public void postaviPoker(JButton ovo,JButton next) // POSTAVLJANJE POKERA
    {
        if(ep.vratiBrojac()==0) ovo.setText("");
        else
        {
            int broj = ep.poker()*4;
            String text;
            if(broj!=0)
            {
                broj+=40;
                text = Integer.toString(broj);
            }
            else
            {
                text = Integer.toString(broj);
            }
            ovo.setText(text);
            ovo.setFont(new Font("Arial",Font.BOLD,20));
            ovo.setEnabled(false);
            if(next.getText().equalsIgnoreCase("")) next.setEnabled(true);
            ep.novoBacanje();
            
        }
    }
    public void postaviJAMB(JButton ovo,JButton next) // POSTAVLJANJE JAMBA
    {
        if(ep.vratiBrojac()==0) ovo.setText("");
        else
        {
            int broj = ep.YAMB()*5;
            String text;
            if(broj!=0)
            {
                broj+=50;
                text = Integer.toString(broj);
            }
            else
            {
                text = Integer.toString(broj);
            }
            ovo.setText(text);
            ovo.setFont(new Font("Arial",Font.BOLD,20));
            ovo.setEnabled(false);
            if(next.getText().equalsIgnoreCase("")) next.setEnabled(true);
            ep.novoBacanje();
            
        }
    }
    
    public void postaviZbir3(JButton kenta,JButton triling,JButton full,JButton poker,JButton jamb,JButton suma) // IZRACUNAVANJE SUME U KENTE,TRILIGA,FULA,POKERA I JAMBA
    {
        if(!kenta.getText().equals("") && !triling.getText().equals("") && !full.getText().equals("") && !poker.getText().equals("") && !jamb.getText().equals("")) 
        {
            int K = Integer.parseInt(kenta.getText());
            int T = Integer.parseInt(triling.getText());
            int F = Integer.parseInt(full.getText());
            int P = Integer.parseInt(poker.getText());
            int Y = Integer.parseInt(jamb.getText());
            int zbir = K+T+F+P+Y;
            String text = Integer.toString(zbir);
            suma.setText(text);
            suma.setFont(new Font("Arial",Font.BOLD,20));
            suma.setEnabled(false);
            ep.novoBacanje();
            
        }  
        else
        {
            suma.setText("");
        }
    }
    public void oznaciKrajKolone(JButton suma1,JButton suma2,JButton suma3,int j)
    {
        if(!suma1.getText().equals("") && !suma1.getText().equals("") && !suma1.getText().equals(""))
        {
            for(int i=0;i<17;i++)
            {
                matrica[i][j].setBackground(Color.green);
            }
        }
    }
    public void otkljucajNajavu() // otkljucavanje kolone najava pri prvom bacanju
    {
        for(int i=1;i<17;i++)
        {
            if(matrica[i][4].getText().equals("")) matrica[i][4].setEnabled(true);
            if(i==7 || i==10 || i==16) matrica[i][4].setEnabled(false);
        }
    }
    public void klikniNajavu(JButton ovo,int k) // metoda za najavu brojeva od 1 do 6
    {
        if(brojacPoteza>52) setDugmeZaBacanje();
        najavaBrojac++;
        DugmeKlikNajava(ovo);
        String broj = Integer.toString(ep.vratiBroj(k)*k);
        brojPotezaNaj++;
        if(ep.vratiBrojac()>0 && brojPotezaNaj>1) 
        {
            ovo.setText(broj);
            ovo.setFont(new Font("Arial",Font.BOLD,20));
            provera();
            ovo.setEnabled(false);
            ep.novoBacanje();
            info6.setText("");
            najavaBrojac=0;
            najavaSuma();
            brojPotezaNaj=0;
            krajIgre();
            
        }
        
    }
      
    public void otkljucajMatricu() // metoda za postavljanje svih kolona nakon najave
    {
        for(int i=1;i<17;i++)
           for(int j = 1;j<6;j++)
           {
               if(matrica[i][j].getText().equals("")) matrica[i][j].setEnabled(true);
               matrica[i][4].setEnabled(false);
               if(i==7 || i==10 || i==16) matrica[i][j].setEnabled(false);
           }
    }
    
    public void postaviPrvuKol() // dodatna metoda za postavljanje KOLONE DOLE nakon zavrsetka najave
    {
        for(int i = 1;i<15;i++)
            if(matrica[i][1].getText().equals("")) matrica[i+1][1].setEnabled(false);
    }
    
    public void postaviTrecuKol() // dodatna metoda za postavljanje KOLONE GORE nakon zavrsetka najave
    {
        for(int i = 15;i>1;i--)
            {
                 if(matrica[i][3].getText().equals("")) matrica[i-1][3].setEnabled(false);
            }
        if(!matrica[11][3].getText().equals("")) { if(matrica[9][3].getText().equals("")) matrica[9][3].setEnabled(true); matrica[8][3].setEnabled(false);}
        if(!matrica[8][3].getText().equals("")) 
        { 
            if(matrica[6][3].getText().equals("")) matrica[6][3].setEnabled(true); 
            matrica[7][3].setEnabled(false);
        }         
    }
    public void najavaSuma() // metoda za racunanje sume prvih 6 brojeva
    {
        if(!matrica[1][4].getText().equals("") && !matrica[2][4].getText().equals("") && !matrica[3][4].getText().equals("") && !matrica[4][4].getText().equals("") && !matrica[5][4].getText().equals("") && !matrica[6][4].getText().equals(""))
        {
            int rezultat = 0; String text;
            rezultat += Integer.parseInt(matrica[1][4].getText()) + Integer.parseInt(matrica[2][4].getText()) + Integer.parseInt(matrica[3][4].getText()) + Integer.parseInt(matrica[4][4].getText()) + Integer.parseInt(matrica[5][4].getText()) + Integer.parseInt(matrica[6][4].getText());
            if(rezultat>=60) 
            {
                rezultat +=30; 
                text = Integer.toString(rezultat);
            }
            else {text = Integer.toString(rezultat);}
            matrica[7][4].setText(text);
            matrica[7][4].setFont(new Font("Arial",Font.BOLD,20));
            matrica[7][4].setEnabled(false);
        }
        
    }
    public void NajavaMax() // najava maksimuma
    {
        if(brojacPoteza>52) setDugmeZaBacanje();
        najavaBrojac++;
        DugmeKlikNajava(matrica[8][4]);
        int broj = ep.maximum();  String broj1 = Integer.toString(broj);
        brojPotezaNaj++;
        if(ep.vratiBrojac()>0 && brojPotezaNaj>1)
        {
            matrica[8][4].setText(broj1);
            matrica[8][4].setFont(new Font("Arial",Font.BOLD,20));
            provera();
            matrica[8][4].setEnabled(false);
            ep.novoBacanje();
            info6.setText("");
            najavaBrojac=0;
            brojPotezaNaj=0;
            suma2();
           
        }
    }
    public void NajavaMin() // najava minimuma
    {
        if(brojacPoteza>52) setDugmeZaBacanje();
        najavaBrojac++;
        DugmeKlikNajava(matrica[9][4]);
        brojPotezaNaj++;
        int broj = ep.minimum();  String broj1 = Integer.toString(broj);
        if(ep.vratiBrojac()>0 && brojPotezaNaj>1)
        {
            matrica[9][4].setText(broj1);
            matrica[9][4].setFont(new Font("Arial",Font.BOLD,20));
            provera();
            matrica[9][4].setEnabled(false);
            ep.novoBacanje();
            info6.setText("");
            najavaBrojac=0;
            suma2();
            brojPotezaNaj=0;
            
        }
    }
    public void provera() // metoda koja vrsi pojedine provere
    {
        otkljucajMatricu();
        postaviPrvuKol();
        postaviTrecuKol();
    }
    
    public void suma2() // metoda za racunanje razlike
    {  
        if(!matrica[9][4].getText().equals("") && !matrica[8][4].getText().equals("") && !matrica[1][4].getText().equals(""))
        {
            int max = Integer.parseInt(matrica[8][4].getText());
            int min = Integer.parseInt(matrica[9][4].getText());
            int jedinice = Integer.parseInt(matrica[1][4].getText());
            int raz = (max - min)*jedinice;
            String razl = Integer.toString(raz);
        
            matrica[10][4].setText(razl);
            matrica[10][4].setFont(new Font("Arial",Font.BOLD,20));
            provera();
            matrica[10][4].setEnabled(false);
            ep.novoBacanje();
            info6.setText("");
        }
    }
    public void najavaKenta() // najava kente
    {
        if(brojacPoteza>52) setDugmeZaBacanje();
        najavaBrojac++;
        DugmeKlikNajava(matrica[11][4]);
        brojPotezaNaj++;
        int broj = ep.kenta(); 
        String text = "";
        switch(broj)
        {
            case 1: text = "66";  break;
            case 2: text = "56"; break;
            case 3: text = "46"; break;
            case 0: text = "0"; break;
            default:     
        }
        postavljanjeIProvera(matrica[11][4],text);
        
        
    }
    public void najavaTriling() // najava trilinga
    {
        if(brojacPoteza>52) setDugmeZaBacanje();
        najavaBrojac++;
        DugmeKlikNajava(matrica[12][4]);
        brojPotezaNaj++;
        int broj = ep.triling()*3; 
        String text = "";
        if(broj!=0)
        {
            broj+=20;
            text = Integer.toString(broj);
        }
        else
        {
            text = Integer.toString(broj);
        }
        postavljanjeIProvera(matrica[12][4],text);
        
            
    }
    public void najavaFull() // najava fula
    {
        if(brojacPoteza>52) setDugmeZaBacanje();
        najavaBrojac++;
        DugmeKlikNajava(matrica[13][4]);
        brojPotezaNaj++;
        int[] pom = ep.full();
        String text;
        int ful = pom[0]*3+pom[1]*2;
        if(ful!=0)
        {
            ful +=30;
            text = Integer.toString(ful);
        }
        else
        {
            text = Integer.toString(ful);
        }
        postavljanjeIProvera(matrica[13][4],text);
            
    }
    public void najavaPoker() // najavaPokera
    {
        if(brojacPoteza>52) setDugmeZaBacanje();
        najavaBrojac++;
        DugmeKlikNajava(matrica[14][4]); // pomocna metoda
        brojPotezaNaj++;
        int broj = ep.poker()*4;
        String text;
        if(broj!=0)
        {
            broj+=40;
            text = Integer.toString(broj);
        }
        else
        {
            text = Integer.toString(broj);
        }
        postavljanjeIProvera(matrica[14][4],text); //pomocna metoda
    }
    public void najavaJamb() // najava jamba
    {
        if(brojacPoteza>52) setDugmeZaBacanje();
        najavaBrojac++;
        DugmeKlikNajava(matrica[15][4]);
        brojPotezaNaj++;
        int broj = ep.YAMB()*5;
        String text;
        if(broj!=0)
        {
            broj+=50;
            text = Integer.toString(broj);
        }
        else
        {
            text = Integer.toString(broj);
        }
        postavljanjeIProvera(matrica[15][4],text);
        
    }
    
    public void proveraSume3() // Zbir sume kente, trilinga, fula, pokera i jamba
    {
        if(!matrica[11][4].getText().equals("")&&!matrica[12][4].getText().equals("")&&!matrica[13][4].getText().equals("")&&!matrica[14][4].getText().equals("")&&!matrica[15][4].getText().equals(""))
        {
            int K = Integer.parseInt(matrica[11][4].getText());
            int T = Integer.parseInt(matrica[12][4].getText());
            int F = Integer.parseInt(matrica[13][4].getText());
            int P = Integer.parseInt(matrica[14][4].getText());
            int Y = Integer.parseInt(matrica[15][4].getText());
            int zbir = K+T+F+P+Y;
            String text = Integer.toString(zbir);
            matrica[16][4].setText(text);
            matrica[16][4].setFont(new Font("Arial",Font.BOLD,20));
        }     
    }
    
    private void DugmeKlikNajava(JButton ovo) // POMOCNA metoda koje proverava da li je neko dugme stisnuto
    {
        info6.setText("Najava");
        for(int i=1;i<17;i++)
           for(int j = 1;j<6;j++)
                {
                   if(matrica[i][j].equals(ovo)) continue;
                   matrica[i][j].setEnabled(false);
                }
    }
    private void postavljanjeIProvera(JButton ovo,String text) //POMOCNA Metoda za koja vrsti postavljanje dugmica posle upisa i upisuje text
    {
        if(ep.vratiBrojac()>0 && brojPotezaNaj>1)
        {
            ovo.setText(text);
            ovo.setFont(new Font("Arial",Font.BOLD,20));
            provera();
            ovo.setEnabled(false);
            ep.novoBacanje();
            info6.setText("");
            proveraSume3();
            najavaBrojac=0;
            brojPotezaNaj=0;
            krajIgre();
            
        } 
    }
    
    public void zakljucajNajavu() // Ako se ne najava na prvom bacanju, poziva se ova metoda koja zakljucava kolonu najave
    {
        for(int i = 1;i<16;i++)
        {
            matrica[i][4].setEnabled(false);
        }
    }
    public int getNajavaBrojac() // geter za staticki brojac
    {
        return najavaBrojac;
    }
    
    public void sumaRucno()
    {
        if(!matrica[1][5].getText().equals("") && !matrica[2][5].getText().equals("") && !matrica[3][5].getText().equals("") && !matrica[4][5].getText().equals("") && !matrica[5][5].getText().equals("") && !matrica[6][5].getText().equals(""))
        {
            int rezultat = 0; String text;
            rezultat += Integer.parseInt(matrica[1][5].getText()) + Integer.parseInt(matrica[2][5].getText()) + Integer.parseInt(matrica[3][5].getText()) + Integer.parseInt(matrica[4][5].getText()) + Integer.parseInt(matrica[5][5].getText()) + Integer.parseInt(matrica[6][5].getText());
            if(rezultat>=60) 
            {
                rezultat +=30; 
                text = Integer.toString(rezultat);
            }
            else {text = Integer.toString(rezultat);}
            matrica[7][5].setText(text);
            matrica[7][5].setFont(new Font("Arial",Font.BOLD,20));
            matrica[7][5].setEnabled(false);
        }
    }
    
    public void sumaRucno2()
    {
        if(!matrica[1][5].getText().equals("") && !matrica[8][5].getText().equals("") && !matrica[9][5].getText().equals(""))
        {
            int max = Integer.parseInt(matrica[8][5].getText());
            int min = Integer.parseInt(matrica[9][5].getText());
            int jedinice = Integer.parseInt(matrica[1][5].getText());
            int raz = (max - min)*jedinice;
            String razl = Integer.toString(raz);
            matrica[10][5].setText(razl);
            matrica[10][5].setFont(new Font("Arial",Font.BOLD,20));
            matrica[10][5].setEnabled(false);
        }
    }
    
    public void brojacPoteza()
    {
        brojacPoteza++;
        info.setText("Potez br: "+brojacPoteza);
    }
    
    public void proveraPoteza()
    {
        if(brojacPoteza>52)
        {
            if(!matrica[7][1].getText().equals("") && !matrica[10][1].getText().equals("") && !matrica[16][1].getText().equals("") && !matrica[7][2].getText().equals("") && !matrica[10][2].getText().equals("") && !matrica[16][2].getText().equals("") && !matrica[7][3].getText().equals("") && !matrica[10][3].getText().equals("") && !matrica[16][3].getText().equals("") && !matrica[7][5].getText().equals("") && !matrica[10][5].getText().equals("") && !matrica[16][5].getText().equals("")) 
            {
                south.postaviLabel("Ostala su vam polja u koloni najava za igru.");
                ep.setDugmeFalse(false);
            }
        }
    }
    
     public void postaviNulu(JButton a) // Postavljanje brojeva na dugme, zavisi od polja koje se otvori
    { 
            String broj = "0";
            a.setText(broj); a.setFont(new Font("Arial",Font.BOLD,20));
            a.setEnabled(false);
            info5.setText("Rezultat 0. Kockice nisu rucno bacene.");
            ep.novoBacanje();   
            south.postaviLabel("");
            
    }
     
    public void postaviInfo5()
    {
        info5.setText("");
    }
    
    public void setDugmeZaBacanje()
    {
        if(ep.vratiBrojac()==1) ep.setDugmeFalse(true);
    }
    
    public void krajIgre()
    {
        if(brojacPoteza==65)
        {
            int rezultat = 0;
            for(int j = 1;j<6;j++)
            {
                rezultat += Integer.parseInt(matrica[7][j].getText()) + Integer.parseInt(matrica[10][j].getText()) + Integer.parseInt(matrica[16][j].getText());
            }
            south.postaviLabel("IGRA ZAVRSENA. UKUPAN REZULTAT JE : "+rezultat);
            ep.setDugmeFalse(false);
            ep.postaviPom().setText("");
        }
    }
}
