package transparencia;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;

import java.awt.image.BufferedImage;

import pdi.PixelsUtils;
import pdi.ProcessadorImagem;

public class AlgoritmoTransparencia extends ProcessadorImagem {
	
	private double transparencia1;
	private double transparencia2;

	public AlgoritmoTransparencia(int fator1, int fator2) {
		this.transparencia1 = ((double)fator1)/100;
		this.transparencia2 = ((double)fator2)/100;
	}

	@Override
	public int[] calculaPixeis(BufferedImage img, int i, int j) {
		int pixeis[] = new int[3];

		Integer pixelR = PixelsUtils.getPixel(img, i, j, POSICAO_R);
		Integer pixelG = PixelsUtils.getPixel(img, i, j, POSICAO_G);
		Integer pixelB = PixelsUtils.getPixel(img, i, j, POSICAO_B);

		pixeis[0] = aplicaTransparencia(pixelR);
		pixeis[1] = aplicaTransparencia(pixelG);
		pixeis[2] = aplicaTransparencia(pixelB);
		
		return pixeis;
	}

	private int aplicaTransparencia(Integer pixel) {
		return (int)((pixel * transparencia2) + (255 * transparencia1));
	}

}
