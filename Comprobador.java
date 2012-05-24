
public class Comprobador{
  public String scan(String exp) throws Exception{
    String x = limpiarEspacios(exp);
    if(okParentesis(x))
      if(okOperadores(x))
        return x;
      else
        x="O";//operador
      else
        x="P";//parentesis
    return x;
  }
  
  public String limpiarEspacios(String exp){
    String aux = "";
    for(int i=0;i<exp.length();i++){
      if(exp.charAt(i)!=' ')
        aux = aux + exp.charAt(i);
    }
    return aux;
  }
  
  public boolean okParentesis(String exp){
    Object aux;
    Pila p = new Pila();
    for(int i=0;i<exp.length();i++){
      if(exp.charAt(i)=='(')
        p.push(exp.charAt(i));
      else if(exp.charAt(i)==')'){
        if(p.vacia())
          return false;
        else
          aux = p.pop();
      }
    }
    return p.vacia();
  }
  
    public String limpiarParentesis(String exp){
    String aux="";
    for(int i=0; i<exp.length();i++){
      if((exp.charAt(i)!='(')&&(exp.charAt(i)!=')'))
        aux = aux + exp.charAt(i);
    }
    return aux;
  }
  
  public boolean Operador(char x){
    boolean op = false;
    switch(x){
      case '+'   : op = true; break;
      case '-'   : op = true; break;
      case '*'   : op = true; break;
      case '/'   : op = true; break;
      case '^'   : op = true; break;
      //case "ln"  : op = true; break;
      //case "log" : op = true; break;
      //case "e"   : op = true; break;
      //case "sin" : op = true; break;
      //case "cos" : op = true; break;
      //case "tan" : op = true; break;
    }  
    return op;
  }
  
  public boolean okOperadores(String exp1){
    String exp = limpiarParentesis(exp1);
    if(Operador(exp.charAt(0)) || (Operador(exp.charAt(exp.length()-1))))
      return false;
    else{
      boolean op=true;
      for(int i=1; i<exp.length()-1;i++){
        if(Operador(exp.charAt(i))&&(Operador(exp.charAt(i+1))))
          return false;
      }
      return op;
    }
  }
}
