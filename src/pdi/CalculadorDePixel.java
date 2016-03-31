package pdi;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;
import static pdi.PixelsUtils.getPixel;

import java.awt.image.BufferedImage;

public class CalculadorDePixel {

    private int[] qtdPixelsR = new int[256];
    private int[] qtdPixelsG = new int[256];
    private int[] qtdPixelsB = new int[256];

    public void calculaQuantidadeDePixels(BufferedImage img) {
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {

                Integer pixelR = getPixel(img, i, j, POSICAO_R);
                qtdPixelsR[pixelR]++;

                Integer pixelG = getPixel(img, i, j, POSICAO_G);
                qtdPixelsG[pixelG]++;

                Integer pixelB = getPixel(img, i, j, POSICAO_B);
                qtdPixelsB[pixelB]++;
            }
        }
    }

	public int[] getQtdPixelsR() {
		return qtdPixelsR;
	}

	public int[] getQtdPixelsG() {
		return qtdPixelsG;
	}

	public int[] getQtdPixelsB() {
		return qtdPixelsB;
	}

}
