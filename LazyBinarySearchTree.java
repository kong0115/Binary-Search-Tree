/**Implements an unbalanced binary search tree with lazy deletion
 * This class is used to store valid set of keys in the range 1-99.
* @author Siang Swee Kong
* @version 1.0.0
*/
public class LazyBinarySearchTree {

	private TreeNode root; //the tree root
	private int count; //number of keys in the tree

	/**
	 *	Initialize the attributes with default constructor 
	 */
	public LazyBinarySearchTree() 
	{
		root = null; count = 0;
	}

	 /**
     * Insert into the tree; duplicates of non-deleted element are ignored, duplicates of deleted element are undeleted logically
     * @param key the item to insert.
     * @return true if insertion is successful
     * @return false if insertion is failed
     * @throws IllegalArgumentException if the key is not valid
     */
	public boolean insert(int key)
	{
		if(key < 1 || key > 99)
			throw new IllegalArgumentException("Invalid key. Valid set of keys is in the range of 1 - 99");
		root = insert(key,root);
		if(root == null) {
			return false;
		}
		return true;
	}

	/**
     * Internal method to insert into the tree.
     * @param key the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
	private TreeNode insert(int key, TreeNode t)
	{	
		if( t == null ) 
		{
			count++;
			return new TreeNode(key, null, null,false ); //create a new TreeNode with given key
		}

		if( key < t.key )
			t.leftChild = insert(key, t.leftChild);
		else if( key > t.key )
			t.rightChild = insert( key, t.rightChild);
		else if (key == t.key && t.deleted == true) //undeleted the key
		{
			t.deleted = false;
		}
		else
			return null;  // Duplicate; do nothing
		return t;
	}

	/**
     * Lazy deletion. Mark the key as logically deleted if the key is found and has not yet been deleted. If the key is not found or already deleted, do nothing.
     * @param key the item to be deleted.
     * @return true if deletion is successful
     * @return false if deletion is failed
     * @throws IllegalArgumentException if the key is not valid
     */
	public boolean delete(int key)
	{
		if(key < 1 || key > 99)
			throw new IllegalArgumentException("Invalid key. Valid set of keys is in the range of 1 - 99");
		TreeNode pointer = root;
		if(pointer == null)
		{
			return false;
		}
		
		while(pointer!=null)
		{
			if(key < pointer.key)
			{
				pointer = pointer.leftChild;
			}
			
			else if (key > pointer.key)
			{
				pointer = pointer.rightChild;
			}
			
			else if (key == pointer.key && pointer.deleted == false) //if it is not deleted
			{
				pointer.deleted = true; //delete the key
				return true;
			}
			else  //it is already marked as deleted
				return false;
		}
		
		return false;
	}
	
	
	/**
     * Find the smallest non-deleted item in the tree.
     * @return smallest non-deleted item or -1 if empty.
     */
   public int findMin( )
    {
        if( isEmpty( ) )
            return -1;
        return findMin(root).key;
    }
	
   /**
    * Internal method to find the smallest non-deleted item in a subtree.
    * @param t the node that roots the subtree.
    * @return node containing the smallest non-deleted item.
    */
    private TreeNode findMin(TreeNode t)
    {
    	if (t == null)
    	{
    		return null;
    	}
    	TreeNode min = findMin(t.leftChild);
	   if(min != null)
	   {
			   return min;
	   }
	   
	   if(t.deleted == false)
	   {
		   return t;
	   }
	   
	   return findMin(t.rightChild);
    }


    /**
     * Find the largest non-deleted item in the tree.
     * @return the largest non-deleted item or -1 if empty.
     */
    public int findMax( )
    {
        if( isEmpty( ) )
            return -1;
        return findMax(root).key;
    }
    
