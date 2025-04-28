package de.devofvictory.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import de.devofvictory.LineupHelper;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

public class OtherUtils {

    public static BufferedImage imageToBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(25,25, BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static void showOnScreen(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        int width = 0, height = 0;
        if( screen > -1 && screen < gd.length ) {
            width = gd[screen].getDefaultConfiguration().getBounds().width;
            height = gd[screen].getDefaultConfiguration().getBounds().height;
            frame.setLocation(
                    ((width / 2) - (frame.getSize().width / 2)) + gd[screen].getDefaultConfiguration().getBounds().x,
                    ((height / 2) - (frame.getSize().height / 2)) + gd[screen].getDefaultConfiguration().getBounds().y
            );
//            frame.setVisible(true);
        } else {
            throw new RuntimeException( "No Screens Found" );
        }
    }

    public static File getFileFromResource(String path) {
        return new File(OtherUtils.class.getClassLoader().getResource(path).getFile());
    }

    public static void playAudio(String filename)
    {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(".\\lineup-helper-assets\\sounds\\" + filename)));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }

    public static Object getNestedObject(Map<String, Object> map, String path) {
        System.out.println(map);
        Map<String, Object> newMap = null;
        for (String key : path.split("\\.")) {
            System.out.println(key);


            if (newMap.containsKey(key)) {
                System.out.println("ja");
                newMap = (Map<String, Object>) newMap.get(key);
                System.out.println(newMap);
            }else {
                return newMap.get(key);
            }

        }
        return newMap;

    }

    public static <T> T getNestedValue(Map<?, ?> map, String... keys) {
        Object value = map;

        for (String key : keys) {
            value = ((Map<?, ?>) value).get(key);
        }

        return (T) value;
    }

    public static String getFocusedWindowName() {
        char[] buffer = new char[1024 * 2];
        WinDef.HWND hwnd = LineupHelper.user32.GetForegroundWindow();
        LineupHelper.user32.GetWindowText(hwnd, buffer, 1024);

        return Native.toString(buffer);
    }

    public static BufferedImage changeTransparency(BufferedImage original, byte alpha) {
        alpha %= 0xff;
        for (int cx = 0; cx < original.getWidth(); cx++) {
            for (int cy = 0; cy < original.getHeight(); cy++) {
                int color = original.getRGB(cx, cy);

                int mc = (alpha << 24) | 0x00ffffff;
                int newcolor = color & mc;
                original.setRGB(cx, cy, newcolor);

            }
        }
        return original;
    }

    public static Color getColorFromTimer(double seconds) {

        double percentage = (100f/45f) * seconds / 100f;

        double H = percentage * 0.4; // Hue (note 0.4 = Green, see huge chart below)
        double S = 1; // Saturation
        double B = 1; // Brightness

        return Color.getHSBColor((float)H, (float)S, (float)B);
    }

}
