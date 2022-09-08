package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Anim;
import com.mygdx.game.Main;

public class GameScreen implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Anim animation;
    boolean direction = true;
    boolean movingForward = true;
    float x;
    float y;

    public GameScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        animation = new Anim("atlas/anim.atlas", "image_part", Animation.PlayMode.LOOP, 1 / 20f);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        animation.setTime(Gdx.graphics.getDeltaTime());
//        Выход в меню по нажатию любой кнопки по ТЗ
//        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
//            direction = true;
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
//            direction = false;
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
//            movingForward = false;
//            direction = false;
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
//            movingForward = true;
//            direction = true;
//        }
        if (!animation.getFrame().isFlipX() && direction) {
            animation.getFrame().flip(false, false);
        }
        if (animation.getFrame().isFlipX() && direction) {
            animation.getFrame().flip(true, false);
        }
        if (animation.getFrame().isFlipX() && !direction) {
            animation.getFrame().flip(false, false);
        }
        if (!animation.getFrame().isFlipX() && !direction) {
            animation.getFrame().flip(true, false);
        }
        batch.begin();
        batch.draw(animation.getFrame(), x, y);
        batch.end();
        if (movingForward) {
            x += 5;
        } else {
            x -= 5;
        }
        if (movingForward && x + animation.getFrame().getRegionWidth() >= Gdx.graphics.getWidth()) {
            direction = false;
            movingForward = false;
        }
        if (!movingForward && x == 0) {
            direction = true;
            movingForward = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            dispose();
            game.setScreen(new MenuScreen(game));
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.animation.dispose();
    }
}