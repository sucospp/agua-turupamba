/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprimir;
    import java.awt.*;
import java.awt.print.*;
/**
 *
 * @author fpenafiel
 */
public class ObjetoDeImpresion implements Printable{
String fec="";
    String impr="";
String ced="";
String nom="";
String med="";
String rec="";
String bac="";
String ant="";
String act="";
String exc="0";
String mor="0";
String min="0";
String reins="0";
String asam="0";
String total="0";
    
    
    
    public ObjetoDeImpresion(String fecha,String cedu, String nomb, String medi, String reci,String baci,String anti, String acti,String exci,String mori, String mini, String reinsi,String asami,String tot){
   fec=fecha;
   ced=cedu;
   nom=nomb;
   med=medi;
   rec=reci;
   bac=baci;
   ant=anti;
   act=acti;
   exc=exci;
   mor=mori;
   min=mini;
   reins=reinsi;
   asam=asami;
   total=tot;
    
    }

public int print(Graphics g, PageFormat f, int pageIndex){
    f.setOrientation(0);
    if(pageIndex == 0){
g.drawString("FECHA: "+fec, 120, 90);
g.drawString("CEDULA: "+ced,120,110);
g.drawString("NOMBRE: "+nom,120,130);
g.drawString("MEDIDOR: "+med,120,150);
g.drawString("MES RECIBO: "+rec,120,170);
g.drawString("COBRO BASICO: $"+bac,120,190);
g.drawString("LECTURA ANTERIOR: "+ant,120,210);
g.drawString("LECTURA ACTUAL: "+act,120,230);
g.drawString("MULTA POR EXCESO: $"+exc,120,250);
g.drawString("MULTA POR MORA: $"+mor,120,270);
g.drawString("MULTA POR MINGA: $"+min,120,290);
g.drawString("MULTA POR REINSTALACION: $"+reins,120,310);
g.drawString("MULTA POR ASAMBLEA: $"+asam,120,330);
g.drawString("TOTAL $"+total,120,350);
g.drawString("FIRMA RESPONSABLE _____________",120,370);
g.drawString("FIRMA CLIENTE _____________",120,390);













return PAGE_EXISTS;
}else{
return NO_SUCH_PAGE;
}
}

public void imprim(){


}

}
