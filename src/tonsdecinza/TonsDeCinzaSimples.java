package tonsdecinza;

public class TonsDeCinzaSimples extends AlgoritmoTonsDeCinza {

	@Override
	protected int aplicaConversaoParaCinza(Integer pixelR, Integer pixelG, Integer pixelB) {
		return (pixelB + pixelG + pixelR) / 3;
	}
}
