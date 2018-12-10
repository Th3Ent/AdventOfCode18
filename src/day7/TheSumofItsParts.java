package day7;

import java.util.*;

import day1.FilePicker;

public class TheSumofItsParts {

	List< List<String> > states = new ArrayList<List<String> >();
	
	public TheSumofItsParts(String file) {
		
		FilePicker fp = new FilePicker(file);
		List<String> dummy = new ArrayList<String>(fp.PickAll());
		
		for(int i = 0; i < dummy.size() ; i++) {
			states.add(new ArrayList<String> ());
			String[] cachos = dummy.get(i).split(" ");
			states.get(i).add(cachos[1]);
			states.get(i).add(cachos[7]);
		}
		for(int i = 0 ; i < states.size() ; i++) {
			int min = i;
			for(int j = i+1; j < states.size() ; j++) {
				if(states.get(min).get(1).charAt(0) > states.get(j).get(1).charAt(0)) {
					min = j;
				}
			}
			List<String> swap = new ArrayList<String>(states.get(min));
			states.set(min, states.get(i));
			states.set(i, swap);
		}
		
//		for(int i = 0 ; i < states.size() ; i++) {
//			System.out.println("Primero: " + states.get(i).get(0) + " , entonces: " + states.get(i).get(1));
//		}
	}
	
	
	public String orden() {
		String salida = "";
		List<String> estados = new ArrayList<String>();

		
		
		Hashtable<String, Boolean> isEnded = new Hashtable<String, Boolean>();
		for(int i = 0 ; i < states.size() ; i++) {
			if(!isEnded.containsKey(states.get(i).get(0))){
				isEnded.put(states.get(i).get(0), false);
				estados.add(states.get(i).get(0));
			}
			if(!isEnded.containsKey(states.get(i).get(1))) {
				isEnded.put(states.get(i).get(1), false);
				estados.add(states.get(i).get(1));
			}
		}
		
		for(int i = 0; i < states.size() ; i++) {
			boolean Inicial = true;
			for(int j = i+1 ; j < states.size() ; j++) {
				if(states.get(i).get(0) == states.get(j).get(1))
					Inicial = false;
			}
			if(Inicial) {
				isEnded.remove(states.get(i).get(0));
				isEnded.put(states.get(i).get(0), true);
			}
		}
		
		List<String> salidaA = new ArrayList<String>();
		boolean finish = false;
		int count = 0;
		while(!finish) {
			count++;
			boolean Posible = true;

			for(int j = 0; j < estados.size() && Posible ; j++) {
				
				for(int i = 0 ; i < states.size() ; i++) {
					if(!isEnded.get(states.get(i).get(0)) && !salidaA.contains(states.get(i).get(0)))
						Posible = false;
				}
				
				
				
				if(Posible && !salidaA.contains(estados.get(j))) {
					isEnded.remove(estados.get(j));
					isEnded.put(estados.get(j), true);
					salida += estados.get(j);
					salidaA.add(estados.get(j));
				}
			}
			finish = true;
			for(int j = 0; j < estados.size() ; j++) {
				System.out.print(estados.get(j) + " ");
				if(!salidaA.contains(estados.get(j))) {
					finish = false;
				}
					
				
			}
			
			System.out.println(count);
		}
		
		
		return salida;
		//CABDFE
	}
	
	
	
	
	public static void main(String[] args) {

		TheSumofItsParts sp = new TheSumofItsParts(args[0]);
		System.out.println(sp.orden());
		
	}

}