    /**
     * Internal method to find the largest non-deleted item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest non-deleted item.
     */
    private TreeNode findMax(TreeNode t)
    {
    	if (t == null)
    	{
    		return null;
    	}
    	TreeNode max = findMax(t.rightChild);
    	if(max!=null)
    	{
    		return max;
    	}
	   if(t.deleted == false)
	   {
		   return t;
	   }
	
	   return findMax(t.leftChild);
    }
    
    /**
     * Find an item in the tree.
     * @param key the item to search for.
     * @return true if the item is found and not deleted.
     * @return false if the item is not found or deleted.
     * @throws IllegalArgumentException if the key is not valid
     */
	public boolean contains(int key)
	{
		if(key < 1 || key > 99)
			throw new IllegalArgumentException("Invalid key. Valid set of keys is in the range of 1 - 99");
		return contains( key, root );
	}

	/**
     * Internal method to find an item in a subtree.
     * @param key the item to search for.
     * @param t the node that roots the subtree.
     * @return true if the item is found and not deleted.
     * @return false if the item is not found or deleted.
     */
	private boolean contains( int key, TreeNode t )
	{
		if( t == null )
			return false;

		if(	key < t.key )
			return contains( key, t.leftChild );
		else if( key > t.key )
			return contains( key, t.rightChild );
		else if (key == t.key && t.deleted == true) //the key found was deleted
			return false; 
		else 
			return true;    // key found is not deleted
	}

	/**
     * Test if the tree is empty.
     * @return true if empty, false otherwise.
     */
	public boolean isEmpty( )
	{
		return root == null;
	}

	 /**
     * Method to contain the key values in pre-order traversal including the deleted element. Deleted element should be preceded by a single asterisk. 
     * @return a string that contains all the key values using pre-order traversal. Return an empty tree message if the tree is empty.
     */
	public String toString( )
	{
		String output = "";
		if( isEmpty( ) )
			output = "Empty tree";
		else
		{
			output = toString( root, output);
		}
		
		return output;
	}

	 /**
     * Internal method to perform pre-order traversal of the tree including the deleted element. Deleted element should be preceded by a single asterisk. 
     * Every pair of adjacent elements should be separated by whitespace in the printing.
     * @param t the node that roots the subtree.
     * @param temp a variable to store the key values into a string
     * @return a string that contains all the key values using pre-order traversal
     */
	private String toString( TreeNode t, String temp)
	{
		if(t.deleted == true)
		{
			temp = "*" + String.valueOf(t.key) + " ";
		}
		
		else 
		{
			temp = String.valueOf(t.key) + " ";
		}
		
		if( t.leftChild != null )
		{
			temp =  temp + toString(t.leftChild, temp);
		}
		
		if(t.rightChild != null)
		{
			temp =  temp + toString(t.rightChild, temp);
		}
		
		return temp;
	}
	
	/**
     * Method to return the count of elements in the tree including deleted elements.
     * @return the count of the tree
     */
	public int size()
	{
		return count;
	}

	/**
     * Method to return the height of the tree including deleted elements.
     * @return the height of the tree
     */
	public int height()
	{
		return height(root);
	}
	
	/**
     * Internal method to compute height of a subtree including deleted elements.
     * @param t the node that roots the subtree.
     * @return the height of the tree, -1 if the tree is empty
     */
	private int height(TreeNode t)
	{
		if( t == null )
			return -1;
		else
			return 1 + Math.max( height( t.leftChild ), height( t.rightChild ) );    
	}


	//nested tree node class
	private static class TreeNode
	{
		public int key;  //the data in the node
		public TreeNode leftChild; //the left child
		public TreeNode rightChild;	//the right child
		public boolean deleted;	//flag to indicate if the key is deleted

		/**
	     * Constructor that take 4 arguments to initialize the attributes.
	     * @param num the data.
	     * @param left the left child 
	     * @param right	the right child
	     * @param del value to indicate if the key is deleted
	     */
		public TreeNode( int num, TreeNode left, TreeNode right, boolean del) 
		{
			key = num; leftChild = left; rightChild = right; deleted = del;
		}

	}

}
