
public class Financiera{
  
  /** Capital inicial
    * tasa de interes
    * tiempo + unidad
    * unidad -> d=dias;m=meses;s=semestres;a=anios
  */
  public void interesSimple(double capIni,double tInt,double tiemp,String unidad){
    String interesTotal="";
    //String tiempo=tiemp+unidad;
    //formula:  Is=Co*i*n
    if(unidad.equals("dias")){
      double aux = (tInt/100)/365;
      interesTotal=""+(capIni*aux*tiemp);
    }
    if(unidad.equals("meses")){
      double aux = (tInt/100)/12;
      interesTotal=""+(capIni*aux*tiemp);
    }
    if(unidad.equals("trimestres")){
      double aux = (tInt/100)/4;
      interesTotal=""+(capIni*aux*tiemp);
    }
    if(unidad.equals("semestres")){
      double aux = (tInt/100)/2;
      interesTotal=""+(capIni*aux*tiemp);
    }
    if(unidad.equals("años")){
      double aux = (tInt/100);
      interesTotal=""+(capIni*aux*tiemp);
    }
    System.out.println(interesTotal);
  }
  
  /** Capital inicial
    * tasa de interes
    * tiempo + unidad
    * unidad -> d=dias;m=meses;s=semestres;a=anios
    * formula -> Cf=Co*(1+r)^n
  */
  public void interesCompuesto(double capIni,double tInt,double tiemp,String unidad){
    String interesTotal="";
    //String tiempo=tiemp+unidad;
    //formula:  Is=Co*i*n
    
    if(unidad.equals("dias")){
      double aux = (1+((tInt/100)/365));
      interesTotal=""+(capIni*Math.pow(aux,(tiemp/365)));
    }
    if(unidad.equals("años")){
      double aux = (tInt/100)+1;
      interesTotal=""+(capIni*Math.pow(aux,tiemp));
    }
    System.out.println(interesTotal);
  }
}
