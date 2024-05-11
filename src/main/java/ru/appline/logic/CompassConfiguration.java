package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CompassConfiguration implements Serializable {

    private static final CompassConfiguration instance = new CompassConfiguration();

    private Map<String, String> directions;

    private CompassConfiguration() {
        directions = new HashMap<>();

//        directions.put("North", "316-45");
//        directions.put("East", "46-135");
//        directions.put("South", "136-225");
//        directions.put("West", "226-315");
    }

    public static CompassConfiguration getInstance(){
        return instance;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    public void setDirections(Map<String, String> directions) {
        for (Map.Entry<String, String> entry : directions.entrySet()) {
            this.directions.put(entry.getKey(), entry.getValue());
        }
    }
}

