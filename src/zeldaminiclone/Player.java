package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/* O retangulo(Rectangle) vai ter toda a box de colisão, vai ter todo o sistema
que dos vetores que precisamos para movimentar o Player
*/
public class Player extends Rectangle {
	// Variáveis de controle do Player
	public int spd = 4; // Velocidade do player
	public boolean right, up, down, left;
	
	public Player(int x, int y) {
	 super(x, y, 32, 32);
	}
	
	// Aqui vai ficar a lógica do meu player
	public void tick() {
	 if (right && World.isFree(x+spd, y)) {
	   x += spd;
	 } else if (left && World.isFree(x-spd, y)) {
	   x -= spd;
	 }
	
	 if (up && World.isFree(x, y-spd)) {
	   y -= spd;
	 } else if (down && World.isFree(x, y+spd)) {
	   y += spd;
	 }
	}
	
	public void render(Graphics g) {
	 // g.setColor(Color.blue);
	 // g.fillRect(x, y, width, height);
	 g.drawImage(Spritesheet.player_front, x, y, 32, 32, null);
	}
}

