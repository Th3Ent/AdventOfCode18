package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilePicker {

	private String file;
	
	public FilePicker(String file) {
		
		this.file = file;
		
	}
	
	public List<String> PickAll() {
		
		List<String> dummy = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(new File(file));
			while(sc.hasNextLine()) {
				dummy.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dummy;
	}
	
}
