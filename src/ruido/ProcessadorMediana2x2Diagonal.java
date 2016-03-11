package ruido;

import java.awt.image.BufferedImage;
import java.util.List;

import pdi.PixelsUtils;

public class ProcessadorMediana2x2Diagonal extends AlgoritmoRuido {

	@Override
	protected List<Integer> getPixeisVizinhos(BufferedImage img, int i, int j, int tipoPixel) {
		return PixelsUtils.getPixeisVizinhos2x2Diagonal(img, i, j, tipoPixel);
	}

	@Override
	protected int aplicaRemocaoDeRuido(List<Integer> vizinhos) {
		return vizinhos.get(2);
	}

}
