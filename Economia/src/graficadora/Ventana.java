package graficadora;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class Ventana extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtm, txtb, txtc, txtd, txte, txtf;
	JButton btngenerar;
	String m="",b="",c="",d="",e="",f="";
	int Nm=0, Nb=0, Nc=0, Nd=0, Ne=0, Nf=0;
	double Nprecios=0;
	JPanel Grafica;
	JFreeChart chart;
	LinkedList<Double> precios = new LinkedList<Double>();
	LinkedList<Double> ingreso_total_IT = new LinkedList<Double>();
	LinkedList<Double> ingreso_marginal_IMG = new LinkedList<Double>();
	LinkedList<Double> costo_total = new LinkedList<Double>();

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 660);
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
		lblFuncionDeCosto.setBounds(452, 63, 166, 14);
		contentPane.add(lblFuncionDeCosto);
		
		Grafica = new JPanel();
		Grafica.setBounds(10, 102, 897, 433);
		Grafica.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		contentPane.add(Grafica);
		
		
		
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
					Nm = Integer.parseInt(m);
					//Nm = Double.parseDouble(m);
					Nb = Integer.parseInt(b);
					Nc = Integer.parseInt(c);
					int tmp = Integer.parseInt(d);
					Nd = tmp * (-1);
					Ne = Integer.parseInt(e);
					Nf = Integer.parseInt(f);
					
					
					int Xfinal = x_cero(Nb,Nm) + 10, pos = 1;
					XYSeries demanda = new XYSeries("Demanda");
					XYSeries img_ingreso_marginal = new XYSeries("Ingreso Marginal");
					XYSeries costo_medio_CME = new XYSeries("Costo Medio");
					
					double img=0, ct=0,cmedio=0;
					for (int x = 0; x < Xfinal; x+=10)
					{
						Nprecios = funcion_demanda_precio(x,Nm,Nb);
						//AGREGAMOS A LISTA CON LOS PRECIOS
						precios.add(Nprecios);
						//AGREGAMOS A LA LISTA DE INGRESO TOTAL
						ingreso_total(x,Nprecios);
						//AGREGAMOS A LA GRAFICA DE DEMANDA
						demanda.add((double)x,Nprecios);
						//CALCULAMOS COSTO TOTAL
						ct = funcion_CT(x,Nc,Nd,Ne,Nf);
						
						//AGREGAMOS A LA LISTA DE COSTO TOTAL
						costo_total.add(ct);
						if(x>=10){
							
							//CALCULAMOS EL IMG
							img = ingreso_marginal(pos);
							//CALCULAMOS EL COSTO MEDIO
							cmedio = costo_medio(pos,x);
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
					
					Grafica.add(panel);
					Grafica.setVisible(true);
					pack();
					
				}else{
					//SI ALGUNO NO ES NUMERO
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos con valores numericos");
				}
			}
		}
	}
	
	private int x_cero(int b, int m){
		return( (int)((b/m)*(-1)) );
	}
	
	private double funcion_demanda_precio(int x, int m, int b){
		return ( (double)(m*x+b) );
	}
	
	private void ingreso_total(int x, double y){
		//CANTIDAD POR PRECIO
		double it = x*y;
		ingreso_total_IT.add(it);
	}
	
	private double costo_medio(int posicion, int x){
		return ( (costo_total.get(posicion))/(x) );
	}
	
	private double ingreso_marginal(int posicion){
		//ingreso_marginal_IMG.add(0.0);
		return ((ingreso_total_IT.get(posicion) - ingreso_total_IT.get(posicion-1))/(10));
		
		
	}
	
	private int funcion_CT(int x, int c, int d, int e, int f){
		return ( (int)( c*(Math.pow(x, 3)) + d*(Math.pow(x, 2)) + e*x + f ));
	}
	
	private boolean esnumero(String dato){
        try{
            Integer.parseInt(dato);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
