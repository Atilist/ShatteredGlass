package net.glasslauncher.screen;

import org.lwjgl.opengl.GL11;

public class MenuScreen implements Screen {
    @Override
    public void renderScreen() {
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
    }
}
