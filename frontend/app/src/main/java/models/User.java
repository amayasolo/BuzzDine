package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user in the Scalar App
 * Author: A'maya
 */
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private static Set<User> users = new HashSet<User>();

    /**
     * Creates a new user in Scalar
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        users.add(this);
    }

    /**
     * Gets first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * RETURNS ALL USERS IN APP
     * @return user set
     */
    public static Set<User> getUsers() {
        return users;
    }

}

