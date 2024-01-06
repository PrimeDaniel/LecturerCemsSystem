package logic;

import java.io.Serializable;

/** this class for sending the extra time which been added to the test, will be send to students doing the test  */
public class AddedTime implements Serializable {
	private int added;

	public int getAdded() {
		return added;
	}

	public void setAdded(int added) {
		this.added = added;
	}
	

}
