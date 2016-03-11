package ruido;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.List;

public abstract class AlgoritmoRuido {

	public BufferedImage processaAlgoritmo(BufferedImage img){
		WritableRaster raster = img.getRaster();
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		int pixels [] = new int[4];
		for (int i = 1; i < img.getWidth()-1; i++) {
			for (int j = 1; j < img.getHeight()-1; j++) {
				raster.getPixel(i, j, pixels);
				int[] medianas = calculaPixeis(img, i, j);
				pixels[0] = medianas[0];
				pixels[1] = medianas[1];
				pixels[2] = medianas[2];
				
				raster.setPixel(i, j, pixels);
			}
		}

		newImage.setData(raster);
		
		return newImage;
		
	}
	
	private int[] calculaPixeis(BufferedImage img, int i, int j) {
		int medianas[] = new int[3];

		List<Integer> vizinhosR = getPixeisVizinhos(img, i, j, POSICAO_R);
		List<Integer> vizinhosG = getPixeisVizinhos(img, i, j, POSICAO_G);
		List<Integer> vizinhosB = getPixeisVizinhos(img, i, j, POSICAO_B);

		medianas[0] = aplicaRemocaoDeRuido(vizinhosR);
		medianas[1] = aplicaRemocaoDeRuido(vizinhosG);
		medianas[2] = aplicaRemocaoDeRuido(vizinhosB);

		return medianas;
	}
	
	protected abstract List<Integer> getPixeisVizinhos(BufferedImage img, int i, int j, int tipoPixel);
	
	protected abstract int aplicaRemocaoDeRuido(List<Integer> vizinhos);

}
