import java.util.LinkedList;

public class Expresion{
  private String exp;
  public Expresion(){
  }
  public Expresion(String exp){
    this.exp=exp;
  }
  public void setExpresion(String exp){
    this.exp=exp;  
  }
  public String getExpresion(){
    return exp;
  }
  
  public LinkedList inFija(){
    LinkedList infija = new LinkedList();
    String expr = exp;//clasica
    int n = expr.length()-1;
    for(int i=0; i<=n; i++){
      infija.addLast(expr.charAt(i));
    }
    //System.out.println("InFija1: "+infija);
    return infija;
  }
  
  public String Infija(){
    LinkedList aux = inFija();
    String aux1="[ "; 
    for(int i=0;i<aux.size();i++){
      if((i+1)==aux.size()){
        aux1 = aux1 + aux.get(i).toString();
      }
      else{
        aux1 = aux1 + aux.get(i).toString()+" ";
      }
    }
    aux1 = aux1 + " ]";
    return aux1;
  }
  
  public LinkedList preFija(){
    Character c,d,e;
    int i,prioridadCima,prioridadOper;
    LinkedList expPre = new LinkedList();
    Pila aux = new Pila();
    Pila med = new Pila();
    Pila pre = new Pila();
    String Expr = exp;//clasica
    for(i=0;i<Expr.length();i++)
      aux.push(new Character(Expr.charAt(i)));
       
    while(!aux.vacia()){
      c = (Character)aux.pop();
      if(c.charValue() == ')'){
        med.push(c);
      }
      else{    
        if(c.charValue() == '('){
          e = (Character)med.top();
          while(e.charValue() != ')'){
            c = (Character) med.pop();
            pre.push(c);
            e = (Character) med.top();
          }
          c = (Character) med.pop();
        }
        else if(operador(c.charValue())){
          e = (Character) med.top(); 
          prioridadCima = prioridad(e);
          prioridadOper = prioridad(c);
          while(!med.vacia() && (prioridadOper < prioridadCima)){
            d = (Character) med.pop();
            pre.push(d);
            e = (Character) med.top();
            prioridadCima = prioridad(e);
          }
          med.push(c);
        }
        else
          pre.push(c);
      }
    }
    while(!med.vacia()){
      c = (Character) med.pop();
      pre.push(c);
    }
    while(!pre.vacia()){
      c = (Character) pre.pop(); 
      expPre.addLast(c.charValue());
    }       
    return expPre;
  }
  
  public boolean operador(char c){
    char operadores[] = {'+','-','*','/','^'};
    boolean op = false;
    for(int i=0;((i<5) && (!op)); i++)
      if(operadores[i] == c)       
        op = true;
    return op; 
  }
  
  public int prioridad(Character op){
    int r = 4;
    if(op != null){
      switch(op.charValue()){
        case ')' : r = 0;
        case '(' : r = 0; break;
        case '+' : r = 1;
        case '-' : r = 1; break;
        case '*' : r = 2;
        case '/' : r = 2; break;
        case '^' : r = 3;
      }
    }
    return r;
  }
  
  public LinkedList CompletoPrefija() throws Exception{
    LinkedList sep = Separar();
    LinkedList completo = new LinkedList();
    int n = sep.size()-1,j=0;
    LinkedList pfija = preFija();
    for(int i=0; ((i<pfija.size())&&(j<=n)) ;i++){
      Character e1 = (Character) pfija.get(i);
      if(Character.isLetter(e1)){
        completo.addLast(sep.get(j));
        j++;
      }
      else
        completo.addLast(e1);
    }
    return completo;
  }
  
  public LinkedList Separar(){
    int i=0;
    int n = exp.length()-1;
    String aux1,aux2;
    aux2=exp;
    aux1="";
    LinkedList e = new LinkedList();
    while((i<=n)){
      while((i<=n)&&(!Character.isDigit(aux2.charAt(i))))
        i++;
      aux1="";
      while((i<=n)&&((Character.isDigit(aux2.charAt(i)))||(aux2.charAt(i)=='.'))){    
        aux1=aux1+aux2.charAt(i);
        i++;
      }
      if(estaBien(aux1))
        e.add(aux1);
    }
    return e;
  }
  
  public boolean estaBien(String nro){
    return true;
  }
  
  public void recorrer(LinkedList test){
    for(int i=0;i<test.size();i++){
      System.out.print(test.get(i));
    }
    System.out.println();
  }
}