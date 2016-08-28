package abstructDataTypeSolution;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Output {
	public static void println(Alphabetizer alpha,FileWriter fw)throws IOException{
		BufferedWriter bw=new BufferedWriter(fw);
		String line;
		for(int i=0;i<alpha.length();i++){
			line = alpha.getLine(i);
			line = java.lang.Character.toUpperCase(line.charAt(0))+line.substring(1);
			bw.write(line +'\n');
		}
		bw.close();
	}
}