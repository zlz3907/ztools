/**
 * 
 */
package com.ztools.stringpro;

/**
 * @author Administrator
 * 
 */
public class TrieTest {

    private static final int ALPH = 26;
    private static final int BUFSIZ = 1024;
    private TrieTest[] children;
    private boolean isWord;

    /**
     * create a new Trie (characters a-z)
     */

    public TrieTest() {
        children = new TrieTest[ALPH];
        isWord = false;
    }

    /**
     * Add a string to the trie
     * 
     * @param s
     *            The string added to Trie
     */

    public void addString(String s) {
        TrieTest t = this;
        int k;
        int limit = s.length();
        for (k = 0; k < limit; k++) {
            int index = s.charAt(k) - 'a';
            if (t.children[index] == null) {
                t.children[index] = new TrieTest();
            }
            t = t.children[index];
        }
        t.isWord = true;
    }

    public void addCString(char s[]) {
        TrieTest t = this;
        int k = 0;
        while (s[k] != '\0') {
            int index = s[k] - 'a';
            if (t.children[index] == null) {
                t.children[index] = new TrieTest();
            }
            t = t.children[index];
            k++;
        }
        t.isWord = true;
    }

    /**
     * print every word in the trie, one per line
     * 
     */

    public void Print() {
        // DoPrint("",this);
        apply(new PrintRecorder());
    }

    public void apply(PrintRecorder rec) {
        char[] buffer = new char[BUFSIZ];
        doApply(rec, 0, buffer, this);
    }

    private void doApply(PrintRecorder rec, int index, char buffer[], TrieTest t) {
        if (t != null) {
            if (t.isWord) {
                rec.record(new String(buffer, 0, index));
            }
            int k;
            for (k = 0; k < ALPH; k++) {
                if (t.children[k] != null) {
                    buffer[index] = (char) (k + 'a');
                    doApply(rec, index + 1, buffer, t.children[k]);
                }
            }
        }
    }

    void DoPrint(String s, TrieTest t) // now superflous (see doApply)
    {
        if (t != null) {
            if (t.isWord) {
                System.out.println(s);
            }
            int k;
            for (k = 0; k < ALPH; k++) {
                if (t.children[k] != null) {
                    DoPrint(s + (char) (k + 'a'), t.children[k]);
                }
            }
        }
    }

    /**
     * determine if a word is in the trie (here or below)
     * 
     * @param s
     *            The string searched for
     * @return true iff s is in trie (rooted here)
     */

    public boolean isWord(String s) {
        TrieTest t = this;
        int k;
        int limit = s.length();
        for (k = 0; k < limit; k++) {
            int index = s.charAt(k) - 'a';
            if (t.children[index] == null)
                return false;
            t = t.children[index];
        }
        return t.isWord;
    }

    /**
     * @return true iff path from some root to this node is a word
     * 
     */

    public boolean isWord() {
        return isWord;
    }

    /**
     * @param ch
     *            Character used to index node (find child)
     * @return Trie formed from this by indexing using ch
     */

    TrieTest childAt(char ch) {
        return children[ch - 'a'];
    }

}

class PrintRecorder {
    public void record(Object o) {
        System.out.println(o);
    }

    public void report() {
        // nothing to do here
    }
}
