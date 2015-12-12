/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author dev
 */
public class Message {

    private Integer id;
    private String body;
    private Integer uid;
    private Integer read_state;
    private Integer date;
    private Integer out;

    public Message(String body, Integer uid, Integer read_state, Integer date, Integer out) {
        this.body = body;
        this.uid = uid;
        this.read_state = read_state;
        this.date = date;
        this.out = out;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return the read_state
     */
    public Integer getRead_state() {
        return read_state;
    }

    /**
     * @param read_state the read_state to set
     */
    public void setRead_state(Integer read_state) {
        this.read_state = read_state;
    }

    /**
     * @return the date
     */
    public Integer getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     * @return the out
     */
    public Integer getOut() {
        return out;
    }

    /**
     * @param out the out to set
     */
    public void setOut(Integer out) {
        this.out = out;
    }

    public String getFrom() {
        if (this.out == 1) {
            return "<<<<";
        }
        return ">>>>";
    }

    public String getFormatedDate() {
        Date d = new Date(this.date * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
//sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        return sdf.format(d);
    }
}
