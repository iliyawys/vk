/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.models;
/**
 *
 * @author dev
 */
public class Users {
    private String name;
    private String link;
    private int online;
    private int id;

    public Users(String name, String link, int online, int id){
        this.name = name;
        this.link = link;
        this.online = online;
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the online
     */
    public int getOnline() {
        return online;
    }

    /**
     * @param online the online to set
     */
    public void setOnline(int online) {
        this.online = online;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
