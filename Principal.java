
/**
 * Write a description of class Principal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Principal{
  public static void main(String arg[])throws Exception{
    Calc cal = new Calc();
    String a="2+4-3^2";
    String b="2/4-3^2";
    String c="(2+4-3)^2";
    System.out.println("--- prueba calculadora ----");
    System.out.println(a+"  = "+cal.calcular(a));
    System.out.println(b+"  = "+cal.calcular(b));
    System.out.println(c+" = "+cal.calcular(c));
  }
}
