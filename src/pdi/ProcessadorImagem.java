package pdi;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import desenhos.PosicoesDTO;

public abstract class ProcessadorImagem {
	
	public BufferedImage processaAlgoritmo(BufferedImage img, PosicoesDTO posicoes){
		WritableRaster raster = img.getRaster();
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		int pixels [] = new int[4];
		for (int i = posicoes.getX1(); i < posicoes.getY1()-1; i++) {
			for (int j = posicoes.getX2(); j < posicoes.getY2()-1; j++) {
				raster.getPixel(i, j, pixels);
				int[] novosPixels = calculaPixeis(img, i, j);
				pixels[0] = novosPixels[0];
				pixels[1] = novosPixels[1];
				pixels[2] = novosPixels[2];
				
				raster.setPixel(i, j, pixels);
			}
		}

		newImage.setData(raster);
		
		return newImage;
		
	}
	
	public abstract int[] calculaPixeis(BufferedImage img, int i, int j);

}
