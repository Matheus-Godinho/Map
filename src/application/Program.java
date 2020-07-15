package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		List<String> document;
		File file;
			String path;
		
			document = Arrays.asList("Alex Blue,15", "Maria Green,22", "Bob Brown,21",
					"Alex Blue,30", "Bob Brown,15", "Maria Green,27", "Maria Green,22",
					"Bob Brown,25", "Alex Blue,31");
		path = sc.next();
		file = new File(path);
		try {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				for (String d: document) {
					bw.append(d);
					bw.newLine();
				}
			}
			
			String line;
			String[] fields;
			Map<String, Integer> candidates;
				String name;
				int votes;
			
			candidates = new LinkedHashMap<>();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				line = br.readLine();
				while (line != null) {
					fields = line.split(",");
					name = fields[0];
					votes = Integer.parseInt(fields[1]);
					if (candidates.containsKey(name))
						votes += candidates.get(name);
					candidates.put(name, votes);
					line = br.readLine();
				}
			}
			for (String c: candidates.keySet())
				System.out.printf("%s,%d%n", c, candidates.get(c));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();

	}

}
