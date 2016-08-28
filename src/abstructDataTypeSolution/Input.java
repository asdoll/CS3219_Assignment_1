package abstructDataTypeSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input {
    public static Character readin(FileReader input_line_file,FileReader input_ignore_words)throws IOException{
        BufferedReader line_buffer=new BufferedReader(input_line_file);
        BufferedReader ignore_buffer=new BufferedReader(input_ignore_words);
        Character characters=new Character();
        while(line_buffer.ready()){
            characters.add_line_Line(line_buffer.readLine());
        }
        line_buffer.close();
        while(ignore_buffer.ready()){
            characters.add_ignore_Line(ignore_buffer.readLine());
        }
        ignore_buffer.close();
        return characters;
    }
}
