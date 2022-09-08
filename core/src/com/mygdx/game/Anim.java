package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Anim {
    private TextureAtlas atlas;
    private Texture img;
    private Animation<TextureRegion> animation;
    private float time;

    public Anim(String atlasName, String pictureName, Animation.PlayMode playMode, float frameDuration) {
        atlas = new TextureAtlas(atlasName);
        animation = new Animation<TextureRegion>(frameDuration, atlas.findRegions(pictureName));
        animation.setPlayMode(playMode);
        time += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getFrame() {
        return animation.getKeyFrame(time);
    }

    public void setTime(float time) {
        this.time += time;
    }

    public void zeroTime(){
        this.time = 0;
    }

    public boolean isAnimationOver(){
        return animation.isAnimationFinished(time);
    }

    public void setPlayMode(Animation.PlayMode playMode){
        animation.setPlayMode(playMode);
    }

    public void dispose (){
        // img.dispose();
        atlas.dispose();
    }
}