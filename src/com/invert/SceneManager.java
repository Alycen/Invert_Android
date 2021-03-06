package com.invert;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.invert.BaseScene;

public class SceneManager {
    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;
    
    private static final SceneManager INSTANCE = new SceneManager();
    private SceneType currentSceneType = SceneType.SCENE_SPLASH;
    private BaseScene currentScene;
    private Engine engine = ResourceManager.getInstance().engine;
    
    public enum SceneType {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
    }
    
    public void setScene(BaseScene scene) {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            default:
                break;
        }
    }
    
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
    	ResourceManager.getInstance().loadSplashScreen();
    	splashScene = new SplashScene();
    	currentScene = splashScene;
    	pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    
    public void disposeSplashScene() {
    	ResourceManager.getInstance().unloadSplashScreen();
    	splashScene.dispose();
    	splashScene = null;
    }
    
    public static SceneManager getInstance() {
        return INSTANCE;
    }
    
    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }
    
    public BaseScene getCurrentScene() {
        return currentScene;
    }
}