package de.devofvictory.utils;

import de.devofvictory.LineupHelper;
import de.devofvictory.objects.Lineup;
import de.devofvictory.objects.enums.ValoAbility;
import de.devofvictory.objects.enums.ValoAgent;
import de.devofvictory.objects.enums.ValoMap;
import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LineupManager {

    @Getter
    private static Lineup activeLineup = null;

    @Getter
    private static final List<Lineup> registeredLineups = new ArrayList<>();

    public static void setActiveLineup(Lineup activeLineup) {
        LineupManager.activeLineup = activeLineup;
        lineupState = 0;
        FrameManager.setCurrentState(0);
        FrameManager.setLineup(activeLineup);

    }

    @Getter
    private static int lineupState = 0;


    public static void nextState() {
        lineupState ++;

        if (lineupState == 3)
            lineupState = 0;

        FrameManager.setCurrentState(lineupState);

        System.out.println("Switched to state " + lineupState);
    }

    public static void registerLineups() {
        Yaml yaml = new Yaml();
        InputStream inputStream = LineupHelper.class
                .getClassLoader()
                .getResourceAsStream("lineups.yaml");

        Map<Integer, Object> obj = yaml.load(inputStream);
        System.out.println("Loaded lineups: " + obj);

        int index = 0;
        while (obj.containsKey(index)) {

            Map<String, Object> lineupMap = (Map<String, Object>) obj.get(index);
            Lineup lineup = new Lineup();

            lineup.setName((String) lineupMap.get("name"));
            lineup.setAuthor((String) lineupMap.get("author"));
            lineup.setDescription((String) lineupMap.get("description"));
            lineup.setMap(ValoMap.valueOf((String) lineupMap.get("map")));
            lineup.setAgent(ValoAgent.valueOf((String) lineupMap.get("agent")));
            lineup.setAbility(ValoAbility.valueOf((String) lineupMap.get("ability")));

            lineup.setFeetPosX(OtherUtils.getNestedValue(lineupMap, "positions", "feet", "x"));
            lineup.setFeetPosY(OtherUtils.getNestedValue(lineupMap, "positions", "feet", "y"));

            lineup.setTargetPosX(OtherUtils.getNestedValue(lineupMap, "positions", "target", "x"));
            lineup.setTargetPosY(OtherUtils.getNestedValue(lineupMap, "positions", "target", "y"));

            lineup.setFeetImageUrl(OtherUtils.getNestedValue(lineupMap, "overlays", "feet"));
            lineup.setCrosshairImageUrl(OtherUtils.getNestedValue(lineupMap, "overlays", "crosshair"));

            registeredLineups.add(lineup);
            FrameManager.addLineup(lineup);

            System.out.println("Lineup added: " + lineup);
            index ++;
        }
    }

}
