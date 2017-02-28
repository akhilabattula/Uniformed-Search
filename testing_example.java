package original_ai;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class testing_example {
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("jigijigi", "UTF-8");
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
  
    }  
		
		
	}


