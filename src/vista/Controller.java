/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import modelo.Calc;
import modelo.Expresion;
import modelo.Memoria;
/**
 *
 * @author Administrador
 */

public class Controller implements ActionListener,ItemListener,KeyListener,WindowListener{
   private Calc modelo;
   private Vbasica vbasica;
   Vbooleana vboleana;
   Vestadisca vestadistica;
   Memoria memoria;
   Dlg_info dlg_info;
   JFileChooser abrirArchivo = null;
   private String titulo;
   int contFix=1;
   
  
  
  public Controller(Vbasica vist ,Calc model, String tit){
    vbasica = vist;
    modelo = model;
    titulo=tit;
    vboleana=new Vbooleana();
    vestadistica=new Vestadisca();
    vbasica.txt_fix.setText(""+contFix);
    memoria = new Memoria();
    dlg_info=new Dlg_info(vbasica, true);
  }
  
  public void iniciar_vista(){
    //frame.setIconImage (new ImageIcon("fichero.gif").getImage());
    /* Personalizacion de las ventanas */
    vbasica.setTitle(titulo);
    vbasica.setLocationRelativeTo(null);
    vbasica.setLocation(350,150);
    vbasica.setSize(300,450);
    
    
    vboleana.setTitle("Calculadora Booleana");
    vboleana.setLocationRelativeTo(null);
    vboleana.setLocation(350,150);
    vboleana.setSize(300,450);
    vestadistica.setTitle("Calculadora Estadistica");
    vestadistica.setLocationRelativeTo(null);
    vestadistica.setLocation(350,150);
    vestadistica.setSize(300,450);
    
    
    dlg_info.setTitle("Memoria Calculadora");
    dlg_info.setAlwaysOnTop(false);
    dlg_info.setSize(260,450);
    dlg_info.setLocation(87,150);// ( X | Y )
    
    
    /*  Agregando botones   */
    vbasica.btn_igual.addActionListener(this);
    vbasica.item_booleana.addActionListener(this);
    vbasica.item_estadistica.addActionListener(this);
    vbasica.btn_file.addActionListener(this);
    vbasica.btn_memoria.addActionListener(this);
    vbasica.btn_fix.addActionListener(this);
    vbasica.btn_listaMemoria.addActionListener(this);
    vbasica.item_salir.addActionListener(this);
    vbasica.item_compl.addActionListener(this);
    
    
    
    vboleana.item_basica.addActionListener(this);
    vboleana.item_estadistica.addActionListener(this);
    vboleana.item_salir.addActionListener(this);
    vboleana.btn_igual.addActionListener(this);
    vboleana.chb_operaciones.addActionListener(this);
    vboleana.chb_conversiones.addActionListener(this);
    
    vestadistica.item_basica.addActionListener(this);
    vestadistica.item_booleana.addActionListener(this);
    vestadistica.item_salir.addActionListener(this);
    vestadistica.item_intSimple.addActionListener(this);
    vestadistica.item_intCompuesto.addActionListener(this);
    vestadistica.btn_totalS.addActionListener(this);
    vestadistica.btn_totalC1.addActionListener(this);
    
    /*  Agregando comboBox  */
    //vista.jcb_bases.addActionListener(this);
 
        
    /*  Agregando TextField  */
    vbasica.txt_resultado.addKeyListener(null);
    
  }
  
  
  //La accion de los botones de la VISTA es capturado, asi como los valores
  //dependiendo del boton pulsado, se envia la informacion al modelo
  //y se espera la respuesta
  @Override
  public void actionPerformed(ActionEvent e) {
    //throw new UnsupportedOperationException("Not supported yet.");
    Object boton = e.getSource();
    
    // vista calBasica
    if(boton==vbasica.btn_file)
      leerArchivo();
    if(boton==vbasica.btn_memoria){
       int a=1+memoria.getBanFix();
      memoria.setBanFix(a);
      vbasica.txt_resultado.setText(memoria.getMemoria());
    }
    if(boton==vbasica.btn_igual)
      btnIgual();
    if(boton==vbasica.btn_listaMemoria){
      System.out.println("***** Jdialog *****");
      capturarJDialog();
    }
    if(boton==vbasica.btn_fix){
      btnFix();
      vbasica.txt_fix.setText(" "+contFix);
    }
    // vista calBooleana
    if(boton==vboleana.btn_igual){
      if(vboleana.chb_conversiones.isSelected())
        convBoolean(vboleana.txt_result.getText());
      else if(vboleana.chb_operaciones.isSelected())
        opeBoolean(vboleana.txt_result.getText());
    }
    // vista estadistica
    
    else if(boton==vestadistica.item_intSimple){
      vestadistica.pn_interesSimp.setVisible(true);
      vestadistica.pn_interesComp.setVisible(false);
    }
    if(boton==vestadistica.item_intCompuesto){
      vestadistica.pn_interesComp.setVisible(true);
      vestadistica.pn_interesSimp.setVisible(false);
    }
    if(boton==vbasica.item_salir||boton==vestadistica.item_salir||boton==vboleana.item_salir){
      System.exit(0);
    }
    if(boton==vestadistica.btn_totalS){
      int val = vestadistica.jcb_tiempo.getSelectedIndex();
      if(val==0){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vestadistica.txt_capInicial.getText()) ,Double.parseDouble(vestadistica.txt_interes.getText()), Double.parseDouble(vestadistica.txt_tiempo.getText()), "dias");
        vestadistica.txt_totalS.setText(aux);
      }
      if(val==1){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vestadistica.txt_capInicial.getText()) ,Double.parseDouble(vestadistica.txt_interes.getText()), Double.parseDouble(vestadistica.txt_tiempo.getText()), "meses");
        vestadistica.txt_totalS.setText(aux);
      }
      if(val==2){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vestadistica.txt_capInicial.getText()) ,Double.parseDouble(vestadistica.txt_interes.getText()), Double.parseDouble(vestadistica.txt_tiempo.getText()), "trimestres");
        vestadistica.txt_totalS.setText(aux);
      }
      if(val==3){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vestadistica.txt_capInicial.getText()) ,Double.parseDouble(vestadistica.txt_interes.getText()), Double.parseDouble(vestadistica.txt_tiempo.getText()), "semestres");
        vestadistica.txt_totalS.setText(aux);
      }
      if(val==4){
        String aux=modelo.calFinaciera().interesSimple(Double.parseDouble(vestadistica.txt_capInicial.getText()) ,Double.parseDouble(vestadistica.txt_interes.getText()), Double.parseDouble(vestadistica.txt_tiempo.getText()), "anios");
        vestadistica.txt_totalS.setText(aux);
      }
    }
    if(boton==vestadistica.btn_totalC1){
      int val = vestadistica.jcb_tiempo1.getSelectedIndex();
      if(val==0){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vestadistica.txt_capInicial1.getText()) ,Double.parseDouble(vestadistica.txt_interes1.getText()), Double.parseDouble(vestadistica.txt_tiempo1.getText()), "dias");
        vestadistica.txt_totalC1.setText(aux);
      }
      if(val==1){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vestadistica.txt_capInicial1.getText()) ,Double.parseDouble(vestadistica.txt_interes1.getText()), Double.parseDouble(vestadistica.txt_tiempo1.getText()), "meses");
        vestadistica.txt_totalC1.setText(aux);
      }
      if(val==2){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vestadistica.txt_capInicial1.getText()) ,Double.parseDouble(vestadistica.txt_interes1.getText()), Double.parseDouble(vestadistica.txt_tiempo1.getText()), "trimestres");
        vestadistica.txt_totalC1.setText(aux);
      }
      if(val==3){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vestadistica.txt_capInicial1.getText()) ,Double.parseDouble(vestadistica.txt_interes1.getText()), Double.parseDouble(vestadistica.txt_tiempo1.getText()), "semestres");
        vestadistica.txt_totalC1.setText(aux);
      }
      if(val==4){
        String aux=modelo.calFinaciera().interesCompuesto(Double.parseDouble(vestadistica.txt_capInicial1.getText()) ,Double.parseDouble(vestadistica.txt_interes1.getText()), Double.parseDouble(vestadistica.txt_tiempo1.getText()), "anios");
        vestadistica.txt_totalC1.setText(aux);
      }
    }
    if(boton==vbasica.item_compl){
      vboleana.setLocation(650,150);//(300)
      vboleana.setVisible(true);
      vestadistica.setLocation(50,150);
      vestadistica.setVisible(true);
    }
    else
      cambioDeVentanas(boton);
  }
  //==============================================
  
  ///*********  metosçdos estadisticos  *************** 
    
  
  
  
  ///**********  metodos adicionales  *******************
  private void convBoolean(String txt){
    if(vboleana.rbtn_decimal.isSelected()){
      if(vboleana.rbtn_decimal1.isSelected()){}
      if(vboleana.rbtn_binario1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().DecBinario(txt));
      if(vboleana.rbtn_octal1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().DecOctal(txt));
      if(vboleana.rbtn_hexad1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().DecHexadecimal(txt));
    }
    if(vboleana.rbtn_binario.isSelected()){
      if(vboleana.rbtn_decimal1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().BinDecimal(txt));
      if(vboleana.rbtn_binario1.isSelected()){}
      if(vboleana.rbtn_octal1.isSelected()){
        vboleana.txt_result.setText(modelo.calEnBase().BinOctal(txt));
      }
      if(vboleana.rbtn_hexad1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().BinHex(txt));
    }
    if(vboleana.rbtn_octal.isSelected()){
      if(vboleana.rbtn_decimal1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().OctalDecimal(txt));
      if(vboleana.rbtn_binario1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().OctalBin(txt));
      if(vboleana.rbtn_octal1.isSelected()){}
      if(vboleana.rbtn_hexad1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().OctalHex(txt));
      
    }
    if(vboleana.rbtn_hexad.isSelected()){
      if(vboleana.rbtn_decimal1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().HexDecimal(txt));
      if(vboleana.rbtn_binario1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().HexBin(txt));
      if(vboleana.rbtn_octal1.isSelected())
        vboleana.txt_result.setText(modelo.calEnBase().HexOctal(txt));
      if(vboleana.rbtn_hexad1.isSelected()){}
    }  
  }
  // operaciopnes booleanas
  private void opeBoolean(String txt){
    String aux="";
    aux=modelo.calEnBase().operarBinarios(txt);
    
    vboleana.txt_result.setText(aux);
  }
  
  private void capturarJDialog(){
    //System.out.println(getMemoriaDialog());
    dlg_info.txtA_info.setText(memoria.getMemoriaDialog());
    dlg_info.setVisible(true);
    dlg_info.dispose();
    
    
  }
  private void btnFix(){
    if(contFix>=0 && contFix<9)
      contFix++;
    else
      contFix=0;
    //return cont;
  }
  
  private void btnIgual(){
      memoria.setBanFix(0);
      String aux=vbasica.txt_resultado.getText();
      aux=aux.trim();
      Expresion e=new Expresion();
      aux=e.token(aux);
    if(!aux.equals("")){
        if(memoria.esMemoria(aux)){
            vbasica.txt_resultado.setText(" "+modelo.calBasica(memoria.calcularMemorias(aux),contFix));
            vbasica.txt_anuncio.setText(" "+aux);
        }
        else if(validar(aux)){
            String aux1=aux;
            memoria.addMemoria(aux);
            vbasica.txt_anuncio.setText(" "+aux1);
            if(!aux.equals("")){
                String resultado=modelo.calBasica(aux,contFix);
                aux=resultado;
                vbasica.txt_resultado.setText(" "+aux);
            }
        }
        else
            vbasica.txt_anuncio.setText("revise sus datos");
    }
    dlg_info.txtA_info.setText(memoria.getMemoriaDialog());
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
  
  public void leerArchivo(){
    String ruta="";
    if(abrirArchivo == null )
        abrirArchivo = new JFileChooser();
      //Con esto solamente podamos abrir archivos
      abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
      int seleccion = abrirArchivo.showOpenDialog(vbasica);
      if(seleccion == JFileChooser.APPROVE_OPTION){
        File f = abrirArchivo.getSelectedFile();
        ruta = f.getAbsolutePath();
      }
      try{
      // Abrimos el archivo
      FileInputStream flujo = new FileInputStream(ruta); // Creando objeto de entrada
      DataInputStream entrada = new DataInputStream(flujo);  // Creando flujo de Lectura
      BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
      String cadena;
      System.out.println("**** Lectura de archivo  *****");
      // Leer el archivo linea por linea
      while((cadena = buffer.readLine())!=null){
        // Imprimimos la linea por pantalla
        //System.out.println("L"+(cont++)+": "+cadena);
        memoria.addMemoria(cadena);
        //ingresando a la calculadora
        //System.out.println("Resultado: "+modelo.calBasica(cadena,0));
        //System.out.println();
      }
      entrada.close();
    }
    catch (Exception e){ //Catch de excepciones
      //System.err.println("Ocurrio un error: " + e.getMessage());
      vbasica.txt_anuncio.setText("Error de datos:");
    }
  }
  
  private void cambioDeVentanas(Object btn){
    if(btn == vbasica.item_booleana){
      vboleana.setVisible(true);
      vbasica.setVisible(false);
      vestadistica.setVisible(false);
    }
    if(btn == vbasica.item_estadistica){
      vestadistica.setVisible(true);
      vboleana.setVisible(false);
      vbasica.setVisible(false);
    }
    if(btn==vboleana.item_basica){
      vbasica.setVisible(true);
      vboleana.setVisible(false);
      vestadistica.setVisible(false);
    }
    if(btn==vboleana.item_estadistica){
      vestadistica.setVisible(true);
      vboleana.setVisible(false);
      vbasica.setVisible(false);
    }
    if(btn == vestadistica.item_basica){
      vbasica.setVisible(true);
      vboleana.setVisible(false);
      vestadistica.setVisible(false);
      
    }
    if(btn == vestadistica.item_booleana){
      vboleana.setVisible(true);
      vbasica.setVisible(false);
      vestadistica.setVisible(false);
    }
  }
  
  //**********************************************
  public static void main(String[] args) {
    //nuevas instancias de clase
    try{
      Vbasica.setDefaultLookAndFeelDecorated(true);
      Dlg_info.setDefaultLookAndFeelDecorated(true);
      //SubstanceLookAndFeel.setSkin( "org.jvnet.substance.skin.EmeraldDuskSkin" );
      //      SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceBottleGreenTheme");
      //      SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceKatakanaWatermark");
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
      UIManager.setLookAndFeel( "ch.randelshofer.quaqua.QuaquaLookAndFeel");
      //UIManager.setLookAndFeel( "napkin.NapkinLookAndFeel");
      
    }catch (Exception e){ }
    
    Calc modelo = new Calc();
    Vbasica vista = new Vbasica();
    Controller controlador1 = new Controller(vista, modelo,"Calculadora Basica");
    controlador1.iniciar_vista();
    vista.setVisible(true);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void keyReleased(KeyEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void windowOpened(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void windowClosing(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void windowClosed(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void windowIconified(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void windowActivated(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void itemStateChanged(ItemEvent ie) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}