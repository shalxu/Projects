import java.util.Arrays;


public class MemoryManager{
	
	public static void main (String[] args)
	{
		int[] list = new int[10]; 
		MemoryManager manager = new MemoryManager(list);
		manager.allocate(2);
		manager.push(1);
		//System.out.println("marker"+manager.stackSize);
		manager.allocate(3);
		for (int i=0; i< list.length;i++)
		  {
			  System.out.print(list[i]+",");
		  }
		  System.out.print("\n");
		  System.out.println(manager.stackSize);
		  System.out.println(manager.heapSize);
		  System.out.println(manager.heap.freeStart);
		manager.pop();
		manager.deallocate(1);
		for (int i=0; i< list.length;i++)
		  {
			  System.out.print(list[i]+",");
		  }
		  System.out.print("\n");
		  System.out.println(manager.stackSize);
		  System.out.println(manager.heapSize);
		  System.out.println(manager.heap.freeStart);
	}
	
	HeapManager heap;
	StackManager stack;
	int heapSize;
	int stackSize;
	int[] memory;
	public MemoryManager (int[] initialMemory){
		heapSize=0;
		stackSize=0;
		memory =initialMemory;
		heap=new HeapManager(memory, heapSize);
		heap.freeStart=heap.NULL;
		stack= new StackManager(memory,stackSize);
		stack.top=memory.length;
		
	}
	public int push (int requestSize){
		try{
			return(stack.push(requestSize));
		}
		catch(StackOverflowError e)
		{
			if(stackSize+requestSize+1+heapSize>10) throw new StackOverflowError(); 
			int temp=stack.top;
			stack=new StackManager(memory,((requestSize + 1)-(stackSize-(memory.length-stack.top))));
			stack.top=temp;
			stackSize=stackSize+(requestSize + 1)-(stackSize-(memory.length-stack.top));
			return(stack.push(requestSize));
		}
		
	}
	public void pop(){
		stack.pop();
	}
	public int allocate (int requestSize){
		try{
			return(heap.allocate(requestSize));
		}
		catch(OutOfMemoryError e)
		{
			if(stackSize+requestSize+1+heapSize>10) throw new StackOverflowError(); 
			int temp=heap.freeStart;
			heap=new HeapManager(memory,heapSize+requestSize+1);
			heap.freeStart=temp;
			int lag=heap.NULL;
			 int p = heap.freeStart;
			    while (p != heap.NULL && p < heapSize) {
			    lag=p;
			      p = memory[p + 1];
			    }
			    if(heapSize==0){
			    	 heap.freeStart = 0;
			    	 memory[0]=requestSize+1;
				     memory[1] = -1;
			    }
			    else if(p==heap.NULL) {
			    	if(lag!=heap.NULL&&lag+memory[lag]>=heapSize-1){
			    		memory[lag]+=requestSize+2-memory[lag];
			    	}
			    	else {
			    		if(lag!=heap.NULL) memory[lag+1]= heapSize;
			    		else heap.freeStart=heapSize;
			    		memory[heapSize] = requestSize+1;
			    		memory[heapSize+1]=-1;}
			    }
			    heapSize+=requestSize+1;
			    return(heap.allocate(requestSize));
		}
	}
	public void deallocate (int address){
		if(address<heapSize)
			heap.deallocate(address);
		else
			throw new IndexOutOfBoundsException();
	}
	public class HeapManager {
		  static private final int NULL = -1; // our null link
		  public int[] memory; // the memory we manage
		  private int freeStart; // start of the free list


		 
		  /**
		   * HeapManager constructor.
		   * @param initialMemory the int[] of memory to manage
		   */
		  public HeapManager(int[] initialMemory,int heapSize) {
		    memory = initialMemory;
		  }

		  /**
		   * Allocate a block and return its address.
		   * @param requestSize int size of block, > 0
		   * @return block address
		   * @throws OutOfMemoryError if no block big enough
		   */
		  public int allocate(int requestSize) {
		    int size = requestSize + 1; // size including header

		    // Do first-fit search: linear search of the free 
		    // list for the first block of sufficient size.

		    int p = freeStart; // head of free list
		    int lag = NULL;
		    while (p+size<heapSize && memory[p] < size) {
		      lag = p; // lag is previous p
		      p = memory[p + 1]; // link to next block
		    }
		    if (p==NULL) // no block large enough
		      throw new OutOfMemoryError();
		    int nextFree = memory[p + 1]; // block after p

		    // Now p is the index of a block of sufficient size,
		    // lag is the index of p's predecessor in the
		    // free list, or NULL, and nextFree is the index of
		    // p's successor in the free list, or NULL.

		    // If the block has more space than we need, carve
		    // out what we need from the front and return the
		    // unused end part to the free list.

		    int unused = memory[p]-size; // extra space in block
		    if (unused > 1) { // if more than a header's worth
		      nextFree = p + size; // index of the unused piece
		      memory[nextFree] = unused; // fill in size 
		      memory[nextFree+1] = memory[p+1]; // fill in link
		      memory[p] = size; // reduce p's size accordingly
		    }

		    // Link out the block we are allocating and done.

		    if (lag == NULL) freeStart = nextFree;
		    else memory[lag + 1] = nextFree;
		    return p+1; // index of useable word (after header)
		  }

		  /**
		   * Deallocate an allocated block.  This works only if
		   * the block address is one that was returned by
		   * allocate and has not yet been deallocated.
		   * @param address int address of the block
		   */
		  public void deallocate(int address) {
		    int addr = address - 1; // real start of the block

		    // Find the insertion point in the sorted free list
		    // for this block.

		    int p = freeStart;
		    int lag = NULL;
		    while (p != NULL && p < addr) {
		      lag = p;
		      p = memory[p + 1];
		    }

		    // Now p is the index of the block to come after
		    // ours in the free list, or NULL, and lag is the
		    // index of the block to come before ours in the
		    // free list, or NULL.

		    // If the one to come after ours is adjacent to it,
		    // merge it into ours and restore the property
		    // described above.

		    if (addr + memory[addr] == p) {
		      memory[addr] += memory[p]; // add its size to ours
		      p = memory[p + 1]; //
		    }

		    if (lag == NULL) { // ours will be first free
		      freeStart = addr;
		      memory[addr + 1] = p;
		    }
		    else if (lag+memory[lag]==addr) { // block before is
		                                   // adjacent to ours
		      memory[lag] += memory[addr]; // merge ours into it
		      memory[lag + 1] = p;
		    }
		    else { // neither, just a simple insertion
		      memory[lag + 1] = addr;
		      memory[addr + 1] = p;
		    }
		  }
	}
		  
		  public class StackManager {
			  private int[] memory; // the memory we manage
			  private int top; // index of top (lowest) stack block

			  /**
			   * StackManager constructor.
			   * @param initialMemory the int[] of memory to manage
			   */
			  public StackManager(int[] initialMemory,int stackSize) {
			    memory = initialMemory;
			  }

			  /**
			   * Allocate a block and return its address.
			   * @param requestSize int size of block, > 0
			   * @return block address
			   * @throws StackOverflowError if out of stack space
			   */
			  public int push(int requestSize) {
			    int oldtop = top;
			    if (top-(requestSize + 1)< memory.length-stackSize) throw new StackOverflowError();
			    top -=(requestSize + 1); // extra word for oldtop
			    memory[top] = oldtop;
			    return top + 1;
			  }

			  /**
			   * Pop the top stack frame.  This works only if the
			   * stack is not empty.
			   */
			  public void pop() {
			    top = memory[top];
			  }
			}
}
