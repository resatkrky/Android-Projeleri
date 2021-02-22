package com.resatkarakaya.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
	SpriteBatch batch; //projenin çalışmasını sağlayan nesne
	Texture background , bird , bee1 , bee2 , bee3;
	float birdX = 0,birdY = 0;
	int gameState = 0; //oyun başlamadı
	float velocity = 0 , gravity = 0.1f;
	float width = 0 , height = 0;
	float enemyVelocity = 2f;
	Random random;
	int score = 0;
	int scoredEnemy = 0;
	BitmapFont font;
	BitmapFont font2;

	Circle birdCircle;
	ShapeRenderer shapeRenderer;

	int numberoOfEnemies = 4;
	float [] enemyX = new float[numberoOfEnemies];
	float [] enemyOffset = new float[numberoOfEnemies];
	float [] enemyOffset2 = new float[numberoOfEnemies];
	float [] enemyOffset3 = new float[numberoOfEnemies];
	Circle[] enemyCircles ;
	Circle[] enemyCircles2 ;
	Circle[] enemyCircles3 ;

	float distance = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		bird = new Texture("bird.png");
		bee1 = new Texture("bee.png");
		bee2 = new Texture("bee.png");
		bee3 = new Texture("bee.png");
		distance = Gdx.graphics.getWidth() / 2;
		random = new Random();

		birdX = Gdx.graphics.getWidth()/2 - bird.getHeight()/2;
		birdY = Gdx.graphics.getHeight()/3;

		birdCircle = new Circle();
		enemyCircles = new Circle[numberoOfEnemies];
		enemyCircles2 = new Circle[numberoOfEnemies];
		enemyCircles3 = new Circle[numberoOfEnemies];
		shapeRenderer = new ShapeRenderer();

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(4);

		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(6);

		width = Gdx.graphics.getWidth()/15;
		height = Gdx.graphics.getHeight()/15;


		for(int i = 0 ; i < numberoOfEnemies ; i++){ // arılar arası mesafeyi ayarlama initialize
			enemyOffset[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());
			enemyOffset2[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());
			enemyOffset3[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());

			enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i*distance;

			enemyCircles[i] = new Circle();
			enemyCircles2[i] = new Circle();
			enemyCircles3[i] = new Circle();

		}
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(gameState == 1){

			if(enemyX[scoredEnemy] < Gdx.graphics.getWidth() / 2 - bird.getHeight() / 2){
				score++;

				if(scoredEnemy < numberoOfEnemies -1){
					scoredEnemy++;
				}
				else {
					scoredEnemy = 0;
				}
			}

			if(Gdx.input.justTouched()){ //kullanıcı tıklayınca ne olacak
				velocity = -7; //alt tarafta birdY -velocity olduğu için - değer verdim kuş yükselmesi için
			}

			for (int i = 0 ; i < numberoOfEnemies ; i++){ //4 setten sonra devam etmesini sağlar arı gelmesinin

				if(enemyX[i] < width){
					enemyX[i] = enemyX[i] + numberoOfEnemies * distance;
					enemyOffset[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() -200);
					enemyOffset2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() -200);
					enemyOffset3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() -200);
				}
				else{
					enemyX[i] = enemyX[i] - enemyVelocity;
				}

				batch.draw(bee1,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffset[i],width,height);
				batch.draw(bee2,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffset2[i],width,height);
				batch.draw(bee3,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffset3[i],width,height);

				enemyCircles[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight()/2 + enemyOffset[i] + Gdx.graphics.getHeight()/20,width/2);
				enemyCircles2[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight()/2 + enemyOffset2[i] + Gdx.graphics.getHeight()/20,width/2);
				enemyCircles3[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight()/2 + enemyOffset3[i] + Gdx.graphics.getHeight()/20,width/2);


			}

			if(birdY > 0){
				velocity = velocity + gravity; //yer çekimyle kuş düşüyor
				birdY = birdY - velocity;
			}
			else {
				gameState = 2;
			}
		}
		else if(gameState == 0){
			if(Gdx.input.justTouched()){ //kullanıcı tıklayınca ne olacak
				gameState = 1; //oyun başladı
			}
		}
		else if(gameState == 2){

			font2.draw(batch,"Game Over! Tap to Play Again!",100,Gdx.graphics.getHeight() / 2);

			if (Gdx.input.justTouched()){
				gameState = 1;
				birdY = Gdx.graphics.getHeight()/3;

				for(int i = 0 ; i < numberoOfEnemies ; i++){ // arılar arası mesafeyi ayarlama initialize
					enemyOffset[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());
					enemyOffset2[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());
					enemyOffset3[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());

					enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i*distance;

					enemyCircles[i] = new Circle();
					enemyCircles2[i] = new Circle();
					enemyCircles3[i] = new Circle();

				}
				velocity = 0;
				scoredEnemy = 0;
				score = 0;
			}
		}
		batch.draw(bird,birdX,birdY,width,height);

		font.draw(batch,String.valueOf(score),100,200);
		batch.end();

		birdCircle.set(birdX + Gdx.graphics.getWidth() / 30,birdY + Gdx.graphics.getHeight() / 20,width / 2);

		//shapeRenderer.begin(shapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(Color.BLACK);
		//shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);

		for(int i = 0 ; i < numberoOfEnemies ; i++){
			//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight()/2 + enemyOffset[i] + Gdx.graphics.getHeight()/20,width/2);
			//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight()/2 + enemyOffset2[i] + Gdx.graphics.getHeight()/20,width/2);
			//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight()/2 + enemyOffset3[i] + Gdx.graphics.getHeight()/20,width/2);

			if(Intersector.overlaps(birdCircle,enemyCircles[i]) || Intersector.overlaps(birdCircle,enemyCircles2[i]) || Intersector.overlaps(birdCircle,enemyCircles3[i])){ //carpışma durumu
				System.out.println("collision detection");
				gameState = 2;

			}
		}
		shapeRenderer.end();
	}
	
	@Override
	public void dispose () {

	}
}
