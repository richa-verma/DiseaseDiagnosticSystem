package application;

import java.io.*;

class Test1{
public static void main(String a[]){
	try{
		
		String pythonScriptPath = "test1.py";
		String[] cmd = new String[4];
		cmd[0] = "python"; // check version of installed python: python -V
		cmd[1] = pythonScriptPath;
		 
		// create runtime to execute external command
		//System.out.println(cmd[1]);
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("/Library/Frameworks/Python.framework/Versions/2.7/bin/python tumhariMeetikaJ.py");
		 pr.waitFor();
		// retrieve output from python script
		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		while((line = bfr.readLine()) != null) {
		// display each output line form python script
		System.out.println(line);
		pr = rt.exec("/opt/local/bin/dot -Tpng belief.dot -o outfile.png");
		}
	}catch(Exception e){e.printStackTrace();}
}
}
 