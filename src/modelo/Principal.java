
package modelo;

public class Principal{
  public static void main(String[] args){
    Calc cal = new Calc();
    String a="2+4-3^2";
    String b="2/4-3^2";
    String c="(2+4-3)^2";
    String d="999/52";
    String e="(5+3)*log3-tan10.5";
    System.out.println("--- prueba calculadora ----");
    System.out.println(a+"  = "+cal.calBasica(a,2));
    System.out.println(b+"  = "+cal.calBasica(b,0));
    System.out.println(c+" = "+cal.calBasica(c,5));
    System.out.println(d+" = "+cal.calBasica(d,3));
    System.out.println(e+" = "+cal.calBasica(e,4));
    System.out.println("--- prueba en Bases ----");
    

    System.out.println("Decimal   ->  Binario");
    String aux1=cal.calEnBase().DecBinario("43");
    String aux2=cal.calEnBase().DecBinario("27");
    System.out.println("43"+" = "+aux1);
    System.out.println("Decimal   ->  Octal");
    System.out.println("27"+" = "+cal.calEnBase().DecOctal("27"));
    System.out.println("Suma de binarios");
    System.out.println(aux1 + aux2+" = "+cal.calEnBase().sumaBinarios(aux1,aux2));
    
    
    System.out.println("--- prueba calculadora finanza ----");
    cal.calFinaciera().interesCompuesto(100, 5, 5,"meses");
  }
}
