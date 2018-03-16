package gr.uom.java.jdeodorant.implementedMetrics;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WritingtheValues {
	
	
	public static void Writingthevalues(String value) {
	try {
		
		String path="//SOEN_6611/input.txt";
	
	FileWriter fr=new FileWriter(path);
	BufferedWriter bw=new BufferedWriter(fr);
	bw.write(value);
	}catch(Exception e) {
		e.printStackTrace();
	}

}
	public static void WritingtheSyatemvalues(String value) {
		try {
			
			String path="//SOEN_6611/input2.txt";
		
		FileWriter fr=new FileWriter(path);
		BufferedWriter bw=new BufferedWriter(fr);
		bw.write(value);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
}