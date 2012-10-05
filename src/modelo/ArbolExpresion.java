
package modelo;
import java.util.LinkedList;

public class ArbolExpresion{
  private Nodo Raiz;
  private int n;
    
  public ArbolExpresion(){
    this.Raiz  = null;
    this.n     = 0;
  }
  
  public Nodo getRaiz(){
    return this.Raiz;
  }
    
  public int getNodos(){
    return n;
  }
  
  public boolean Hoja(Nodo R){
    return ((R.getAnt()==null)&&(R.getSig()==null));
  }
    
  public void setRaiz(Nodo R){
    if(R!=null){
      this.Raiz = R;
      n++;
    }
    else
      this.Raiz = R;
  }
   //*******   El objetivo del arbol   *********
  public ArbolExpresion(LinkedList Exp){
    String aux =  (String)Exp.get(0);
    Tupla a = new Tupla();
    a.setValor(aux.toString());
    if(operador(aux))     
      a.setOperador(true);
    else
      a.setOperador(false);
    Nodo op,q = new Nodo(a);
    Pila p = new Pila();
    boolean antesOperando = false;
    Raiz = q;
    for(int i=1;i<Exp.size(); i++ ){
      String aux2 = (String)Exp.get(i);
      Tupla b = new Tupla(aux2.toString(),false);
      if(operador(aux2))
        b.setOperador(true);
      op = new Nodo(b);
      if(antesOperando){
        q = (Nodo)p.pop();
        q.setSig(op);
      }
      else{
        q.setAnt(op);
        p.push(q);
      }
      q = op;
      if(operador((String)Exp.get(i)))
        antesOperando = false;
      else
        antesOperando = true;
    }
  }
    
  private boolean operador(String c){
    String operadores[] = {"+","-","*","/","^"};
    boolean existe = false;
    String aux = c.toString();
    for(int i=0; ((i<5) && (!existe)); i++)
      if(operadores[i].equals(aux))
        existe = true;
    return existe;
  }
    
  public double evaluar(){
    return evaluar(Raiz);
  }
  private double evaluar(Nodo R){
    double res=0;
    if(R==null)
      return res=0;
    else{
      if(Hoja(R)){ // Operando
        String aux = R.getDato().getValor();
        res = Double.parseDouble(aux);
      }
      else{
        double vizq = evaluar(R.getAnt());
        double vder = evaluar(R.getSig());
        String op = R.getDato().getValor();
        /*switch(op){
          case "+" : res = vizq + vder;break;
          case "-" : res = vizq - vder;break;
          case "*" : res = vizq * vder;break;
          case "/" : res = vizq / vder;break;
          case "^" : res = Math.pow(vizq,vder);break;
          default:; break;
        }*/
        if(op.equals("+"))
          res = vizq + vder;
        if(op.equals("-"))
          res = vizq - vder;
        if(op.equals("*"))
          res = vizq * vder;
        if(op.equals("/"))
          res = vizq / vder;
        if(op.equals("^"))
          res = res = Math.pow(vizq,vder);
      }  
    }
    return res;
  }
}