package ETU1950.framework;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    String vue;
    HashMap<String,Object> data;

    public ModelView() {

    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public String getVue() {
        return vue;
    }

    public void setVue(String vue) {
        this.vue = vue;
    }

    public ModelView(String vue) {
        setVue(vue);
        setData(new HashMap<>());
    }
    public ModelView(String vue, HashMap<String  , Object > data)
    {
        setVue(vue);
        setData(data);
    }

    public void addItem(String key, Object value)
    {
        this.getData().put(key,value);
    }

    public void setAttributes (HttpServletRequest request){
        for (Map.Entry<String, Object> entry : this.getData().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            request.setAttribute(key,value);
        }
    }

}

