package day1;

import java.util.ArrayList;
import java.util.List;

public class Frecuency {

	List<Integer> num = new ArrayList<Integer>();
	
	public Frecuency(String file) {
		FilePicker fp = new FilePicker(file);
		List<String> dummy = fp.PickAll();
		for(int i = 0; i <dummy.size(); i++) 
			num.add(Integer.valueOf(dummy.get(i)));
		
		
		
	}
		
		public int result() {
			
			int dummy = 0;
			
			for(int i = 0; i < num.size(); i++){
				dummy += num.get(i);
			}
			return dummy;
			
		}
		
		public int firstFrecuencyRepeat() {
			
			List<Integer> dummy = new ArrayList<Integer>();
			boolean find = false;
			int i = 1;
			dummy.add(num.get(0));
			while(!find) {
				int sum = dummy.get(i-1) + num.get(i%num.size());
				if(!dummy.contains(sum)){
					dummy.add(sum);
				}else
					return sum;
					
				i++;
				
			}
			
			return -1;
			
		}
	
	
	
	
	public static void main(String[] args) {
		Frecuency f = new Frecuency(args[0]);
		System.out.println(f.result());
		System.out.println(f.firstFrecuencyRepeat());

	}

}
