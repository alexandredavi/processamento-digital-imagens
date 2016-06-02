package reconhecimento.geometrico;

import java.awt.image.BufferedImage;

import pdi.PixelsUtils;

public class ReconhecimentoQuadrado {
	
	public boolean isQuadradoPreenchido(BufferedImage img){
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				int pixel = PixelsUtils.getPixel(img, i, j, PixelsUtils.POSICAO_R);
				if (isPixelPreto(pixel)) {
					i++;
					j++;
					int proximoPixel = PixelsUtils.getPixel(img, i, j, PixelsUtils.POSICAO_R);
					if (isPixelPreto(proximoPixel)) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}

	private boolean isPixelPreto(int pixel) {
		return pixel == 0;
	}
}
