package day5;

import java.util.*;

import day1.FilePicker;

public class PolimerReactions {

	List<String> chain = new ArrayList<String>();
	List<String> letras = new ArrayList<String>();
	public PolimerReactions(String File) {
		List<String> dummy = new ArrayList<String>();
		FilePicker fp = new FilePicker(File);
		dummy = fp.PickAll();
		for(int j = 0; j < dummy.size(); j++)
		for(int i = 0; i < dummy.get(j).length() ; i++)
			chain.add(Character.toString(dummy.get(j).charAt(i)));
		
		
	}
	
	public int resultPolimer() {
		
		List<String> dummy = new ArrayList<String>(chain);

		boolean change = true;
		while(change) {
			change = false;
			
			for(int i = 0; i+1 < dummy.size() && !change; i++) {
				if(!letras.contains(dummy.get(i).toLowerCase())) {
					letras.add(dummy.get(i).toLowerCase());
				}
				if(dummy.get(i).toUpperCase().equals(dummy.get(i+1)) && !dummy.get(i).equals(dummy.get(i+1))) {
					change = true;
					dummy.remove(i+1);
					dummy.remove(i);
				} else if(dummy.get(i).toLowerCase().equals(dummy.get(i+1)) && !dummy.get(i).equals(dummy.get(i+1))) {
					change = true;
					dummy.remove(i+1);
					dummy.remove(i);
				}
			}
		}
		
		
		
		return dummy.size();
	}
	
	public void eleminateAndResult() {
		for(int i = 0 ; i < letras.size() ; i++) {
			List<String> dummy = new ArrayList<String>(chain);
			boolean change = true;
			int count = 0;
			while(count < dummy.size()){
				boolean aumentar = true;
				if(dummy.get(count).equals(letras.get(i))) {
					dummy.remove(count);
					aumentar = false;
				}else if(dummy.get(count).equals(letras.get(i).toUpperCase())) {
					dummy.remove(count);
					aumentar = false;
				}
				if(aumentar)
					count++;
					
			}
			while(change) {
				change = false;
				
				for(int k = 0; k+1 < dummy.size() && !change; k++) {
					if(!letras.contains(dummy.get(k).toLowerCase())) {
						letras.add(dummy.get(k).toLowerCase());
					}
					if(dummy.get(k).toUpperCase().equals(dummy.get(k+1)) && !dummy.get(k).equals(dummy.get(k+1))) {
						change = true;
						dummy.remove(k+1);
						dummy.remove(k);
					} else if(dummy.get(k).toLowerCase().equals(dummy.get(k+1)) && !dummy.get(k).equals(dummy.get(k+1))) {
						change = true;
						dummy.remove(k+1);
						dummy.remove(k);
					}
				}
			}
			System.out.println("Letras removida: "+letras.get(i) + " Tamaño resultante:" + dummy.size());
		}
		
	}
	
	
	public static void main(String[] args) {
	
		PolimerReactions pr = new PolimerReactions(args[0]);
		System.out.println(pr.resultPolimer());
		pr.eleminateAndResult();
	
	}
	
}
