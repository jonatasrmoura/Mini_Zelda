package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blocks extends Rectangle {
  
  public Blocks(int x, int y) {
    // tamanho do meus blocos 32x32
    super(x, y, 32, 32);
  }

  public void render(Graphics g) {
    g.setColor(Color.MAGENTA);
    g.fillRect(x, y, width, height);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, width, height);
  }
}
