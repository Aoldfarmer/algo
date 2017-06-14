package chapter1.execsise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chapter1.entity.Word;
import chapter1.entity.WordIndex;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-12 6:01 PM
 */
public class Execsice02 {

    private static final Random RANDOM = new Random(47);

    private static final String WORDS[] = {"xxxsw", "fsa", "terry", "bki", "sdsaf"};

    public static char[][] createDictionary(int length) {
        char[][] dictionary = new char[length][length];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                dictionary[i][j] = (char) (97 + RANDOM.nextInt(26));
        return dictionary;
    }

    //每个单词有八个方向，从第一个字母算起（逆时针为1-8）, i 行， j列
    public static Word getWord(char[][] dic, int i, int j, int dir) {
        int length = dic.length;
        Word word = new Word();
        switch (dir) {
            case 1:
                StringBuffer sb1 = new StringBuffer();
                List<WordIndex> index1 = new ArrayList<>();
                for (int m = j; m < length; m++) {
                    setWordIndex(dic[i][m], i, m, sb1, index1);
                }
                word.setWord(sb1.toString());
                word.setWordIndexList(index1);
                break;
            case 2:
                StringBuffer sb2 = new StringBuffer();
                List<WordIndex> index2 = new ArrayList<>();
                do {
                    setWordIndex(dic[i][j], i, j, sb2, index2);
                } while (i-- != 0 && j++ != (length - 1));
                word.setWord(sb2.toString());
                word.setWordIndexList(index2);
                break;
            case 3:
                StringBuffer sb3 = new StringBuffer();
                List<WordIndex> index3 = new ArrayList<>();
                do {
                    setWordIndex(dic[i][j], i, j, sb3, index3);
                } while (i-- != 0);
                word.setWord(sb3.toString());
                word.setWordIndexList(index3);
                break;
            case 4:
                StringBuffer sb4 = new StringBuffer();
                List<WordIndex> index4 = new ArrayList<>();
                do {
                    setWordIndex(dic[i][j], i, j, sb4, index4);
                } while (i-- != 0 && j-- !=0);
                word.setWord(sb4.toString());
                word.setWordIndexList(index4);
                break;
            case 5:
                StringBuffer sb5 = new StringBuffer();
                List<WordIndex> index5 = new ArrayList<>();
                do {
                    setWordIndex(dic[i][j], i, j, sb5, index5);
                } while (j-- != 0);
                word.setWord(sb5.toString());
                word.setWordIndexList(index5);
                break;
            case 6:
                StringBuffer sb6 = new StringBuffer();
                List<WordIndex> index6 = new ArrayList<>();
                do {
                    setWordIndex(dic[i][j], i, j, sb6, index6);
                } while (j-- != 0 && i++ != length - 1);
                word.setWord(sb6.toString());
                word.setWordIndexList(index6);
                break;
            case 7:
                StringBuffer sb7 = new StringBuffer();
                List<WordIndex> index7 = new ArrayList<>();
                do {
                    setWordIndex(dic[i][j], i, j, sb7, index7);
                } while (i++ != length - 1);
                word.setWord(sb7.toString());
                word.setWordIndexList(index7);
                break;
            case 8:
                StringBuffer sb8 = new StringBuffer();
                List<WordIndex> index8 = new ArrayList<>();
                do {
                    setWordIndex(dic[i][j], i, j, sb8, index8);
                } while (j++ != length - 1 && i++ != length - 1);
                word.setWord(sb8.toString());
                word.setWordIndexList(index8);
                break;
        }
        return word;
    }

    private static void setWordIndex(char c, int i, int j, StringBuffer sb4, List<WordIndex> index4) {
        sb4.append(c);
        WordIndex wordIndex = new WordIndex();
        wordIndex.setX(i);
        wordIndex.setY(j);
        index4.add(wordIndex);
    }

    public static List<Word> getWordsList(char[][] dic, String[] words) {

        List<Word> wordList = new ArrayList<>();
        for (String word: words) {
            char firstChar = word.charAt(0);
            for (int i = 0; i < dic.length; i++) {
                for (int j = 0; j < dic.length; j++) {
                    if (dic[i][j] == firstChar) {
                        Word wordDir1 = getWord(dic, i ,j , 1);
                        if (wordDir1.getWord().contains(word)) {
                            wordDir1.setWord(word);
                            wordDir1.setWordIndexList(
                                        wordDir1.getWordIndexList().subList(0, word.length())
                            );
                            wordList.add(wordDir1);
                        }
                        Word wordDir2 = getWord(dic, i ,j , 2);
                        if (wordDir2.getWord().contains(word)) {
                            wordDir2.setWord(word);
                            wordDir2.setWordIndexList(
                                    wordDir2.getWordIndexList().subList(0, word.length())
                            );
                            wordList.add(wordDir2);
                        }
                        Word wordDir3 = getWord(dic, i ,j , 3);
                        if (wordDir3.getWord().contains(word)) {
                            wordDir3.setWord(word);
                            wordDir3.setWordIndexList(
                                    wordDir3.getWordIndexList().subList(0, word.length())
                            );
                            wordList.add(wordDir3);
                        }
                        Word wordDir4 = getWord(dic, i ,j , 4);
                        if (wordDir4.getWord().contains(word)) {
                            wordDir4.setWord(word);
                            wordDir4.setWordIndexList(
                                    wordDir4.getWordIndexList().subList(0, word.length())
                            );
                            wordList.add(wordDir4);
                        }
                        Word wordDir5 = getWord(dic, i ,j , 5);
                        if (wordDir5.getWord().contains(word)) {
                            wordDir5.setWord(word);
                            wordDir5.setWordIndexList(
                                    wordDir5.getWordIndexList().subList(0, word.length())
                            );
                            wordList.add(wordDir5);
                        }
                        Word wordDir6 = getWord(dic, i ,j , 6);
                        if (wordDir6.getWord().contains(word)) {
                            wordDir6.setWord(word);
                            wordDir6.setWordIndexList(
                                    wordDir6.getWordIndexList().subList(0, word.length())
                            );
                            wordList.add(wordDir6);
                        }
                        Word wordDir7 = getWord(dic, i ,j , 7);
                        if (wordDir7.getWord().contains(word)) {
                            wordDir7.setWord(word);
                            wordDir7.setWordIndexList(
                                    wordDir7.getWordIndexList().subList(0, word.length())
                            );
                            wordList.add(wordDir7);
                        }
                        Word wordDir8 = getWord(dic, i ,j , 8);
                        if (wordDir8.getWord().contains(word)) {

                            wordDir8.setWord(word);
                            wordDir8.setWordIndexList(
                                    wordDir8.getWordIndexList().subList(0, word.length())
                            );
                            wordList.add(wordDir8);
                        }
                    }
                }
            }
        }
        return wordList;
    }


    public static void main(String[] args) {
        char[][] dic = createDictionary(5000);
        List<Word> result = getWordsList(dic, WORDS);
        for (Word word: result) {
            System.out.println(word);
        }
    }

}
