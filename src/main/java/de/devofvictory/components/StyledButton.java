package de.devofvictory.components;

import de.devofvictory.utils.OtherUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StyledButton extends JLabel {

    private Runnable onPress;


    public StyledButton(String text) {
        this.setBackground(new Color(134, 205, 193));
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        this.setText(text.toUpperCase());


        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        EmptyBorder eBorder = new EmptyBorder(5, 15, 5, 15);
        LineBorder lBorder = new LineBorder(new Color(100, 100, 100));
        this.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
        this.setFont(new Font("Impact", Font.PLAIN, 16));
        this.setSize(getText().length() * 30, 50);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                OtherUtils.playAudio("ui_click.wav");

                onPress.run();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(88, 138, 130));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(134, 205, 193));
            }
        });

        this.setVisible(true);
    }

    public void setOnPress(Runnable onPress) {
        this.onPress = onPress;
    }
}
