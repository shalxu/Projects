public class HeapManager {
  static private final int NULL = -1; // our null link
  public int[] memory; // the memory we manage
  private int freeStart; // start of the free list

  public static void main (String[] args){
	  int[] list = new int [7];
	  HeapManager mm= new HeapManager(list);
	  int a = mm.allocate (2);
	  int b = mm.allocate(1);
	  int c = mm.allocate(1);
	  System.out.print("\n");
	  mm.deallocate(a);
	  mm.deallocate(c);
	  for (int i=0; i< list.length;i++)
	  {
		  System.out.print(list[i]+",");
	  }
	  System.out.print("\n");
	  System.out.println(mm.freeStart);
	  a = mm.allocate (1);
	  c = mm.allocate(2);
	  for (int i=0; i< list.length;i++)
	  {
		  System.out.print(list[i]+",");
	  }
	  System.out.print("\n");
	  System.out.println(mm.freeStart);
  }
	  /*
	  int[] list = new int [11];
	  HeapManager mm=new HeapManager(list);
	  int a=mm.allocate(4);
	  int b=mm.allocate(1);
	  int c=mm.allocate(3);
	  mm.deallocate(a);
	  mm.deallocate(c);
	  for (int i=0; i< list.length;i++)
	  {
		  System.out.print(list[i]+",");
	  }
	  System.out.print("\n");
	  System.out.println(mm.freeStart);
	  a=mm.allocate(2);
	  for (int i=0; i< list.length;i++)
	  {
		  System.out.print(list[i]+",");
	  }
	  System.out.print("\n");
	  System.out.println(mm.freeStart);
	  c=mm.allocate(1);
	  int d=mm.allocate(3);
	  for (int i=0; i< list.length;i++)
	  {
		  System.out.print(list[i]+",");
	  }
	  System.out.print("\n");
	  System.out.println(mm.freeStart);
	  }*/
  
  /**
   * HeapManager constructor.
   * @param initialMemory the int[] of memory to manage
   */
  public HeapManager(int[] initialMemory) {
    memory = initialMemory;
    memory[0] = memory.length; // one big free block
    memory[1] = NULL; // free list ends with it
    freeStart = 0; // free list starts with it
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
    int tempAddress=NULL;
    int tempLag=NULL;
    while (p != NULL) {
      if (memory[p] >= size){
    	  if(tempAddress==NULL|(tempAddress!=NULL&&memory[p]<memory[tempAddress]))
    	  {
    		  tempLag=lag;
    		  tempAddress=p;
    		  // lag is previous p
    	  }
      }
    	  lag=p;
          p = memory[p + 1]; // link to next block
      }
    if (tempAddress == NULL) // no block large enough
      throw new OutOfMemoryError();
    p=tempAddress;
    lag=tempLag;
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
