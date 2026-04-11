package Algo;

/*
 * Cloth Cut to Size: You have a large roll of cloth of N feet. The price of cloth is different for different lengths. 
 * You are given the list of prices for 1ft, 2ft, 3ft and so on till Nft. 
 * You are trying to maximize the profit you can make by possibly cutting the cloth to smaller pieces. 
 * For example, Input: 8,{0:0, 1:3, 2:7, 3:8, 4:9, 5:12, 6:17, 7:17, 8:20} => you have 8 ft of cloth, 
 * and the prices of cloth is given. 
 * 8ft of cloth is worth 20$, but if you cut it into two pieces of 6 and 2, it is worth 17$ + 7$ = 24$.
 */

public class CutACloth {

	int optimalPrize[];
	int size;
	
	CutACloth(){
		size = 8;
		optimalPrize = new int[size+1];
	}
	CutACloth(int size){
		this.size = size;
		optimalPrize = new int[size+1];
	}
	
	
	//Iterative bottom up approach using array
	//Complexity O(n2)
	
	public int maxProfitItBottomUp(int size, int arPrize[]){
		
		for(int i=2;i<=size;i++){
			for(int j=i; j>0; j--){				
				int currPrize = arPrize[j]+arPrize[i-j];
				arPrize[i]= Math.max(arPrize[i],currPrize);
			}
		}
		
		return arPrize[size];
	}
	
	
	//Simple Recursive
	public int maxProfitRecursive(int size, int arPrize[]) {
		
		int val = 0;
		
		if(size == 0){
			val = arPrize[0];
		}
		else if(size == 1){
			val = arPrize[1];
		}
		else if(size >= 2){
			int cost = arPrize[size];		
			
			for(int j=size-1; j>0; j--){				
				int currPrize = maxProfitRecursive(j,arPrize)+maxProfitRecursive(size-j,arPrize);
				cost = Math.max(cost,currPrize);
			}
			val = cost;
		}	
		return val;
	}
	
	//DP approach
		public int maxProfitDPMemoization(int size, int arPrize[]) {
			
			//we will not modify values for arPrize[0],arPrize[1],arPrize[2]
			//those are the base cases

			int ar[] = new int[size+1];
			
			for(int i=3; i<ar.length; i++){
				ar[i]=-1;
			}
			
			return maxProfitDP(size,ar,arPrize);
			
		}
		
		public int maxProfitDP(int size, int[] ar, int arPrize[]){
		
			if(ar[size] < 0){
				int cost = arPrize[size];					
				for(int j=size-1; j>0; j--){				
					int currPrize = maxProfitDP(j,ar,arPrize)+maxProfitDP(size-j,ar,arPrize);
					cost = Math.max(cost,currPrize);
				}
				ar[size]=1;
			}
			return arPrize[size];
			
		}
	
	
	public static void main(String args[]){
		
		CutACloth c = new CutACloth();
		int[] prize = {0,3,7,8,9,12,17,17,20};
		int size = 8;
		System.out.println("\n");
		
		System.out.println("Optimized prize with max profit for size "+size+" using:");
		System.out.println("\nIterative bottom up approach -> "+c.maxProfitItBottomUp(size,prize));
		System.out.println("\nSimple Recursive approach -> "+c.maxProfitRecursive(size,prize));
		System.out.println("\nDP Memoization approach -> "+c.maxProfitDPMemoization(size,prize));
		
		
	}
}

/*
 * 
0: 0 : 0
1: 3: 3
2: 7 : 7
3: 8:  10
4: 9 : 14
5: 12: 17
6: 17:  21
7: 17 : 24
8: 20 : 28
 */
