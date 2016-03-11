package ruido;

import java.awt.image.BufferedImage;
import java.util.List;

import pdi.PixelsUtils;

public class ProcessadorMedia3x3 extends ProcessadorMedia{

	@Override
	protected List<Integer> getPixeisVizinhos(BufferedImage img, int i, int j, int tipoPixel) {
		return PixelsUtils.getPixeisVizinhos3x3(img, i, j, tipoPixel);
	}

}
