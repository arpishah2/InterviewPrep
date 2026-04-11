package Algo;/*
Paving a Path: 
Assume you are given tiles that are 1ft x 2ft in dimension. You need to lay them out in a 2ft wide path of length 100ft. 
In how many different ways can you accomplish this?
In the simplest case: if you were supposed to pave a 2ftx2ft path, you can lay the tiles horizontally (one on top of another),
or vertically (side by side), so two ways.
See at the bottom for more details.
*/

import java.math.BigInteger;

public class PavingPath {

BigInteger arr[]; //store no of ways
int no; //stores no of tiles
	
	PavingPath(){	
		this.no = 100;
		this.arr = new BigInteger[this.no+1];
	}
	
	PavingPath(int number){	
		this.no = number;
		this.arr = new BigInteger[number+1];
	}
	
	public BigInteger waysIterativeDP(int noOfTiles){ 		//DP using -> Iterative bottom up construction
		//noOfTiles = this.no;
		arr[0] = BigInteger.valueOf(0);
		arr[1] = BigInteger.valueOf(1);
		arr[2] = BigInteger.valueOf(2);
		
		for(int i=3; i<=noOfTiles; i++){
			arr[i] = arr[i-1].add(arr[i-2]);
			//System.out.println("ways("+i+") : "+arr[i]);
		}
		return arr[noOfTiles];
	}
	
	public BigInteger waysRecursion(int noOfTiles){ 		//DP using -> Iterative bottom up construction
		//noOfTiles = this.no;
		
		if (noOfTiles == 0){
			return BigInteger.valueOf(0);
		}
		else if (noOfTiles == 1){
			return BigInteger.valueOf(1);
		}
		else if (noOfTiles == 2){
			return BigInteger.valueOf(2);
		}
		
		return waysRecursion(noOfTiles-1).add(waysRecursion(noOfTiles-2));
	}
	
	public BigInteger waysDPMemoization(int noOfTiles){ 		//DP using -> Iterative bottom up construction
	
		arr[0] = BigInteger.valueOf(0);
		arr[1] = BigInteger.valueOf(1);
		arr[2] = BigInteger.valueOf(2);
		
		for(int i=3; i<=noOfTiles; i++){
			arr[i] = BigInteger.valueOf(-1);		
		}	
		
		return waysDPM2(arr,noOfTiles);
	}
	
	public BigInteger waysDPM2(BigInteger[] arr1, int noOfTiles){ 		//DP using -> Iterative bottom up construction	
		
		if(arr1[noOfTiles].compareTo(BigInteger.valueOf(0)) == -1){ //if not computed
			//noOfTiles < 0
			arr1[noOfTiles]= waysDPM2(arr1,noOfTiles-1).add(waysDPM2(arr1,noOfTiles-2));
		}

		return arr1[noOfTiles];
	}
	
	public void printStructure(int arr1[][]){
		
		for(int i=0; i< arr1.length; i++){
			for(int j=0; j<arr1[i].length; j++){
				System.out.println(arr1[i][j]);
			}
		}	
	}	
	
	public static void main(String args[]){
		
		int no = 100;
		PavingPath pp = new PavingPath(100);
		
		System.out.println("Total no of ways to pave the path for "+no+" tiles using:");
		System.out.println("\nIterative bottom up approach -> "+pp.waysIterativeDP(10));
		System.out.println("\nSimple Recursive approach -> "+pp.waysRecursion(10));
		System.out.println("\nDP Memoization approach -> "+pp.waysDPMemoization(10));
		
	}
}


/*
Q: PavePath(1)
A: This can be done in 1 way(s)
Solution 1:
1

1Q: PavePath(2)
A: This can be done in 2 way(s)
Solution 1:
12
12
Solution 2:
11
22

Q: PavePath(3)
A: This can be done in 3 ways
Solution 1:
123
123
Solution 2:
122
133
Solution 3:
113
223


--solution--
Goal: find a way to arrange n bricks
Find mathematical formulae (recursive soln) that uses smaller value
Each individual element should be discrete group

Generate 2XN 
Orientation of last 2 bricks?
||
=

ways(n) = ways(n-1)+ways(n-2)
Use all ways of n-2, put horizontally 2 bricks over other = solved for n
Use ways of n-1, put 1 vertically = solved for n

 */
