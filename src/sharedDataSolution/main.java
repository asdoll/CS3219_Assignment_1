package sharedDataSolution;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class main {

    public static char[] chars_array=null;
    public static int[] line_index_array=null;
    public static int[][] circular_shifts=null;
    public static int[][] alphabetized=null;
    public static ArrayList<String> ignore_words = new ArrayList<String>();



    public static void input(FileReader input_line_file,FileReader input_ignore_words)throws IOException{
    	line_input(input_line_file);
    	ignore_input(input_ignore_words);
    }


    
    public static void circular_shift(){
        ArrayList circular_shifts_list=new ArrayList(0);
        String temp_getline=null;
        int[] inlist=null;
        int word_index=0;
        for(int i=0;i<line_index_array.length;i++){
           word_index=0;
           if(i!=line_index_array.length-1){
        	   temp_getline=new String(chars_array,line_index_array[i],line_index_array[i+1]-line_index_array[i]);
           }
           else{
        	   temp_getline=new String(chars_array,line_index_array[i],chars_array.length-line_index_array[i]);
           }
           while(true){
               inlist=new int[2];
               inlist[0]=i;
               inlist[1]=word_index+line_index_array[i];
               circular_shifts_list.add(inlist);
               word_index=temp_getline.indexOf(' ', word_index);
               if(word_index==-1){
            	   break;
               }
               else{
            	   word_index=word_index+1;
               }
              }
        }
        int[] temp=null;
        int[] temp2=null;
        String word="";
        for(int x=0;x<circular_shifts_list.size();x++){     	
        	temp=(int[])circular_shifts_list.get(x);
            int char_first=temp[1];
            int char_last=0;
            if(x<(circular_shifts_list.size()-1)){
            	temp2=(int[])circular_shifts_list.get(x+1);
            	char_last=temp2[1];
            	word=new String(chars_array,char_first,char_last-char_first).trim();
            }
            else{
            	word=new String(chars_array,char_first,chars_array.length-char_first);
            }
            
            for(int z=0;z<ignore_words.size();z++){
            	if(ignore_words.get(z).equals((word.toLowerCase()))){
            		circular_shifts_list.remove(x);
            		x--;
            		break;
            	}
            }
        }
        
        circular_shifts=new int[circular_shifts_list.size()][2];
        for(int j=0;j<circular_shifts.length;j++){
        	temp=(int[])circular_shifts_list.get(j);
            circular_shifts[j][0]=temp[0];
            circular_shifts[j][1]=temp[1];
        }
    }
    
    
    
    public static void alphabetize(){
        alphabetized=new int[circular_shifts.length][2];
        for(int n=0;n<circular_shifts.length;n++){
            alphabetized[n][0]=circular_shifts[n][0];
            alphabetized[n][1]=circular_shifts[n][1];
        }
        
        int[] temp=new int[2];
        for(int i=1;i<alphabetized.length;i++){
        	
            for(int j=i;(j>0)&&(main.char_compare(j,j-1)<0);j--){
            		temp[0]=alphabetized[j][0];
            		temp[1]=alphabetized[j][1];               
            		alphabetized[j][0]=alphabetized[j-1][0];
            		alphabetized[j][1]=alphabetized[j-1][1];
            		alphabetized[j-1][0]=temp[0];
                	alphabetized[j-1][1]=temp[1];
            }
        }
    }
    
    
    
    public static void output(FileWriter output_file)throws IOException{
        BufferedWriter buffer=new BufferedWriter(output_file);
        String line=null;
        for(int i=0;i<alphabetized.length;i++){
        		line=getStrFromAlphabetizedMatrix(i);
        		line = Character.toUpperCase(line.charAt(0))+line.substring(1);
        		System.out.println(line);
        		buffer.write(line+'\n');
        }
        buffer.close();
    }
    
    
    
    public static void controller(String inputFilename, String ignoreFilename, String outputFilename) throws IOException{
        FileReader input_line_file=new FileReader(inputFilename);
        FileReader input_ignore_words=new FileReader(ignoreFilename);
        input(input_line_file,input_ignore_words);
        circular_shift();
        alphabetize();
        FileWriter output_file=new FileWriter(outputFilename);
        output(output_file);
    }
    
    
    
/*-----------------------------------------------helper--------------------------------------------------*/    
    
    
    
    public static void ignore_input(FileReader input_ignore_file)throws IOException{
        BufferedReader buffer=new BufferedReader(input_ignore_file);
        while(buffer.ready()){
        	String line = buffer.readLine();
        	ignore_words.add(line);
        }
        buffer.close();
    }
    
    
    
    public static void line_input(FileReader input_line_file)throws IOException{
        BufferedReader buffer=new BufferedReader(input_line_file);
        String total_str="";
        int line_first_index=0;
        ArrayList<Integer> line_index_list=new ArrayList<Integer>(0);
        while(buffer.ready()){
            line_index_list.add(line_first_index);
            total_str=total_str+buffer.readLine();
            total_str=total_str.trim();
            line_first_index=total_str.length();
        }
        buffer.close();
        chars_array=total_str.toCharArray();
        line_index_array=new int[line_index_list.size()];
        
        for(int i=0;i<line_index_list.size();i++){
          line_index_array[i]=line_index_list.get(i);
        }
    }

    
    private static String getStrFromAlphabetizedMatrix(int index){
       int[][] matrix=null;
       matrix=alphabetized;
       int char_index=matrix[index][0];
       int char_first=matrix[index][1];
       int line_first=line_index_array[char_index];
       int line_last=0;
       if(char_index==line_index_array.length-1){
    	   line_last=chars_array.length-1;
       }
       else{
    	   line_last=line_index_array[char_index+1]-1;
       }
       String temp1="";
       String temp2="";
       temp2=new String(chars_array,char_first,line_last-char_first+1);
       if(char_first==line_first){
    	   return temp2;
       }
       else{
    	   temp1=new String(chars_array,line_first,char_first-line_first-1);
    	   return temp2+" "+temp1;
       }
    }
    
    
    private static String getStrFromShiftesMatrix(int index){
        int[][] matrix=null;
        matrix=circular_shifts;
        int char_index=matrix[index][0];
        int char_first=matrix[index][1];
        int line_first=line_index_array[char_index];
        int line_last=0;
        if(char_index==line_index_array.length-1){
        	line_last=chars_array.length-1;
        }
        else{
        	line_last=line_index_array[char_index+1]-1;
        }
        String temp1="";
        String temp2="";
        temp2=new String(chars_array,char_first,line_last-char_first+1);
        if(char_first==line_first){
        	return temp2;
        }
        else{
        	temp1=new String(chars_array,line_first,char_first-line_first-1);
        	return temp2+" "+temp1;
    	}
    }
    
    
    private static int char_compare(int index1,int index2){
        return main.getStrFromAlphabetizedMatrix(index1).toUpperCase().compareTo(main.getStrFromAlphabetizedMatrix(index2).toUpperCase());
    }
}
