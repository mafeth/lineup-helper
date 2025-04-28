package de.devofvictory.frames;

import de.devofvictory.objects.Lineup;
import de.devofvictory.utils.FrameManager;
import de.devofvictory.utils.LineupManager;
import de.devofvictory.utils.OtherUtils;
import lombok.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OverlayPanel extends JPanel {

    private JLabel overlayLabel;
    private final JLabel stateLabel;
    private final JLabel selectedLabel;
    @Getter
    private final JLabel bombTimerLabel;
    private BufferedImage pixel;

    {
        try {
            pixel = ImageIO.read(new File(".\\lineup-helper-assets\\images\\map-background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage img;


    public OverlayPanel() {
        this.setVisible(true);
        this.setBackground(new Color(0f, 0f, 0f, 0f));
        this.setLayout(null);

        this.overlayLabel = new JLabel();
        this.overlayLabel.setVisible(true);
        this.overlayLabel.setLocation(0,0);
        this.overlayLabel.setSize(1920, 1080);

        this.stateLabel = new JLabel("No overlay");
        this.stateLabel.setVisible(true);
        this.stateLabel.setBounds(1693, 1035, 103, 25);
        this.stateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.stateLabel.setForeground(Color.WHITE);
        this.stateLabel.setFont(new Font("SansSerif", Font.BOLD, 17));

        this.selectedLabel = new JLabel("None");
        this.selectedLabel.setVisible(true);
        this.selectedLabel.setBounds(1693, 1013, 171, 25);
        this.selectedLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.selectedLabel.setForeground(Color.WHITE);
        this.selectedLabel.setFont(new Font("SansSerif", Font.BOLD, 17));

        this.bombTimerLabel = new JLabel("45");
        this.bombTimerLabel.setVisible(false);
        this.bombTimerLabel.setIcon(new ImageIcon(".\\lineup-helper-assets\\images\\icons\\clock.png"));
        this.bombTimerLabel.setBounds(937, 92, 40, 16);
        this.bombTimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.bombTimerLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
        this.bombTimerLabel.setForeground(Color.GREEN);

        this.add(stateLabel);
        this.add(selectedLabel);
        this.add(bombTimerLabel);
        this.add(overlayLabel);

    }

    public void setSelectedLineup(Lineup lineup) {
        this.selectedLabel.setText(lineup.getName());
    }

    public void setState(int state) {

        switch (state) {
            case 0 -> {
                // TODO: change dirty fix

                this.overlayLabel.setIcon(null);
                this.stateLabel.setText("No overlay");
            }
            case 1 -> {

                this.overlayLabel.setIcon(new ImageIcon(OtherUtils.changeTransparency(LineupManager.getActiveLineup().getFeetOverlay(), (byte)100)));
                this.stateLabel.setText("Feet");
            }
            case 2 -> {
                this.overlayLabel.setIcon(new ImageIcon(OtherUtils.changeTransparency(LineupManager.getActiveLineup().getCrosshairOverlay(), (byte)100)));
                this.stateLabel.setText("Crosshair");
            }
        }

        FrameManager.getMainFrame().repaint();
    }

}
