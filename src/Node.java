

public class Node {
	private int i;
	private int j;
	private int value;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return arg0 instanceof Node && value == ((Node) arg0).value;

	}
}