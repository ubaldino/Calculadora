public class Bases{
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
  
  public static void main(String args[]){
    Bases base=new Bases();
  }
}