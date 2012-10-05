
package modelo;
public class Contenedor{
  private String dato, a,b;
  
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
  
  private void evaluar(){
    /**switch(tipoOp(dato)){
      case "e": dato=""+Math.E;break;
      case "pi": dato=""+Math.PI;break;
      case "log": dato=""+Math.log10(Double.parseDouble(format(exp)));break;
      case "ln": dato=""+Math.log(Double.parseDouble(format(exp)));break;
      case "sin": dato=""+Math.sin(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "asin": dato=""+Math.asin(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "cos": dato=""+Math.cos(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "acos": dato=""+Math.acos(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "tan": dato=""+Math.tan(Math.toRadians(Double.parseDouble(format(exp))));break;
      case "atan": dato=""+Math.atan(Math.toRadians(Double.parseDouble(format(exp))));break;
      default:; break;*/
      //if(tipoOp(dato).equals("e"))
        //dato=""+Math.E;
      if(tipoOp(dato).equals("pi"))
        dato=""+Math.PI;
      if(tipoOp(dato).equals("log"))
        dato=""+Math.log10(Double.parseDouble(format(dato)));
      if(tipoOp(dato).equals("ln"))
        dato=""+Math.log(Double.parseDouble(format(dato)));
      if(tipoOp(dato).equals("sin"))
        dato=""+Math.sin(Math.toRadians(Double.parseDouble(format(dato))));
      if(tipoOp(dato).equals("asin"))
        dato=""+Math.asin(Math.toRadians(Double.parseDouble(format(dato))));
      if(tipoOp(dato).equals("cos"))
        dato=""+Math.cos(Math.toRadians(Double.parseDouble(format(dato))));
      if(tipoOp(dato).equals("acos"))
        dato=""+Math.acos(Math.toRadians(Double.parseDouble(format(dato))));
      if(tipoOp(dato).equals("tan"))
        dato=""+Math.tan(Math.toRadians(Double.parseDouble(format(dato))));
      if(tipoOp(dato).equals("atan"))
        dato=""+Math.atan(Math.toRadians(Double.parseDouble(format(dato))));
      if(tipoOp(dato).equals("√"))
        dato=""+Math.sqrt(Double.parseDouble(format(dato))) ;
      if(tipoOp(dato).equals("e")){
        formatE(dato);
        dato=""+(Double.parseDouble(a)*Math.pow(10,Double.parseDouble(b)));  
      }
  }
  
  
  public String tipoOp(String dat){
    String aux="";
    for(int i=0;i<dat.length();i++){
      if(dat.charAt(i)=='√'){
        aux=""+dat.charAt(i);
        break;
      }
      else if(Character.isLetter(dat.charAt(i)))
        aux+=dat.charAt(i);
    }
    return aux;
  }
  
  public String format(String dat){
    String aux="";
    for(int i=0;i<dat.length();i++)
      if(Character.isDigit(dat.charAt(i)) || dat.charAt(i)=='.')
        aux+=dat.charAt(i);
    return aux;
  }
  
  
  public void formatE(String dat){
    boolean band=false;
    a="";
    b="";
    //System.out.println(dat);
    for(int i=0;i<dat.length();i++){
      if(dat.charAt(i)=='e'){
          band=true;
      }
      if( (Character.isDigit(dat.charAt(i)) || dat.charAt(i)=='.') && band==false){
          a+=""+dat.charAt(i);
        }
      else if((Character.isDigit(dat.charAt(i)) || dat.charAt(i)=='.'))
        b+=""+dat.charAt(i);
    }
    //System.out.println(a);
    //System.out.println(b);
  }
  
  /*public static void main(String []args){
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
  }*/
}
