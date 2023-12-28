package com.cc;

import java.util.Scanner;

public class Ex {

	public static void main(String[] args) {
		String s = "    this    is karun";
		StringBuilder n = new StringBuilder();
		Scanner sc = new Scanner(s);
		while(sc.hasNext()) {
			StringBuilder nw = new StringBuilder().append(sc.next()+" ");
			n = nw.append(n);
		}
		System.out.println(n);
		
		
		
	}

}
