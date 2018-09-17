
public class TreeNode {
	public Integer val;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode (int val) {
		this.val = val;
		this.left = null;
		this.right = null;
	}
	
	public TreeNode (int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	public void preOrderWalk() {
		if (val == null) {
			return;
		}
		System.out.print(val + " ");
		if (left != null) left.preOrderWalk();
		if (right != null) right.preOrderWalk();
	}
	
	public void inOrderWalk() {
		if (left != null) left.inOrderWalk();
		if (val == null) {
			return;
		}
		System.out.print(val + " ");
		if (right != null) right.inOrderWalk();
	}
	
	public void postOrderWalk() {
		if (left != null) left.postOrderWalk();
		if (right != null) right.postOrderWalk();
		if (val == null) {
			return;
		}
		System.out.print(val + " ");
	}
}
