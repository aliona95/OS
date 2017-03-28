import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class Loader {
	public void checkCommands(String filename) throws Exception{
		BufferedReader inputStream = new BufferedReader(new FileReader(filename));
		String command;
		
		Machine.expect("$WOW",inputStream);
    	Machine.expect(".NAM ", inputStream);
    	Machine.expect(".DAT ", inputStream);	
    	int line = 4;
    	while(!(command = inputStream.readLine()).startsWith("$WRT")){
    		if(command.length() > Machine.WORD_SIZE){
    			throw new Exception("Zodzio dydis turi buti ne ilgesnis nei " + Machine.WORD_SIZE);
    		}
    		line++;
    	}
    	while(!(command = inputStream.readLine()).startsWith("$END")){
    		if(command.length() > Machine.WORD_SIZE){
    			throw new Exception("Komandos dydis turi buti ne ilgesnis nei " + Machine.WORD_SIZE);
    		}
    		line++;
    		checkCommand(command, line);
        }
    	if(inputStream.readLine() != null){
    		throw new Exception("Pabaigos klaida");
    	}
    	inputStream.close();
	}	
	public void checkCommand(String command, int line) throws Exception{
	   String commandStart = command;   
	   switch(commandStart.substring(0, 2)){
	   // ATMINTIES KOMANDOS
		case "LA":
			break;
		case "LB":
   			break;
		case "SA":
			break;
		case "SB":
			break;
		case "CO":
			break;
		// ARITMETINES KOMANDOS
		case "AA":
			break;
		case "AB":
			break;
		case "BA":
			break;
		case "BB":
			break;
		// LOGINES KOMANDOS
		case "CA":
			break;
		case "CB":
			break;
		// VALDYMO PERDAVIMO KOMANDOS
		case "JP":
			break;
		case "JE":	
			break;
		case "JL":
			break;
		case "JG":
			break;
	    //ISVEDIMO IR IVEDIMO KOMANDOS
		case "OP":
			break;
		case "IP":
			break;
		// PABAIGA
		case "HA":
			if(!commandStart.substring(2, 4).equals("LT")){
				JOptionPane.showMessageDialog(null, "Unknown command " + command + "at line " + line, "Error", JOptionPane.ERROR_MESSAGE);
				throw new Exception("Unknown command " + command + "at line " + line);
			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "Unknown command " + command + "at line " + line, "Error", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Unknown command " + command + "at line " + line);
	   }
	}
}
