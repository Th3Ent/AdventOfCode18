package day4;

import java.util.*;

import day1.FilePicker;

public class Sort {

	List<String> horarios = new ArrayList<String>();
	
	
	public Sort(String file) {
		FilePicker fp = new FilePicker(file);
		horarios = fp.PickAll();
		for(int i = 0; i < horarios.size(); i++) {
			int min = i;
			for(int j = i + 1 ; j < horarios.size() ; j++) {
				String[] cachos = horarios.get(j).split("-|:| |]");
				String[] minc = horarios.get(min).split("-|:| |]");
				if(Integer.parseInt(minc[1]) > Integer.parseInt(cachos[1]) ) {
					min = j;
				}
			}
			String dumy = horarios.get(min);
			horarios.set(min, horarios.get(i));
			horarios.set(i, dumy);
		}
		
		for(int i = 0; i < horarios.size(); i++) {
			int min = i;
			boolean sameDay = true;
			for(int j = i + 1 ; j < horarios.size() && sameDay ; j++) {
				String[] cachos = horarios.get(j).split("-|:| |]");
				String[] minc = horarios.get(min).split("-|:| |]");
				if(Integer.parseInt(minc[2]) > Integer.parseInt(cachos[2]) && Integer.parseInt(minc[1]) == Integer.parseInt(cachos[1])  ) {
					min = j;
				} else if(Integer.parseInt(minc[1]) != Integer.parseInt(cachos[1])) {
					sameDay = false;
				}
			}
			String dumy = horarios.get(min);
			horarios.set(min, horarios.get(i));
			horarios.set(i, dumy);
			
		}
		
		for(int i = 0; i < horarios.size(); i++) {
			int min = i;
			boolean sameDay = true;
			for(int j = i + 1 ; j < horarios.size() && sameDay ; j++) {
				String[] cachos = horarios.get(j).split("-|:| |]");
				String[] minc = horarios.get(min).split("-|:| |]");
				if(Integer.parseInt(minc[3]) > Integer.parseInt(cachos[3]) && Integer.parseInt(minc[1]) == Integer.parseInt(cachos[1]) &&  Integer.parseInt(minc[2]) == Integer.parseInt(cachos[2]) ) {
					min = j;
				}else if(Integer.parseInt(minc[1]) != Integer.parseInt(cachos[1])) {
					sameDay = false;
				}
			}
			String dumy = horarios.get(min);
			horarios.set(min, horarios.get(i));
			horarios.set(i, dumy);
			
		}
		
		for(int i = 0; i < horarios.size(); i++) {
			int min = i;
			boolean sameDay = true;
			for(int j = i + 1 ; j < horarios.size() && sameDay ; j++) {
				String[] cachos = horarios.get(j).split("-|:| |]");
				String[] minc = horarios.get(min).split("-|:| |]");
				if(Integer.parseInt(minc[4]) > Integer.parseInt(cachos[4]) && Integer.parseInt(minc[3]) == Integer.parseInt(cachos[3]) && Integer.parseInt(minc[1]) == Integer.parseInt(cachos[1]) &&  Integer.parseInt(minc[2]) == Integer.parseInt(cachos[2]) ) {
					min = j;
				}else if(Integer.parseInt(minc[1]) != Integer.parseInt(cachos[1])) {
					sameDay = false;
				}
			}
			String dumy = horarios.get(min);
			horarios.set(min, horarios.get(i));
			horarios.set(i, dumy);
			
		}
		
	}
	
