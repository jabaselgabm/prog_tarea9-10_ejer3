
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TresEnRaya extends JFrame implements ActionListener {
	
		int filas = 3;
		int columnas = 3; 
		private int jugadaNumero = 1;
		int maxJugadas = filas * columnas;

		JPanel panelArriba;
		JLabel label;
		JPanel panelCentral;
		JPanel panelAbajo;
		JButton reiniciar;
		JToggleButton [][] tableroBotones = new JToggleButton [filas][columnas];
		
		
		// Variable para controlar el turno
	    boolean turnoX = true;
	    

		void addJugada() {
			jugadaNumero++;
		}

		boolean ultimaJugada () {
			return jugadaNumero > maxJugadas;
		}
	    
	    
		private void inicializarPanelSuperior () {
			// Elementos para el juego
			// Panel superior
			// Un JLabel en la parte superior que indique el turno actual del jugador.
			panelArriba = new JPanel ();
			label = new JLabel ("Turno: X"); // Primer turno X 
			label.setFont(new Font("Arial", Font.BOLD, 20));
			panelArriba.add(label);
			add(panelArriba, BorderLayout.NORTH);	    	
	    }
	    
	    private void inicializarPanelCentral () {
			// Panel Central
			panelCentral = new JPanel();
			panelCentral.setBackground(new Color(0x000000));
			
			// El tablero debe estar formado por una cuadrícula de 3x3 botones (JButton)
			// Para mejorar la apariencia los he metido en un layout tipo Grid
			// así se autoajusta				
			panelCentral.setLayout(new GridLayout(filas, columnas, 0, 0));
			for (int i = 0; i < filas; ++i) {
				for (int j = 0; j < columnas; ++j) {
					tableroBotones[i][j] = new JToggleButton("");
					tableroBotones[i][j].setFont(new Font("Arial", Font.BOLD, 40));
					tableroBotones[i][j].addActionListener(this);
					panelCentral.add(tableroBotones[i][j]);
				}
			}
	    	
	    }
	    
	    private void inicializarPanelInferior () {
			// Panel inferior
			// Un botón 'Reiniciar' que permita comenzar una nueva partida.
			panelAbajo = new JPanel ();
			reiniciar = new JButton ("Reiniciar");
			reiniciar.setFont(new Font("Arial", Font.BOLD, 18));
			reiniciar.setBackground(new Color (0x4d8076));
			reiniciar.addActionListener(this);
			// Lo meto en un Grid y asi ocupa todo el ancho y alto como
			// en la imagenes de muestra
			panelAbajo.setLayout(new GridLayout(1, 1, 0, 0));
			panelAbajo.add(reiniciar);
	    	
	    }
	    
	    
		TresEnRaya () {
			// Configuramos la ventana principal
			super ("Tres en Raya");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(606, 606);
			setLayout(new BorderLayout()); // El layout por defecto de JFrame es BorderLayout
			

			// Iniciarlizar los paneles
			inicializarPanelSuperior();
			inicializarPanelCentral();
			inicializarPanelInferior();
			// Añadirlos al frame principal
			getContentPane().add(BorderLayout.NORTH, panelArriba);
			getContentPane().add(BorderLayout.CENTER, panelCentral);
			getContentPane().add(BorderLayout.SOUTH, panelAbajo);
			
		}
		void resetearJuego () {
			// el turno vuelve a ser de X
			turnoX = true;
			jugadaNumero = 1;
			label.setText ("Turno: X");
			
			// Limpiar y activar el tablero
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {	
					tableroBotones [i][j].setText("");
					tableroBotones [i][j].setSelected(false);
					tableroBotones [i][j].setEnabled(true);
				}
			}
			
		}
		
		boolean hayGanador (String quienJuega)  {
			// comprobar fila
			try {
				for (int i = 0; i < this.filas; i++) {
					if ((tableroBotones[i][0].getText().equals(quienJuega)) &&
						(tableroBotones[i][1].getText().equals(quienJuega)) &&
						(tableroBotones[i][2].getText().equals(quienJuega))) {
						return true;
					}
				}
						
			// comprobar columnas
				for (int j = 0; j < this.columnas; j++) {
					if ((tableroBotones[0][j].getText().equals(quienJuega)) &&
						(tableroBotones[1][j].getText().equals(quienJuega)) &&
						(tableroBotones[2][j].getText().equals(quienJuega))) {
						return true;
					}
				}
			
			// comprobar diagonales
				if ((tableroBotones[0][0].getText().equals(quienJuega)) &&
					(tableroBotones[1][1].getText().equals(quienJuega)) &&
					(tableroBotones[2][2].getText().equals(quienJuega))) {
						return true;
				}
				if ((tableroBotones[0][2].getText().equals(quienJuega)) &&
					(tableroBotones[1][1].getText().equals(quienJuega)) &&
					(tableroBotones[2][0].getText().equals(quienJuega))) {
						return true;
				}
			} catch (Exception e) {
				return false;
			}
			
			return false;
		}
		
		void informarGanador (String quien) {
			JOptionPane.showMessageDialog(
					this, 						// componente padre 
					"¡Gano " + quien + "!", 	// Mensaje
					"Fin de la partida",		// Titulo Ventana
					JOptionPane.INFORMATION_MESSAGE);  // Tipo Mensaje
		}
		
		
		void informarFinDePartida () {
			JOptionPane.showMessageDialog(
					this, 						// componente padre 
					"¡Empate!", 				// Mensaje
					"Fin de la partida",		// Titulo Ventana
					JOptionPane.INFORMATION_MESSAGE);  // Tipo Mensaje
		}
		
		@Override
		public void actionPerformed (ActionEvent e) {
			// Identificar el boton pulsado
			if (e.getSource() == reiniciar) {
				// Reiniciamos el tablero
				System.out.println("Resetar tablero");
				resetearJuego();
			} else {
				// Hemos pulsado uno de los botnoes del tablero
				JToggleButton botonPulsado = (JToggleButton) e.getSource();
				// Deshabilitar el botón para que ya no pueda ser pulsado
				botonPulsado.setEnabled(false);
				
				this.addJugada();
					
				String quienJuega;	

				if (turnoX) {
					quienJuega = "X";
					botonPulsado.setText("X");
					label.setText("Turno: O");
				} else {
					quienJuega = "O";
					botonPulsado.setText("O");
					label.setText("Turno: X");				
				}
				turnoX=!turnoX;
				
				// comprobar si ha ganado alguien
				if (hayGanador(quienJuega)) {
					informarGanador(quienJuega);
					resetearJuego();
				} else if (this.ultimaJugada ()) {
					System.out.println("ultima jugada");
					
					//  aqui ira el cuadro de dialogo de última jugada de empate
					informarFinDePartida();
					resetearJuego();
				}
			}
		}
		
		void empezar () {		
			setVisible(true);
		}


		
	



}
