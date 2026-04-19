
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Una ventana principal (JFrame) que contenga el tablero del juego
 * Además tambien recibe ActiconListener para poder controlar los 
 * eventos de los botones
 */
public class TresEnRaya extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		private int filas = 6;			// para definir
		private int columnas = 6; 		// el tablero
		private int jugadaNumero = 1;	// Nos permite controlar las jugadas
			// para saber si hemos termimando
		private int maxJugadas = filas * columnas; // Máximo número posible de jugadas

		// Definimos el Layout como el de la muestra del ejercicio
		private JPanel panelArriba;	
		private JLabel label;
		private JPanel panelCentral;
		private JPanel panelAbajo;
		private JButton reiniciar;
		// Uso el Botón que una vez que lo pulsas se queda pulsaddo
		private JButton [][] tableroBotones = new JButton [filas][columnas];
		
		// Variable para controlar el turno
	    private boolean turnoX = true;

		/**
		 * Para ir sumando jugadas
		 */
		void addJugada() {
			jugadaNumero++;
		}

		/**
		 * @return True si estamos en la última jugada
		 */
		boolean ultimaJugada () {
			return jugadaNumero > maxJugadas;
		}
	    
	    
		// Elementos para el juego
		/**
		 * Panel superior
		 * Un JLabel en la parte superior que indique el turno actual del jugador.
		 */
		private void inicializarPanelSuperior () {
			panelArriba = new JPanel ();
			label = new JLabel ("Turno: X"); // Primer turno X 
			label.setFont(new Font("Arial", Font.BOLD, 20));
			panelArriba.add(label);	    
			add(BorderLayout.NORTH, panelArriba);
	    }
	    
	    /**
	     * El tablero debe estar formado por una cuadrícula de 3x3 botones (JButton)
	     * Para mejorar la apariencia los he metido en un layout tipo Grid
	     * así se autoajusta
	     */
	    private void inicializarPanelCentral () {
			// Panel Central
			panelCentral = new JPanel();
			panelCentral.setBackground(new Color(0x000000));
			
			panelCentral.setLayout(new GridLayout(filas, columnas, 0, 0));
			// Recorro la matriz para rellenar con los botones
			for (int i = 0; i < filas; ++i) {
				for (int j = 0; j < columnas; ++j) {
					tableroBotones[i][j] = new JButton("");
					tableroBotones[i][j].setFont(new Font("Arial", Font.BOLD, 48));
					tableroBotones[i][j].addActionListener(this);
					panelCentral.add(tableroBotones[i][j]);
				}
			}
			add(BorderLayout.CENTER, panelCentral);
	    }
	    
	    /**
	     * Panel inferior
	     * Un botón 'Reiniciar' que permita comenzar una nueva partida.
	     */
	    private void inicializarPanelInferior () {
			panelAbajo = new JPanel ();
			reiniciar = new JButton ("Reiniciar");
			reiniciar.setFont(new Font("Arial", Font.BOLD, 18));
			reiniciar.setBackground(new Color (0x4d8076));
			reiniciar.addActionListener(this);
			// Lo meto en un Grid y asi ocupa todo el ancho y alto como
			// en la imagenes de muestra
			panelAbajo.setLayout(new GridLayout(1, 1, 0, 0));
			panelAbajo.add(reiniciar);
			add(BorderLayout.SOUTH, panelAbajo);
	    }
	    
	    
		/**
		 * CONSTRUCTOR
		 * Monta toda la ventana y los paneles
		 */
		TresEnRaya () {
			// Configuramos la ventana principal
			// llama al constructor del padre
			super ("Tres en Raya");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(606, 606);
			setLayout(new BorderLayout()); // El layout por defecto de JFrame es BorderLayout

			// Iniciarlizar los paneles
			inicializarPanelSuperior();
			inicializarPanelCentral();
			inicializarPanelInferior();		
		}
		
		/**
		 * Accion para cuando pulsamos el boton REINICIAR
		 */
		void resetearJuego () {
			// el turno vuelve a ser de X
			turnoX = true;
			// Pone las jugadas al principio
			jugadaNumero = 1;
			// Ajusta los textos
			label.setText ("Turno: X");
			
			// Limpiar y activar el tablero de botones
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {	
					tableroBotones [i][j].setText("");
					tableroBotones [i][j].setSelected(false);
					tableroBotones [i][j].setEnabled(true);
				}
			}
		}
		
		/**
		 * @param quienJuega
		 * @return True si hay jugada ganadores
		 * Comprueba las filas, columnas y diagonales
		 */
		boolean hayGanador (String quienJuega)  {
			// comprobar fila
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
		
			// Si noy hay ganador devolvemos falso
			return false;
		}
		
		/**
		 * @param quien
		 * Lanza una ventana modal con el Ganador
		 */
		void informarGanador (String quien) {
			JOptionPane.showMessageDialog(
					this, 						// componente padre 
					"¡Gano " + quien + "!", 	// Mensaje
					"Fin de la partida",		// Titulo Ventana
					JOptionPane.INFORMATION_MESSAGE);  // Tipo Mensaje
		}
		
		
		/**
		 * Lanza una modal con el Empate y fin de partida
		 */
		void informarFinDePartida () {
			JOptionPane.showMessageDialog(
					this, 						// componente padre 
					"¡Empate!", 				// Mensaje
					"Fin de la partida",		// Titulo Ventana
					JOptionPane.INFORMATION_MESSAGE);  // Tipo Mensaje
		}
		
		/**
		 * Sobreescribimos el metodo que controla los eventos
		 */
		@Override
		public void actionPerformed (ActionEvent e) {
			String quienJuega;  	// para guardar quien esta jugando
			
			// Identificar el boton pulsado
			if (e.getSource() == reiniciar) {
				// Si se ha pulsado el boton reiniciar, Reiniciamos el tablero
				resetearJuego();
			} else {
				// Hemos pulsado uno de los botones del tablero
				// Buscamos el botón pulsado
				JButton botonPulsado = (JButton) e.getSource();
				if (!botonPulsado.getText().equals("")) return; // No hace nada si ya está marcado
				// Deshabilitar el botón para que ya no pueda ser pulsado
				// botonPulsado.setEnabled(false);
				
				// Aumentamos en 1 las jugadas
				this.addJugada();

				// Hacemos los cambios en el botón que corresponda
				if (turnoX) {
					quienJuega = "X";
					botonPulsado.setText("X");
					label.setText("Turno: O");
					botonPulsado.setForeground(Color.RED);
				} else {
					quienJuega = "O";
					botonPulsado.setText("O");
					label.setText("Turno: X");			
					botonPulsado.setForeground(Color.GREEN);
				}
				
				// comprobar si ha ganado alguien
				if (hayGanador(quienJuega)) {
					informarGanador(quienJuega);
					resetearJuego();
				} else if (this.ultimaJugada ()) {
					// Se ha producido la última jugada y no ha hay ganador
					informarFinDePartida();
					resetearJuego();
				} else {
					// Si no hay ni ganador ni ha terminado la partida
					// cambiamos el turno y seguimos jugando
					turnoX=!turnoX;
				}
			}
		}
		
		void empezar () {		
			setVisible(true);
		}

}
