/*
 * op1: [a b] -> a b, priority: 1
 * op2: <a b> -> b a, priority: 1
 */
class Op {
	public String name;
	public int priority;
	
	public Op(String name, int priority) {
		this.name = name;
		this.priority = priority;
	}
	
	public String cal(String left, String right) {
		switch (this.name) {
			case "op1":
				return left +  " " + right;
			case "op2":
				return right + " " + left;
			default:
				return "";
		}
	}
	
	public String getLeftOp() {
		switch (this.name) {
			case "op1":
				return "[";
			case "op2":
				return "<";
			default:
				return "";
		}
	} 
}
