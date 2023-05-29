
package config;

import frames.Menu;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author schafler92
 */
public class MasterReport {
         private final Connection con;  
         String file,date_time;
         conectarDB DB;
  
    public MasterReport(Connection con,String fileName){
        this.con=con;
        this.file=fileName;
        DB=new conectarDB();
       // this.date_time=date_time;
    }  

    
        public final void generate_date_report(String fDesde,String fHasta ,String ruta){
            try {
                JasperReport report=JasperCompileManager.compileReport("src/informes/"+file+".jrxml"); 
                
                Map parametro = new HashMap();
      //          parametro.put("desde",fDesde);
        //        parametro.put("hasta",fHasta);
                  
                JasperPrint print = JasperFillManager.fillReport(report, parametro,con);
                JasperViewer view = new JasperViewer(print, false);
                
                view.setTitle("Vista Previa - Informe");
                view.setExtendedState(Frame.MAXIMIZED_BOTH);
                view.setLocationRelativeTo(null);
                view.setVisible(true);   
                
            
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
        }
        }

//----------------------------------------------------------------JASPERVIEWER-----------------------------------------------------------------    
    public void generateReportCod(String cod){
      JasperReport masterReport = null;
 try{
            Map param=new HashMap();
            param.put("cod",cod);
            
            masterReport = (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_c.jasper");
    
            JasperPrint masterPrint = JasperFillManager.fillReport(masterReport,param,con);
            JasperViewer ventana = new JasperViewer(masterPrint,false);
            ventana.setTitle("Informe de "+file);
            ventana.setVisible(true);
            ventana.toFront();
 }
 catch ( JRException e){
     JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(), "Error al Generar Informe", JOptionPane.ERROR_MESSAGE);
 }
 }
        
public void generateReportFrom_to_and_suc(Date fDesde,Date fHasta,String suc){
      JasperReport masterReport = null;
 try{
          Map param = new HashMap();
                param.put("desde",fDesde);
                param.put("hasta",fHasta);
                param.put("suc",suc);
                
            masterReport = (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_ds.jasper");
    
            JasperPrint masterPrint = JasperFillManager.fillReport(masterReport,param,con);
            JasperViewer ventana = new JasperViewer(masterPrint,false);
            ventana.setTitle("Informe de "+file);
            ventana.setVisible(true);
            ventana.toFront();
 }
 catch ( JRException e){
     JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(), "Error al Generar Informe", JOptionPane.ERROR_MESSAGE);
 }
 } 
        
public void generateReport(){
      JasperReport masterReport = null;
 try{
            masterReport = (JasperReport) JRLoader.loadObject("src/informes/"+file+".jasper");    
            JasperPrint masterPrint = JasperFillManager.fillReport(masterReport,new HashMap(),DB.con);
            JasperViewer ventana = new JasperViewer(masterPrint,false);
            ventana.setTitle("Informe de "+file);
            ventana.setVisible(true);
            ventana.toFront();
 }
 catch ( JRException e){
     JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(), "Error al Generar Informe", JOptionPane.ERROR_MESSAGE);
 }
 }

public void generateReportSuc(String suc){
      JasperReport masterReport = null;
 try{
          Map param = new HashMap();
                param.put("suc",suc);
                
            masterReport = (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_s.jasper");
    
            JasperPrint masterPrint = JasperFillManager.fillReport(masterReport,param,con);
            JasperViewer ventana = new JasperViewer(masterPrint,false);
            ventana.setTitle("Informe de "+file);
            ventana.setVisible(true);
            ventana.toFront();
 }
 catch ( JRException e){
     JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(), "Error al Generar Informe", JOptionPane.ERROR_MESSAGE);
 }
 } 
//----------------------------------------------------------------JASPERPRINT PDF-----------------------------------------------------------------
public final void generateReportToPDF(){
     try {

            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/reportes/"+file+".jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, new HashMap(), con);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:\\Schafler\\Pdf\\"+date_time+" "+file+".pdf"));
            exporter.exportReport();
           // Desktop.getDesktop().open(new File("i_"+cod+".pdf"));
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
        }
        }
       
