/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import GUI.Messager;
import GUI.models.Message;
import GUI.models.Users;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author dev
 */
public class API {

    private String login = "89087804913";
    private String password = "";
    private String access_token;
    private String email;
    private String uid;
    private String client_id = "2274003";
    private JSONArray users = null;
    private ArrayList<Message> messages;
    private ArrayList<Users> userList = new ArrayList<Users>();
    
    private static volatile API instance;

    public static API getInstance() {
        API localInstance = instance;
        if (localInstance == null) {
            synchronized (API.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new API();
                }
            }
        }
        return localInstance;
    }
    
    public boolean checkAuth() {
        this.Auth();
        return true;
    }

    public void Auth() {
        messages = new ArrayList<Message>();
        String result = API.excutePost("https://oauth.vk.com/token?"
                + "grant_type=password&"
                + "scope=notify,friends,photos,audio,video,docs,notes,pages,status,wall,groups,messages,notifications,stats,ads,offline,email&"
                + "client_id=2274003&"
                + "client_secret=hHbZxrka2uZ6jB1inYsH&"
                + "username="+this.login+"&password="+this.password+"&v=5.21", "");
        JSONObject rArray = new JSONObject(result);
        access_token = (String) rArray.get("access_token");
        email = (String) rArray.get("email");
        uid = rArray.get("user_id") + "";
    }

    public JSONArray getFriends() {
        String result = API.excutePost("https://api.vk.com/method/friends.get?fields=nickname,online&user_id=" + this.uid, "");
        JSONObject rArray = new JSONObject(result);
        setUsers((JSONArray) rArray.get("response"));
        return this.users;
    }

    private static String excutePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * @return the users
     */
    public JSONArray getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(JSONArray users) {
        this.users = users;
    }

    public void getMsg(int id) {
        String result = API.excutePost("https://api.vk.com/method/messages.getHistory?access_token=" + this.access_token + "&user_id=" + id, "");
        JSONObject rArray = new JSONObject(result);
        setMessages(rArray);
    }

    private void setMessages(JSONObject mArray) {
        messages = new ArrayList<Message>();
        /*refact!!!*/
        JSONObject v;
        JSONArray msgArray = (JSONArray) mArray.get("response");

        for (int i = 1; i < msgArray.length(); i++) {
            v = msgArray.getJSONObject(i);
            String body = (String) v.get("body");
            Integer Uid = (Integer) v.get("uid");
            Integer read_state = (Integer) v.get("read_state");
            Integer date = (Integer) v.get("date");            
            Integer out = (Integer) v.get("out");
            messages.add(new Message(body, Uid, read_state, date, out));
        }
        Messager.getInstance().updateMsg();
    }

    public ArrayList getMessage() {
        return this.messages;
    }

    /**
     * @return the userList
     */
    public ArrayList<Users> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(ArrayList<Users> userList) {
        this.userList = userList;
    }
}
