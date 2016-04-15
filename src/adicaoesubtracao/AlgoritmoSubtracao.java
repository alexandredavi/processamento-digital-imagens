package adicaoesubtracao;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;

import java.awt.image.BufferedImage;

import pdi.PixelsUtils;
import pdi.ProcessadorImagem;

public class AlgoritmoSubtracao extends ProcessadorImagem {

	private BufferedImage imagemSomar;

	public AlgoritmoSubtracao(BufferedImage imagemAdicionar) {
		this.imagemSomar = imagemAdicionar;
	}
	
	@Override
	public int[] calculaPixeis(BufferedImage img, int i, int j) {
		int pixeis[] = new int[3];

		
		pixeis[0] = aplicaSubtracaoDePixel(i, j, img, POSICAO_R);
		pixeis[1] = aplicaSubtracaoDePixel(i, j, img, POSICAO_G);
		pixeis[2] = aplicaSubtracaoDePixel(i, j, img, POSICAO_B);

		return pixeis;
	}

	private int aplicaSubtracaoDePixel(int i, int j, BufferedImage img, int posicao) {
		Integer pixel = PixelsUtils.getPixel(img, i, j, posicao);
		Integer pixelSomar;
		try {
			pixelSomar = PixelsUtils.getPixel(imagemSomar, i, j, posicao);
		} catch (ArrayIndexOutOfBoundsException e) {
			pixelSomar = 0;
		}
		if (pixelSomar != null) {
			int subtracao = pixel-pixelSomar;
			if (subtracao < 0) {
				return subtracao*-1;
			}
			return subtracao;
		}
		return pixel;
	}

}
