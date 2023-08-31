import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Definição da classe Ball, que herda características da classe Rectangle
public class Ball extends Rectangle {
    Random random; // Objeto para gerar números aleatórios
    int xVelocity; // Velocidade horizontal da bola
    int yVelocity; // Velocidade vertical da bola
    int initialSpeed = 2; // Velocidade inicial da bola

    // Construtor da classe Ball
    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height); // Chama o construtor da classe base (Rectangle)
        random = new Random(); // Inicializa o gerador de números aleatórios
        // Gera uma direção horizontal aleatória (-1 ou 1) para a bola
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection * initialSpeed);

        // Gera uma direção vertical aleatória (-1 ou 1) para a bola
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);
    }

    // Define a velocidade horizontal da bola
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    // Define a velocidade vertical da bola
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    // Move a bola de acordo com as velocidades definidas
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    // Desenha a bola na tela
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, height, width); // Desenha a bola como um círculo preenchido
    }
}
