package abstructDataTypeSolution;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Output {
	public static void println(Alphabetizer alpha,FileWriter fw)throws IOException{
		BufferedWriter bw=new BufferedWriter(fw);
		for(int i=0;i<alpha.length();i++){
			bw.write(alpha.getLine(i)+'\n');
		}
		bw.close();
	}
}