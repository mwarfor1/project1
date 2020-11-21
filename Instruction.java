package project;

public enum Instruction {
	NOP(0) {
		@Override
		public void execute(int arg) {
			// increment the IP by calling processor.incIP();
			processor.incIP()++;
		}
	},
	NOT(1) {
		@Override
		public void execute(int arg) {
			// applyNot in the processor
			processor.applyNot();
			// increment the IP
			processor.incIP();
			
		}		
	},
	LOD_IMM(2) {
		@Override
		public void execute(int arg) {
			int temp = arg;
			// set the accumulator of the processor to temp
			processor.setAcc(temp);
			// increment the IP
			processor.incIP();
		}
	},
	LOD_DIR(3) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(arg);
			// set the accumulator of the processor to temp
			processor.setAcc(temp);
			// increment the IP
			processor.incIP();
		}		
	},
	LOD_IND(4) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(memory.getData(arg));
			// set the accumulator of the processor to temp
			processor.setAcc(temp);
			// increment the IP
			processor.incIP();
		}		
	},
	STO_DIR(5) {
		@Override
		public void execute(int arg) {
			int index = arg;
			int value = processor.getAcc();
			// call setData on memory with index and value
			memory.setData(index, value);
			// increment the IP
			processor.incIP();
		}
	},
	STO_IND(6) {
		@Override
		public void execute(int arg) {
			int index = memory.getData(arg);
			int value = processor.getAcc();
			// call setData on memory with index and value
			memory.setData(index, value);
			// increment the IP
			processor.incIP();
		}
	},
	ADD_IMM(7) {
		@Override
		public void execute(int arg) {
			int temp = arg;
			// change the accumulator by adding temp
			processor.setAcc(processor.getAcc()+temp);
			// increment the IP
			processor.incIP();
		}				
	},
	ADD_DIR(8) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(arg);
			// change the accumulator by adding temp
			processor.setAcc(processor.getAcc()+temp);
			// increment the IP
			processor.incIP();
		}						
	},
	ADD_IND(9) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(memory.getData(arg));
			// change the accumulator by adding temp
			processor.setAcc(processor.getAcc()+temp);
			// increment the IP
			processor.incIP();
		}								
	},
	SUB_IMM(10) {
	// copy from ADD_IMM and change + to -
		@Override
		public void execute(int arg) {
			int temp = arg;
			// change the accumulator by adding temp
			processor.setAcc(processor.getAcc()-temp);
			// increment the IP
			processor.incIP();
	},
	SUB_DIR(11) {
	// copy from ADD_DIR and change + to -
		@Override
		public void execute(int arg) {
			int temp = memory.getData(arg);
			// change the accumulator by adding temp
			processor.setAcc(processor.getAcc()-temp);
			// increment the IP
			processor.incIP();
		}
	},
	SUB_IND(12) {
	// copy from ADD_IND and change + to -
		@Override
		public void execute(int arg) {
			int temp = memory.getData(memory.getData(arg));
			// change the accumulator by adding temp
			processor.setAcc(processor.getAcc()-temp);
			// increment the IP
			processor.incIP();
		}
	},
	MUL_IMM(13) {
	// copy from ADD_IMM and change + to *
		// copy from ADD_IMM and change + to -
		@Override
		public void execute(int arg) {
			int temp = arg;
			// change the accumulator by adding temp
			processor.setAcc(processor.getAcc()*temp);
			// increment the IP
			processor.incIP();
		}
	},
	MUL_DIR(14) {
	// copy from ADD_DIR and change + to *
		@Override
		public void execute(int arg) {
			int temp = memory.getData(arg);
			// change the accumulator by adding temp
			processor.setAcc(processor.getAcc()*temp);
			// increment the IP
			processor.incIP();
		}
	},
	MUL_IND(15) {
	// copy from ADD_IND and change + to *
		@Override
		public void execute(int arg) {
			int temp = memory.getData(memory.getData(arg));
			// change the accumulator by adding temp
			processor.setAcc(processor.getAcc()*temp);
			// increment the IP
			processor.incIP();
		}
	},
	DIV_IMM(16) {
	// copy from ADD_IMM and change + to / but if temp is 0 throw new DivideByZeroException("Division by zero")
		@Override
		public void execute(int arg) {
			int temp = arg;
			// change the accumulator by adding temp
			if(temp == 0) throw new DivideByZeroException("Division by zero");
			processor.setAcc(processor.getAcc()/temp); 
			// increment the IP
			processor.incIP();
		}
	},
	DIV_DIR(17) {
	// copy from ADD_DIR and change + to / but if temp is 0 throw new DivideByZeroException("Division by zero")
		@Override
		public void execute(int arg) {
			int temp = memory.getData(arg);
			// change the accumulator by adding temp
			if(temp == 0) throw new DivideByZeroException("Division by zero");
			processor.setAcc(processor.getAcc()/temp);
			// increment the IP
			processor.incIP();
		}
	},
	DIV_IND(18) {
	// copy from ADD_IND and change + to / but if temp is 0 throw new DivideByZeroException("Division by zero")
		@Override
		public void execute(int arg) {
			int temp = memory.getData(memory.getData(arg));
			// change the accumulator by adding temp
			if(temp == 0) throw new DivideByZeroException("Division by zero");
			processor.setAcc(processor.getAcc()*temp);
			// increment the IP
			processor.incIP();
		}
	},
	AND_IMM(16) {
		@Override
		public void execute(int arg) {			   // temp * temp1 implements this:
			int temp = arg;				   // if temp is TRUE
			int temp1 = processor.getAcc();		   // and temp1 is TRUE
			if(temp * temp1 != 0) processor.setAcc(1); // set accumulator to TRUE
			else processor.setAcc(0); 		   // otherwise FALSE
			// increment the IP		
			processor.incIP();
		}		
	},
	AND_DIR(17) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(arg);
			// copy the rest from AND_IMM
			int temp1 = processor.getAcc();
			if(temp * temp1 != 0) processor.setAcc(1);
			else processor.setAcc(0);
			// increment the IP		
			processor.incIP();
		}		
	},
	AND_IND(18) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(memory.getData(arg));
			// copy the rest from AND_IMM
			int temp1 = processor.getAcc();
			if(temp * temp1 != 0) processor.setAcc(1);
			else processor.setAcc(0);
			// increment the IP		
			processor.incIP();
		}				
	},
	CMPL_DIR(19) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(arg);
			// if temp < 0 set accumulator to 1
			if(temp < 0) processor.setAcc(1);
			// set accumulator to 0
			processor.setAcc(0);
			// increment the IP
			processor.incIP();
		}		
	},
	CMPL_IND(20) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(memory.getData(arg));
			// copy the rest from CMPL_DIR
			if(temp < 0) processor.setAcc(1);
			// set accumulator to 0
			processor.setAcc(0);
			// increment the IP
			processor.incIP();
		}				
	},
	CMPZ_DIR(21) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(arg);
			// if temp is 0 set accumulator to 1
			if(temp == 0) processor.setAcc(1);
			// set accumulator to 0
			processor.setAcc(0);
			// increment the IP
			processor.incIP();
		}				
	},
	CMPZ_IND(22) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(memory.getData(arg));
			// copy the rest from CMPZ_DIR
			if(temp == 0) processor.setAcc(1);
			// set accumulator to 0
			processor.setAcc(0);
			// increment the IP
			processor.incIP();
		}						
	},
	JUMP_IMM(23) { // this and the next 2 JUMPS is relative to the current ip
		@Override
		public void execute(int arg) {
			int temp = arg;
			int ip = processor.getIP();
			// set the IP of the processor to temp + ip 
			processor.setIP(temp+processor.getIP()); 
		}				
	},
	JUMP_DIR(24) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(arg);
			// copy the rest form JUMP_IMM
			int ip = processor.getIP();
			// set the IP of the processor to temp + ip 
			processor.setIP(temp+processor.getIP());
		}						
	},
	JUMP_IND(25) {
		@Override
		public void execute(int arg) {
			int temp = memory.getData(memory.getData(arg));
			// copy the rest form JUMP_IMM
			int temp = arg;
			int ip = processor.getIP();
			// set the IP of the processor to temp + ip 
			processor.setIP(temp+processor.getIP());
		}								
	},
	JUMP_ABS(26) { // special absolute addressing
		@Override
		public void execute(int arg) {
			processor.setIP(arg);
		}						
	},
	JMPZ_IMM(27) {
		// if accumulator is 0 copy JUMP_IMM
		if(processor.getAcc() == 0) {
			@Override
			public void execute(int arg) {
				int temp = arg;
				int ip = processor.getIP();
				// set the IP of the processor to temp + ip 
				processor.setIP(temp+processor.getIP()); 
			}
		}
		// else only increment the IP
		else processor.incIP();
	},
	JMPZ_DIR(28) {
		// if accumulator is 0 copy JUMP_DIR
		if(processor.getAcc() == 0) {
			@Override
			public void execute(int arg) {
				int temp = memory.getData(arg);
				// copy the rest form JUMP_IMM
				int ip = processor.getIP();
				// set the IP of the processor to temp + ip 
				processor.setIP(temp+processor.getIP());
			}
		}
		// else only increment the IP
		else processor.incIP();
	},
	JMPZ_IND(29) {
		// if accumulator is 0 copy JUMP_IND
		if(processor.getAcc() == 0) {
			@Override
			public void execute(int arg) {
				int temp = memory.getData(memory.getData(arg));
				// copy the rest form JUMP_IMM
				int temp = arg;
				int ip = processor.getIP();
				// set the IP of the processor to temp + ip 
				processor.setIP(temp+processor.getIP());
			}
		}
		// else only increment the IP
		else processor.incIP();
	},
	JMPZ_ABS(30) {
		// if accumulator is 0 copy JUMP_ABS
		if(processor.getAcc() == 0) {
			@Override
			public void execute(int arg) {
				processor.setIP(arg);
			}
		}
		// else only increment the IP
		else processor.incIP();
	},
	HALT(31) {
		@Override
		public void execute(int arg) {
			halt.accept();			
		}
	};

	// define private static fields
	// Processor processor, Memory memory, HaltCommand halt
	// define a private int field opcode
	private static Processor processor;
	private static Memory memory;
	private static HaltCommand halt;
	private int opcode;
	
	/**
	 * constructor
	 */
	private Instruction(int op) {
		opcode = op;
	}

	// get the Instruction for the specific opcode
	public static Instruction getInstruction(int opcode) {
		return Instruction.values()[opcode]; // values() is a method all enums
	}
	
	public abstract void execute(int arg);

	public int getOpcode() {
		return opcode;
	}

	// provide 3 static setter methods for the 3 static fields.
	public Processor getProcessor() {
		return processor;
	}
	public void setProcessor(Processor p) {
		this.processor = p;
	}
	public Memory getMemory() {
		return memory;
	}
	public void setMemory(Memory m) {
		this.memory = m;
	}
	public Processor getHaltCommand() {
		return halt;
	}
	public void setHaltCommand(HaltCommand h) {
		this.halt = h;
	}
}
