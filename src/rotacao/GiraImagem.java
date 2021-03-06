package rotacao;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import desenhos.PosicoesDTO;

public class GiraImagem {

	
	public BufferedImage girar(BufferedImage img, PosicoesDTO posicoes){
		WritableRaster raster = img.getRaster();
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		WritableRaster rasterNewImage = newImage.getRaster();
		int pixels [] = new int[4];
		for (int i = posicoes.getX1(); i < posicoes.getX2(); i++) {
			for (int j = posicoes.getY1(); j < posicoes.getY2(); j++) {
				raster.getPixel(i, j, pixels);
				rasterNewImage.setPixel(i, img.getHeight()-j, pixels);
			}
		}

		newImage.setData(rasterNewImage);
		
		return newImage;
		
	}
}
