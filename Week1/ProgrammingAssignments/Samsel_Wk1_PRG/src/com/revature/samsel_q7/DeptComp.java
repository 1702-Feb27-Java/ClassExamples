package com.revature.samsel_q7;

import java.util.Comparator;

public class DeptComp implements Comparator<SortCompInt>{

	@Override
	public int compare(SortCompInt o1, SortCompInt o2) {
		return (o1.department).compareTo(o2.department);
	}
}
