package graficadora;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class Archivo {
	
	
	
	public void Guardar(String texto){
		File archi = new File("DatosGrafica.eco");
		JFileChooser FC = new JFileChooser("C:\\Users\\Edu\\Desktop\\");
		try {
			FileWriter escritor = new FileWriter(archi);
			BufferedWriter bw = new BufferedWriter(escritor);
			PrintWriter salida = new PrintWriter(bw);
			salida.write(texto);
			salida.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
