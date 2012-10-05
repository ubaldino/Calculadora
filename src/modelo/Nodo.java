
package modelo;
public class Nodo{
  private Nodo ant;
  private Tupla dato;
  private Nodo sig;
    
  public Nodo(){
    dato = new Tupla();
    ant = null;
    sig = null;
  }
  public Nodo(Tupla dat){
    this.dato = dat;
    //this.dato.setValor(dat.getValor());
    //this.dato.setOperador(dat.getOperator());
    this.ant = null;
    this.sig = null;
  }
    
  public Nodo(Nodo ant, Tupla dat, Nodo sig){
    this.dato.setValor(dat.getValor());
    this.dato.setOperador(dat.getOperator());
    this.ant = ant;
    this.sig = sig;
  }
    
  public void setDato(Tupla dat){
    this.dato.setValor(dat.getValor());
    this.dato.setOperador(dat.getOperator());
  }
    
  public void setAnt(Nodo ant){
    this.ant= ant;
  }
    
  public void setSig(Nodo sig){
    this.sig = sig;
  }
    
  public Tupla getDato(){
    return this.dato;
  }
    
  public Nodo getAnt(){
    return this.ant;
  }
    
  public Nodo getSig(){
    return this.sig;
  }
}

