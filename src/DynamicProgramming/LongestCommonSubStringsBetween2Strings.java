package DynamicProgramming;

import java.util.ArrayList;

/*
 * Given two strings S1 and S2. Find the longest common substring between S1 and S2. 
Example: S1 = "LCLC"  S2 = "CLCL"  LCS = "CLC"

This is solved using Dynamic Programming

 */
public class LongestCommonSubStringsBetween2Strings {

    public static void main(String[] args) {
        LongestCommonSubStringsBetween2Strings lcs = new LongestCommonSubStringsBetween2Strings();
        ArrayList<String> results = lcs.lcsBetween2SubString("abcdaf", "zbcdf");
        System.out.println("LCS between strings abcdaf and zbcdf is" + results);
        ArrayList<String> results2 = lcs.lcsBetween2SubString("lclc", "clcl");
        System.out.println("LCS between strings lclc and clcl is" + results2);
    }

    public ArrayList<String> lcsBetween2SubString(String s1, String s2) {

        int len1 = s1.length();
        int len2 = s2.length();

        Integer[][] match = new Integer[len1][len2];
        int max = Integer.MIN_VALUE;
        ArrayList<String> results = new ArrayList<String>();


        //traverse over both the strings and populate the match 2d array
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {

                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0)
                        match[i][j] = 1;
                    else
                        match[i][j] = match[i - 1][j - 1] + 1;


                    if (match[i][j] > max) {
                        max = match[i][j];
                        results = new ArrayList<String>();
                        results.add(s1.substring(i - max + 1, i + 1));
                    } else if (match[i][j] == max) {
                        results.add(s1.substring(i - max + 1, i + 1));
                    }
                } else {
                    match[i][j] = 0;
                }
            }
        }

        return results;
    }

}
