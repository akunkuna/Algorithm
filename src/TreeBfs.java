import java.util.ArrayList;
import java.util.List;

public class TreeBfs {

	public static void main(String[] args) {
		//    10
		//   5  15
		// 2  7   20
		//1 3    17 
		TreeNode root = new TreeNode(10, 
				new TreeNode(5, 
						new TreeNode(2,
								new TreeNode(1, null, null),
								new TreeNode(3, null, null)),
						new TreeNode(7, null, null)),
				new TreeNode(15,
						null,
						new TreeNode(20,
								new TreeNode(17, null, null),
								null)));
		
		// normal bfs
		System.out.println("normal-bfs:");
		normalBfs(root);
		System.out.println();
		
		// reverse bfs
		System.out.println("reverse-bfs:");
		reverseBfs(root);
		System.out.println();

	}
	
	public static void normalBfs (TreeNode root) {
		List<TreeNode> list = new ArrayList<>();
		list.add(root);
		while (!list.isEmpty()) {
			TreeNode t = list.remove(0);
			System.out.print(t.val + " ");
			if(t.left != null) list.add(t.left);
			if(t.right != null) list.add(t.right);
		}
	}
	
	public static void reverseBfs (TreeNode root) {
		List<TreeNode> list = new ArrayList<>();
		list.add(root);
		while (!list.isEmpty()) {
			TreeNode t = list.remove(0);
			System.out.print(t.val + " ");
			if(t.right != null) list.add(t.right);
			if(t.left != null) list.add(t.left);
		}
	}

}
