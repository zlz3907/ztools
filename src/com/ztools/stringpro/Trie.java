package com.ztools.stringpro;

import java.util.List;

public interface Trie {
    public void addWord(String word);

    public void removeWord(String word);

    public boolean isWord(String word);

    public List<String> searchKey(String key);
}
