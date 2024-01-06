package logic;

import java.io.Serializable;
/**
 * this class is for saving the code for test which entered by student , and tybe for knowing if 
 * student wants to do the exam manual or digital way (digital : type=0, manual : type=1)
 */
public class TestCode implements Serializable {
	private int code;
	private int type;

	public int getType() {
		return type;
	}

	public int getCode() {
		return code;
	}

	public TestCode(int code,int type) {
		super();
		this.code = code;
		this.type=type;
	}
    
}


