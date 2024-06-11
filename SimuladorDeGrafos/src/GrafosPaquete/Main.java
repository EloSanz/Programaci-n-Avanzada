package GrafosPaquete;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setTitle("Simulador de Grafos");
	
		Simulador simulador = new Simulador();
		ventana.add(simulador);
		ventana.pack();
		
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
		simulador.iniciarSimulacion();
	}

}
