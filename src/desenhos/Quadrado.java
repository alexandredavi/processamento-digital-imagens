package desenhos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Quadrado {

	public BufferedImage criaQuadrado(BufferedImage img, PosicoesDTO posicoes){
		Graphics graphics = img.getGraphics();
		Graphics2D graphics2d = (Graphics2D) graphics.create();
		graphics2d.setStroke(new BasicStroke(4));
		graphics2d.setColor(Color.BLUE);

		int x1 = posicoes.getX1();
		int x2 = posicoes.getX2();
		int y1 = posicoes.getY1();
		int y2 = posicoes.getY2();
		
		graphics2d.drawPolygon(new int[]{x1, x1, x2, x2}, new int[]{y1, y2, y2, y1}, 4);
		graphics2d.dispose();
		return img;
	}
	
}
