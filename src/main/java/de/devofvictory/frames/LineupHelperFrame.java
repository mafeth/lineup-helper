package de.devofvictory.frames;

import com.sun.jna.platform.DesktopWindow;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.WinDef;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LineupHelperFrame extends JFrame {


    public LineupHelperFrame(String title) throws IOException {

//        this.overlayPanel.setLayout(null);

        WinDef.HWND hwnd = null;


        for (DesktopWindow dw : WindowUtils.getAllWindows(true)) {
            if (dw.getTitle().contains("Fotos")) {
                hwnd = dw.getHWND();
                break;
            }

        }


        this.setTitle(title);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setUndecorated(true);
//        frame.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setFocusableWindowState(false);
//        frame.setFocusable(false);


        this.setBackground(new Color(0f, 0f, 0f, 0.00f));
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);


//        Main.user32.SetForegroundWindow(hwnd);
//        Main.user32.SetFocus(hwnd);
//        Main.user32.ShowWindow(hwnd, 9);

    }


}
