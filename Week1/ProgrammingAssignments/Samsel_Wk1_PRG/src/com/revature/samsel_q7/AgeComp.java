package com.revature.samsel_q7;

import java.util.Comparator;

public class AgeComp implements Comparator<SortCompInt>{

	@Override
	public int compare(SortCompInt o1, SortCompInt o2) {
		// TODO Auto-generated method stub
		if(o1.age==o2.age)
			return 0;
		else if(o1.age > o2.age)
			return 1;
		else
			return 0;
	}
}
