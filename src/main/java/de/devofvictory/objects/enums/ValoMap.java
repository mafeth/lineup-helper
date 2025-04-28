package de.devofvictory.objects.enums;

public enum ValoMap {

    ASCENT("Ascent"),
    BIND("Bind"),
    BREEZE("Breeze"),
    FRACTURE("Fracture"),
    HAVEN("Haven"),
    SPLIT("Split"),
    ICEBOX("Icebox");

    private final String name;

    public String getName() {
        return name;
    }

    ValoMap(String name) {
        this.name = name;
    }
}
