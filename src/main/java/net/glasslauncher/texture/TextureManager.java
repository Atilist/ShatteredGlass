package net.glasslauncher.texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.opengl.GL41;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private static final int COLOR_CHANNELS = 4;
    private static final Map<String, Integer> TEXTURE_PATH_TO_GL_ID = new HashMap<>();

    public static int getTextureId(String path) throws IOException {
        path = normalizePath(path);
        if (TEXTURE_PATH_TO_GL_ID.containsKey(path)) {
            return TEXTURE_PATH_TO_GL_ID.get(path);
        }

        PNGDecoder decoder = new PNGDecoder(TextureManager.class.getResourceAsStream(path));
        ByteBuffer buffer = ByteBuffer.allocateDirect(COLOR_CHANNELS * decoder.getHeight() * decoder.getWidth());
        decoder.decode(buffer, decoder.getWidth() * COLOR_CHANNELS, PNGDecoder.Format.RGBA);
        buffer.flip();
        int id = GL41.glGenTextures();

        //bind the texture
        GL41.glBindTexture(GL41.GL_TEXTURE_2D, id);

        //tell opengl how to unpack bytes
        GL41.glPixelStorei(GL41.GL_UNPACK_ALIGNMENT, 1);

        //set the texture parameters, can be GL_LINEAR or GL_NEAREST
        GL41.glTexParameterf(GL41.GL_TEXTURE_2D, GL41.GL_TEXTURE_MIN_FILTER, GL41.GL_LINEAR);
        GL41.glTexParameterf(GL41.GL_TEXTURE_2D, GL41.GL_TEXTURE_MAG_FILTER, GL41.GL_LINEAR);

        //upload texture
        GL41.glTexImage2D(GL41.GL_TEXTURE_2D, 0, GL41.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL41.GL_RGBA, GL41.GL_UNSIGNED_BYTE, buffer);

        // Generate Mip Map
//        GL41.glGenerateMipmap(GL41.GL_TEXTURE_2D);

        TEXTURE_PATH_TO_GL_ID.put(path, id);

        return id;
    }

    private static String normalizePath(String path) {
        path = path.replace("\\", "/");
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return path;
    }
}
