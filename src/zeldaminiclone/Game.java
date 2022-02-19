package zeldaminiclone;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 * KeyListener *
 * � uma lib do JAVA que vai me dar os comandos para eu conseguir controlar 
 */

public class Game extends Canvas implements Runnable, KeyListener {
	
	// Definindo o tamanho da minha janela
	public static int WIDTH = 480, HEIGHT = 480;
	public Player player;

	public World world;
	
	public Game() {
		this.addKeyListener(this); // Add eventos de teclados
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		new Spritesheet();
		player = new Player(32, 32); // 32x32 � aonde o player ira aparecer quando o jogo come�a
		world = new World(); // Respons�vel por renderializar tudo, inigimos, blocos etc
	}

	// L�gica do jogo, movimenta��o do player, colis�es, etc
	public void tick() {
		player.tick();
	}

	// Renderizar os gr�ficos, todas as renderiza��o vai ficar aqui 
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		player.render(g);

		world.render(g);

		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Mini Zelda");
		
		// Empacotar tudo e calcular o tamanho certo da minha janela
		frame.pack();
		
		// Deixar a janela centralizada
		frame.setLocationRelativeTo(null);
		/* Quando eu fechar meu Jframe � para o processo do Java tamb�m ser fechado e finalizado,
		 para n�o ficar gastando mem�ria do computador de quem est� jogando. */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Ver a janela
		frame.setVisible(true);
		
		// Renderizar as a��es que o usu�rio faz na tela
		new Thread(game).start();
	}

	@Override
	public void run() {
		while(true) {
			tick();
			render();

			// Quero que meu jogo rode � 60FPS
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
	}
}