	public int theMostSleep() {
		int guard = 0;
		int fallSleep = 0;
		int wakesup = 0;
		List<Integer> guardias = new ArrayList<Integer>();
		Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
		for(int i = 0; i < horarios.size(); i++) {
			String[] cachos = horarios.get(i).split("-|:| |]|#");
			if(cachos[6].equals("Guard")) {
				guard = Integer.parseInt(cachos[8]);
			}else if(cachos[6].equals("falls")) {
				fallSleep = Integer.parseInt(cachos[4]);
			} else if(cachos[6].equals("wakes")) {
				wakesup = Integer.parseInt(cachos[4]);
				if(ht.containsKey(guard)) {
					int count = wakesup - fallSleep + ht.get(guard);
					ht.remove(guard);
					ht.put(guard, count);
				}else {
					
					ht.put(guard,   wakesup - fallSleep);
					guardias.add(guard);
				}
			}
				
			

		}
		int max = guardias.get(0);
		for(Integer i: guardias) {
			
			if(ht.get(max) < ht.get(i))
				max = i;
			
		}
		
		
		
		return max;
	}
	
	
	public int minuteMostleep(int guardia) {
		int minute = 0;
		int fallSleep = 0;
		int wakesup = 0;
		int guard = 0; 
		List<Integer> minits = new ArrayList<Integer>();
		for(int i = 0; i < 60 ; i++)
			minits.add(0);
		
		for(int i = 0; i < horarios.size(); i++) {
			String[] cachos = horarios.get(i).split("-|:| |]|#");
			if(cachos[6].equals("Guard")) {
				guard = Integer.parseInt(cachos[8]);
			}else if(cachos[6].equals("falls")) {
				fallSleep = Integer.parseInt(cachos[4]);
			} else if(cachos[6].equals("wakes")) {
				wakesup = Integer.parseInt(cachos[4]);
				if(guard == guardia) {
					for(int j = fallSleep ; j <  wakesup ; j++)
						minits.set(j, 1+minits.get(j));
				}
			}
				
			

		}
		
		for(int i = 0; i < minits.size() ; i++) {
			if(minits.get(minute) < minits.get(i)) {
				minute = i;
			}
		}
		
		
		
		return minute;
	}
	
	
	public void part2() {
		List<Integer> guardias = new ArrayList<Integer>();
		int guard = 0;
		int fallSleep = 0;
		int wakesup = 0;
		List<Integer> minits = new ArrayList<Integer>();
		
		for(int i = 0; i < horarios.size(); i++) {
			String[] cachos = horarios.get(i).split("-|:| |]|#");
			if(cachos[6].equals("Guard"))
				if(!guardias.contains(Integer.parseInt(cachos[8])))
				guardias.add(Integer.parseInt(cachos[8]));
		}
		
		for(int j = 0; j < guardias.size() ; j++){
			
			minits.clear();
			for(int i = 0; i < 60 ; i++)
				minits.add(0);
			
			for(int i = 0; i < horarios.size(); i++) {
				String[] cachos = horarios.get(i).split("-|:| |]|#");
				if(cachos[6].equals("Guard")) {
					guard = Integer.parseInt(cachos[8]);
				}else if(cachos[6].equals("falls")) {
					fallSleep = Integer.parseInt(cachos[4]);
				} else if(cachos[6].equals("wakes")) {
					wakesup = Integer.parseInt(cachos[4]);
					if(guard == guardias.get(j)) {
						for(int k = fallSleep ; k <  wakesup ; k++)
							minits.set(k, 1+minits.get(k));
					}
				}
			}
			int max = 0;
			for(int i = 1; i < minits.size() ; i++) {
				if(minits.get(max) < minits.get(i)) {
					max = i;
				}
			}
			System.out.println("Guard: " +guardias.get(j) + " Minit: " + max + " Times: " + minits.get(max) + " Result: " + max*guardias.get(j));
			
		}
		
	}
	
	
	
	public String toString() {
		String salida = "";
		
		for(int i = 0 ; i < horarios.size(); i++) {
			salida += horarios.get(i) + " \n";
		}
		
		return salida;
		
	}
	
	public static void main(String[] args) {
		
		Sort ordenar = new Sort(args[0]);
		System.out.println(ordenar.minuteMostleep(ordenar.theMostSleep()) * ordenar.theMostSleep());
		ordenar.part2();
		
		
	
	}
	
	
}
