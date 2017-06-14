package chapter1.entity;

import java.util.List;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-14 11:56 AM
 */
public class Word {

    private String word;

    private List<WordIndex> wordIndexList;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<WordIndex> getWordIndexList() {
        return wordIndexList;
    }

    public void setWordIndexList(List<WordIndex> wordIndexList) {
        this.wordIndexList = wordIndexList;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", wordIndexList=" + wordIndexList +
                '}';
    }
}
