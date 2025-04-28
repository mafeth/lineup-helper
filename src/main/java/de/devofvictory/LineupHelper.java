package de.devofvictory;


import com.sun.jna.platform.win32.User32;
import de.devofvictory.listeners.KeyListener;
import de.devofvictory.utils.FrameManager;
import de.devofvictory.utils.LineupManager;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.python.core.Py;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;


public class LineupHelper {

    public static User32 user32 = User32.INSTANCE;


    public static void main(String[] args) {
//        PySystemState systemState = Py.getSystemState();
//        //Link some external python libraries installed via maven plugin
//        systemState.path.append(new PyString("target/classes/Lib"));
//        PythonInterpreter python = new PythonInterpreter();
//
//        python.exec("from valclient.client import Client");
////        python.exec("client = Client()");
////        python.exec("client.activate()");
//        python.exec("print(client.coregame_fetch_match())");

        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        logger.setUseParentHandlers(false);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.exit(-1);
        }

        GlobalScreen.addNativeKeyListener(new KeyListener());

        FrameManager.initMainFrame();
        LineupManager.registerLineups();

        Timer timer = new Timer();
        try {
            Robot r = new Robot();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!FrameManager.isBombPlanted()) {
                        BufferedImage img = r.createScreenCapture(new Rectangle(915, 15, 85, 77));
                        Color color = new Color(img.getRGB(34, 12));

                        Color lightRed = new Color(170, 0, 0);
                        Color darkRed = new Color(124, 0, 0);
                        if (color.equals(lightRed) || color.equals(darkRed)) {
                            FrameManager.startBombTimer();
                        }
                    }
                }
            }, 0, 50);
        } catch (AWTException e) {
            e.printStackTrace();
        }


    }
}
