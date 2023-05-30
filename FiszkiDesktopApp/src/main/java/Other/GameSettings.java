package Other;

public class GameSettings {
    private static String category ="", typeGame="";
    private static int scoreWords=0, allWords=0, pointsForGame=0;
    private static GameSettings instance = null;
    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }
    public String getCategory() { return category; }
    public String getTypeGame() { return typeGame; }
    public int getScoreWords() {
        return scoreWords;
    }
    public int getAllWords() { return allWords; }
    public int getPointsForGame() { return pointsForGame; }
    public void setCategory(String category) {
        GameSettings.category = category;
    }
    public void setScoreWords(int scoreWords) {
        GameSettings.scoreWords = scoreWords;
    }
    public void setAllWords(int allWords) {
        GameSettings.allWords = allWords;
    }
    public void setPointsForGame(int pointsForGame) { GameSettings.pointsForGame = pointsForGame; }
    public void setTypeGame(String typeGame) {
        GameSettings.typeGame = typeGame;
    }
}
