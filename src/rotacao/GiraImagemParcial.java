package rotacao;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import desenhos.PosicoesDTO;

public class GiraImagemParcial {

	
	public BufferedImage girar(BufferedImage img, PosicoesDTO posicoes){
		WritableRaster raster = img.getRaster();
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		WritableRaster rasterNewImage = newImage.getRaster();
		int pixels [] = new int[4];
		for (int i = 1; i < img.getWidth()-1; i++) {
			for (int j = 1; j < img.getHeight()-1; j++) {
				raster.getPixel(i, j, pixels);
				rasterNewImage.setPixel(i, j, pixels);
			}
		}

		for (int i = posicoes.getX1(); i < posicoes.getX2(); i++) {
			int pixeisInvertidos = 0;
			for (int j = posicoes.getY1(); j < posicoes.getY2(); j++) {
				raster.getPixel(i, j, pixels);
				rasterNewImage.setPixel(i,posicoes.getY2()-pixeisInvertidos, pixels);
				pixeisInvertidos++;
			}
		}

		newImage.setData(rasterNewImage);
		
		return newImage;
		
	}
}
