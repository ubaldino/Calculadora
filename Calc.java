import java.util.LinkedList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
public class Calc{
  public Calc(){
  }
    
  public String calBasica(String expre,int dec){
    String resultado="";
    Expresion exp = new Expresion();
    Sintaxis syn=new Sintaxis();
    
    if(syn.check(expre)){
      exp.separar(expre);
      LinkedList expr = exp.preFija();
      if((expr.size()!=0)&&(expr.size()!=2)){
        ArbolExpresion ar = new ArbolExpresion(expr);
        resultado = ""+ar.evaluar(); 
      }
    }
    else
      System.out.println("La expresion no es valida");
    if(dec>0){
      BigDecimal redondear = new BigDecimal(resultado);  
      resultado = ""+redondear.setScale(dec,RoundingMode.CEILING);//UNNECESSARY || CEILING
    }
    return resultado;
  }
  // Realiza operaciones fimancieras
  public String calFinaciera(String expre){
    /**
     * Interes simple
     * interes compuesto
     * rentas constantes *
     * 
     * 
     * 
       */
    String resultado="";
    return resultado;
  }
  // Realiza operaciones de sistemas de numeracion
  public String calEnBase(){
    Bases base=new Bases();
    Scanner scan=new Scanner(System.in);
    String resultado="";
    System.out.println("** Ingrese una opcion **");
    String caso=scan.nextLine();
    switch(caso){
      case "1":base.DecBinario(scan.nextLine());break;
      case "2":base.BinDecimal(scan.nextLine());break;
      case "3":base.DecOctal(scan.nextLine());break;
      case "4":base.OctalDecimal(scan.nextLine());break;
      case "5":base.DecHexadecimal(scan.nextLine());break;
      case "6":base.HexDecimal(scan.nextLine());break;
    }
    return resultado;
  }
}
