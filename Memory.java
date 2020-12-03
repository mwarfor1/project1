package project;
import java.util.Arrays;


public class Memory {
	public static final int DATA_SIZE = 512;
	public static final int CODE_SIZE = 256;
	private long[] code = new long[CODE_SIZE];
	private int programSize = 0;
	private int [] data = new int[DATA_SIZE];
	private int changeDataIndex = -1;
	
	public long getCode(int index) {
		if(index < 0 || index >= CODE_SIZE) {
			throw new CodeAccessException("illegal index " + index);
		}
		return code[index];
	}
	
	public void setCode(int index, long value) {
		if(index < 0 || index >= CODE_SIZE) {
			throw new CodeAccessException("illegal index " + index);
		}
		code[index] = value;
		programSize = Math.max(programSize, index + 1);
	}
	
	public long[] getCodeRange(int min, int max) {
		if(min < 0 && max >= CODE_SIZE && min > max) {
			throw new CodeAccessException("illegal indices " + min + " " + max);
		}
		return Arrays.copyOfRange(code, min, max);
	}
	
	public long[] getCodeArray() {
		return code;
	}
	
	public int getProgramSize() {
		return programSize; 
	}
	
	public void setProgramSize(int sizeProgram) {
		programSize = sizeProgram;
	}
	
	public void clearCode() {
		for(int i = 0; i < CODE_SIZE; i++) {
			code[i] = 0
		}
		programSize = 0;
	}

	
	public int getChangeDataIndex() {
		return changeDataIndex;
	}


	public int getData(int index) {
		if (index < 0 || index >= DATA_SIZE) {
			throw new DataAccessException("illegal index " + index);
		}
		return data[index];
	}
	
	
	public void setData(int index, int value) {
		if (index < 0 || index >= DATA_SIZE) {
			throw new DataAccessException("illegal index " + index);
		}
		data[index] = value;
		changeDataIndex = index;
	}
	
	
	public int[] getDataRange(int min, int max) {
		if (min < 0 && max >= DATA_SIZE &&  min > max) {
			throw new DataAccessException("illegal indices " + min + " " + max);
		}
		
		return Arrays.copyOfRange(data, min, max);	
	}
	
	
	public int[] getDataArray() {
		return data;
	}
	
	
	public void clearData() {
		for (int x: data) {
			x = 0;
		}
		changeDataIndex = -1;
	}
}
