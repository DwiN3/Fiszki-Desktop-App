package Other;


public class TokenInstance {
    private static String userName ="", token="";
    private static TokenInstance instance = null;

    public static TokenInstance getInstance() {
        if (instance == null) {
            instance = new TokenInstance();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }

    public void setUserName(String userName) {
        TokenInstance.userName = userName;
    }

    public void setToken(String token) {
        TokenInstance.token = token;
    }
}
