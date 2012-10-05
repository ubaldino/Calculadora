/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import modelo.Calc;
import vista.Ventana;

/**
 * 
 *
 * @author Administrador
 */
public class Controlador implements ActionListener,KeyListener{
   private Calc modelo;
   private Ventana vista;
   private String titulo;
   HashMap<String,String> memoria=new HashMap<String, String>();
   int band=0;
   int contFix=1;
  
  public Controlador(Ventana vist ,Calc model, String tit){
    vista = vist;
    modelo = model;
    titulo=tit;
    vista.txt_fix.setText(""+contFix);
  }
  
  public void iniciar_vista(){
    vista.setTitle(titulo);
    vista.setLocationRelativeTo(null);
    /*  Agregando botones   */
    vista.btn_decBase.addActionListener(this);
    vista.btn_baseDec.addActionListener(this);
    vista.btn_igual.addActionListener(this);
    vista.btn_clear.addActionListener(this);
    vista.btn_memoria.addActionListener(this);
    vista.btn_totalS.addActionListener(this);
    vista.btn_totalC.addActionListener(this);
    vista.btn_fix.addActionListener(this);
    vista.btn_opBases.addActionListener(this);
    
    /*  Agregando comboBox  */
    vista.jcb_bases.addActionListener(this);
    vista.jcb_baseDec.addActionListener(this);
    vista.jcb_tiempo.addActionListener(this);
    vista.jcb_opBases.addActionListener(this);
        
    /*  Agregando TextField  */
    vista.txt_resultado.addKeyListener(null);
    vista.txt_resultado.addActionListener(null);
  }
  
  
  //La accion de los botones de la VISTA es capturado, asi como los valores
  //dependiendo del boton pulsado, se envia la informacion al modelo
  //y se espera la respuesta
  @Override
  public void actionPerformed(ActionEvent e) {
    //throw new UnsupportedOperationException("Not supported yet.");
    int val;
    String txt;
    Object boton = e.getSource();
    
    /**
      * btn_decBase 
    */
    if(boton == vista.btn_decBase){
      val = this.vista.jcb_bases.getSelectedIndex();
      txt = vista.txt_decBase.getText();
      if(txt.equals(""))
        vista.txt_decBase.setText("ingrese dato");
      else if(isNumber(txt)){
        if(val == 0){
          String aux=modelo.calEnBase().DecBinario(txt);
          vista.txt_decBase.setText(aux);
        }
        if(val == 1){
          String aux=modelo.calEnBase().DecOctal(txt);
          vista.txt_decBase.setText(aux);
        }
        if(val == 2){
          String aux=modelo.calEnBase().DecHexadecimal(txt);
          vista.txt_decBase.setText(aux);
        }
      }
      else
        vista.txt_decBase.setText("dato invalido");
    }
    
    /**
      * btn_baseDec 
    */
    if(boton == vista.btn_baseDec){
      val = vista.jcb_baseDec.getSelectedIndex();
      txt = vista.txt_baseDec.getText();
      if(txt.equals(""))
        vista.txt_baseDec.setText("ingrese dato");
      if(val == 0){
        if(validarBinario(txt)){
          String aux=modelo.calEnBase().BinDecimal(txt);
          vista.txt_baseDec.setText(aux);
        }else
          vista.txt_baseDec.setText("dato invalido");
      }
      if(val == 1){
        if(validarOctal(txt)){
          String aux=modelo.calEnBase().OctalDecimal(txt);
          vista.txt_baseDec.setText(aux);
        }else
          vista.txt_baseDec.setText("dato **invalido");
      }
      if(val == 2){
        if(validarHexa(txt)){
          String aux=modelo.calEnBase().HexDecimal(txt);
          vista.txt_baseDec.setText(aux);
        }
        else
          vista.txt_baseDec.setText("dato invalido");
      }
    }
    
    /**
     * Boton igual
     */
    if(boton==vista.btn_igual){
      band=0;
      String aux=vista.txt_resultado.getText();
      if(validar(aux)){
        String aux1=aux;
        addMemoria(aux);
        vista.txt_anuncio.setText(aux1);
        if(!aux.equals("")){
          String resultado=modelo.calBasica(aux,contFix);
          aux=resultado;
          vista.txt_resultado.setText(aux);
        }
      }
      else
        vista.txt_anuncio.setText("revise sus datos");
    }
    
    if(boton==vista.btn_memoria){
      band++;
      vista.txt_resultado.setText(getMemoria());
    }
    
    if(boton == vista.btn_totalS){
      val = vista.jcb_tiempo.getSelectedIndex();
      if(val==0){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "dias");
        vista.txt_totalS.setText(aux);
      }
      if(val==1){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "meses");
        vista.txt_totalS.setText(aux);
      }
      if(val==2){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "trimestres");
        vista.txt_totalS.setText(aux);
      }
      if(val==3){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "semestres");
        vista.txt_totalS.setText(aux);
      }
      if(val==4){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "anios");
        vista.txt_totalS.setText(aux);
      }
    }
    
    if(boton == vista.btn_totalC){
      val = vista.jcb_tiempo.getSelectedIndex();
      if(val==0){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "dias");
        vista.txt_totalC.setText(aux);
      }
      if(val==1){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "meses");
        vista.txt_totalC.setText(aux);
      }
      if(val==2){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "trimestres");
        vista.txt_totalC.setText(aux);
      }
      if(val==3){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "semestres");
        vista.txt_totalC.setText(aux);
      }
      if(val==4){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vista.txt_capInicial.getText()) ,Double.parseDouble(vista.txt_interes.getText()), Double.parseDouble(vista.txt_tiempo.getText()), "anios");
        vista.txt_totalC.setText(aux);
      }
    }
    if(boton==vista.btn_fix){
       btnFix();
       vista.txt_fix.setText(""+contFix);
    }
    if(boton == vista.btn_opBases){
      val=vista.jcb_opBases.getSelectedIndex();
      if(val==0){
      
      }
      if(val==1){
      
      }
    }
    
    
    
  }//fin actionPerformed
  
  @Override
  public void keyPressed(KeyEvent e) {
    //throw new UnsupportedOperationException("Not supported yet.");
    Object key =e.getSource();
    //if   (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE){
    
    if(e.getKeyCode() == KeyEvent.VK_ENTER){
      band=0;
      String aux=vista.txt_resultado.getText();
      if(validar(aux)){
        String aux1=aux;
        vista.txt_anuncio.setText(aux1);
        addMemoria(aux);
        String resultado=modelo.calBasica(aux, 0);
        aux=resultado;
        vista.txt_resultado.setText(aux);
      }
    }
      
  }// fin keyPressed
  
  /* Metodos adicionales */
  private boolean isNumber(String tex){
    boolean val=true;
    for(int i=0;i<tex.length();i++)
      if(Character.isLetter(tex.charAt(i))){
        val=false;
        i=tex.length();
      }
    return val;
  }
  private void btnFix(){
    if(contFix>=0 && contFix<9)
      contFix++;
    else
      contFix=0;
    //return cont;
  }
   
  private boolean validarBinario(String text){
    boolean val=true;
    for(int i=0;i<text.length();i++){
      if(Character.isLetter(text.charAt(i))){
        val=false;
        i=text.length();
      }
      else if(Integer.parseInt(""+text.charAt(i))<0 || Integer.parseInt(""+text.charAt(i))>1)
        val=false;
    }
    return val;
  }
  
  private boolean validarOctal(String text){
    text=text.toLowerCase();
    boolean band1=true;
    for(int i=0;i<text.length();i++){
      if(Character.isLetter(text.charAt(i))){
        band1=false;
        i=text.length();
      }
      else if(Integer.parseInt(""+text.charAt(i))<1 || Integer.parseInt(""+text.charAt(i))>7)
        band1=false;
    }
    return band1;
  }
  
  private boolean validarHexa(String text){
    text=text.toLowerCase();
    boolean band1=false;
    String validos = "123456789abcdef";
    //Va de caracter en caracter
    for(int i=0;i<text.length();i++){
      boolean valido=false;
      char car1 = text.charAt(i);
      for(int j=0;j<validos.length();j++){
        char car2 = validos.charAt(j); 
        if(car1==car2){
          valido=true;
          j=validos.length();
        }
      }
      if(valido==true){
        band1=true;
      }else{
        band1=false;
        i=text.length();
      }
    }
    return band1;
  }
  
  private boolean validar(String text){
    text=text.toLowerCase();
    boolean band1=false;
    String validos = "0123456789asincostlge.+-*/^()√";
    //Va de caracter en caracter
    for(int i=0;i<text.length();i++){
      boolean valido=false;
      char car1 = text.charAt(i);
      for(int j=0;j<validos.length();j++){
        char car2 = validos.charAt(j); 
        if(car1==car2){
          valido=true;
          j=validos.length();
        }
      }
      if(valido==true){
        band1=true;
      }else{
        band1=false;
        i=text.length();
      }
    }
    return band1;
  }
  
  private void addMemoria(String m){
    String aux="M"+(memoria.size()+1);
    memoria.put(aux,m);
  }
  private void getMemoria(String aux){
    Iterator it = memoria.entrySet().iterator();
    while(it.hasNext()){
      Map.Entry e = (Map.Entry)it.next();
      if(e.getKey().equals(aux)){
        System.out.println(aux + " -> " + e.getValue());
      }
    }
  }
  private String getMemoria(){
    String aux="";
    Iterator it = memoria.entrySet().iterator();
    Map.Entry e=null;
    int cont=0;
    while(it.hasNext()&&cont<=band){
      e = (Map.Entry)it.next();
      cont++;
      if(cont==band)
        aux=e.getValue().toString();
    }
    return aux;
  }
  public void leerArchivo(){
    try{
      // Abrimos el archivo
      FileInputStream flujo = new FileInputStream("expresion.txt"); // Creando objeto de entrada
      DataInputStream entrada = new DataInputStream(flujo);  // Creando flujo de Lectura
      BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
      String cadena;
      int cont=1;
      System.out.println("**** Lectura de archivo  *****");
      // Leer el archivo linea por linea
      while((cadena = buffer.readLine())!=null){
        // Imprimimos la linea por pantalla
        System.out.println("L"+(cont++)+": "+cadena);
        addMemoria(cadena);
        //ingresando a la calculadora
        System.out.println("Resultado: "+modelo.calBasica(cadena,0));
        System.out.println();
      }
      entrada.close();
    }
    catch (Exception e){ //Catch de excepciones
      //System.err.println("Ocurrio un error: " + e.getMessage());
      vista.txt_anuncio.setText("Ocurrio un error:");
    }
  }
  
  
  
  
  
  public static void main(String[] args) {
    //nuevas instancias de clase
    Calc modelo = new Calc();
    Ventana vista = new Ventana();
    Controlador controlador1 = new Controlador(vista, modelo,"Calculadora 1");
    controlador1.iniciar_vista();
    vista.setVisible(true);
  }

 // No utilizados 
  @Override
  public void keyTyped(KeyEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void keyReleased(KeyEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
