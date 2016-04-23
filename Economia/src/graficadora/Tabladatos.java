package graficadora;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tabladatos {
	
	public JFrame ventana;
	private JTable tabla;
	
	public Tabladatos(String datos, int fila){
		ventana = new JFrame("Tabla de Datos");
		ventana.setLayout(new FlowLayout());
		ventana.setSize(700, 400);
		ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		ventana.setVisible(true);
		String titulos[] = {"NP", "Precios (Q)", "Ing. Total", "Costo Total", "Costo Medio", "Costo Marginal", "Ing. Marginal"};
		tabla = new JTable(Llenar_Tabla(datos,fila),titulos);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(400,150));
		ventana.add(scroll);
	}
	
	private String[][] Llenar_Tabla(String contenido, int numFila){
		//x,y
		String datos[][] = new String[numFila][7];
		String valores[] = contenido.split("\t");
		int posicion=0, total=6*numFila;
			for(int x=0; x<numFila; x++){
				for(int y=0; y<7; y++){
					if( posicion < total){
						datos[x][y] = valores[posicion];
						posicion++;
					}else{
						break;
					}
					
				}
				
			}
		return datos;
	}

}
