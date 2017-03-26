import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * 
 * @author Aliona ir Eimantas
 *  
 *  
 */
public class Machine{
	public final static int WORD_SIZE = 4;
	public final static int BLOCK_SIZE = 16;
	public final static int USER_BLOCKS = 48;
	private final static int BLOCKS = 64;
	public int[] pagingTablesNum = new int[3 * BLOCK_SIZE];
	
	private static int dataBlocksNum = 0;
	
	public final static byte memory[] = new byte[BLOCKS * BLOCK_SIZE * WORD_SIZE];
	private VM vm[];
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public static String filename = "failas.txt";
	
	public final static byte PLR[] = new byte[WORD_SIZE];
	private final byte AX[]  = new byte[WORD_SIZE];
    private final byte BX[]  = new byte[WORD_SIZE];
    private final byte IC[]  = {0, 0};  // kelinta komanda vykdoma
    private byte C;             
    private byte SF;
    private byte MODE;
    private byte CH1;
    private byte CH2;
    private byte CH3;
    private byte IOI;
    private byte PI;
    private byte SI;
    private byte TI = 10;
    public int channelNumber;
    public byte channelDeviceBuffer[] = new byte[64];
    public int X, Y;
    
    
  
    // !!!!!!!!!! CLOSE FILE
    // !!!!!!!!!! nuimti patikrinimus
    private void loader(String fileName) throws Exception{
    	///Irasome psl. lenteles skaicius.
    	for(int i = 0; i < BLOCK_SIZE; i++){
    		memory[BLOCK_SIZE * WORD_SIZE * (PLR[2] * 10 + PLR[3]) + i * WORD_SIZE] = (byte) pagingTablesNum[i]; 
    		System.out.println("Paging table num " + pagingTablesNum[i]);
    	}
    	//System.out.println("MEMORY SK" + BLOCK_SIZE * WORD_SIZE * (PLR[2]*10+PLR[3]));
    	
    	String command;
    	BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
    	int dat[] = new int[2];
    	int code[] = new int[2];
    	code[0] = 0;
    	code[1] = 0;
    
    	expect("$WOW",inputStream);
    	expect(".NAM ", inputStream);
    	expect(".DAT ", inputStream);
    	
    	
    	//System.out.println("DataBlocksNum" + dataBlocksNum);
    	dat[0] = BLOCK_SIZE - dataBlocksNum;
    	//System.out.println("DAT[0] = " +dat[0]);
    	dat[1] = 0;
    	int dataSegment = dat[0];
    	while(!(command = inputStream.readLine()).startsWith("$WRT")){
    		writeToMemory(command, dat);
    		dat = nextAddr(dat);
    	}
    	
    	// WRT
    	int index = 0;
    	while(!(command = inputStream.readLine()).startsWith("$END")){
    	// patikrinimas su 4 !!!!!!!!!!!!!!!!!!!!!!!!!!
    		writeToMemory(command, code);
    		System.out.println("DATA SEGMENT" + dataSegment);
    		System.out.println("CODe" + code[0]);
    		if(code[0] == dataSegment){
    			throw new Exception("Virtual machine has no more space");
    		}else{
    			code = nextAddr(code);	
    		}
    	}
    		
    	// END
    	inputStream.close();
    }
    public void writeToMemory(String command, int[] memCoord){
    	int address = realAddress(memCoord[0], memCoord[1]);
    	//System.out.println("REAL ADDRESS " + address);
    	for(int i = 0; i < WORD_SIZE; i++){
    		memory[address + i] = (byte) command.charAt(i); 
    	}
    }
    public int realAddress(int x, int y) {
		int pagingTableAddr = (((int)PLR[2]) * 10 + (int)PLR[3]) * BLOCK_SIZE * WORD_SIZE;
		System.out.println("PAGING TABLE ADDRESS " + pagingTableAddr);
	    int pagingRandomNumber = memory[pagingTableAddr + x * 4]; 
	    System.out.println("PAGING RANDOM NUMBER " + pagingRandomNumber);
	    return pagingRandomNumber * BLOCK_SIZE + y * WORD_SIZE;  
	}
    public int[] nextAddr(int[] code) throws Exception{
    	int x = code[0];
    	int y = code[1];
    	y++;
    	//System.out.println(code[0]);
    	//System.out.println(code[1]);
    	if(y > 15){
    		x++;
    		y = 0;
    	}
    	if(x > 15){
    		throw new Exception("Virtual memory has no more space");
    	}
    	code[0] = x;
    	code[1] = y;
    	return code;
    }
    public void commandInterpreter() throws Exception{  // !!!!!! padaryti per realu adr su IC
    	String command = "";
    	int address = realAddress(IC[0], IC[1]);
    	for(int i = 0; i < WORD_SIZE; i++){
    		command += (char)memory[address + i];
    	}
    	String commandStart = command;
    	System.out.println("KOMANDA " + command);
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
    			commandJP(Character.getNumericValue(command.charAt(2)), 
					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		case "JE":
    			commandJE(Character.getNumericValue(command.charAt(2)), 
					  	  Character.getNumericValue(command.charAt(3)));	
    			break;
    		case "JL":
    			commandJL(Character.getNumericValue(command.charAt(2)), 
					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		case "JG":
    			commandJG(Character.getNumericValue(command.charAt(2)), 
					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    	    //ISVEDIMO IR IVEDIMO KOMANDOS
    		case "OP":
    			commandOP(Character.getNumericValue(command.charAt(2)), 
					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		case "IP":
    			commandIP(Character.getNumericValue(command.charAt(2)), 
					  	  Character.getNumericValue(command.charAt(3)));
    			break;
    		// PABAIGA
    		case "HALT":
    			commandHALT();
    			break;
    		default:
    			throw new Exception("Unknow command");
    	}
    }
    
    // KOMANDOS
    // ATMINTIES KOMANDOS
    public void commandLA(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] = memory[address + i];
    	}
    }
    public void commandLAFB(){
    	incIC();
    	clearC();
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] = BX[i];
    	}
    }
    public void commandLB(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] = memory[address + i];
    	}
    }
    public void commandLBFA(){
    	incIC();
    	clearC();
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] = AX[i];
    	}
    }
    public void commandSA(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	for(int i = 0; i < WORD_SIZE; i++){
    		memory[address + i] = AX[i];
    	}
    }
    public void commandSB(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	for(int i = 0; i < WORD_SIZE; i++){
    		memory[address + i] = BX[i];
    	}
    }
    public void commandCOPA(){
    	incIC();
    	clearC();
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] = BX[i];
    	}
    }
    public void commandCOPB(){
    	incIC();
    	clearC();
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] = AX[i];
    	}
    }
    // ARITMETINES KOMANDOS
    public void commandAA(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] += memory[address + i];
    	}
    }
    public void commandAB(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] += memory[address + i];
    	}
    }
    public void commandBA(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	for(int i = 0; i < WORD_SIZE; i++){
    		AX[i] -= memory[address + i];
    	}
    }
    public void commandBB(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	for(int i = 0; i < WORD_SIZE; i++){
    		BX[i] -= memory[address + i];
    	}
    }
    // LOGINES KOMANDOS
    public void commandCA(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	int correct = 0;
    	for(int i = 0; i < WORD_SIZE; i++){
    		if(AX[i] == memory[address + i]){
    			correct++;
    		}
    	}
    	if (correct == WORD_SIZE){
    		setC();
    		setZF();
    	}else{
    		clearZF();
    		clearC();
    	}
    }
    public void commandCB(int x, int y){
    	incIC();
    	clearC();
    	int address = realAddress(x,y);
    	int correct = 0;
    	for(int i = 0; i < WORD_SIZE; i++){
    		if(BX[i] == memory[address + i]){
    			correct++;
    		}
    	}
    	if (correct == WORD_SIZE){
    		setC();
    		setZF();
    	}else{
    		clearZF();
    		clearC();
    	}
    }
    // VALDYMO PERDAVIMO
    public void commandJP(int x, int y){
    	IC[0] = (byte)x;
    	IC[1] = (byte)y;
    }
    public void commandJE(int x, int y){
    	if(getBit(3) == 1){
    		IC[0] = (byte)x;
        	IC[1] = (byte)y;
    	}else{
    		incIC();
    	}
    }
    public void commandJL(int x, int y){
    	if(getBit(3) == 0 && getBit(4) == getBit(2)){
    		IC[0] = (byte)x;
        	IC[1] = (byte)y;
    	}else{
    		incIC();
    	}
    }
    public void commandJG(int x, int y){
    	if(getBit(3) == 0 && getBit(4) != getBit(2)){
    		IC[0] = (byte)x;
        	IC[1] = (byte)y;
    	}else{
    		incIC();
    	}
    }
    //ISVEDIMO IR IVEDIMO KOMANDOS
    public void commandOP(int x, int y){
    	incIC();
    	SI = 2;  
    	int address = realAddress(x,y);
    	X = x;
    	Y = y;
    	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PATIKRINTI X IR Y ir NUSTATYTI PI = 1
    }
    public void commandIP(int x, int y){
    	incIC();
    	SI = 1;  
    	int address = realAddress(x,y);
    	X = x;
    	Y = y;
    	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PATIKRINTI X IR Y ir NUSTATYTI PI = 1
    }
    // PABAIGA
    public void commandHALT(){
    	SI = 3;
    }
    public void incIC(){
    	IC[1]++;
    	if(IC[1] > 15){
    		IC[0]++;
    		IC[1] = 0;
    	}
    }
    // PAGING TABLE
    public void setPagingTable(){
		for(int i = 0; i < pagingTablesNum.length; i++){
			pagingTablesNum[i] = i;
		}
		int counter = 0;
		Random randomGenerator = new Random(System.currentTimeMillis());
		for(int i = 0; i < pagingTablesNum.length; i++){
			int random = randomGenerator.nextInt(pagingTablesNum.length - counter++);
			swap(i, random + i);
		}
	}
	public void swap(int from, int to){
		int temp = pagingTablesNum[from];
		pagingTablesNum[from] = pagingTablesNum[to];
		pagingTablesNum[to] = temp;
	}
	public void setZF(){
		SF = (byte) (SF | (1 << 3));
	}
	public void clearZF(){
		SF = (byte) (SF & ~(1 << 3));
	}
	public void clearC(){
		C = 0;
	}
	public void setC(){
		C = 1;
	}
	public int getBit(int pos){
		return(SF >> pos) & 1;
	}
	// INTERRUPTS
	public void checkInterrupt(){
		if((byte) TI == 0){
			System.out.println("Program has exceeded its time limit");
			MODE = 1;
			restartTimer();
			MODE = 0;
		}
		if((byte) PI != 0){
			switch((byte) PI){
			case 1:
				System.out.println("Program interrupt. Incorrect command");
				MODE = 1;
				stopProgram();
				break;
			case 2:
				System.out.println("Program interrupt. Negative result");
				MODE = 1;
				stopProgram();
				break;
			case 3:
				System.out.println("Program interrupt. Division by zero");
				MODE = 1;
				stopProgram();
				break;
			case 4:
				System.out.println("Program interrupt. Program overflow");
				MODE = 1;
				stopProgram();
				break;
			}
		}
		if((byte) SI != 0){
			switch((byte) SI){
			case 1:
				System.out.println("Program interrupt. Data input");
				MODE = 1;
				channelNumber = 1;
				MODE = 0;
				SI = 0;
				break;
			case 2:
				System.out.println("Program interrupt. Data output");
				MODE = 1;
				channelNumber = 2;
				MODE = 0;
				SI = 0;
				break;
			case 3:
				System.out.println("Program interrupt. Command halt");
				MODE = 1;
				stopProgram();
				break;
			}
		}
		if((byte) IOI != 0){
			switch((byte) IOI){
			case 1:
				System.out.println("Channel 1 done");
				MODE = 1;
				IOI = 0;
				MODE = 0;
				break;
			case 2:
				System.out.println("Channel 2 done");
				MODE = 1;
				IOI = 0;
				MODE = 0;
				break;
			case 3:
				System.out.println("Channel 3 done");
				MODE = 1;
				IOI = 0;
				MODE = 0;
				break;
			}
		}
	}
	public void restartTimer(){
		if((byte) MODE == 1){
			TI = (byte) 10;
			System.out.println("Supervisor => Timer restarted succesfully");
		}
	}
	public void startIO() throws IOException{
		if(channelNumber == 1){
			channelNumber = 0;
			CH1 = 1;
			System.out.println("INPUT DATA ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			int length = input.length();
			if(length > 64){
				length = 64;
			}
			for(int i = 0; i < length; i++){
				channelDeviceBuffer[i] = (byte) input.charAt(i);
			}
			// PABAIGOS SIMBOLIS
			if(length < 64){
				channelDeviceBuffer[length] = (byte) '$';     // 36
			}
			int startPos = X * BLOCK_SIZE + Y;  ///patasyt poto iki F!!!!!!!!!!!!!!!!!!!!!!!!!
			outerloopCH1:
			for(int i = 0; i < BLOCK_SIZE; i++){
				int x = (startPos + i) / 16;
				int y = (startPos + i) % 16;
				int address = realAddress(x, y);
				for(int j = 0; j < WORD_SIZE; j++){
					if(channelDeviceBuffer[i * WORD_SIZE + j] == '$'){
						break outerloopCH1;
					}
					memory[address + j] = channelDeviceBuffer[i * WORD_SIZE + j];
				}
			}
			CH1 = (byte) 0;
			IOI += 1; 
		}
		if(channelNumber == 2){
			channelNumber = 0;
			CH2 = 1;
			int startPos = X * BLOCK_SIZE + Y;  // !!!!!!!!!!!!!!!!!!!
			outerLoopCH2:
			for(int i = 0; i < BLOCK_SIZE; i++){
				int x = (startPos + i) / 16;
				int y = (startPos + i) % 16;
				int address = realAddress(x, y);
				for(int j = 0; j < WORD_SIZE; j++){
					channelDeviceBuffer[i * WORD_SIZE + j] = memory[address + j];
					if(memory[address + j] == '$'){
						break outerLoopCH2;
					}
				}
			}
			for(int i = 0; i < 64; i++){
				if(channelDeviceBuffer[i] == '$'){
					System.out.println();
					break;
				}
				System.out.write(channelDeviceBuffer[i]);
			}
			CH2 = 0;
			IOI += 2; 
		}
		if(channelNumber == 3){
			channelNumber = 0;
			CH3 = 1;
			CH3 = 0;
			IOI += 4;
		}
	}
	public void stopProgram(){
		System.exit(0);
	}
    public void expect(String expectCommand, BufferedReader inputStream) throws Exception{
    	String command = inputStream.readLine();
    	if(command.startsWith((expectCommand))){
    		if(expectCommand == ".DAT "){
    			if(command.length() == 6){
    				dataBlocksNum = Integer.parseInt(command.substring(5)); 
    			}else if(command.length() > 6){
    				dataBlocksNum = Integer.parseInt(command.substring(5, command.length())); 
    			}
    		}
    	}else{
    		throw new Exception("Invalid command " + command + " expected " + expectCommand);
    	}
    }
    public void printMemory(){
    	for(int i = 0; i < BLOCKS * BLOCK_SIZE * WORD_SIZE; i++){
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
	public static void main(String[] args) throws Exception{
		// try{
			 PLR[2] = 6; 
			 PLR[3] = 1;
			 Machine machine = new Machine();
			 machine.setPagingTable();
			 machine.loader(filename);
			 //machine.printMemory();
			 
			 //machine.vm[0] = new VM();
			 
			 //VM vm = new VM();
			 //vm.vm(filename);   //????????
			 //vm.checkCommands(/*filename*/);
			 //vm.getTable().setValueAt(1417, 1, 1);
			 
			 
			// PATIKRINTI AR KOMANDOS VALIDZIOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// reikia inicializuoti viska 0 pradzioje
			// parasyti kuri virtuali masina yra uzkrauta 
			// KOMANDU VYKDYMAS
			 
		    while(true){
		   		machine.printRegisters();
		   		machine.pause();
		   		machine.commandInterpreter();
		   		machine.printMemory();
		   		machine.TI -= 1;
		   		
		   		machine.startIO();
		   		machine.checkInterrupt();
		   		///Reiketu patikrinima ideti ar nebuvo interupt	
		     }  
		 //}catch(Exception e){
			// System.out.println(e.toString());
		// }
	}
}