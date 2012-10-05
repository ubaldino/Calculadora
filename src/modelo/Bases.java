
package modelo;
/** realizar opraciones con bases
  * Las principales   sumas y restas
  * 
 */
public class Bases{
  Pila pila = new Pila();
  Pila pila1 = new Pila();
  
  
  public String sumaBases(){
    String res="";
    return res;
  }
  
  public String restaBases(){
    String res="";
    return res;
  }
  /**
    * decimal a binario
   */
  public String DecBinario(String dec){
    int i = Integer.parseInt(dec);
    String bin = Integer.toBinaryString(i);
    return bin;
  }
  
  /**
    * binario a decimal
  */
  public String BinDecimal(String bin){
    int i = Integer.parseInt(bin,2);
    String dec = Integer.toString(i);
    return dec;
  }
  
  /**
    * decimal a octal
  */
  public String DecOctal(String dec){
    int i = Integer.parseInt(dec);
    String oct = Integer.toOctalString(i);
    return oct;  
  }
  
  /**
    * octal a decimal
  */
  public String OctalDecimal(String oct){
    int i = Integer.parseInt(oct,8);
    String dec = Integer.toString(i);
    return dec;  
  }
  
  /**
    * decimal a hexadecimal
  */
  public String DecHexadecimal(String dec){
    int i = Integer.parseInt(dec);
    String hex = Integer.toHexString(i);
    return hex;
  }
  
  /**
    * hexadecimal a decimal
  */
  public String HexDecimal(String hex){
    int i = Integer.parseInt(hex,16);
    String dec = Integer.toString(i);
    return dec;
  }
  
  public String BinOctal(String bin){
    int i = Integer.parseInt(DecOctal(BinDecimal(bin)));
    String oct = Integer.toString(i);
    return oct;
  }
  
  public String BinHex(String dec){
    int i = Integer.parseInt(BinDecimal(dec));
    String hex = Integer.toHexString(i);
    return hex;
  }
  
  public String OctalBin(String dec){
    int i = Integer.parseInt(OctalDecimal(dec));
    String bin = Integer.toBinaryString(i);
    return bin;
  }
  
  public String OctalHex(String dec){
    int i = Integer.parseInt(OctalDecimal(dec));
    String hex = Integer.toHexString(i);
    return hex;
  }
  
  public String HexBin(String dec){
    int i = Integer.parseInt(HexDecimal(dec));
    String bin = Integer.toBinaryString(i);
    return bin;
  }
  
  public String HexOctal(String bin){
    int i = Integer.parseInt(DecOctal(HexDecimal(bin)));
    String oct = Integer.toString(i);
    return oct;
  }
  
  
  
  /**
    * operaciones entre bases
  */
  
   
  
  public void separar(String exp){
    
    String a="";
    for(int i=0;i<exp.length();i++){
      char car = exp.charAt(i);
      if(car=='+' || car=='-' || car=='*' || car=='/'){
        pila.push(a);
        pila.push(""+car);
        a="";
      }else
        a+=car;
    }
    pila.push(a);
    
    while(!pila.vacia()){
      pila1.push(pila.pop());
    }
    //pila1.Imprime_Datos();
  }
  
  public String operarBinarios(String exp){
    separar(exp);
    Calc calc =new Calc();
    boolean band=false;
    String aux="";
    while(!pila1.vacia()){
      if(!band){
        aux=BinDecimal((String)pila1.pop());
        aux+=(String)pila1.pop();
        aux+=BinDecimal((String)pila1.pop());
        aux=calc.calBasica(aux,0);
        band=true;
      }
      else{
        aux+=(String)pila1.pop();
        aux+=BinDecimal((String)pila1.pop());
        aux=calc.calBasica(aux,0);
      }
    }
    aux=aux.replace(".0","");
    aux=DecBinario(aux);
    return aux;
  }
  
  
  
  
  public String sumaBinarios(String bins1, String bins2){
    String aux =""+DecBinario(""+Integer.parseInt(BinDecimal(bins1))+Integer.parseInt(BinDecimal(bins2)));
    return aux;
  }
  
  public static void main(String args[]){
    Bases base=new Bases();
    //base.separar("101+10");
    base.operarBinarios("101+10");
  }
}
