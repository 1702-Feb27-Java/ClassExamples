package com.revature.samsel_q7;

import java.util.Comparator;

public class NameComp implements Comparator<SortCompInt>{

	@Override
	public int compare(SortCompInt o1, SortCompInt o2) {
		return (o1.name).compareTo(o2.name);
	}

}
