package de.devofvictory.objects;

import de.devofvictory.objects.enums.ValoAbility;
import de.devofvictory.objects.enums.ValoAgent;
import de.devofvictory.objects.enums.ValoMap;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@Data
public class Lineup {

    private String name;
    private String description;
    private String author;

    private ValoMap map;
    private ValoAgent agent;
    private ValoAbility ability;

    private int feetPosX;
    private int feetPosY;
    private int targetPosX;
    private int targetPosY;

    private String feetImageUrl;
    private String crosshairImageUrl;

    @Setter(AccessLevel.NONE)
    private BufferedImage feetOverlay;

    @Setter(AccessLevel.NONE)
    private BufferedImage crosshairOverlay;


    public void setFeetImageUrl(String feetImageUrl) {
        this.feetImageUrl = feetImageUrl;
        try {
            this.feetOverlay = ImageIO.read(new File(".\\lineup-helper-assets\\images\\lineups\\" + feetImageUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCrosshairImageUrl(String crosshairImageUrl) {
        this.crosshairImageUrl = crosshairImageUrl;
        try {
            this.crosshairOverlay = ImageIO.read(new File(".\\lineup-helper-assets\\images\\lineups\\" + crosshairImageUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
