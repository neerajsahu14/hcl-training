package org.oop.collection;

import java.util.Vector;

public class VectorUseCase {
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<>(3,2);
		System.out.println(v.size());
		System.out.println(v.capacity());
		v.addElement(4);
		v.add(10);
		System.out.println(v);
		System.out.println(v.capacity());
		System.out.println(v.size());
//		System.out.println(v);
//		System.out.println(v.size());
//		System.out.println(v.size());
//		System.out.println(v.size());
//		System.out.println(v.size());

	}
}
