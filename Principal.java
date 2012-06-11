
/**
 * Write a description of class Principal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Principal{
  public static void main(String[] args){
    Calc cal = new Calc();
    String a="2+4-3^2";
    String b="2/4-3^2";
    String c="(2+4-3)^2";
    String d="999/52";
    System.out.println("--- prueba calculadora ----");
    System.out.println(a+"  = "+cal.calBasica(a,2));
    System.out.println(b+"  = "+cal.calBasica(b,0));
    System.out.println(c+" = "+cal.calBasica(c,5));
    System.out.println(d+" = "+cal.calBasica(d,0));
  }
}
