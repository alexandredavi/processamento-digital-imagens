package pdi;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PixelsUtils {
	
	public static final int POSICAO_R = 0;
	public static final int POSICAO_G = 1;
	public static final int POSICAO_B = 2;

	
	public static List<Integer> getPixeisVizinhos3x3(BufferedImage img, int i, int j, int posicaoRgb) {
		List<Integer> pixeisVizinhos = new ArrayList<>();
		WritableRaster raster = img.getRaster();
		int pixels [] = new int[4];
		pixeisVizinhos.add(raster.getPixel(i, j, pixels)[posicaoRgb]);
		pixeisVizinhos.add(raster.getPixel(i-1, j-1,pixels)[posicaoRgb]);
		pixeisVizinhos.add(raster.getPixel(i-1, j,pixels)[posicaoRgb]);
		pixeisVizinhos.add(raster.getPixel(i-1, j+1,pixels)[posicaoRgb]);
		pixeisVizinhos.add(raster.getPixel(i, j-1,pixels)[posicaoRgb]);
		pixeisVizinhos.add(raster.getPixel(i, j,pixels)[posicaoRgb]);
		pixeisVizinhos.add(raster.getPixel(i, j+1,pixels)[posicaoRgb]);
		pixeisVizinhos.add(raster.getPixel(i+1, j-1,pixels)[posicaoRgb]);
		pixeisVizinhos.add(raster.getPixel(i+1, j,pixels)[posicaoRgb]);
		pixeisVizinhos.add(raster.getPixel(i+1, j+1,pixels)[posicaoRgb]);
		
		Collections.sort(pixeisVizinhos);
		
		return pixeisVizinhos;
	}

}
