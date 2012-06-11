//Guarda y analiza secuencias
// para sus operaciones
public class Pila{
  int tope=-1;
  Object list[];
    
  Pila(){
    int max=100;
    list=new Object[max];
  }
  
  Pila(int max){
    list=new Object[max];
  }
  
  public boolean llena(){
    return tope==list.length-1;
  }
 
   public boolean vacia(){
    return tope==-1;
  }
  
  public void push(Object dato){
    if(!llena()){
      if(!vacia()){
        tope++;
        list[tope]=dato;  
      }else{
        tope=0;
        list[tope]=dato;
      }
    }else
      System.out.println("Overflow");
  }
  
  public Object top(){
    if(!vacia())
      return list[tope];
    else
      return null;   
  }
  
  public Object pop(){
    Object aux;
    if(!vacia()){  
      aux=list[tope];
      tope--;      
    }
    else{
      System.out.println("La pila esta vacia");
      return -1;
    }
    return aux;
  }
  
  public void Imprime_Datos(){
    if(!vacia())
      for(int i=0;i<list.length;i++)
        System.out.println("El valor de la pila en ["+i+"]:=> "+list[i]);
    else                        
      System.out.println("La pila esta vacia, ingrese datos primero:");
  }
}   
