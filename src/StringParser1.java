import java.util.LinkedList;
import java.util.List;


public class StringParser1 {

	public static void main(String[] args) {
		String input = "[I <love read [the book]>]";
		Op op;
		List<String> list = new LinkedList<>();
		
		String word = new String("");
		for (int i = 0; i < input.length(); i++) {
			Character c = input.charAt(i);
			if (c == '[' || c == '<') {
				list.add(0, c.toString());
			} else if (c == ' ') {
				list.add(0, word);
				word = new String("");
			} else if (c == ']') {
				if (word.length() > 0) {
					list.add(0, word);
					word = new String("");
				}
				op = new Op("op1", 1);
				cal(op, list);
			} else if (c == '>') {
				if (word.length() > 0) {
					list.add(0, word);
					word = new String("");
				}
				op = new Op("op2", 1);
				cal(op, list);
			}else {
				word += c.toString();
			}
		}
		
		System.out.println("result:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		

	}
	
	public static void cal (Op op, List<String> list) {
		String left = "";
		String right = "";
		String leftOp = op.getLeftOp();
		while(!list.get(0).equals(leftOp)) {
			if (!left.equals("") && !right.equals("")) {
				right = left + " " + right;
			} else if (!left.equals("") || !right.equals("")) {
				right = left + right;
			}
			left = list.remove(0);			
		}
		list.remove(0);
		list.add(0, op.cal(left, right));
	}
	

}
