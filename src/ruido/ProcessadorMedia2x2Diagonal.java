package ruido;

import java.awt.image.BufferedImage;
import java.util.List;

import pdi.PixelsUtils;

public class ProcessadorMedia2x2Diagonal extends ProcessadorMedia {

	@Override
	protected List<Integer> getPixeisVizinhos(BufferedImage img, int i, int j, int tipoPixel) {
		return PixelsUtils.getPixeisVizinhos2x2Diagonal(img, i, j, tipoPixel);
	}

}
