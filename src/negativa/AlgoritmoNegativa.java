package negativa;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;

import java.awt.image.BufferedImage;

import pdi.PixelsUtils;
import pdi.ProcessadorImagem;

public class AlgoritmoNegativa extends ProcessadorImagem {

	@Override
	public int[] calculaPixeis(BufferedImage img, int i, int j) {
		int pixeis[] = new int[3];

		Integer pixelR = getPixeis(img, i, j, POSICAO_R);
		Integer pixelG = getPixeis(img, i, j, POSICAO_G);
		Integer pixelB = getPixeis(img, i, j, POSICAO_B);

		pixeis[0] = aplicaNegativo(pixelR);
		pixeis[1] = aplicaNegativo(pixelG);
		pixeis[2] = aplicaNegativo(pixelB);

		return pixeis;
	}
	
	private int aplicaNegativo(Integer pixel) {
		return 255 - pixel;
	}

	private Integer getPixeis(BufferedImage img, int i, int j, int posicao) {
		return PixelsUtils.getPixel(img, i, j, posicao);
	}

}
