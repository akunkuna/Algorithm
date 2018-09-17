
public class TreeDfs {

	public static void main(String[] args) {
		//  10
		// 5  15
		//2 7   20
		//     17 
		TreeNode root = new TreeNode(10, 
				new TreeNode(5, 
						new TreeNode(2, null, null),
						new TreeNode(7, null, null)),
				new TreeNode(15,
						null,
						new TreeNode(20,
								new TreeNode(17, null, null),
								null)));
		System.out.println("preOrderWalk:");
		root.preOrderWalk();
		System.out.println();
		
		System.out.println("inOrderWalk:");
		root.inOrderWalk();
		System.out.println();
		
		System.out.println("postOrderWalk:");
		root.postOrderWalk();
		System.out.println();

	}

}
