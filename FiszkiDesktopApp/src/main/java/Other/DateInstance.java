package Other;

public class DateInstance {
    private static DateInstance instance = null;

    // Token
    private static String userName ="", token="";

    // Game
    private static String gameMode = "", name = "", language ="", selectData ="";
    private static int bestTrain = 0, points=0, allWords=0, borderMaxFlashcards =15, borderMinFlashcardQuiz=10;


    public static DateInstance getInstance() {
        if (instance == null) {
            instance = new DateInstance();
        }
        return instance;
    }


    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        DateInstance.gameMode = gameMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameCategory) {
        DateInstance.name = nameCategory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        DateInstance.language = language;
    }

    public String getSelectData() {
        return selectData;
    }

    public void setSelectData(String selectData) {
        DateInstance.selectData = selectData;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        DateInstance.points = points;
    }

    public int getAllWords() {
        return allWords;
    }

    public void setAllWords(int allWords) {
        DateInstance.allWords = allWords;
    }

    public int getBestTrain() {
        return bestTrain;
    }

    public void setBestTrain(int bestTrain) {
        DateInstance.bestTrain = bestTrain;
    }

    public int getBorderMaxFlashcards() {
        return borderMaxFlashcards;
    }

    public void setBorderMaxFlashcards(int borderMaxFlashcards) {
        DateInstance.borderMaxFlashcards = borderMaxFlashcards;
    }

    public int getBorderMinFlashcardQuiz() {
        return borderMinFlashcardQuiz;
    }

    public void setBorderMinFlashcardQuiz(int borderMinFlashcardQuiz) {
        DateInstance.borderMinFlashcardQuiz = borderMinFlashcardQuiz;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }

    public void setUserName(String userName) {
        DateInstance.userName = userName;
    }

    public void setToken(String token) {
        DateInstance.token = token;
    }
}