public final void generateReportToPDFcod(int cod){
     try {

            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_c.jasper");
            Map parametro = new HashMap();
            parametro.put("cod", cod);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con);

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:\\Schafler\\Pdf\\"+date_time+" "+file+".pdf"));
            exporter.exportReport();
           // Desktop.getDesktop().open(new File("i_"+cod+".pdf"));
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
        }
        }
              
public final void generateReportToPDFFrom_to_and_suc(Date fDesde,Date fHasta,String suc){
     try {

            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_ds.jasper");
            Map param = new HashMap();
            param.put("desde",fDesde);
            param.put("hasta",fHasta);
            param.put("suc", suc);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,param, con);

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:\\Schafler\\Pdf\\"+date_time+" "+file+".pdf"));
            exporter.exportReport();
           // Desktop.getDesktop().open(new File("i_"+cod+".pdf"));
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
        }
        }

public final void generateReportToPDFsuc(String suc){
     try {

            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_s.jasper");
            Map param = new HashMap();
            param.put("suc", suc);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,param, con);

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:\\Schafler\\Pdf\\"+date_time+" "+file+".pdf"));
            exporter.exportReport();
           // Desktop.getDesktop().open(new File("i_"+cod+".pdf"));
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
        }
        }
//----------------------------------------------------------------JASPERPRINT XLS-----------------------------------------------------------------
public final void generateReportToXLSX(){
     try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/reportes/"+file+".jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, new HashMap(), con);
            JRExporter exporter=new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:\\Schafler\\Excel\\"+date_time+" "+file+".xlsx"));
            exporter.exportReport();
            
           // Desktop.getDesktop().open(new File("i_"+cod+".pdf"));
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);

    }
        }
                
public final void generateReportToXLSXcod(String cod){
     try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_c.jasper");
            Map param = new HashMap();
            param.put("cod", cod);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, param, con);
            JRExporter exporter=new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:\\Schafler\\Excel\\"+date_time+" "+file+".xlsx"));
            exporter.exportReport();
            
           // Desktop.getDesktop().open(new File("i_"+cod+".pdf"));
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);

    }
        }
                                
public final void generateReportToXLSXFrom_to_and_suc(Date fDesde,Date fHasta,String suc){
     try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_ds.jasper");
            Map param = new HashMap();
            param.put("desde",fDesde);
            param.put("hasta",fHasta);
            param.put("suc", suc);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, param, con);
            JRExporter exporter=new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:\\Schafler\\Excel\\"+date_time+" "+file+".xlsx"));
            exporter.exportReport();
            
           // Desktop.getDesktop().open(new File("i_"+cod+".pdf"));
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);

    }
        }

public final void generateReportToXLSXsuc(String suc){
     try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_s.jasper");
            Map param = new HashMap();
            param.put("suc", suc);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, param, con);
            JRExporter exporter=new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:\\Schafler\\Excel\\"+date_time+" "+file+".xlsx"));
            exporter.exportReport();
            
           // Desktop.getDesktop().open(new File("i_"+cod+".pdf"));
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);

    }
        }
//----------------------------------------------------------------JASPERPRINT PNG-----------------------------------------------------------------


             
        public final void generateReportToPNG(){
         JasperReport jasperReport;
         JasperPrint jasperPrint;
         try{
           jasperReport= (JasperReport) JRLoader.loadObject("src/reportes/"+file+".jasper");
           jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), con );
