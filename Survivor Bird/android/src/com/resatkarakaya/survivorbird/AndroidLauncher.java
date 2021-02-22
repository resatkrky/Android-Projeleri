package com.resatkarakaya.survivorbird;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.resatkarakaya.survivorbird.SurvivorBird;

public class AndroidLauncher extends AndroidApplication {
	//Oyunu android için konfigür edecek
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SurvivorBird(), config);
	}
}
