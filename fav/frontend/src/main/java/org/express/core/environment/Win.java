package org.express.core.environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Win {
	 public static void main(String args[]){
         callCmd(args[0]);
      }
	 
      public static void  callCmd(String locationCmd)
      {
          try 
          {
        	  Runtime runtime = Runtime.getRuntime();
        	  Process child = runtime.exec(locationCmd , null , new File(locationCmd.substring(0 , locationCmd.lastIndexOf("\\") +1)));
        	  runtime.gc();
        	  BufferedReader input = new BufferedReader(new InputStreamReader(child.getInputStream()));
        	  String line = null, result = "";
        	  while ((line = input.readLine()) != null)
        	     result += line + "\r\n";
        	  input.close();
        	  System.out.println(result);
          }
          catch (IOException e) 
          {
	           e.printStackTrace();
          }
      }
}
