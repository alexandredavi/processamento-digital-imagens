package rotacao;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class GiraImagem {

	
	public BufferedImage girar(BufferedImage img){
		WritableRaster raster = img.getRaster();
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		WritableRaster rasterNewImage = newImage.getRaster();
		int pixels [] = new int[4];
		for (int i = 1; i < img.getWidth()-1; i++) {
			for (int j = 1; j < img.getHeight()-1; j++) {
				raster.getPixel(i, j, pixels);
				rasterNewImage.setPixel(img.getWidth()-i, img.getHeight()-j, pixels);
			}
		}

		newImage.setData(rasterNewImage);
		
		return newImage;
		
	}
}
