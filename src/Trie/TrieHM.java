package Trie;

import java.util.HashMap;

/* Implement a trie with insert, search, and startsWith methods.
 *
 */

class TrieHMNode {
    char ch;
    HashMap<Character, TrieHMNode> children = new HashMap<Character, TrieHMNode>();
    boolean isEnd;

    public TrieHMNode() {
    }

    public TrieHMNode(char c) {
        ch = c;
    }
}

public class TrieHM {

    private TrieHMNode root;

    public TrieHM() {
        root = new TrieHMNode();
    }

    // Inserts a word into the TrieHM
    public void insert(String word) {
        HashMap<Character, TrieHMNode> children = root.children;

        //Loop through word and get the character, try to insert it
        //for first char in word, check if it exists on level 1(root.children). For second char, go to level2 by finding/creating char1 and searching its children and so on
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieHMNode t;
            if (children.containsKey(c))            //get the TrieHMNode if it exists
                t = children.get(c);
            else {
                t = new TrieHMNode(c);            //create the TrieHMNode and add it to the parent nodes children
                children.put(c, t);
            }
            children = t.children;                //update children since we move to next char in the word(to be inserted at next level)

            if (i == word.length() - 1)                //mark leaf
                t.isEnd = true;
        }
    }


    // Returns if the word is in the trie
    public boolean search(String word) {

        HashMap<Character, TrieHMNode> children = root.children;
        TrieHMNode curr = null;

        for (int i = 0; i < word.length(); i++) {                    //Check char by char if TrieHMNode(char) exits in TrieHM
            if (children.containsKey(word.charAt(i))) {
                curr = children.get(word.charAt(i));
                children = curr.children;
            } else
                return false;
        }
        if (curr.isEnd == true)    //if the word exists and TrieHMNode(last char) is the leaf node in TrieHM
            return true;
        else
            return false;
    }

}
