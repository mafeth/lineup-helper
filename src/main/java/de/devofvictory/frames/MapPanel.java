package de.devofvictory.frames;

import de.devofvictory.components.LineupButton;
import de.devofvictory.components.StyledButton;
import de.devofvictory.listeners.MapListener;
import de.devofvictory.utils.FrameManager;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    @Getter
    private final JLabel mapLabel;

    public MapPanel() {

        this.setLayout(null);
        this.setBackground(new Color(0f, 0f, 0f, 0f));
        this.setVisible(true);


        StyledButton styledButton = new StyledButton("Close map");
        styledButton.setLocation(1920-styledButton.getWidth()-10,10);

        styledButton.setOnPress(FrameManager::toggleMap);

        JLabel backgroundLabel = new JLabel();
        Image img = Toolkit.getDefaultToolkit().getImage(".\\lineup-helper-assets\\images\\map-background4.png");

        backgroundLabel.setIcon(new ImageIcon(img.getScaledInstance(1920, 1080, Image.SCALE_DEFAULT)));
        backgroundLabel.setVisible(true);
        backgroundLabel.setSize(1920, 1080);

        backgroundLabel.addMouseListener(new MapListener());



        mapLabel = new JLabel();
        ImageIcon image = new ImageIcon(".\\lineup-helper-assets\\images\\maps\\ascent.png");

        int width = (int) Math.round(image.getIconWidth() * 1.3);
        int height = (int) Math.round(image.getIconHeight() * 1.3);


        mapLabel.setIcon(new ImageIcon(image.getImage().getScaledInstance(width , height, Image.SCALE_SMOOTH)));
        mapLabel.setBackground(new Color(0,0,0,0f));

        mapLabel.setBounds(1920/2-width/2, 1080/2-height/2, width, height);

        mapLabel.addMouseListener(new MapListener());


        this.add(styledButton);
        this.add(mapLabel);
        this.add(backgroundLabel);
    }

    public void addLineup(LineupButton button) {
        this.mapLabel.add(button);
    }

}
