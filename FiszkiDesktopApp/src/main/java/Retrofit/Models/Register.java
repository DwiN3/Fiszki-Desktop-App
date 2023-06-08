package Retrofit.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a registration request
 */
public class Register {
    private String nick,email,password, repeatedPassword, content;

    @SerializedName("body")
    private String text;

    /**
     * Constructs a Register object with only password and repeatedPassword
     * @param password          the password
     * @param repeatedPassword  the repeated password
     */
    public Register(String password, String repeatedPassword) {
        this.password = password;
        this.repeatedPassword = repeatedPassword;
    }

    /**
     * Constructs a Register object with all registration information
     * @param email             the email
     * @param password          the password
     * @param repeatedPassword  the repeated password
     * @param nick              the nickname
     */
    public Register(String email, String password, String repeatedPassword, String nick) {
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.nick = nick;
    }

    /**
     * Returns the nickname
     * @return the nickname
     */
    public String getNick() { return nick; }

    /**
     * Returns the email
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Returns the password
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Returns the repeated password
     * @return the repeated password
     */
    public String getRepeatedPassword() { return repeatedPassword; }

    /**
     * Returns the content
     * @return the content
     */
    public String getContent() { return content; }

    /**
     * Returns the text
     * @return the text
     */
    public String getText() { return text; }
}


