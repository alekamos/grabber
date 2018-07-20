package it.spinore.grabber.action;

import java.util.ArrayList;

import it.spinore.grabber.bean.Instruction;

public class Validation {


	public static ArrayList<Instruction> getIstruzioni(String[] text) throws Exception{
		ArrayList<Instruction> istruzioni = new ArrayList<>();

		//Check lenght
		if(text!=null) {
			for (int i = 0; i < text.length; i++) {

				Instruction in = new Instruction();

				if(isNumeric(text[i]))
					in.setElementNumber(Integer.parseInt(text[i]));
				else if (text[i].contains("htmlTag:")) 
					in.setHtmlTag(text[i].replace("htmlTag:",""));
				else 
					in.setTriggerString(text[i]);

				istruzioni.add(in);
			}
		}


		return istruzioni;

	}







	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			int d = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}

}
