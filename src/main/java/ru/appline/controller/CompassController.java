package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.CompassConfiguration;

import java.util.Map;

@RestController
public class CompassController {

    private static final CompassConfiguration compassConfiguration = CompassConfiguration.getInstance();

    @PostMapping(value = "/configure", consumes = "application/json")
    public String configure(@RequestBody Map<String, String> configuration) {
        compassConfiguration.setDirections(configuration);
        return "Конфигурация установлена успешно!";
    }

    @GetMapping(value = "/getDirection", consumes = "application/json", produces = "application/json")
    public Map<String, String> getDirection(@RequestBody Map<String, Integer> requestDegree) {
        int degree = requestDegree.get("Degree");
        for (Map.Entry<String, String> entry : compassConfiguration.getDirections().entrySet()) {
            String[] range = entry.getValue().split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            if (start < end) {
                if (degree >= start && degree <= end) {
                    return Map.of("Side", entry.getKey());
                }
            } else {
                if ((degree >= start && degree <= 359) || (degree >= 0 && degree <= end)){
                    return Map.of("Side", entry.getKey());
                }
            }
        }
        return Map.of("Error", "Введено неверное число");
    }
}

