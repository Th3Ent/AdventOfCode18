package day2;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import day1.FilePicker;
import day1.Frecuency;

public class CheckSum {
	int two;
	int three;
	List<String> codes = new ArrayList<String>();
	
	public CheckSum(String file) {
		two = 0;
		three = 0;
		FilePicker fp = new FilePicker(file);
		codes = fp.PickAll();
	}
	
	public String generateCheckSum() {
		for(int i = 0; i < codes.size(); i++) {
			Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
			for(int j = 0; j < codes.get(i).length() ; j++) {
				String word = Character.toString((codes.get(i).charAt(j)));
				if(ht.containsKey(word)) {
					int count = 1 + ht.get(word);
					ht.remove(word);
					ht.put(word, count);
				}else
					ht.put(word, 1);
			}
			List<Integer> values = new ArrayList<Integer>(ht.values());
			boolean twob = false;
			boolean threeb = false;
			for(int j = 0; j < values.size(); j++) {
				if(values.get(j) == 2 && !twob) {
					twob = true;
					two+=1;
				}else if(values.get(j) == 3 && !threeb) {
						threeb = true;
						three+=1;
					  }
			}
		}
		return two + " * " + three + " = " + (two*three); 
	}
	
	public String commonLetters() {
		String sol = "";
		for(int i = 0; i < codes.size(); i++) {
			for(int j = i+1; j < codes.size(); j++) {
				int diferencia = 0;
				int pos = 0;
				for(int k = 0; k < codes.get(i).length(); k++) {
					if(codes.get(i).charAt(k) != codes.get(j).charAt(k)) {
						diferencia++;
						pos = k;
					}
				}
				if(diferencia == 1) {
					for(int k = 0; k < codes.get(i).length(); k++) {
						if(k != pos)
							sol += Character.toString(codes.get(i).charAt(k));
					}
					
				}
						
			}
		}
		
		return sol;
	}
	
	
	public static void main(String[] args) {
		CheckSum cs = new CheckSum(args[0]);
		System.out.println(cs.generateCheckSum());
		System.out.println(cs.commonLetters());

	}
	
}