//           jasperPrint.get diseñar get para repetir accion con todas las paginas
           BufferedImage bufferedImage = (BufferedImage) JasperPrintManager.printPageToImage(jasperPrint, 0, 2f);//el cero es el index de pagina, ver pàra hacer for y ademas cambiar nombre
           ImageIO.write(bufferedImage,"png", new File("C:\\Schafler\\Imagenes\\"+date_time+" "+file+".png") );
         }
         catch (JRException | IOException ex){
          JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
        }
     } 
        
                     
        public final void generateReportToPNGcod(String cod){
         JasperReport jasperReport;
         JasperPrint jasperPrint;
         try{
           Map param = new HashMap();
            param.put("cod",cod);
           jasperReport= (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_c.jasper");
           jasperPrint = JasperFillManager.fillReport(jasperReport,param, con );
//           jasperPrint.get
           BufferedImage bufferedImage = (BufferedImage) JasperPrintManager.printPageToImage(jasperPrint, 0, 2f);//el cero es el index de pagina, ver pàra hacer for y ademas cambiar nombre
           //se escribe imagen en disco en formato JPG y en la direccion indicada en FILE
           ImageIO.write(bufferedImage,"png", new File("C:\\Schafler\\Imagenes\\"+date_time+" "+file+".png") );
         }
         catch (JRException | IOException ex){
          JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
        }
     } 
        
                     
        public final void generateReportToPNGFrom_to_and_suc(Date fDesde,Date fHasta,String suc){
         JasperReport jasperReport;
         JasperPrint jasperPrint;
         try{
           Map param = new HashMap();
            param.put("desde",fDesde);
            param.put("hasta",fHasta);
            param.put("suc", suc);
           jasperReport= (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_ds.jasper");
           jasperPrint = JasperFillManager.fillReport(jasperReport,param, con );
//           jasperPrint.get
           BufferedImage bufferedImage = (BufferedImage) JasperPrintManager.printPageToImage(jasperPrint, 0, 2f);//el cero es el index de pagina, ver pàra hacer for y ademas cambiar nombre
           ImageIO.write(bufferedImage,"png", new File("C:\\Schafler\\Imagenes\\"+date_time+" "+file+".png") );
         }
         catch (JRException | IOException ex){
          JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
        }
     } 
        
                public final void generateReportToPNGsuc(String suc){
         JasperReport jasperReport;
         JasperPrint jasperPrint;
         try{
           Map param = new HashMap();
            param.put("suc", suc);
           jasperReport= (JasperReport) JRLoader.loadObject("src/reportes/"+file+"_s.jasper");
           jasperPrint = JasperFillManager.fillReport(jasperReport,param, con );
//           jasperPrint.get
           BufferedImage bufferedImage = (BufferedImage) JasperPrintManager.printPageToImage(jasperPrint, 0, 2f);//el cero es el index de pagina, ver pàra hacer for y ademas cambiar nombre
           ImageIO.write(bufferedImage,"png", new File("C:\\Schafler\\Imagenes\\"+date_time+" "+file+".png") );
         }
         catch (JRException | IOException ex){
          JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
        }
     }
        
        
//         
//              public final void Imprimir2(){                    
//         /* Array para almacenar las impresoras del sistema */
//         PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
//         if( printService.length>0 )//si existen impresoras
//        {
//            //se elige la impresora
//            PrintService impresora = (PrintService) JOptionPane.showInputDialog(null, "Eliga impresora:",
//                     "Imprimir Reporte", JOptionPane.QUESTION_MESSAGE, null, printService, printService[0]);
//             if( impresora != null ){
//                 try {
//                    //esto para el JasperReport
//                    JasperReport jasperReport;
//                    JasperPrint jasperPrint;
//                    //se carga el reporte
//                    URL in = this.getClass().getResource("reporte.jasper");
//                    jasperReport = (JasperReport) JRLoader.loadObject(in);
//                    //se procesa el archivo jasper
//                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),con);
//                    //se manda a la impresora
//                    JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
//                    jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint );
//                    jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, impresora );
//                    jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
//                    jrprintServiceExporter.exportReport();
//                 } catch (JRException ex) {
//                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al Generar Reporte",JOptionPane.ERROR_MESSAGE);
//                 }
//             }else{
//                 //no elijio nada
//             }
//        }
//   }

}
