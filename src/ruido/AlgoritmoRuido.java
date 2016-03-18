package ruido;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;

import java.awt.image.BufferedImage;
import java.util.List;

import pdi.ProcessadorImagem;

public abstract class AlgoritmoRuido extends ProcessadorImagem {

	
	@Override
	public int[] calculaPixeis(BufferedImage img, int i, int j) {
		int medianas[] = new int[3];

		List<Integer> vizinhosR = getPixeisVizinhos(img, i, j, POSICAO_R);
		List<Integer> vizinhosG = getPixeisVizinhos(img, i, j, POSICAO_G);
		List<Integer> vizinhosB = getPixeisVizinhos(img, i, j, POSICAO_B);

		medianas[0] = aplicaRemocaoDeRuido(vizinhosR);
		medianas[1] = aplicaRemocaoDeRuido(vizinhosG);
		medianas[2] = aplicaRemocaoDeRuido(vizinhosB);

		return medianas;
	}
	
	protected abstract List<Integer> getPixeisVizinhos(BufferedImage img, int i, int j, int tipoPixel);
	
	protected abstract int aplicaRemocaoDeRuido(List<Integer> vizinhos);

}
