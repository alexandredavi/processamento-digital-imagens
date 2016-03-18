package linearizacao;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;

import java.awt.image.BufferedImage;

import pdi.PixelsUtils;
import pdi.ProcessadorImagem;

public class AlgoritmoLinearizacao extends ProcessadorImagem {

	int pontoDeCorte = 0;
	
	public AlgoritmoLinearizacao(int pontoDeCorte) {
		this.pontoDeCorte = pontoDeCorte;
	}
	
	@Override
	public int[] calculaPixeis(BufferedImage img, int i, int j) {
		int pixeis[] = new int[3];

		Integer pixelR = getPixeis(img, i, j, POSICAO_R);
		Integer pixelG = getPixeis(img, i, j, POSICAO_G);
		Integer pixelB = getPixeis(img, i, j, POSICAO_B);

		pixeis[0] = aplicaLinearizacao(pixelR);
		pixeis[1] = aplicaLinearizacao(pixelG);
		pixeis[2] = aplicaLinearizacao(pixelB);

		return pixeis;
	}
	
	private int aplicaLinearizacao(Integer pixel) {
		return pixel <= pontoDeCorte ? 0 : 255;
	}

	private Integer getPixeis(BufferedImage img, int i, int j, int posicao) {
		return PixelsUtils.getPixel(img, i, j, posicao);
	}

}
