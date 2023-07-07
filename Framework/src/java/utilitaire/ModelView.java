/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitaire;

import java.util.HashMap;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabien
 */

public class ModelView {
    
    String View = "index.jsp";
    HashMap<String, Object> data = new HashMap<>();
    HashMap<String, Object> sessions = new HashMap<>();

    public void addSession(String key, Object value){
        this.getSessions().put(key, value);
    }
    
    public HashMap<String, Object> getSessions() {
        return sessions;
    }

    public void setSessions(HashMap<String, Object> sessions) {
        this.sessions = sessions;
    }

    public void addItem(String key, Object data) {
        this.getData().put(key, data);
    }
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    
    public HashMap<String, Object> getData() {
        return data;
    }
    
    public void setView(String View) {
        this.View = View;
    }

    public String getView() {
        return View;
    }
}
