package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Anim {
    private Texture img;
    private Animation<TextureRegion> anm;
    private float time;

    public Anim(String name, int col, int row, Animation.PlayMode playMode) {
        img = new Texture(name);
        TextureRegion region = new TextureRegion(img);
        int xCount = region.getRegionWidth() / col;
        int yCount = region.getRegionHeight() / row;
        TextureRegion[][] textureRegions = region.split(xCount, yCount);
        TextureRegion[] regions1 = new TextureRegion[textureRegions.length * textureRegions[0].length];
        int cnt = 0;
        for (int i = 0; i < textureRegions.length; i++) {
            for (int j = 0; j < textureRegions[0].length; j++) {
                regions1[cnt++] = textureRegions[i][j];
            }
        }
        anm = new Animation<TextureRegion>(1 / 15f, regions1);
        anm.setPlayMode(Animation.PlayMode.LOOP);

        time += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getFrame() {
        return anm.getKeyFrame(time);
    }
    public void zeroTime() {
        this.time = 0;
    }
    public void setTime(float time) {
        this.time += time;
    }
    public boolean isAnimationOver() {
        return anm.isAnimationFinished(time);
    }
    public void setPlayMode(Animation.PlayMode playMode) {anm.setPlayMode(playMode);}

    public void dispose() {
        img.dispose();
    }
}
