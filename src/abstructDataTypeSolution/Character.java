package abstructDataTypeSolution;

import java.util.ArrayList;

public class Character  { 
    private ArrayList<String> lines=null;
    private ArrayList<String> ignore_words=null;
    
    public Character(){
        lines=new ArrayList<String>(0);
        ignore_words=new ArrayList<String>(0);
    }
    
    public String getLine(int index){
        return lines.get(index);
    }
    
    public void add_line_Line(String line){
        lines.add(line);
        //System.out.println(lines);
    }
    
    public void add_ignore_Line(String line){
        ignore_words.add(line);
        //System.out.println(ignore_words);
    }
    
    public int length(){
    	//System.out.println(lines.size());
        return lines.size();
    }
    
    public int words_number_of_line(int index){
        return lines.get(index).split(" ").length;
    }
    
    public boolean check_ignore(String temp_line){
    	for(int i=0;i<ignore_words.size();i++){
    		if(ignore_words.get(i).equals(temp_line.split(" ")[0].toLowerCase())){
    			return true;
    		}
    	}
    	return false;
    }
   
    public String shiftwords(int line_index){
        String line=this.getLine(line_index);
        int temp_index=line.indexOf(' ');
        String temp1="";
        String temp2="";
        if(temp_index!=-1){
            temp1=line.substring(0,temp_index);
            temp2=line.substring(temp_index+1);
            lines.set(line_index,temp2+" "+temp1);
            return temp2+" "+temp1;
        }
        else return null;
    }
}
