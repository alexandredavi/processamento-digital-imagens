package adicaoesubtracao;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;

import java.awt.image.BufferedImage;

import pdi.PixelsUtils;
import pdi.ProcessadorImagem;

public class AlgoritmoAdicao extends ProcessadorImagem {
	
	private BufferedImage imagemSomar;
	private double transparencia1;
	private double transparencia2;

	public AlgoritmoAdicao(BufferedImage imagemAdicionar, int fator1, int fator2) {
		this.imagemSomar = imagemAdicionar;
		this.transparencia1 = ((double)fator1)/100;
		this.transparencia2 = ((double)fator2)/100;
	}

	@Override
	public int[] calculaPixeis(BufferedImage img, int i, int j) {
		int pixeis[] = new int[3];

		
		pixeis[0] = aplicaSomaDePixel(i, j, img, POSICAO_R);
		pixeis[1] = aplicaSomaDePixel(i, j, img, POSICAO_G);
		pixeis[2] = aplicaSomaDePixel(i, j, img, POSICAO_B);

		return pixeis;
	}

	private int aplicaSomaDePixel(int i, int j, BufferedImage img, int posicao) {
		Integer pixel = PixelsUtils.getPixel(img, i, j, posicao);
		Integer pixelSomar;
		try {
			pixelSomar = PixelsUtils.getPixel(imagemSomar, i, j, posicao);
		} catch (ArrayIndexOutOfBoundsException e) {
			pixelSomar = 0;
		}
		if (pixelSomar != null) {
			return (int)((pixel*transparencia1)+(pixelSomar*transparencia2));
		}
		return pixel;
	}

}
