package tonsdecinza;

public class TonsDeCinzaPonderado extends AlgoritmoTonsDeCinza {

	int percentualR;
	int percentualG;
	int percentualB;
	
	
	public TonsDeCinzaPonderado(int percentualR, int percentualG, int percentualB) {
		this.percentualR = percentualR;
		this.percentualG = percentualG;
		this.percentualB = percentualB;
	}


	@Override
	protected int aplicaConversaoParaCinza(Integer pixelR, Integer pixelG, Integer pixelB) {
		return ((pixelR * percentualR) + (pixelG * percentualG) + (pixelB * percentualB)) / 100;
	}
	
}
