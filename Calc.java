import java.util.LinkedList;

public class Calc{
  public Calc(){
  }
    
  public double calcular(String expre) throws Exception{
    double resultado=0;
    String aux = expre;
    Comprobador scanner = new Comprobador();
    aux = scanner.scan(aux);
    if(aux.equals("P")||aux.equals("O")){     
      if(aux.equals("P"))
        throw new Exception("Verifique los parentesis.");
      else
        throw new Exception("El numero de operadores no es correcto.");
    }
    else{
      Expresion e = new Expresion(aux);
      LinkedList exp = e.CompletoPrefija();
      if((exp.size()!=0)&&(exp.size()!=2)){
        ArbolExpresion ar = new ArbolExpresion(exp);
        resultado = ar.evaluar(); 
      }
      else
        throw new Exception("Verifique la expresion.");
    }
    return resultado;   
  }
}
