package Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Write code to filter out "bad words" in a chat screen by replacing the characters in the word with asterisks.
 * The format of the string could be multiple words, spaces and periods between letters/words.
 * You have a list of "bad words"
 */
public class ReplaceCharWith_ {

    ArrayList<String> badwords;


    public ReplaceCharWith_() {
        TrieHM trie = new TrieHM();
        badwords = new ArrayList<String>();
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("Trie/NegWordsList.txt"));
            while ((sCurrentLine = br.readLine()) != null)
                trie.insert(sCurrentLine);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String args[]) {
        ReplaceCharWith_ obj = new ReplaceCharWith_();
    }
}
