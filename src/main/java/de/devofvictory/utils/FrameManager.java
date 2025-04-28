package de.devofvictory.utils;

import de.devofvictory.components.LineupButton;
import de.devofvictory.frames.LineupHelperFrame;
import de.devofvictory.frames.MapPanel;
import de.devofvictory.frames.OverlayPanel;
import de.devofvictory.objects.Lineup;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class FrameManager {

    @Getter
    private static LineupHelperFrame mainFrame;

    private static MapPanel mapPanel;
    private static OverlayPanel overlayPanel;

    private static final Map<LineupButton, List<JComponent>> hoverComponents = new HashMap<>();

    @Getter @Setter
    private static boolean bombPlanted = false;


    public static void initMainFrame() {
        mapPanel = new MapPanel();
        overlayPanel = new OverlayPanel();

        try {
            mainFrame = new LineupHelperFrame("Lineup Helper");
            mainFrame.setContentPane(overlayPanel);
            mainFrame.setBounds(0,0, 1920, 1080);
            mainFrame.validate();
            mainFrame.pack();

            OtherUtils.showOnScreen(1, mainFrame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void toggleMap() {
        mainFrame.setContentPane(mainFrame.getContentPane() == mapPanel ? overlayPanel : mapPanel);
        mainFrame.validate();
        System.out.println(mainFrame.getContentPane().getClass().getSimpleName());
    }

    public static void addLineup(Lineup lineup) {
        mapPanel.addLineup(new LineupButton(lineup));
    }

    public static void setCurrentState(int state) {
        overlayPanel.setState(state);

        if (state != 0) {
            // Hide mouse cusror
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage("");
            

        }
    }

    public static void setLineup(Lineup lineup) {
        overlayPanel.setSelectedLineup(lineup);
    }

    public static void enableHover(LineupButton lineupButton) {
        Lineup lineup = lineupButton.getLineup();
        List<JComponent> hovers = new ArrayList<>();

        lineupButton.setIcon(new ImageIcon(lineupButton.getImage(30)));

        JLabel targetLabel = new JLabel();
        targetLabel.setVisible(true);
        int targetSize = 45;
        targetLabel.setBounds(lineup.getTargetPosX()-targetSize/2, lineup.getTargetPosY()-targetSize/2, targetSize, targetSize);
        String path = "C:\\Users\\delfi\\Desktop\\lineup-helper\\images\\mollys\\" + lineup.getAgent().getName().toLowerCase() + "_" + lineup.getAbility().toString().toLowerCase() + ".png";
        targetLabel.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(targetSize, targetSize, Image.SCALE_DEFAULT)));
        mapPanel.getMapLabel().add(targetLabel, 0);


        JPanel informationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(new Color(180, 182, 187));
                g.fillRect(0, 20, 252, 64);

                g.setColor(new Color(75, 146, 196));
                g.fillRect(0, 69, 252, 20);

                g.setColor(new Color(29, 34, 41));
                g.fillRect(0, 89, 252, 74);

                g.setColor(Color.WHITE);
                g.drawString(lineup.getName(), 20, 69);
            }
        };
        informationPanel.setVisible(true);
        informationPanel.setBounds(lineupButton.getX() + 40, lineupButton.getY(), 252, 158);
        informationPanel.setBackground(new Color(0, 0, 0, 0.01f));
        mapPanel.getMapLabel().add(informationPanel, 0);


        hovers.add(informationPanel);
        hovers.add(targetLabel);

        hoverComponents.put(lineupButton, hovers);
        mainFrame.repaint();
        mapPanel.repaint();
        mapPanel.validate();
        mainFrame.validate();
    }

    public static void disableHover(LineupButton lineupButton) {
        lineupButton.setIcon(new ImageIcon(lineupButton.getImage(25)));

        for (JComponent comp : hoverComponents.get(lineupButton)) {
            mapPanel.getMapLabel().remove(comp);
        }
        hoverComponents.remove(lineupButton);

        mapPanel.repaint();
        mapPanel.validate();
        mainFrame.validate();
        mainFrame.repaint();
    }

    public static void startBombTimer() {
        System.out.println("Bomb plant detected");
        setBombPlanted(true);

        overlayPanel.getBombTimerLabel().setVisible(true);
        overlayPanel.getBombTimerLabel().setText("45");
        overlayPanel.getBombTimerLabel().setForeground(Color.GREEN);
        mainFrame.repaint();
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int seconds = 45;
            @Override
            public void run() {
                seconds -= 1;
                overlayPanel.getBombTimerLabel().setText(""+seconds);
                overlayPanel.getBombTimerLabel().setForeground(OtherUtils.getColorFromTimer(seconds));


                if (seconds == 0) {
                    cancel();
                    setBombPlanted(false);
                    overlayPanel.getBombTimerLabel().setVisible(false);
                }
                mainFrame.repaint();
            }
        }, 1000, 1000);
    }
}
