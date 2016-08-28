package abstructDataTypeSolution;

public class Alphabetizer {
	
	private CircularShifter shift;
	
	private int[] sorted;
	
	public Alphabetizer(CircularShifter shift){
		this.shift=shift;
		sorted=new int[shift.length()];
		for(int i=0;i<sorted.length;i++){
			sorted[i]=i;
		}
	}
	
	public void sort(){
		int temp;
		for(int i=1;i<sorted.length;i++){
			for(int j=i;(j>0)&&(shift.compare(sorted[j], sorted[j-1]))<0;j--){
				temp=sorted[j];
				sorted[j]=sorted[j-1];
				sorted[j-1]=temp;
			}
		}
	}
	
	public String getLine(int index){
		return shift.getLine(sorted[index]);
	}
	
	public int length(){
		return sorted.length;
	}
}
