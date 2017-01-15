import java.util.ArrayList;
import java.util.Arrays;


/**
 * This Solver class takes in values for an exising standard sudoku puzzle, and tries to find a solution if capable.
 * The existing values are passed in as a 3-D array 
 * (first index is the sub-square, from 0 to 8,from top left to top middle to top right)
 * (second index is the horizontal row within the sub-square, from 0 to 2) 
 * (third index is the vertical column within the sub-square, from 0 to 2)
 * (All possible values in the array are from 1 to 9)
 * This solver can only resolve cases where no trial-and-error is needed to solve the sudoku. Only one possible solution is assumed.
 * @author Shal Xu
 *
 */
public class Solver {
	private int[][][] state=new int[9][3][3];
	
	private Solver(int[][][] state){
		this.state=state;
	}
	
	private void solve(){
		boolean notSolved=true;
		while(notSolved){
			this.checkEach();
			notSolved=false;
			for(int a=0;a<9;a++){
				for(int b=0;b<3;b++){
					for(int c=0;c<3;c++){
						if(state[a][b][c]==-1)
							notSolved=true;
					}
				}
			}
		}
		System.out.println("Puzzle Solved");
		System.out.println(Arrays.deepToString(state));
	}
	
	private void checkEach(){
		for(int i=0;i<9;i++){
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					if (state[i][j][k]==-1){
						ArrayList<Integer> tempList=new ArrayList<Integer>();
						for(int a=1;a<=9;a++){
							tempList.add(new Integer(a));
						}
						for(int a=i%3;a<9;a+=3){
							for(int b=0;b<3;b++){
								tempList.remove(new Integer(state[a][b][k]));
							}
						}
						for(int a=i/3*3;a<i/3*3+3;a++){
							for(int b=0;b<3;b++){
								tempList.remove(new Integer(state[a][j][b]));
							}
						}
						for(int a=0;a<3;a++){
							for(int b=0;b<3;b++){
								tempList.remove(new Integer(state[i][a][b]));
							}
						}

						if(tempList.size()==1){
							state[i][j][k]=tempList.get(0);
							System.out.println("Solved at position "+i+j+k+" to "+tempList.get(0));
							return;
						}
					}
				}
			}
		}
		System.out.println("Can no longer proceed with obvious answers");
	}
	

	public static void main(String[] args) {
		
		/**Test with one existing puzzle**/
		int[][][] num = 
		    {  { {5,3,-1}, {6,-1,-1}, {-1,9,8} },
		       { {-1,7,-1}, {1,9,5}, {-1,-1,-1} },
		       { {-1,-1,-1}, {-1,-1,-1}, {-1,6,-1} },
		       { {8,-1,-1}, {4,-1,-1}, {7,-1,-1} },
		       { {-1,6,-1}, {8,-1,3}, {-1,2,-1} },
		       { {-1,-1,3}, {-1,-1,1}, {-1,-1,6} },
		       { {-1,6,-1}, {-1,-1,-1}, {-1,-1,-1} },
		       { {-1,-1,-1}, {4,1,9}, {-1,8,-1} },
		       { {2,8,-1}, {-1,-1,5}, {-1,7,9} }};
		Solver solver=new Solver(num);
		solver.solve();

	}

}
