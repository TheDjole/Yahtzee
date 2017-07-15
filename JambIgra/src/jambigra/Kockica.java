package jambigra;

import java.util.Random;

public class Kockica 
{
    private int broj;
    private boolean bacaj = true;
    private boolean rucno = true;
    
    
    public void baciKockicu(){
     if(bacaj==true)
     {
        Random r = new Random();
        broj = r.nextInt(6)+1;
     }   
      
    }

    public void setBacaj(boolean bacaj) {
        this.bacaj = bacaj;
    }

    public boolean isBacaj() {
        return bacaj;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public boolean isRucno() {
        return rucno;
    }

    public void setRucno(boolean rucno) {
        this.rucno = rucno;
    }
    
    
   
}
