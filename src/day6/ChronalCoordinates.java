package day6;

import java.util.*;

import day1.FilePicker;

public class ChronalCoordinates {

	public final int TAM = 400;
	public final int SUMDIST = 10000;
	
	List<String> points = new ArrayList<String>();
	List<List<String> > map = new ArrayList<List<String> >();
	
	public ChronalCoordinates(String file) {
		FilePicker fp = new FilePicker(file);
		List<String> dummy = new ArrayList<String>();
		dummy = fp.PickAll();
		
		for(int i = 0 ; i <= TAM ; i++) {
			map.add(new ArrayList<String>());
			for(int j = 0; j <= TAM ; j++) {
				map.get(i).add(".");
			}
		}
		
		for(int i = 0; i < dummy.size() ; i++) {
			String[] cachos = dummy.get(i).split(" |,");
			map.get(Integer.valueOf(cachos[0])).set(Integer.valueOf(cachos[2]), ""+(i+1));
			points.add(cachos[0] +" " + cachos[2] + " " + (i+1));
 		}
		
	}
	
	public void rellenar() {
		
		for(int i = 0 ; i < map.size() ; i++) {
			for(int j = 0; j < map.get(i).size() ; j++) {
				int min = 0;
				boolean igual = false;
				for(int k = 1; k < points.size() ; k++) {
					String[] minc = points.get(min).split(" ");
					String[] cacho = points.get(k).split(" ");
					int calcMin = Math.abs(Integer.valueOf(minc[0]) - i) + Math.abs(Integer.valueOf(minc[1]) - j);
					int calcCac = Math.abs(Integer.valueOf(cacho[0]) - i) + Math.abs(Integer.valueOf(cacho[1]) - j);
					if(calcMin > calcCac) {
						min = k;
					}
				}
				for(int k = 0; k < points.size() && !igual ; k++) {	//Para ver si hay alguno igual
					String[] minc = points.get(min).split(" ");
					String[] cacho = points.get(k).split(" ");
					int calcMin = Math.abs(Integer.valueOf(minc[0]) - i) + Math.abs(Integer.valueOf(minc[1]) - j);
					int calcCac = Math.abs(Integer.valueOf(cacho[0]) - i) + Math.abs(Integer.valueOf(cacho[1]) - j);
					if(calcMin == calcCac && min != k) {
//						System.out.println(calcMin + "   " + Integer.valueOf(cacho[0]) + " " +Integer.valueOf(cacho[1])+ "   " + calcCac);
						igual = true;
					}
				}
				if(igual)
					map.get(i).set(j,".");
				else
					map.get(i).set(j,""+min);
			}
		}
	}
	
	public void contar () {
		Hashtable<String, Integer> infinitos = new Hashtable<String, Integer>();
		for(int i = 0; i < map.size(); i++) {
			if(!infinitos.containsKey(map.get(0).get(i))) {
				infinitos.put(map.get(0).get(i),i);
			}
		}
		for(int i = 0; i < map.size(); i++) {
			if(!infinitos.containsKey(map.get(i).get(0))) {
				infinitos.put(map.get(i).get(0),i);
			}
		}
		for(int i = 0; i < map.size(); i++) {
			if(!infinitos.containsKey(map.get(i).get(map.size()-1))) {
				infinitos.put(map.get(i).get(map.size()-1),i);
			}
		}
		for(int i = 0; i < map.size(); i++) {
			if(!infinitos.containsKey(map.get(map.size()-1).get(i))) {
				infinitos.put(map.get(map.size()-1).get(i),i);
			}
		}
		
		
			
		
		
		Hashtable<String, Integer> finitos = new Hashtable<String, Integer>();
		for(int i = 0 ; i < map.size() ; i++) {
			for(int j = 0; j < map.get(i).size() ; j++) {
				if(!infinitos.containsKey(map.get(i).get(j))) {
					if(finitos.containsKey(map.get(i).get(j))) {
						int count = finitos.get(map.get(i).get(j));
						finitos.remove(map.get(i).get(j));
						finitos.put(map.get(i).get(j), count+1);
					}
					else
						finitos.put(map.get(i).get(j), 1);
				}
				
				
			}
		}
		System.out.println(finitos);
	}
	
	
	public int rellenarDist() {
		for(int i = 0 ; i < map.size() ; i++) {
			for(int j = 0; j < map.get(i).size() ; j++) {
				int sumaDist = 0;
				for(int k = 0 ; k < points.size() ; k++) {
					String[] cacho = points.get(k).split(" ");
					sumaDist += Math.abs(Integer.valueOf(cacho[0]) - i) + Math.abs(Integer.valueOf(cacho[1]) - j);
				}
//				System.out.println(sumaDist);
				if(sumaDist < SUMDIST) {
					map.get(i).set(j,"#");
				} else
					map.get(i).set(j,".");
				
			}
		}
		int suma = 0;
		
		for(int i = 0 ; i < map.size() ; i++) {
			for(int j = 0; j < map.get(i).size() ; j++) {
				if(map.get(i).get(j).equals("#"))
					suma += 1;
			}
		}
		 return suma;
		
	}
	
	public String toString() {
		String salida = "";
		for(int i = 0 ; i < map.size() ; i++) {
			for(int j = 0; j < map.get(i).size() ; j++) {
				salida +=" "+map.get(i).get(j);
			}
			salida += "\n";
		}
		return salida;
	}
	
	
	
	public static void main(String[] args) {
		
		ChronalCoordinates cc = new ChronalCoordinates(args[0]);
		System.out.println(cc.rellenarDist());
//		System.out.println(cc.toString());
		
	}

}
