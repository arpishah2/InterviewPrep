package StringProblems;/*
 * Given a string, print all the rearrangements of letters, keeping the vowels together.
void printArrangementsVowelsTogether(String word)
Note: If the question were, keep the consonants together is that the same?
 */

public class RearrangementKeepingVowelTogether {

    /*
    public String[] rearrange(String word){

        ArrayList<String> result;

        if(word.length() == 1)
            return result.add(word);
        else{
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                String remaining1 = word.substring(0, i);
                String remaining2 = word.substring(i+1,word.length());
                result =  c + "" + this.rearrange(remaining1.concat(remaining2));
            }
        }

        return result;

    }
    */
    public static void main(String[] args) {
        String input = "abcd";
        RearrangementKeepingVowelTogether r = new RearrangementKeepingVowelTogether();
        r.rearrangements(input);

    }

    public void rearrangements(String word) {

        for (int i = 0; i < word.length(); i++) {

            //char c = word.charAt(i);
            //String remaining1 = word.substring(0, i);
            //String remaining2 = word.substring(i+1,word.length());
            //System.out.println(c + "" + this.rearrange(remaining1.concat(remaining2)));
        }
    }

}
