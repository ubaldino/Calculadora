
/**
 * Write a description of class Prueba here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Prueba{
  public static void main(String args[]){
    Financiera finanza=new Financiera();
    System.out.println("****  Prueba  ****");
    finanza.interesSimple(100,5,6,"dias");
    finanza.interesSimple(100,5,6,"meses");
    finanza.interesSimple(100,5,6,"trimestres");
    finanza.interesSimple(100,5,6,"semestres");
    finanza.interesSimple(100,5,6,"años");
  }
}

