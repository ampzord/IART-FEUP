package database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.java_cup.internal.runtime.Scanner;

import src.Utilities;

public class Database {

	private static ArrayList<String> authors = new ArrayList<String>();
	private static ArrayList<String> themes = new ArrayList<String>();
	private static ArrayList<String> periods = new ArrayList<String>();


	public Database() throws IOException {
		File theme = new File("database/themes.txt");
		File author = new File("database/authors.txt");

		BufferedReader br_theme = new BufferedReader(new FileReader(theme));
		BufferedReader br_author = new BufferedReader(new FileReader(author));

		String st;
		int i = 0;
		while ((st = br_theme.readLine()) != null || i < Utilities.THEME_DATABASE_SIZE) {
			themes.add(st);
			i++;
		}
		
		st = "";
		i = 0;
		while ((st = br_author.readLine()) != null || i < Utilities.AUTHORS_DATABASE_SIZE) {
			authors.add(st);
			i++;
		}
		
		
		periods.add("08:00 -> 10:00 AM");
		periods.add("11:00 -> 13:00 AM");
		periods.add("14:00 -> 16:00 PM");
		periods.add("17:00 -> 19:00 PM");
	}


	/**
	 * @return the authors
	 */
	public static ArrayList<String> getAuthors() {
		return authors;
	}
	
	/**
	 * @return String author name
	 */
	public static String getAuthorsByID(int id) {
		return authors.get(id);
	}


	/**
	 * @return the themes
	 */
	public static ArrayList<String> getThemes() {
		return themes;
	}
	
	
	/**
	 * @return  String theme name
	 */
	public static String getThemessByID(int id) {
		return themes.get(id);
	}
	
	/** 
	 * @return String name of period
	 */
	public static String getScheduleByID(int id) {
		return periods.get(id);
	}
	
	
}

