package com.revature.arrays;

public class MainApp {

	public static void main(String[] args){
		int arr1[], arr2[], arr3[];

		arr1 = new int[5];

		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = (i + 1);
		}

		for (int i : arr1) {
			System.out.println(i);
		}

		int[] arr4 = { 1, 2, 3, 4, 5 };

		int j = 5;
		MainApp.changeInt(j);

		System.out.println(j); // 5

		MainApp.changeIndex0(arr4);

		System.out.println(arr4[0]);

		String s1 = "dog";
		String s2 = "dog";
		String s3 = "cat";
		String s4 = new String("dog");

		System.out.println("s1 = s2: " + s1 == s2);
		System.out.println("s1 = s3: " + s1 == s3);
		System.out.println("s1 = s4: " + s1 == s4);
		System.out.println("s1 equals s2: " + s1.equals(s2));
		System.out.println("s1 equals s3: " + s1.equals(s3));
		System.out.println("s1 equals s4: " + s1.equals(s4));

		System.out.println("s1 loc: " + System.identityHashCode(s1));
		System.out.println("s2 loc: " + System.identityHashCode(s2));
		System.out.println("s3 loc: " + System.identityHashCode(s3));
		System.out.println("s4 loc: " + System.identityHashCode(s4));

		System.out.println("interning s4");
		s4 = s4.intern();
		System.out.println("s4 loc: " + System.identityHashCode(s4));

	}

	public static void changeIndex0(int[] arr) {
		arr[0] = 10;
	}

	public static void changeInt(int num) {
		num = 10;
	}

}
