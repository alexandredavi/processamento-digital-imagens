package pdi;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;
import static pdi.PixelsUtils.getPixel;

import java.awt.image.BufferedImage;

public class CalculadorDeHistograma {

    private int[] histogramaR = new int[256];
    private int[] histogramaG = new int[256];
    private int[] histogramaB = new int[256];

    private int[] histogramaRAcumulado = new int[256];
    private int[] histogramaGAcumulado = new int[256];
    private int[] histogramaBAcumulado = new int[256];

    public void calculaHistogramas(BufferedImage img) {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                Integer pixelR = getPixel(img, i, j, POSICAO_R);
                histogramaR[pixelR]++;

                Integer pixelG = getPixel(img, i, j, POSICAO_G);
                histogramaG[pixelG]++;

                Integer pixelB = getPixel(img, i, j, POSICAO_B);
                histogramaB[pixelB]++;
            }
        }
    }
    
    public void acumulaHistogramas(BufferedImage img){
    	calculaHistogramas(img);
    	histogramaRAcumulado = acumulaHistograma(histogramaR);
    	histogramaGAcumulado = acumulaHistograma(histogramaG);
    	histogramaBAcumulado = acumulaHistograma(histogramaB);
    }
    
    private int[] acumulaHistograma(int[] histograma) {
		int[] histogramaAcumulado = new int[histograma.length];
		
		for (int i = 0; i < histograma.length; i++) {
			histogramaAcumulado[i] = i == 0 ? histograma[i] : histograma[i] + histogramaAcumulado[i-1];
		}
		
		return histogramaAcumulado;
	}

	public int[] getHistogramaR() {
		return histogramaR;
	}

	public int[] getHistogramaG() {
		return histogramaG;
	}

	public int[] getHistogramaB() {
		return histogramaB;
	}

	public int[] getHistogramaRAcumulado() {
		return histogramaRAcumulado;
	}

	public int[] getHistogramaGAcumulado() {
		return histogramaGAcumulado;
	}

	public int[] getHistogramaBAcumulado() {
		return histogramaBAcumulado;
	}

}
