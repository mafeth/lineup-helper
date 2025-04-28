package de.devofvictory.listeners;

import de.devofvictory.utils.FrameManager;
import de.devofvictory.utils.LineupManager;
import de.devofvictory.utils.OtherUtils;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;

public class KeyListener implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        String btn = NativeKeyEvent.getKeyText(e.getKeyCode());
        if (btn.equals("L")) {

            if (OtherUtils.getFocusedWindowName().contains("VALORANT")) {
                System.out.println("Map toggled");
                FrameManager.toggleMap();
            }
        }else if (btn.equals("K")) {
            if (OtherUtils.getFocusedWindowName().contains("VALORANT")) {
                LineupManager.nextState();
            }
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {}
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}
}
