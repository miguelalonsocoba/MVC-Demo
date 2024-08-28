package com.cjava.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TreeSet;

@SpringBootApplication
public class S06DevDemo01Application {

	public static void main(String[] args) {
		TreeSet treeSet = new TreeSet<>();

		treeSet.add("Geeks");
		treeSet.add("for");
		treeSet.add("Geeks");
		treeSet.add("GeeksforGeeks");

		for (Object temp : treeSet)
			System.out.printf(temp + " ");

		System.out.println("\n");
		SpringApplication.run(S06DevDemo01Application.class, args);
	}


}
