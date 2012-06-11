/*
 * contenedor tiene q soportar
 *    operaciones cientificas
 *    log, ln, sen, cos, tan, ln-1,.....
   
 */
import java.math.RoundingMode; 
import java.math.BigDecimal;
public class Contenedor{
  String dato;
  public Contenedor(String dat){
    dato=dat;
    evaluar();
  }
  public void setDato(String dat){
    dato=dat;
    evaluar();
  }
  
  public String getDato(){
    return dato;
  }
  
  public void evaluar(){
    String exp=dato;
    String aux="";
    int tamDec=2;// tamaño de decimales
    switch(tipoOp(exp)){
      case "e": dato=""+Math.E;break;
      case "pi": dato=""+Math.PI;break;
      case "log": dato=""+Math.log10(Double.parseDouble(format(exp)));break;
      case  "ln": dato=""+Math.log(Double.parseDouble(format(exp)));break;
      case "sin": dato=""+Math.sin(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "asin": dato=""+Math.asin(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "cos": dato=""+Math.cos(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "acos": dato=""+Math.acos(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "tan": dato=""+Math.tan(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "atan": dato=""+Math.atan(Math.toRadians(Double.parseDouble(format(exp))));break;
    }
  }
  
  public String tipoOp(String dat){
    String aux="";
    for(int i=0;i<dat.length();i++)
      if(Character.isLetter(dat.charAt(i)))
        aux+=dat.charAt(i);
    return aux;
  }
  public String format(String dat){
    String aux="";
    for(int i=0;i<dat.length();i++)
      if(Character.isDigit(dat.charAt(i)) || dat.charAt(i)=='.')
        aux+=dat.charAt(i);
    return aux;
  }
  
  public static void main(String []args){
    Contenedor cont1=new Contenedor("log8");
    Contenedor cont2=new Contenedor("ln8");
    Contenedor cont3=new Contenedor("sin8");
    Contenedor cont4=new Contenedor("cos8");
    Contenedor cont5=new Contenedor("tan8");
    System.out.println("******************");
    System.out.println(cont1.getDato());
    System.out.println(cont2.getDato());
    System.out.println(cont3.getDato());
    System.out.println(cont4.getDato());
    System.out.println(cont5.getDato());
  }
}
