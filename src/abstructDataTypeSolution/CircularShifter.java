package abstructDataTypeSolution;
import java.util.ArrayList;

public class CircularShifter {
	
	private Character shifts=null;
	
	public CircularShifter(Character shifts){
		this.shifts=shifts;
	}
	
	public void newShifted(){
		int number_of_lines=shifts.length();
		//System.out.println(number_of_lines);
		ArrayList<ArrayList> final_list=new ArrayList<ArrayList>(0);
		ArrayList<String> temp_list=new ArrayList<String>(0);
		for(int i=0;i<number_of_lines;i++){
			for(int j=0;j<shifts.words_number_of_line(i);j++){
				if(j==0){
					String temp_line = shifts.getLine(i);
					if(shifts.check_ignore(temp_line)!=true){
						temp_list.add(temp_line);
					}
				}
				else{
					String temp_line = shifts.shiftwords(i);
					if(shifts.check_ignore(temp_line)!=true){
						temp_list.add(temp_line);
					}
				}
			}
			final_list.add(temp_list);
			temp_list=new ArrayList<String>(0);
		}
		shifts=new Character();
		for(int i=0;i<number_of_lines;i++){
			for(int j=0;j<final_list.get(i).size();j++){
				shifts.add_line_Line((String)final_list.get(i).get(j));
			}
		}
//		for(int i=0;i<shifts.length();i++){
//			System.out.println(shifts.getLine(i));
//		}
	}

	public String getLine(int index){
		return shifts.getLine(index);
	}
 
	public int length(){
        return shifts.length();
    }

	public int compare(int index1,int index2){
		return shifts.getLine(index1).compareTo(shifts.getLine(index2));
	}
}
