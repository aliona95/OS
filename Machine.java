import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Aliona ir Eimantas
 *  
 *  
 */
public class Machine{
	public final static int WORD_SIZE = 4;
	private final static int BLOCK_SIZE = 16;
	private final static int BLOCKS = 70;
	private static int dataBloksNum = 0;
	
	private final static byte memory[] = new byte[BLOCKS * BLOCK_SIZE * WORD_SIZE];
	private VM vm[];
	public static String filename = "failas.txt";
	
	private final byte PLR[] = new byte[WORD_SIZE];
	private final byte AX[]  = new byte[WORD_SIZE];
    private final byte BX[]  = new byte[WORD_SIZE];
    private final byte IC[]  = {48, 48};  // kelinta komanda vykdoma
    private byte C;             
    private byte SF;
    private byte MODE;
    private byte CH1;
    private byte CH2;
    private byte CH3;
    private byte IOI;
    private byte PI;
    private byte SI;
    private byte TI;
  
    // !!!!!!!!!! CLOSE FILE
    // !!!!!!!!!! nuimti patikrinimus
    private void loader(String fileName) throws Exception{
    	String command;
    	BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
    	
    	expect("$WOW",inputStream);
    	expect(".NAM ", inputStream);
    	expect(".DAT ", inputStream);
    	
    	int dataCounter = 0;
    	
    	int dataIndex = 0;
    	while(!(command = inputStream.readLine()).startsWith("$WRT")){
    		for(int i = 0; i < WORD_SIZE; i++){
    			memory[(BLOCK_SIZE * BLOCK_SIZE * WORD_SIZE) - (dataBloksNum * BLOCK_SIZE * WORD_SIZE) + dataIndex] = (byte) command.charAt(i);
    			dataIndex++;  // !!!! pachekinti  
    		}
    	}
    	
    	// WRT
    	int index = 0;
    	while(!(command = inputStream.readLine()).startsWith("$END")){
    		System.out.println(command);
    		for(int i = 0; i < command.length(); i++){      // patikrinimas su 4
    			memory[index] = (byte) command.charAt(i);
    			if((index - 1) < BLOCKS * BLOCK_SIZE * WORD_SIZE){
        			index++;
        		}else{
        			index = 0;
        		}
    		}
    	}
    		
    	// END
    	inputStream.close();
    }
    public void commandInterpreter(int memoryIndex){  // !!!!!! padaryti per realu adr su IC
    	String command = "";
    	for(int i = 0; i < WORD_SIZE; i++){
    		command += (char)memory[memoryIndex + i];
    	}
    	
    	String commandStart = command;
    	switch(commandStart.substring(0, 2)){  // paimame pirmus 2 komandos simbolius
    		// ATMINTIES KOMANDOS
    		case "LA":
    			if(command.equals("LAFB")){
    				commandLAFB();
    				break;
    			}else{
	    			commandLA(Character.getNumericValue(command.charAt(2)), 
	    					  Character.getNumericValue(command.charAt(3)));
	    			break;
    			}
    		case "LB":
    			if(command.equals("LBFA")){
    				commandLBFA();
    				break;
    			}else{
	    			commandLB(Character.getNumericValue(command.charAt(2)), 
	  					  	  Character.getNumericValue(command.charAt(3)));
	    			break;
    			}
    		case "SA":
    			commandSA(Character.getNumericValue(command.charAt(2)), 
					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		case "SB":
    			commandSB(Character.getNumericValue(command.charAt(2)), 
  					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		case "CO":
    			if(command.equals("COPA")){
    				commandCOPA();
    				break;
    			}else{ 
    				if(command.equals("COPB")){
    					commandCOPB();
    					break;
    				}
    			}
    		//// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    		case "AW":
    			commandAW();        // reikia BX priskirti visa zodi, dar 2 papildomi simboliai turi buti
    			break;
    		// ARITMETINES KOMANDOS
    		case "AA":
    						//  !!!!! nepadaryta su perpildymu
    			commandAA(Character.getNumericValue(command.charAt(2)), 
					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		case "AB":
				//  !!!!! nepadaryta su perpildymu
    			commandAB(Character.getNumericValue(command.charAt(2)), 
    					  Character.getNumericValue(command.charAt(3)));
    			break;
    		case "BA":
				//  !!!!! nepadaryta su perpildymu
    			commandBA(Character.getNumericValue(command.charAt(2)), 
    					  Character.getNumericValue(command.charAt(3)));
    			break;
    		case "BB":
				//  !!!!! nepadaryta su perpildymu
    			commandBB(Character.getNumericValue(command.charAt(2)), 
    					  Character.getNumericValue(command.charAt(3)));
    			break;
    		// LOGINES KOMANDOS
    		case "CA":
    			commandCA(Character.getNumericValue(command.charAt(2)), 
  					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		case "CB":
    			commandCA(Character.getNumericValue(command.charAt(2)), 
  					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		// VALDYMO PERDAVIMO KOMANDOS
    		case "JP":
    			commandJP(command.charAt(2),command.charAt(3));
    			break;
    		case "JE":
    				
    			break;
    		case "JL":
    			
    			break;
    		case "JG":
    			break;
    	    //ISVEDIMO IR IVEDIMO KOMANDOS
    		case "OP":
    			commandOP(Character.getNumericValue(command.charAt(2)), 
					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		// PABAIGA
    		case "HALT":
    			commandHALT();
    		default:
    			// padarysim exception
    	}
    }
    
    // KOMANDOS
    // ATMINTIES KOMANDOS
    public void commandLA(int x, int y){
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] = memory[x * BLOCK_SIZE * WORD_SIZE + y + i];
    	}
    }
    public void commandLAFB(){
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] = BX[i];
    	}
    }
    public void commandLB(int x, int y){
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] = memory[x * BLOCK_SIZE * WORD_SIZE + y + i];
    	}
    }
    public void commandLBFA(){
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] = AX[i];
    	}
    }
    public void commandSA(int x, int y){
    	for(int i = 0; i < WORD_SIZE; i++){
    		memory[x * BLOCK_SIZE * WORD_SIZE + y + i] = AX[i];
    	}
    }
    public void commandSB(int x, int y){
    	for(int i = 0; i < WORD_SIZE; i++){
    		memory[x * BLOCK_SIZE * WORD_SIZE + y + i] = BX[i];
    	}
    }
    public void commandCOPA(){
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] = BX[i];
    	}
    }
    public void commandCOPB(){
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] = AX[i];
    	}
    }
    public void commandAW(){
    	/////////////////////////////
    }
    // ARITMETINES KOMANDOS
    public void commandAA(int x, int y){
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] += memory[x * BLOCK_SIZE * WORD_SIZE + y + i];
    	}
    }
    public void commandAB(int x, int y){
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] += memory[x * BLOCK_SIZE * WORD_SIZE + y + i];
    	}
    }
    public void commandBA(int x, int y){
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] -= memory[x * BLOCK_SIZE * WORD_SIZE + y + i];
    	}
    }
    public void commandBB(int x, int y){
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] -= memory[x * BLOCK_SIZE * WORD_SIZE + y + i];
    	}
    }
    // LOGINES KOMANDOS
    public void commandCB(int x, int y){
    	int correct = 0;
    	for(int i = 0; i < WORD_SIZE; i++){
    		if(BX[i] == memory[x * BLOCK_SIZE * WORD_SIZE + y + i]){
    			correct++;
    		}
    	}
    	if (correct == WORD_SIZE){
    		SF = 8;         // ?????????????
    	}
    }
    public void commandCA(int x, int y){
    	int correct = 0;
    	for(int i = 0; i < WORD_SIZE; i++){
    		if(AX[i] == memory[x * BLOCK_SIZE * WORD_SIZE + y + i]){
    			correct++;
    		}
    	}
    	if (correct == WORD_SIZE){
    		SF = 8;         // ?????????????
    	}
    }
    // VALDYMO PERDAVIMO
    public void commandJP(char x, char y){
    	IC[0] = (byte)x;
    	IC[1] = (byte)y;
    	IC[1] -= 1;  ///cia tam kad gali buti klaidu su IC nes poto mes ji papliusinam
    }
    //ISVEDIMO IR IVEDIMO KOMANDOS
    public void commandOP(int x, int y){
    	SI = 2;  
    }
    // PABAIGA
    public void commandHALT(){
    	SI = 3;
    }
    public void incIC(){
    	IC[1] += 1;   					 // !!!! padaryt IC[0]
    }
 
    public void expect(String expectCommand, BufferedReader inputStream) throws Exception{
    	String command = inputStream.readLine();
    	if(command.startsWith((expectCommand))){
    		if(expectCommand == ".DAT "){
    			dataBloksNum = Character.getNumericValue(command.charAt(5));  // kas po to seka - ignoruojama
    		}
    	}else{
    		throw new Exception("Invalid command " + command + " expected " + expectCommand);
    	}
    }
    public void printMemory(){
    	for(int i = 0; i < 1024; i++){
			 System.out.println(i + "| " + (char)memory[i]);
		 }
    }
    public void printRegisters(){
    	System.out.println("AX = " + AX[0] + " " + AX[1] + " " + AX[2] + " " + AX[3]);
    	System.out.println("BX = " + BX[0] + " " + BX[1] + " " + BX[2] + " " + BX[3]);
    	System.out.println("IC = " + IC[0] + " " + IC[1]);
    	System.out.println("C  = " + C);
    }
    public void pause(){
    	System.out.println("Press any key to continue");
    	new java.util.Scanner(System.in).nextLine();
    }
    public int realAddress(char x, char y){
    
    	return 0;
    }
	public static void main(){
		 try{
			 Machine machine = new Machine();
			 machine.loader(filename);
			 machine.printMemory();
			 //machine.vm[0] = new VM();
			 
			 VM vm = new VM();
			 vm.vm(filename);   //????????
			 //vm.checkCommands(/*filename*/);
			 //vm.getTable().setValueAt(1417, 1, 1);
			 
			 
			// PATIKRINTI AR KOMANDOS VALIDZIOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// reikia inicializuoti viska 0 pradzioje
			// parasyti kuri virtuali masina yra uzkrauta 
			// KOMANDU VYKDYMAS
			int memoryIndex = 0;
	/*	    while(true){
		    	memoryIndex = (int)machine.IC[1] - '0';
		      	//System.out.println("MEMORY INDEX " + memoryIndex);
		    	memoryIndex *= WORD_SIZE;
		    	System.out.println("MEMORY INDEX " + memoryIndex);
		   		machine.printRegisters();
		   		machine.pause();
		   		machine.commandInterpreter(memoryIndex);
		   		//memoryIndex += 4;
		   		machine.incIC();
		   		///Reiketu patikrinima ideti ar nebuvo interupt	
		     }  
	*/
		 }catch(Exception e){
			 System.out.println(e.toString());
		 }
	}
}