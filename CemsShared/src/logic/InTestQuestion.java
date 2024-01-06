package logic;

import java.io.Serializable;

/**
 * this class is for saving question info to show it in the test screen
 */
public class InTestQuestion implements Serializable{
	
	private String qTxt; //Question text
	private String [] ansTxt = new String[4]; //4 answers array
	private int score; //question number of points
	private int quesId;// question id
	private int cAns; //the number of correct answer
	public InTestQuestion(String qTxt, String[] ansTxt,int score,int quesId,int cAns) {
		this.qTxt = qTxt;
		this.ansTxt = ansTxt;
		this.score=score;
		this.quesId=quesId;
		this.cAns=cAns;
	}
	

	public int getQuesId() {
		return quesId;
	}


	public String getqTxt() {
		return qTxt;
	}
	public String[] getAnsTxt() {
		return ansTxt;
	}
	public int getScore() {
		return score;
	}
	public int getcAns() {
			return cAns;
		}
	
	
	
	

}
