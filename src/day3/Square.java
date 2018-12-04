package day3;

import java.util.Hashtable;

public class Square{
	
	int x;
	int y;
	int width;
	int height;
	String Key;
	Hashtable<String, Boolean> reclaims = new Hashtable<String, Boolean>();
	int col = 0;
	
	public Square(int x, int y, int width, int height , String key) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.Key = key;
	}
	
	public int colision(Square b) {		//No funciona, muy poco optimo
		
		for(int i = x; i < x+width; i++) {
			for(int j = y; j < y+height ; j++) {
				for(int k = b.x ; k < b.x+b.width ; k++) {
					for(int l = b.y ; l < b.y+b.height ; l++) {
						if(i == k && j == l && !reclaims.containsKey(i+""+j+"") && !b.reclaims.containsKey(k+""+l+"") ) {
							col++;
							  reclaims.put(i+""+j+"", true);
							b.reclaims.put(k+""+l+"", true);
						}
							
					}
				}
			}
		}
		
		return col;
	}
	
	
}


