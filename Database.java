import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.java_cup.internal.runtime.Scanner;

public class Database {

	private ArrayList<String> authors = new ArrayList<String>();
	private ArrayList<String> themes = new ArrayList<String>();


	public Database() throws IOException {
		File theme = new File("database/themes.txt");
		File author = new File("database/authors.txt");

		BufferedReader br_theme = new BufferedReader(new FileReader(theme));
		BufferedReader br_author = new BufferedReader(new FileReader(author));

		String st;
		while ((st = br_theme.readLine()) != null) {
			themes.add(st);
		}
		
		st = "";
		
		while ((st = br_author.readLine()) != null) {
			authors.add(st);
		}
		
		for(int i = 0; i < authors.size();i++) {
			System.out.println(authors.get(i));
		}
		
		for(int i = 0; i < themes.size();i++) {
			System.out.println(themes.get(i));
		}
	}
}

