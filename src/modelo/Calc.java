
package modelo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

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
      if(dec>0){
        BigDecimal redondear = new BigDecimal(resultado);  
        resultado = ""+redondear.setScale(dec,RoundingMode.CEILING);//UNNECESSARY || CEILING
      }
    }
    else
      //System.out.println("La expresion no es valida");
      resultado="La expresion no es valida";
    return resultado;
  }
  
  // Realiza operaciones fimancieras
  public Financiera calFinaciera(){
    Financiera finanza=new Financiera();
    return finanza;
  }
  // Realiza operaciones de sistemas de numeracion
  public Bases calEnBase(){
    Bases base=new Bases();
    return base;
  }
}
