package Other;

public class GameSettingsInstance {
    private static String category ="", typeGame="";
    private static int scoreWords=0, allWords=0, pointsForGame=0, bestTrain = 0;
    private static GameSettingsInstance instance = null;
    public static GameSettingsInstance getInstance() {
        if (instance == null) {
            instance = new GameSettingsInstance();
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
        GameSettingsInstance.category = category;
    }
    public void setScoreWords(int scoreWords) {
        GameSettingsInstance.scoreWords = scoreWords;
    }
    public void setAllWords(int allWords) {
        GameSettingsInstance.allWords = allWords;
    }
    public void setPointsForGame(int pointsForGame) { GameSettingsInstance.pointsForGame = pointsForGame; }
    public void setTypeGame(String typeGame) {
        GameSettingsInstance.typeGame = typeGame;
    }

    public int getBestTrain() {
        return bestTrain;
    }

    public void setBestTrain(int bestTrain) {
        GameSettingsInstance.bestTrain = bestTrain;
    }
}
