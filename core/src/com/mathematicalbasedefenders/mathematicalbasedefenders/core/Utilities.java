package com.mathematicalbasedefenders.mathematicalbasedefenders.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import org.apache.commons.codec.binary.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utilities {

    // OTHERS
    // other stuff
    public static boolean useGMTTimezone = true;
    public static boolean statsInTitle = false;

    /**
     * Gets the time in real life.
     *
     * @return The time in real life as a string
     */
    public static String getRealLifeTime() {
        if (useGMTTimezone) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat.format(new Date(TimeUtils.millis()));
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS Z");
            return simpleDateFormat.format(new Date(TimeUtils.millis()));
        }
    }

    public static String convertMillisecondsToStringifiedTime(double milliseconds) {
        if (milliseconds < 1000) {
            return milliseconds + "ms";
        } else {
            return (int) Math.floor(milliseconds / 1000) + "s" + milliseconds % 1000 + "ms";
        }
    }

    public String convertMillisecondsToColonifiedTime(double milliseconds) {
        double ms = milliseconds % 1000;
        double s = (milliseconds / 1000) % 60;
        double m = (milliseconds / (1000 * 60)) % 60;
        if (milliseconds < 60000) {
            return String.format("0:%02d.%03d", (int) s, (int) ms);
        } else {
            return String.format("%01d:%02d.%03d", (int) m, (int) s, (int) ms);
        }
    }

    public void toggleStatsVisibility() {
        // statsInTitle = !statsInTitle;
    }

    public int countOccurrencesOfACharacterInAString(String string, char character) {
        int total = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == character) {
                total++;
            }
        }
        return total;
    }

    /**
     * Converts an RGB color value to HSL. Conversion formula
     * adapted from http://en.wikipedia.org/wiki/HSL_color_space.
     * https://stackoverflow.com/questions/2353211/hsl-to-rgb-color-conversion/9493060#9493060
     *
     * @param r The red color value
     * @param g The green color value
     * @param b The blue color value
     * @return float array, the HSL representation
     */
    public float[] rgbToHSL(float r, float g, float b) {

        float max = (r > g && r > b) ? r : Math.max(g, b);
        float min = (r < g && r < b) ? r : Math.min(g, b);

        float h, s, l;
        l = (max + min) / 2.0f;

        if (max == min) {
            h = s = 0.0f;
        } else {
            float d = max - min;
            s = (l > 0.5f) ? d / (2.0f - max - min) : d / (max + min);

            if (r > g && r > b)
                h = (g - b) / d + (g < b ? 6.0f : 0.0f);

            else if (g > b)
                h = (b - r) / d + 2.0f;

            else
                h = (r - g) / d + 4.0f;

            h /= 6.0f;
        }

        return new float[]{h, s, l};
    }

    public Pixmap changeColorsOfAPixmap(Pixmap pixmap, Color color) {
        Pixmap pixmapToReturn = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Pixmap.Format.RGBA8888);
        for (int x = 0; x < pixmap.getWidth(); x++) {
            for (int y = 0; y < pixmap.getHeight(); y++) {
                if ((pixmap.getPixel(x, y) & 0xff) > 0) {
                    pixmapToReturn.drawPixel(x, y, Color.rgba8888(color));
                } else {
                    // transparent
                    pixmapToReturn.drawPixel(x, y, 0);
                }
            }
        }
        return pixmapToReturn;
    }

    /**
     * Combines two textures in to one.
     * https://stackoverflow.com/questions/25257012/libgdx-combine-a-number-of-textures-into-one
     *
     * @param leftTexture  The first texture
     * @param rightTexture The second texture
     * @return The first and second texture as one texture. (Combined)
     */
    public Texture putTwoTexturesSideBySide(Texture leftTexture, Texture rightTexture) {

        Pixmap pixmap = new Pixmap(leftTexture.getWidth() + rightTexture.getWidth(), leftTexture.getHeight() + rightTexture.getHeight(), Pixmap.Format.RGBA8888);

        if (!leftTexture.getTextureData().isPrepared()) {
            leftTexture.getTextureData().prepare();
        }

        Pixmap pixmap1 = leftTexture.getTextureData().consumePixmap();

        if (!rightTexture.getTextureData().isPrepared()) {
            rightTexture.getTextureData().prepare();
        }
        Pixmap pixmap2 = rightTexture.getTextureData().consumePixmap();

        pixmap.drawPixmap(pixmap1, 0, 0);
        pixmap.drawPixmap(pixmap2, rightTexture.getWidth(), rightTexture.getHeight() + (rightTexture.getHeight() / 2));

        pixmap1.dispose();
        pixmap2.dispose();

        Texture textureToReturn = new Texture(pixmap);

        pixmap.dispose();

        return textureToReturn;

    }

    /**
     * Puts a Texture on top of an other Texture
     *
     * @param bottomTexture
     * @param topTexture
     * @return
     */
    public Texture putATextureOnTopOfAnother(Texture topTexture, Texture bottomTexture, int x, int y) {

        if (!bottomTexture.getTextureData().isPrepared()) {
            bottomTexture.getTextureData().prepare();
        }

        Pixmap pixmap1 = bottomTexture.getTextureData().consumePixmap();

        if (!topTexture.getTextureData().isPrepared()) {
            topTexture.getTextureData().prepare();
        }

        Pixmap pixmap2 = topTexture.getTextureData().consumePixmap();

        pixmap1.drawPixmap(pixmap2, x, y);
        Texture textureToReturn = new Texture(pixmap1);

        bottomTexture.dispose();
        topTexture.dispose();
        pixmap1.dispose();
        pixmap2.dispose();

        return textureToReturn;
    }

    public Texture convertTextureRegionToTexture(TextureRegion textureRegion) {
        return textureRegion.getTexture();
    }

    public static Pixmap convertTextureAtlasRegionToPixmap(TextureAtlas.AtlasRegion atlasRegion) {
        Pixmap pixmap = new Pixmap(atlasRegion.getRegionWidth(), atlasRegion.getRegionHeight(), Pixmap.Format.RGBA8888);
        if (!atlasRegion.getTexture().getTextureData().isPrepared()) {
            atlasRegion.getTexture().getTextureData().prepare();
        }

        Pixmap pixmap2 = atlasRegion.getTexture().getTextureData().consumePixmap();


        // haha funny
        int xd = 0;
        int yd = 0;

        for (int x = atlasRegion.getRegionX(); x < atlasRegion.getRegionX() + atlasRegion.getRegionWidth(); x++) {
            for (int y = atlasRegion.getRegionY(); y < atlasRegion.getRegionY() + atlasRegion.getRegionHeight(); y++) {
                pixmap.drawPixel(xd, yd, pixmap2.getPixel(x, y));
                yd++;
            }
            xd++;
            yd = 0;
        }


        pixmap2.dispose();
        return pixmap;
    }

    public String encodeWithBase64(String string, int rounds) {
        String currentString = string;
        for (int i = 0; i < rounds; i++) {
            currentString = new String(Base64.encodeBase64(currentString.getBytes()));
        }
        return currentString;
    }

}
