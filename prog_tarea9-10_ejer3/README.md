Ejercicio 3
En este ejercicio se pide desarrollar una aplicación gráfica en Java utilizando la
biblioteca Swing. La aplicación consistirá en una versión del juego clásico Tres en
Raya (Tic-Tac-Toe) para dos jugadores.
Para ello, deberás crear una interfaz gráfica que permita a dos jugadores turnarse
para colocar sus símbolos (X y O) en un tablero de 3x3 hasta que uno de los
jugadores gane o el tablero quede lleno.
Requisitos de la interfaz gráfica
• Una ventana principal (JFrame) que contenga el tablero del juego.
• El tablero debe estar formado por una cuadrícula de 3x3 botones (JButton).
• Un JLabel en la parte superior que indique el turno actual del jugador.
• Un botón 'Reiniciar' que permita comenzar una nueva partida.
Funcionamiento del juego
1. El juego comienza con el turno del jugador X.
2. Cuando un jugador pulse una casilla vacía, se mostrará su símbolo (X u O).
3. Una casilla ya utilizada no puede volver a pulsarse.
4. Después de cada movimiento se debe comprobar si existe un ganador.
5. Si un jugador consigue tres símbolos iguales en línea (horizontal, vertical o
diagonal), gana la partida.
6. Si todas las casillas están ocupadas y nadie ha ganado, el resultado será un
empate.
7. Cuando el juego termina, debe mostrarse un mensaje indicando el resultado.
Requisitos técnicos
• Utilizar la biblioteca Swing para la interfaz gráfica.
• Gestionar los eventos de los botones mediante ActionListener.
• Utilizar una estructura de datos adecuada (por ejemplo, un array bidimensional
de JButton) para representar el tablero.
4
• Implementar métodos para comprobar si hay un ganador y si el tablero está
lleno.
• Implementar un método que reinicie el juego cuando se pulse el botón
'Reiniciar'