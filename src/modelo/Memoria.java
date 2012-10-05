/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Memoria {
        //  M1+M2-M3
  Pila pila =new Pila();
  HashMap<String,String> memoria=new HashMap<String, String>();
  int bandFix=0,contMem=0;
  
  
  
  
  public boolean esMemoria(String mem){
    boolean aux=false;//M1+M2
     if(mem.charAt(0)=='M')
       aux=true;
    return aux;
  }
  //**************************************
  
  //*****************************************  
  
  
  public void addMemoria(String m){
    contMem+=1;
    String aux="M"+contMem;
    memoria.put(aux,m);
    
  }
  public String getMemoriaDialog(){
    String var="";
    Iterator it = memoria.entrySet().iterator();
    while(it.hasNext()){
      Map.Entry e = (Map.Entry)it.next();
      Calc calc=new Calc();
      String aux=calc.calBasica((String)e.getValue(),0);
      var+=e.getKey()+": "+e.getValue()+" = "+aux+"\n------------------------------\n";
    }
    return var;
  }
  public String getMemoria(){
    String aux="";
    Iterator it = memoria.entrySet().iterator();
    Map.Entry e=null;
    int cont=0;
    while(it.hasNext()&&cont<=bandFix){
      e = (Map.Entry)it.next();
      cont++;
      if(cont==bandFix)
        aux=e.getValue().toString();
    }
    return aux;
  }
  public HashMap getMem(){
      return memoria;
  }
  
  public void setBanFix(int a){
    bandFix=a;
  }
  public int getBanFix(){
    return bandFix;
  }
  
  
  private int cantMemorias(String mem){
    mem=mem.trim();
    int i,cont=0;
    for(i=0;i<mem.length();i++){
      if(mem.charAt(i)=='M'){//&& Character.isDigit(mem.charAt(i+1))
        cont++;
      }
    }
    return cont;
  }
  
  private void capturarMemorias(String mem){
    String aux ="";
    for(int i=0;i<mem.length();i++){
      if(mem.charAt(i)=='M'){
        aux=""+mem.charAt(i);
        int cont=i+1;
        while(Character.isDigit(mem.charAt(cont))){
          aux=aux+""+mem.charAt(cont);
          cont++;
          if(cont==mem.length()){
            i=mem.length();
            break;
          }
        }
        pila.push(aux);
      }
    }
    //pila.Imprime_Datos();
  }
  
  public String calcularMemorias(String mem){
    String aux=mem;
    //System.out.println(aux);
    capturarMemorias(aux);
    Calc calc=new Calc();
    int band=cantMemorias(aux);
    int i=1;
    while(i<=band){
      String dato=(String)pila.pop();
      //System.out.println(dato);
      String exp=memoria.get(dato);
      //System.out.println(exp);
      String result=calc.calBasica(exp,0);
      aux=aux.replace(dato,result);
      i++;
    }
    //System.out.println(aux);
    return aux;
  }
  
  //**********************************************************
  public static void main(String args[]){
    Memoria memoria=new Memoria();
    memoria.addMemoria("1");
    memoria.addMemoria("2");
    memoria.addMemoria("3");
    memoria.addMemoria("4");
    //System.out.println(memoria.getMemoriaDialog());
    //String a="M1+M2+sinM3+(M4)";
    String a="M1+M2";
    //memoria.capturarMemorias(a);
    //System.out.println("cantMemorias: "+);
    //System.out.println("Memorias: "+memoria.getMemoriaDialog());
    System.out.println("calcular: "+memoria.calcularMemorias(a));
    
  }
}
