
public class Tupla{
  private String  valor;
  private boolean operador;
    
  public Tupla(){
    operador = false;
    valor= "";
  }
    
  public Tupla(String valor, boolean  op){
    this.valor = valor;
    operador = op;
  }
    
  public void setValor(String valor){
    this.valor = valor;
  }
    
  public void setOperador(boolean op){
    this.operador = op;
  }
    
  public String getValor(){
    return valor;
  }
    
  public boolean getOperator(){
    return operador;
  }
}
