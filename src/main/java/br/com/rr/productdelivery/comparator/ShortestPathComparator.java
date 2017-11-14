package br.com.rr.productdelivery.comparator;

import java.util.Comparator;

import br.com.rr.productdelivery.model.Path;

public class ShortestPathComparator implements Comparator<Path> {
	
	@Override
	public int compare(Path o1, Path o2) {
		return Integer.valueOf(o1.getNodes().size()).compareTo(o2.getNodes().size());
	}
}