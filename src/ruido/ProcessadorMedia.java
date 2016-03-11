package ruido;

import java.util.List;

public abstract class ProcessadorMedia extends AlgoritmoRuido {

	@Override
	protected int aplicaRemocaoDeRuido(List<Integer> vizinhos) {
		return getMedia(vizinhos);
	}
	
	private int getMedia(List<Integer> vizinhosR) {
		int valorTotal = 0;
		for (Integer vlrVizinho : vizinhosR) {
			valorTotal+=vlrVizinho;
		}
		return valorTotal/vizinhosR.size();
	}

}
