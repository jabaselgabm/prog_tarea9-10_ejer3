
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class TresEnRaya {
	
	class Tablero {
		final int alto = 3;
		final int ancho = 3;
		
		char [][] jugada = new char[3][3];
		
		[[(0,0), [(0, 1), (0,2)], [(1,0), [(1, 1), (1,2)]]
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Una ventana principal (JFrame) que contenga el tablero del juego.
		JFrame frame = new JFrame("Tres en Raya");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(606, 606);
		
		// Elementos para el juego
		// Panel superior
		// Un JLabel en la parte superior que indique el turno actual del jugador.
		JPanel panelArriba = new JPanel ();
		JLabel label = new JLabel ("Turno: X"); // Primer turno X 
		panelArriba.add(label);
		
		// Panel Central
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(0x000000));
		
		// Panel inferior
		// Un botón 'Reiniciar' que permita comenzar una nueva partida.
		JPanel panelAbajo = new JPanel ();
		JButton reiniciar = new JButton ("Reiniciar");
		reiniciar.setFont(new Font("Arial", Font.BOLD, 18));
		reiniciar.setBackground(new Color (0x4d8076));
		// Lo meto en un Grid y asi ocupa todo el ancho y alto como
		// en la imagenes de muestra
		panelAbajo.setLayout(new GridLayout(1, 1, 0, 0));
		panelAbajo.add(reiniciar);
	
		// El tablero debe estar formado por una cuadrícula de 3x3 botones (JButton)
		// Para mejorar la apariencia los he metido en un layout tipo Grid
		// así se autoajusta
		int filas = 3;
		int columnas = 3; 
		JButton [][] tablero = new JButton [filas][columnas];
		
		panelCentral.setLayout(new GridLayout(filas, columnas, 0, 0));
		for (int i = 0; i < filas; ++i) {
			for (int j = 0; j < columnas; ++j) {
				tablero[i][j] = new JButton(String.valueOf(1 + j + (i * columnas)));
				tablero[i][j].setFont(new Font("Arial", Font.BOLD, 40));
				panelCentral.add(tablero[i][j]);
			}
		}

		frame.getContentPane().add(BorderLayout.NORTH, panelArriba);
		frame.getContentPane().add(BorderLayout.CENTER, panelCentral);
		frame.getContentPane().add(BorderLayout.SOUTH, panelAbajo);
		
		frame.setVisible(true);

				
		
		

		
		

		

		
		


	}

}
