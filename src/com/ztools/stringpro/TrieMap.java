/**
 * 
 */
package com.ztools.stringpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class TrieMap implements Trie {

    private char key;
    private boolean isWord;
    private HashMap<Character, TrieMap> childMap;

    private TrieMap parent;

    public TrieMap() {
        this.childMap = new HashMap<Character, TrieMap>();
        this.isWord = false;
        this.parent = null;
    }

    // private TrieMap

    /*
     * (non-Javadoc)
     * 
     * @see com.ztools.stringpro.Trie#addWord(java.lang.String)
     */
    public void addWord(String word) {
        TrieMap rm = this;

        int strLength = word.length();
        for (int i = 0; i < strLength; i++) {
            HashMap<Character, TrieMap> pMap = rm.getChildMap();
            Character key = word.charAt(i);
            TrieMap tmpTrie = pMap.get(key);
            if (null == tmpTrie) {
                tmpTrie = new TrieMap();
                tmpTrie.setParent(rm);
                tmpTrie.setKey(key);
                pMap.put(key, tmpTrie);
            }
            rm = tmpTrie;
        }
        rm.setWord(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ztools.stringpro.Trie#isWord()
     */
    public boolean isWord(String word) {
        if (null != word) {
            TrieMap t = this;
            int limit = word.length();
            for (int i = 0; i < limit; i++) {
                TrieMap temp = t.getChildMap().get(word.charAt(i));
                if (null == temp) {
                    return false;
                }
                t = temp;
            }
            return t.isWord;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ztools.stringpro.Trie#removeWord(java.lang.String)
     */
    public void removeWord(String word) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ztools.stringpro.Trie#searchKey(java.lang.String)
     */
    public List<String> searchKey(String key) {
        TrieMap tm = this;
        List<String> list = new ArrayList<String>();
        if (tm.isWord()) {
            String word = tm.toWord();
            if (null != word && 0 == word.indexOf(key)) {
                list.add(tm.toWord());
            }
        } else {
            HashMap<Character, TrieMap> sub = tm.getChildMap();
            for (TrieMap st : sub.values()) {
                list.addAll(st.searchKey(key));
            }
        }
        return list;
    }

    public String toWord() {
        if (this.isWord) {
            StringBuilder sbd = new StringBuilder();
            sbd.append(this.getKey());
            // System.out.println(this.getKey());
            TrieMap p = this.getParent();
            while (null != p) {
                char key = p.getKey();
                // System.out.println(">>" +(int)key + "<<");
                if (0 != key)
                    sbd.append(p.getKey());
                p = p.getParent();
            }
            // System.out.println(sbd.toString());
            return sbd.reverse().toString();
        }
        return null;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public HashMap<Character, TrieMap> getChildMap() {
        return childMap;
    }

    public void setChildMap(HashMap<Character, TrieMap> childMap) {
        this.childMap = childMap;
    }

    public void setWord(boolean isWord) {
        this.isWord = isWord;
    }

    public boolean isWord() {
        return isWord;
    }

    public TrieMap getParent() {
        return parent;
    }

    public void setParent(TrieMap parent) {
        this.parent = parent;
    }

}
