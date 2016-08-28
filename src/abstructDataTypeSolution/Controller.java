package abstructDataTypeSolution;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Controller {

    public static void controller(String inputFilename, String ignoreFilename, String outputFilename) throws IOException{
       FileReader input_line_file=new FileReader(inputFilename);
       FileReader input_ignore_words=new FileReader(ignoreFilename);
       Character characters=Input.readin(input_line_file,input_ignore_words);
       CircularShifter shift=new CircularShifter(characters);
       shift.newShifted();
       Alphabetizer alphabetized=new Alphabetizer(shift);
       alphabetized.sort();
       FileWriter output_file=new FileWriter(outputFilename);
       Output.println(alphabetized, output_file);
    }
}
