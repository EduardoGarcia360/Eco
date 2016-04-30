package graficadora;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;


public class Ventana extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtm, txtb, txtc, txtd, txte, txtf;
	JButton btngenerar, btnGuardar, btnDatos, btnImagen;
	String m="",b="",c="",d="",e="",f="", Tabla_contenido="", todo="";
	double Dm=0, Db=0, Dc=0, Dd=0, De=0, Df=0, Nprecios=0;
	boolean activar_botones=false;
	int numero_filas=0;
	JPanel Grafica;
	JFreeChart chart;
	DecimalFormat decimal = new DecimalFormat("0.00");
	Archivo a = new Archivo();
	LinkedList<Double> precios = new LinkedList<Double>();
	LinkedList<Double> ingreso_total_IT = new LinkedList<Double>();
	LinkedList<Double> ingreso_marginal_IMG = new LinkedList<Double>();
	LinkedList<Double> costo_total = new LinkedList<Double>();
	LinkedList<Double> costo_medio_LIST = new LinkedList<Double>();
	LinkedList<Double> costo_marginal_LIST = new LinkedList<Double>();
	
	//XYSeries demanda = new XYSeries("Demanda");
	//XYSeriesCollection dataset = new XYSeriesCollection();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
					frame.setMinimumSize(new Dimension( 933, 660));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 625);
		setTitle("Eduardo antonio garcia franco - 2012-12961");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos:");
		lblIngreseLosDatos.setBounds(26, 35, 103, 14);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblY = new JLabel("Y =");
		lblY.setBounds(132, 35, 26, 14);
		contentPane.add(lblY);
		
		txtm = new JTextField();
		txtm.setBounds(153, 32, 62, 20);
		contentPane.add(txtm);
		txtm.setColumns(10);
		
		JLabel lblX = new JLabel("X  +/-");
		lblX.setBounds(216, 35, 35, 14);
		contentPane.add(lblX);
		
		txtb = new JTextField();
		txtb.setBounds(247, 32, 86, 20);
		contentPane.add(txtb);
		txtb.setColumns(10);
		
		JLabel lblM = new JLabel("m");
		lblM.setBounds(172, 11, 26, 14);
		contentPane.add(lblM);
		
		JLabel lblB = new JLabel("b");
		lblB.setBounds(276, 11, 19, 14);
		contentPane.add(lblB);
		
		JLabel lblCt = new JLabel("CT =");
		lblCt.setBounds(356, 35, 26, 14);
		contentPane.add(lblCt);
		
		txtc = new JTextField();
		txtc.setBounds(382, 32, 86, 20);
		contentPane.add(txtc);
		txtc.setColumns(10);
		
		JLabel lblX_1 = new JLabel("X^3  -");
		lblX_1.setBounds(469, 35, 35, 14);
		contentPane.add(lblX_1);
		
		txtd = new JTextField();
		txtd.setBounds(507, 32, 86, 20);
		contentPane.add(txtd);
		txtd.setColumns(10);
		
		JLabel lblX_2 = new JLabel("X^2  +");
		lblX_2.setBounds(594, 35, 40, 14);
		contentPane.add(lblX_2);
		
		txte = new JTextField();
		txte.setBounds(630, 32, 86, 20);
		contentPane.add(txte);
		txte.setColumns(10);
		
		JLabel lblX_3 = new JLabel("X  +");
		lblX_3.setBounds(720, 35, 35, 14);
		contentPane.add(lblX_3);
		
		txtf = new JTextField();
		txtf.setBounds(752, 32, 86, 20);
		contentPane.add(txtf);
		txtf.setColumns(10);
		
		JLabel lblC = new JLabel("c");
		lblC.setBounds(398, 11, 46, 14);
		contentPane.add(lblC);
		
		JLabel lblD = new JLabel("d");
		lblD.setBounds(522, 11, 46, 14);
		contentPane.add(lblD);
		
		JLabel lblE = new JLabel("e");
		lblE.setBounds(645, 11, 46, 14);
		contentPane.add(lblE);
		
		JLabel lblF = new JLabel("f");
		lblF.setBounds(769, 11, 46, 14);
		contentPane.add(lblF);
		
		btngenerar = new JButton("Generar");
		btngenerar.addActionListener(this);
		btngenerar.setBounds(20, 68, 89, 23);
		contentPane.add(btngenerar);
		
		JLabel lblF_1 = new JLabel("Funcion de la Demanda");
		lblF_1.setBounds(169, 63, 133, 14);
		contentPane.add(lblF_1);
		
		JLabel lblFuncionDeCosto = new JLabel("Funcion de Costo Total");
		lblFuncionDeCosto.setBounds(356, 63, 166, 14);
		contentPane.add(lblFuncionDeCosto);
		
		Grafica = new JPanel();
		Grafica.setBounds(10, 102, 897, 462);
		Grafica.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		contentPane.add(Grafica);
		
		btnGuardar = new JButton("Guardar Datos");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(630, 63, 127, 23);
		btnGuardar.setEnabled(false);
		contentPane.add(btnGuardar);
		
		btnDatos = new JButton("Ver Datos");
		btnDatos.addActionListener(this);
		btnDatos.setBounds(517, 63, 103, 23);
		btnDatos.setEnabled(false);
		contentPane.add(btnDatos);
		
		btnImagen = new JButton("Guardar Imagen");
		btnImagen.addActionListener(this);
		btnImagen.setBounds(762, 63, 133, 23);
		btnImagen.setEnabled(false);
		contentPane.add(btnImagen);
		
	}
	
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource() == btngenerar){
			m = txtm.getText();
			b = txtb.getText();
			c = txtc.getText();
			d = txtd.getText();
			e = txte.getText();
			f = txtf.getText();
			//VALIDAMOS QUE NINGUNA ESTE VACIA
			if(m.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || e.isEmpty() || f.isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
			}else{
				//SI TIENE ALGO, VERIFICAMOS QUE SEA UN NUMERO
				if(esnumero(m) && esnumero(b) && esnumero(c) && esnumero(d) && esnumero(e) && esnumero(f)){
					//YA TENEMOS LOS VALORES NUMERICOS INGRESADOS
					btnGuardar.setEnabled(true);
					btnDatos.setEnabled(true);
					btnImagen.setEnabled(true);
					//Generar_Datos();
					generar_demanda gd = new generar_demanda();
					gd.start();
				}else{
					//SI ALGUNO NO ES NUMERO
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos con valores numericos");
				}
			}
		}else if(ev.getSource() == btnGuardar){
			if(activar_botones){
				a.Guardar(todo);
			}else{
				JOptionPane.showMessageDialog(null, "Debe ingresar los valores antes de guardar algo");
			}
		}else if(ev.getSource() == btnDatos){
			if(activar_botones){
				Tabladatos td = new Tabladatos(Tabla_contenido, numero_filas);
			}else{
				JOptionPane.showMessageDialog(null, "Para generar datos debe ingresar los valores");
			}
		}else if(ev.getSource() == btnImagen){
			JFileChooser fc=new JFileChooser("C:/");
			fc.setCurrentDirectory(new File("C:/"));
			fc.setSelectedFile(new File("C:/Grafica.jpg"));
			int ret=fc.showSaveDialog(null);
			if(ret==fc.APPROVE_OPTION)
			{
			try {
			ChartUtilities.saveChartAsJPEG(fc.getSelectedFile(), chart, 1220, 720);
			JOptionPane.showMessageDialog(null, "Guardado con Exito!");
			} catch (IOException IOe) {
			JOptionPane.showMessageDialog(null, "Error al guardar la imagen " + IOe.toString());
			}
			}
		}
	}
	
	public void Generar_Datos(){
		Dm = Double.parseDouble(m);
		Db = Double.parseDouble(b);
		Dc = Double.parseDouble(c);
		double tmp = Double.parseDouble(d);
		Dd = tmp * (-1);
		De = Double.parseDouble(e);
		Df = Double.parseDouble(f);
		activar_botones = true;
		
		int Xfinal = x_cero(Db,Dm) + 10, pos = 1;
		XYSeries demanda = new XYSeries("Demanda");
		XYSeries img_ingreso_marginal = new XYSeries("Ingreso Marginal");
		XYSeries costo_medio_CME = new XYSeries("Costo Medio");
		XYSeries costo_marginal_CMG = new XYSeries("Costo Marginal");
		
		costo_medio_LIST.add(0.0);
		ingreso_marginal_IMG.add(0.0);
		costo_marginal_LIST.add(0.0);
		
		double img=0, ct=0,cmedio=0, cmarginal=0;
		for (int x = 0; x < Xfinal; x+=10)
		{
			Nprecios = funcion_demanda_precio(x,Dm,Db);
			//AGREGAMOS A LISTA CON LOS PRECIOS
			precios.add(Nprecios);
			//AGREGAMOS A LA LISTA DE INGRESO TOTAL
			ingreso_total(x,Nprecios);
			//AGREGAMOS A LA GRAFICA DE DEMANDA
			demanda.add((double)x,Nprecios);
			//CALCULAMOS COSTO TOTAL
			ct = funcion_CT(x,Dc,Dd,De,Df);
			//AGREGAMOS A LA LISTA DE COSTO TOTAL
			costo_total.add(ct);
			numero_filas++;
			if(x>=10){
				
				//CALCULAMOS EL IMG
				img = ingreso_marginal(pos);
				//LO AGREGAMOS A LA LISTA
				ingreso_marginal_IMG.add(img);
				//CALCULAMOS EL COSTO MEDIO
				cmedio = costo_medio(pos,x);
				//AGREGAMOS COSTO MEDIO A LA LISTA
				costo_medio_LIST.add(cmedio);
				//CALCULAMOS COSTO MARGINAL
				cmarginal = costo_marginal(pos);
				//AGREGAMOS COSTO MARGINAL A LA LISTA
				costo_marginal_LIST.add(cmarginal);
				//AGREGAMOS A LA GRAFICA DE COSTO MARGINAL
				costo_marginal_CMG.add(x,cmarginal);
				//AGREGAMOS A LA GRAFICA DE COSTO MEDIO
				costo_medio_CME.add(x,cmedio);
				
				if(img > 0){
					img_ingreso_marginal.add(x,img);
					pos++;
				}else{
					break;
				}
				
			}
			
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(demanda);
		dataset.addSeries(img_ingreso_marginal);
		dataset.addSeries(costo_medio_CME);
		dataset.addSeries(costo_marginal_CMG);
		
		chart = ChartFactory.createXYLineChart(
				"", //TITULO
				"Eje X: Cantidades", //NOMBRE EJE X
				"Eje Y: Demanda en Q.", //NOMBRE EJE Y
				dataset, //AGREGAMOS EL DATASET
				PlotOrientation.VERTICAL, //Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
		);
		
		ChartPanel panel = new ChartPanel(chart);
		if(activar_botones){
			Grafica.removeAll();
		}
		Grafica.add(panel);
		Grafica.setVisible(true);
		pack();
		
		Crear_Tabla();
	}
	
	public void Crear_Tabla(){
		
		todo = "Ecuacion de la demanda: y= " + Dm + "x + (" + Db + ")\n"
				+ "Ecuacion de Costo Total: CT= ("+ Dc + ")x^3 + (" + Dd + ")x^2 + (" + De + ")x + (" + Df + ")\n"
				+ "NP\tPRECIO\tING TOT\t\tCTOTAL\t\tCMEDIO\t\tCMARGINAL\tINGRESO MARGINAL\n";
		//Tabla_contenido = "NP\tPRECIO\tING TOT\t\tCTOTAL\t\tCMEDIO\t\tCMARGINAL\tINGRESO MARGINAL\n";
		int cada_diez=0;
		for(int i=0; i<precios.size(); i++){
			Tabla_contenido += cada_diez + "\t"+decimal.format(precios.get(i)) + "\t"+decimal.format(ingreso_total_IT.get(i))+"\t"
					+decimal.format(costo_total.get(i))+"\t"+decimal.format(costo_medio_LIST.get(i))
					+"\t"+decimal.format(costo_marginal_LIST.get(i))+"\t"+decimal.format(ingreso_marginal_IMG.get(i))+"\t\n";
			cada_diez+=10;
		}
		todo += Tabla_contenido;
		
	}
	
	private class generar_demanda extends Thread{
		
		public void run(){
			int i=0, j=10;
			Dm = Double.parseDouble(m);
			Db = Double.parseDouble(b);
			double losprecios=0;
			//XYSeries nueva_demanda = new XYSeries("Demanda");
			//XYSeriesCollection datos_demanda = new XYSeriesCollection();
			JFreeChart fc;
			while(i<17){
				XYSeries nueva_demanda = new XYSeries("Demanda");
				for(int x=0; x<j; x++){
					losprecios = funcion_demanda_precio(x,Dm,Db);
					nueva_demanda.add((double)x,losprecios);
				}
				XYSeriesCollection datos_demanda = new XYSeriesCollection();
				datos_demanda.addSeries(nueva_demanda);
				i++;
				j+=10;
				try{
					
					fc = ChartFactory.createXYLineChart(
							"", //TITULO
							"Eje X: Cantidades", //NOMBRE EJE X
							"Eje Y: Demanda en Q.", //NOMBRE EJE Y
							datos_demanda, //AGREGAMOS EL XYSERIESCOLLECTION
							PlotOrientation.VERTICAL, //Plot Orientation
							true, // Show Legend
							true, // Use tooltips
							false // Configure chart to generate URLs?
					);
					
					ChartPanel panel = new ChartPanel(fc);
					
					Grafica.removeAll();
					Grafica.add(panel);
					Grafica.setVisible(true);
					pack();
					Thread.sleep(1000);
					
				}catch(InterruptedException e) {
					System.out.println("Error!: " + e.toString());
				}
			}//fin while
			generar_ingreso_marginal im = new generar_ingreso_marginal();
			im.start();
		}//fin run
		
	}
	
	private class generar_ingreso_marginal extends Thread{
		public void run(){
			
		}
	}
	
	private int x_cero(double b, double m){
		return( (int)((b/m)*(-1)) );
	}
	
	private double funcion_demanda_precio(double x, double m, double b){
		return ( (double)(m*x+b) );
	}
	
	private void ingreso_total(double x, double y){
		//CANTIDAD POR PRECIO
		double it = x*y;
		ingreso_total_IT.add(it);
	}
	
	private double costo_medio(int posicion, double x){
		return ( (costo_total.get(posicion))/(x) );
	}
	
	private double costo_marginal(int posicion){
		return( (costo_total.get(posicion) - costo_total.get(posicion-1))/(10) );
	}
	
	private double ingreso_marginal(int posicion){
		return ((ingreso_total_IT.get(posicion) - ingreso_total_IT.get(posicion-1))/(10));
	}
	
	private double funcion_CT(double x, double c, double d, double e, double f){
		return ( (double)( c*(Math.pow(x, 3)) + d*(Math.pow(x, 2)) + e*x + f ));
	}
	
	private boolean esnumero(String dato){
        try{
        	Double.parseDouble(dato);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
