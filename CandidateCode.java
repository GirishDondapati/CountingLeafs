/* 
 * Enter your code here. Read input from STDIN. Print your output to STDOUT. 
 * Your class should be named CandidateCode.
*/

import java.io.*;
import java.util.*;
public class CandidateCode {
	static int delNodeIndex;
	Node root;
	// Tree Node
	static class Node
	{
		int data;
		int index;
		Node left, right;
		Node(int data,int index)
		{
			this.data = data;
			this.index = index;
			this.left = null;
			this.right = null;
		}
	}

	// Function to insert nodes in level order
	public Node insertLevelOrder(int[] arr, Node root, int i)
	{
		// Base case for recursion
		if (i < arr.length) {
			Node temp = new Node(arr[i],i);
			root = temp;

			// insert left child
			root.left = insertLevelOrder(arr, root.left, 2 * i + 1);

			// insert right child
			root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
		}
		return root;
	}

	int getLeafCount()
	{
	return getLeafCount(root);
	}

	int getLeafCount(Node node)
	{
		if (node == null)
			return 0;

		// first recur on left subtree
		if(delNodeIndex == node.index)
			return 0;

		if (node.left == null && node.right == null)
			return 1;
		else
			return getLeafCount(node.left) + getLeafCount(node.right);
	}

	public static void main(String args[] ) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String noOfNodesStr = reader.readLine();
		int noOfNodes = Integer.parseInt(noOfNodesStr);

		int nodesArr[] = new int[noOfNodes];
		String nodesStr = reader.readLine();

		String deleteIndexStr = reader.readLine();
		delNodeIndex = Integer.parseInt(deleteIndexStr);

		StringTokenizer sz = new StringTokenizer(nodesStr," ");
		int count = sz.countTokens();
		for(int i = 0; i < noOfNodes; i++)
		{
			String temp = sz.nextToken();
			nodesArr[i] = Integer.parseInt(temp);
		}

		CandidateCode obj = new CandidateCode();
		obj.root = obj.insertLevelOrder(nodesArr, obj.root, 0);
		System.out.println(obj.getLeafCount());
	}
}
