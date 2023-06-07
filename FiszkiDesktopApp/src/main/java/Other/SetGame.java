package Other;


import Retrofit.Models.FlashcardID;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetGame {
    private DateInstance dateInstance = DateInstance.getInstance();
    private String[] NameWord, correctANS, sentense, sentenseTra;
    private String[] ans1, ans2, ans3, ans4;
    private int listSize = 0, borrder = 0;
    private ArrayList<FlashcardID> wordsList;

    public SetGame(String data, String mode, String language, ArrayList<FlashcardID> wordsListAll) {
        borrder = dateInstance.getBorderMaxFlashcards();
        if (mode.equals("quiz")) {
            Random randomWords = new Random();
            Set<Integer> selectedIndices = new HashSet<>();
            ArrayList<FlashcardID> selectedWords = new ArrayList<>();
            if (wordsListAll.size() <= borrder) borrder = wordsListAll.size();

            while (selectedIndices.size() < borrder) {
                int randomIndex = randomWords.nextInt(wordsListAll.size());

                if (!selectedIndices.contains(randomIndex)) {
                    selectedIndices.add(randomIndex);
                    selectedWords.add(wordsListAll.get(randomIndex));
                }
            }
            wordsList = selectedWords;
        } else wordsList = wordsListAll;

        this.NameWord = new String[wordsList.size()];
        this.correctANS = new String[wordsList.size()];
        this.sentense = new String[wordsList.size()];
        this.sentenseTra = new String[wordsList.size()];
        this.ans1 = new String[wordsList.size()];
        this.ans2 = new String[wordsList.size()];
        this.ans3 = new String[wordsList.size()];
        this.ans4 = new String[wordsList.size()];

        Random random = new Random();
        for (int i = 0; i < wordsList.size(); i++) {
            if (language.equals("pl")) {
                NameWord[i] = wordsList.get(i).getWord();
                correctANS[i] = wordsList.get(i).getTranslatedWord();
            } else {
                NameWord[i] = wordsList.get(i).getTranslatedWord();
                correctANS[i] = wordsList.get(i).getWord();
            }
            sentense[i] = wordsList.get(i).getExample();
            sentenseTra[i] = wordsList.get(i).getTranslatedExample();

            if (mode.equals("quiz")) {
                Set<String> uniqueWords = new HashSet<>();
                uniqueWords.add(correctANS[i]);

                while (uniqueWords.size() < 4) {
                    int randomIndex = random.nextInt(wordsList.size());
                    String randomWord;
                    if (language.equals("pl")) randomWord = wordsList.get(randomIndex).getTranslatedWord();
                    else randomWord = wordsList.get(randomIndex).getWord();
                    uniqueWords.add(randomWord);
                }

                String[] uniqueWordsArray = uniqueWords.toArray(new String[0]);
                ans1[i] = uniqueWordsArray[0];
                ans2[i] = uniqueWordsArray[1];
                ans3[i] = uniqueWordsArray[2];
                ans4[i] = uniqueWordsArray[3];
            }
        }

        listSize = wordsList.size();
    }

    public String getNameWord(int i) {
        return NameWord[i];
    }

    public String getAns1(int i) {
        return ans1[i];
    }

    public String getAns2(int i) {
        return ans2[i];
    }

    public String getAns3(int i) {
        return ans3[i];
    }

    public String getAns4(int i) {
        return ans4[i];
    }

    public String getCorrectANS(int i) {
        return correctANS[i];
    }

    public String getSentense(int i) {
        return sentense[i];
    }

    public String getSentenseTra(int i) {
        return sentenseTra[i];
    }

    public int getListSize() {
        return listSize;
    }

    public int getBorrder() {
        return borrder;
    }

}