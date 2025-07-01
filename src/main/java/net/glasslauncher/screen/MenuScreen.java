package net.glasslauncher.screen;

import net.glasslauncher.texture.TextureManager;

import static org.lwjgl.opengl.GL41.*;

public class MenuScreen implements Screen {
    @Override
    public void renderScreen() {
        glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

        try {
            System.out.println(TextureManager.getTextureId("black.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
