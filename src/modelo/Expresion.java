
package modelo;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.ArrayList;
public class Expresion{
  public ArrayList<String> cad=new ArrayList<String>();//(tam*2)+1
  public String exp;
  
  public void separar(String letra){
    String let=letra.toLowerCase(); //   log8888+(5-244)+4-6/2.4^(5)
    let=let.trim();
    //let=token(let);
    
    int i,c,ind=0;
    for(c=0;let.length()>0;c++){
      int cont=0;
      String aux="";
      if(let.charAt(0)=='+' || let.charAt(0)=='-' || let.charAt(0)=='*' || let.charAt(0)=='/' || let.charAt(0)=='^'|| let.charAt(0)=='(' || let.charAt(0)==')'){
        aux=""+let.charAt(0);
        let=let.substring(1);
      }
      else{
        for(i=0;i<let.length();i++)
          if(let.charAt(i)!='+' && let.charAt(i)!='-' && let.charAt(i)!='*' && let.charAt(i)!='/' && let.charAt(i)!='^'&& let.charAt(i)!='(' && let.charAt(i)!=')'){
            aux=aux+let.charAt(i);
            cont++;
          }
          else
            break;
        let=let.substring(cont);
      }
      //System.out.println(let);
      cad.add(aux);
    }
  }
  
  public void evaluar(){
    System.out.println("------****-------");
    String op="";
    for(String aux: cad){
      String aux1 = aux.toString();
      //for(int i=0;i<cad.length();i++){
        if(aux1.charAt(0)=='l'){
          op=op+(int)Math.log(5);
          aux.replaceAll(aux1,op);
        }
      //}
    }
    //for(String aux: cad)
      //System.out.println(aux.toString());
  }
  
  public void preffija(){
    Pila aux=new Pila();
    for(int i=0;i<cad.size();i++)
      aux.push(cad.get(i));
    aux.Imprime_Datos();
  }
  
  public LinkedList preFija(){
    LinkedList expPre = new LinkedList();
    String c,e,d;
    int i,prioridadCima,prioridadOper;
    
    Pila aux = new Pila();
    Pila med = new Pila();
    Pila pre = new Pila();
    // ingresando a la pila aux ArrayList(cad)
    for(i=0;i<cad.size();i++){
      String expr=cad.get(i);
      Contenedor cont=new Contenedor(expr);
      String valor=cont.getDato();
      aux.push(valor);
    }
    //final ingreso a la pila aux
       
    while(!aux.vacia()){
      c = (String)aux.pop();//(String)
      if(c.equals(")"))
        med.push(c);
      else if(c.equals("(")){
        e = (String)med.top();
        while(!e.equals(")")){
          c = (String)med.pop();
          pre.push(c);
          e = (String)med.top();
        }
        c = (String)med.pop();
      }
      else if(operador(c.toString())){
        e = (String)med.top(); 
        prioridadCima = prioridad(e);
        prioridadOper = prioridad(c);
        while(!med.vacia() && (prioridadOper < prioridadCima)){
          d = (String)med.pop();
          pre.push(d);
          e = (String)med.top();
          prioridadCima = prioridad(e);
        }
        med.push(c);
      }
      else
        pre.push(c);
    }
  
    while(!med.vacia()){
      c = (String)med.pop();
      pre.push(c);
    }
    while(!pre.vacia()){
      c = (String)pre.pop(); 
      expPre.addLast(c.toString());
    }       
    return expPre;
  }
  
  /*
    public LinkedList CompletoPrefija(){
    LinkedList sep = Separar();
    LinkedList completo = new LinkedList();
    int n = sep.size()-1,j=0;
    LinkedList pfija = preFija();
    for(int i=0; ((i<pfija.size()) && (j<=n)) ;i++){
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
  
  */
  public boolean operador(String c){
    String operadores[] = {"+","-","*","/","^"};
    boolean op = false;
    for(int i=0;((i<5) && (!op)); i++)
      if(operadores[i].equals(c))       
        op = true;
    return op; 
  }  
  public int prioridad(String op){
    int r = 4;
    if(op != null){
      /*switch(op.toString()){
        case ")" : r = 0;
        case "(" : r = 0; break;
        case "+" : r = 1;
        case "-" : r = 1; break;
        case "*" : r = 2;
        case "/" : r = 2; break;
        case "^" : r = 3;
      }*/
      if(op.toString().equals("(") || op.toString().equals(")"))
        r=0;
      if(op.toString().equals("+") || op.toString().equals("-"))
        r=1;
      if(op.toString().equals("*") || op.toString().equals("/"))
        r=2;
      if(op.toString().equals("^"))
        r=3;
    }
    return r;
  }
  
  public String token(String text){
    String aux="";
    StringTokenizer tokens=new StringTokenizer(text," ");
    while(tokens.hasMoreTokens()){
      aux+=tokens.nextToken();
    }
    return aux;
  }
  public int contOp(String let){
    int cont=0;
    for(int i=0;i<let.length();i++)
      if((let.charAt(i)=='+')||let.charAt(i)=='-'||(let.charAt(i)=='*') || let.charAt(i)=='/' || let.charAt(i)=='^' || let.charAt(i)==')')
        cont++;
    return cont;
  }
  public void recorrer(){
    System.out.println("**** Recorrido cadena *****");
    for(String aux: cad)
      System.out.println(aux);
    //System.out.println();
    evaluar();
  }
  
  public void ingresarPila(){
    Pila aux=new Pila();
    for(int i=0;i<cad.size();i++){
      String expr=cad.get(i);
      Contenedor cont=new Contenedor(expr);
      String valor=cont.getDato();
      aux.push(valor);
    }
    aux.Imprime_Datos();
  }
  //*************************************************
/*  public static void main(String arg[]){
    String letra ="log12.3+(sin5-244)+4-6/tan2.4^(5-3)";
    String cadena = "abcdefgh";
    
    Cadena cade=new Cadena();
    System.out.println("**** separado de letras *****");
    //System.out.println(letra);
    cade.separar(letra);
    cade.recorrer();
    System.out.println("**** ingreso a la pila *****");
    cade.ingresarPila();
    /**System.out.println("empieza");
    for(int i=0;i<cad.length;i++){
      if(Pattern.matches("^s$",cad[i]))
        System.out.println(cad[i]);
    }/
  }*/ 
}