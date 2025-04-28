package de.devofvictory.components;

import de.devofvictory.objects.Lineup;
import de.devofvictory.utils.FrameManager;
import de.devofvictory.utils.LineupManager;
import de.devofvictory.utils.OtherUtils;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LineupButton extends JLabel {

    @Getter
    private Lineup lineup;
    private Image image;

    public LineupButton(Lineup lineup) {
        this.lineup = lineup;
        this.image = getImage(25);
        setIcon(new ImageIcon(image));

        this.setSize(30, 30);
        this.setLocation(lineup.getFeetPosX()-25/2, lineup.getFeetPosY()-25/2);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("Lineup selected: " + lineup.getName());
                LineupManager.setActiveLineup(lineup);
                OtherUtils.playAudio("ui_click.wav");
                FrameManager.toggleMap();

            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {

                FrameManager.enableHover(LineupButton.this);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                FrameManager.disableHover(LineupButton.this);
            }
        });
        this.setVisible(true);
    }

    public Image getImage(int size) {
        String name = lineup.getAgent().getName().toLowerCase() + "_" + lineup.getAbility().toString().toLowerCase() + ".png";
        return new ImageIcon(".\\lineup-helper-assets\\images\\icons\\"+name).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }

}
