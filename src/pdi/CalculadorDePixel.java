package pdi;

import static pdi.PixelsUtils.POSICAO_B;
import static pdi.PixelsUtils.POSICAO_G;
import static pdi.PixelsUtils.POSICAO_R;
import static pdi.PixelsUtils.getPixel;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class CalculadorDePixel {

    private Map<Integer, Integer> qtdPixelsRMap = new HashMap<>();
    private Map<Integer, Integer> qtdPixelsGMap = new HashMap<>();
    private Map<Integer, Integer> qtdPixelsBMap = new HashMap<>();

    public void calculaQuantidadeDePixels(BufferedImage img) {
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {

                Integer pixelR = getPixel(img, i, j, POSICAO_R);
                adicinaPixelNoMap(pixelR, qtdPixelsRMap);

                Integer pixelG = getPixel(img, i, j, POSICAO_G);
                adicinaPixelNoMap(pixelG, qtdPixelsGMap);

                Integer pixelB = getPixel(img, i, j, POSICAO_B);
                adicinaPixelNoMap(pixelB, qtdPixelsBMap);
            }
        }
    }

    private void adicinaPixelNoMap(Integer pixel, Map<Integer, Integer> qtdPixelsMap) {
        if (qtdPixelsMap.containsKey(pixel)) {
            Integer qtdPixel = qtdPixelsMap.get(pixel);
            qtdPixelsMap.put(pixel, ++qtdPixel);
        } else {
            qtdPixelsMap.put(pixel, 1);
        }
    }

    public Map<Integer, Integer> getQtdPixelsRMap() {
        return qtdPixelsRMap;
    }

    public Map<Integer, Integer> getQtdPixelsGMap() {
        return qtdPixelsGMap;
    }

    public Map<Integer, Integer> getQtdPixelsBMap() {
        return qtdPixelsBMap;
    }

}
