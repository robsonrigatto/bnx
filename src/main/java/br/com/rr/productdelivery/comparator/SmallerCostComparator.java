package br.com.rr.productdelivery.comparator;

import java.util.Comparator;

import br.com.rr.productdelivery.model.Path;

public class SmallerCostComparator implements Comparator<Path> {

	@Override
	public int compare(Path o1, Path o2) {
		return o1.getTotalDistance().compareTo(o2.getTotalDistance());
	}

}
