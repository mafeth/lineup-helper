package de.devofvictory.objects.enums;

public enum ValoAgent {

    BRIMSTONE("Brimstone"),
    CYPHER("Cypher"),
    KAYO("Kayo"),
    KILLJOY("Killjoy"),
    PHEONIX("Pheonix"),
    RAZE("Raze"),
    SAGE("Sage"),
    SOVA("Sova"),
    VIPER("Viper");

    private final String name;

    ValoAgent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
