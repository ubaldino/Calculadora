
package modelo;
public class Prueba{
  public static void main(String args[]){
    Financiera finanza=new Financiera();
    System.out.println("****  Prueba  ****");
    finanza.interesCompuesto(100,5,6,"dias");
    finanza.interesCompuesto(100,5,6,"meses");
    finanza.interesCompuesto(100,5,6,"trimestres");
    finanza.interesCompuesto(100,5,6,"semestres");
    finanza.interesCompuesto(100,5,6,"años");
  }
}

