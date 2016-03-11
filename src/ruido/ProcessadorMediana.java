package ruido;

import java.util.List;

public abstract class ProcessadorMediana extends AlgoritmoRuido{
	
	@Override
	protected int aplicaRemocaoDeRuido(List<Integer> vizinhos) {
		return getMediana(vizinhos);
	}
	private int getMediana(List<Integer> vizinhosR) {
		return vizinhosR.get(4);
	}

}
