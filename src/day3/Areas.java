package day3;

import java.util.ArrayList;
import java.util.List;

import day1.FilePicker;





public class Areas {

	List<List<Integer> > mapa = new ArrayList<List<Integer> >();
	List<Square> sq = new ArrayList<Square>();
	public Areas(String file) {
		FilePicker fp = new FilePicker(file);
		List<String> dummy = new ArrayList<String>();
		dummy = fp.PickAll();
		for(int i = 0; i < dummy.size() ; i++) {
			String[] cachos =  dummy.get(i).split(" ");
			String[] coordenadas = cachos[2].split(",|:");
			String[] tamano = cachos[3].split("x");
			String[] key = cachos[0].split("#");
			sq.add(new Square(Integer.parseInt(coordenadas[0]),Integer.parseInt(coordenadas[1]),Integer.parseInt(tamano[0]),Integer.parseInt(tamano[1]), key[1]));
		}
	}
	
	public int getColisions() {
		for(int i = 0 ; i < 1100 ; i++) {
			mapa.add(new ArrayList<Integer>());
			for(int j = 0; j < 1100 ; j++) {
				mapa.get(i).add(0);
			}
		}
		
		for(int i = 0; i < sq.size(); i++) {
			for(int k = sq.get(i).x ; k < sq.get(i).x + sq.get(i).width ; k++) {
				for(int l = sq.get(i).y ; l < sq.get(i).y + sq.get(i).height ; l++) {
					mapa.get(k).set(l,1+mapa.get(k).get(l));
				}
			}
		}
		int colisiones = 0;
		for(int k = 0 ; k < 1100 ; k++) {
			for(int l = 0 ; l < 1100 ; l++) {
				if(mapa.get(k).get(l) >= 2)
					colisiones++;
			}
		}
		
		return colisiones;
	}
	
	public int getColExp() {
		int colisiones = 0;
		
		for(int i = 0; i < sq.size(); i++) {
			for(int j = i + 1; j < sq.size() ; j++) {
				sq.get(i).colision(sq.get(j));
			}
			colisiones += sq.get(i).col;
		}
		
		return colisiones;
		
		
	}
	
	public String getNoColision() {
		
		String sol = "";
		
		for(int i = 0; i < sq.size(); i++) {
			boolean noColision = true;
			for(int k = sq.get(i).x ; k < sq.get(i).x + sq.get(i).width ; k++) {
				for(int l = sq.get(i).y ; l < sq.get(i).y + sq.get(i).height ; l++) {
					if(mapa.get(k).get(l) >= 2)
						noColision = false;
				}
			}
			if(noColision) {
				sol += " " + sq.get(i).Key;
			}
		}
		
		
		
		return sol;
	}
	
	public static void main(String[] args) {
		
		Areas ar = new Areas(args[0]);
		System.out.println(ar.getColisions());
//		System.out.println(ar.getColExp() + " Experimental");
		System.out.println(ar.getNoColision());
	}
}



