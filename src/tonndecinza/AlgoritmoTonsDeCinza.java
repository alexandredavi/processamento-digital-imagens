package tonndecinza;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;

import java.awt.image.BufferedImage;

import pdi.PixelsUtils;
import pdi.ProcessadorImagem;

public abstract class AlgoritmoTonsDeCinza extends ProcessadorImagem {

	@Override
	public int[] calculaPixeis(BufferedImage img, int i, int j) {
		int pixeis[] = new int[3];

		Integer pixelR = getPixeis(img, i, j, POSICAO_R);
		Integer pixelG = getPixeis(img, i, j, POSICAO_G);
		Integer pixelB = getPixeis(img, i, j, POSICAO_B);

		int valorPixelConvertidoEmCinza = aplicaConversaoParaCinza(pixelR, pixelG, pixelB);
		
		pixeis[0] = valorPixelConvertidoEmCinza;
		pixeis[1] = valorPixelConvertidoEmCinza;
		pixeis[2] = valorPixelConvertidoEmCinza;

		return pixeis;
	}

	protected abstract int aplicaConversaoParaCinza(Integer pixelR, Integer pixelG, Integer pixelB);

	private Integer getPixeis(BufferedImage img, int i, int j, int posicao) {
		return PixelsUtils.getPixel(img, i, j, posicao);
	}

}
