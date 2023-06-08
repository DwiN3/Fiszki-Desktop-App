package Retrofit.Models;

import com.google.gson.annotations.SerializedName;

public class Login {
    private String nick, password, content, token;

    @SerializedName("body")
    private String text;

    /**
     * Constructs a Login object
     * @param nick     the nickname
     * @param password the password
     */
    public Login(String nick, String password){
        this.nick = nick;
        this.password = password;
    }

    /**
     * Returns the token
     * @return the token
     */
    public String getToken() { return token; }

    /**
     * Returns the nickname
     * @return the nickname
     */
    public String getNick() { return nick; }

    /**
     * Returns the password
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Returns the content
     * @return the content
     */
    public String getContent() { return content; }

    /**
     * Returns the text
     * @return the text
     */
    public String getText() {
        return text;
    }
}
