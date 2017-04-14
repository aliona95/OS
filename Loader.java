import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class Loader {
	private final int programNameLength = 10; //nurodomas programos vardo ilgis 10 baitu
	private String fileSystem;
	
	Loader(String fileSystem){
		this.fileSystem = fileSystem;
	}
	
	public void checkCommands(String progamName) throws Exception{
		BufferedReader inputStream = new BufferedReader(new FileReader(fileSystem));
		String command = "";
		
	    while(command != null){ //ieskome programos failu sistemoje
	    	command = inputStream.readLine();
	    	if(command == null){
	    		throw new Exception("Programa " + progamName + " failu sistemoje neegzistuoja ");
	    	}
	    	if(command.equals('#' + progamName)){
	    		if(command.length() > programNameLength){
	    			throw new Exception("Programos vardas turi buti ne ilgesnis nei 10b");
	    		}
	    		break;
			}
	    }
	    Machine.expect("$WOW",inputStream);
	    Machine.expect(".DAT ", inputStream);	
	    int line = 3; //skaiciuosime programos eilutes
	    command = "";
	    while(command != null){ //skaitome duomenu segmenta
	    	command = inputStream.readLine();
	    	line++;
	    	if(command == null){
	    		throw new Exception("Nerasta duomenu segmento pabaiga");
	    	}
	    	if(command.equals("$WRT")){ //tikriname duomenu segmento pabaiga
	    		break;
			}
	    	checkDataCommandLength(command, line);
	    }
	    
	    command = "";
	    while(command != null){ //tikriname kodo segmenta
	    	command = inputStream.readLine();
	    	line++;
	    	if(command == null){
	    		throw new Exception("Nerasta kodo segmento pabaiga");
	    	}
	    	if(command.equals("$END")){ //suradome kodo segmento pabaiga
	    		break;
			}
	    	checkCodeCommandLength(command, line);
	    	checkCommand(command, line);
	    }
    	inputStream.close();
	}	
	
    public void checkDataCommandLength(String command, int line) throws Exception{
    	if(command.length() > Machine.WORD_SIZE){
    		throw new Exception("Duomenu eilute neuzima daugiau nei " + Machine.WORD_SIZE + ". Klaida eiluteje " + line);
    	}
    }
    public void checkCodeCommandLength(String command, int line) throws Exception{
    	if(command.length() != Machine.WORD_SIZE){
    		throw new Exception("Komandos dydis turi buti " + Machine.WORD_SIZE + ". Klaida eiluteje " + line);
    	}
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
				throw new Exception("Neatpazinta komanda " + command + "eiluteje " + line);
			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "Unknown command " + command + "at line " + line, "Error", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Neatpazinta komanda " + command + "eiluteje " + line);
	   }
	}
}
