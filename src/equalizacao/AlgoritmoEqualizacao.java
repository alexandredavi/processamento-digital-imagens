package equalizacao;

import static pdi.PixelsUtils.POSICAO_R;

import java.awt.image.BufferedImage;

import pdi.CalculadorDeHistograma;
import pdi.PixelsUtils;
import pdi.ProcessadorImagem;

public class AlgoritmoEqualizacao extends ProcessadorImagem {
	
	

	private int[] histogramaRAcumulado;

	public AlgoritmoEqualizacao(BufferedImage img) {
		super();
		CalculadorDeHistograma calculador = new CalculadorDeHistograma();
		calculador.acumulaHistogramas(img);
		this.histogramaRAcumulado = calculador.getHistogramaRAcumulado();
	}

	@Override
	public int[] calculaPixeis(BufferedImage img, int i, int j) {
		int pixeis[] = new int[3];
		
		Integer pixelR = getPixeis(img, i, j, POSICAO_R);
		
		pixeis[0] = calculaEqualizacao(pixelR, img);
		pixeis[1] = calculaEqualizacao(pixelR, img);
		pixeis[2] = calculaEqualizacao(pixelR, img);
		
		return pixeis;
	}
	
	private int calculaEqualizacao(Integer pixel, BufferedImage img) {
		double mXn = img.getHeight()*img.getWidth();
		return Math.round((int) ((255/mXn) * histogramaRAcumulado[pixel]));
	}

	private Integer getPixeis(BufferedImage img, int i, int j, int posicao) {
		return PixelsUtils.getPixel(img, i, j, posicao);
	}

}
