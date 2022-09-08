package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Anim animation;
    private boolean lookRight;
    private int positionAnimX = 0;

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(800, 400);
        batch = new SpriteBatch();
        animation = new Anim("anim.jpg", 6, 2, Animation.PlayMode.LOOP);

    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);

        animation.setTime(Gdx.graphics.getDeltaTime());
        float y = Gdx.input.getX() - animation.getFrame().getRegionWidth() / 2;
        float x = Gdx.graphics.getHeight() - Gdx.input.getY() - animation.getFrame().getRegionHeight() / 2;

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) lookRight = true;
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) lookRight = false;

        if (positionAnimX + 170 >= Gdx.graphics.getWidth()) lookRight = false;
        if (positionAnimX <= 0) lookRight = true;

        if(!animation.getFrame().isFlipX() && !lookRight) animation.getFrame().flip(true, false);
        if(animation.getFrame().isFlipX() && lookRight) animation.getFrame().flip(true, false);

        if (lookRight) {
            positionAnimX += 5;
        } else {
            positionAnimX -= 5;
        }

        batch.begin();
        batch.draw(animation.getFrame(), positionAnimX, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        animation.dispose();
    }
}
