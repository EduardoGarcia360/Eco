package graficadora;

import java.io.File;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Archivo {
	
	public void Guardar(String texto){
		JFileChooser FC = new JFileChooser();
		int s = FC.showSaveDialog(null);
        try{
            if (s == JFileChooser.APPROVE_OPTION){
                File JFC = FC.getSelectedFile();
                String ruta = JFC.getAbsolutePath();
                PrintWriter printwriter = new PrintWriter(JFC);
                printwriter.print(texto);
                printwriter.close();
                
                if( !(ruta.endsWith(".eco")) ){
                	File temp = new File(ruta+".eco");
                    JFC.renameTo(temp);
                }
                	JOptionPane.showMessageDialog(null, "Guardado Correctamente","Notificacion", JOptionPane.INFORMATION_MESSAGE);
                
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al guardar", "Notificacion", JOptionPane.ERROR_MESSAGE);
        }
	}

}
