package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import models.Player;
import objects.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel  implements Runnable{

  //SCREEN SETTINGS
  final int originalTileSize = 16; // 16x16 tile
  final int scale = 3;

  public final int tileSize = originalTileSize * scale; // 48x48 tile
  public final int maxScreenCol = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
  public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

  // WORLD SETTINGS
  public final int maxWorldCol = 100;
  public final int maxWorldRow = 100;

  // FPS
  int FPS = 60;

  // SYSTEM
  TileManager tileManager = new TileManager(this);
  KeyHandler keyHandler = new KeyHandler();
  Sound music = new Sound();
  Sound soundEffect = new Sound();
  public CollisionChecker collisionChecker = new CollisionChecker(this);
  public AssetSetter assetSetter = new AssetSetter(this);
  public UI ui = new UI(this);
  Thread gameThread;

  // ENTITY AND OBJECT
  public Player player = new Player(this, keyHandler);
  public SuperObject[] superObject = new SuperObject[10];

  public GamePanel() {

    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHandler);
    this.setFocusable(true);
  }

  public void setUpGame() {

    assetSetter.setObject();

    playMusic(0);

  }

  public void startGameThread() {

    gameThread = new Thread(this);
    gameThread.start();
  }

  public void run() {
    double drawInterval = (double) 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;

    while (gameThread != null) {
      currentTime = System.nanoTime();


      delta += (currentTime - lastTime) / drawInterval;
      timer += (currentTime - lastTime);
      lastTime = currentTime;

      if (delta >= 1) {
        update();
        repaint();
        delta--;
        drawCount++;
      }


      if (timer >= 1000000000) {
        System.out.println("FPS: " + drawCount);
        drawCount = 0;
        timer = 0;
      }
    }
  }

  public void update() {

    player.update();

  }

  public void paintComponent(Graphics graphics) {

    super.paintComponent(graphics);

    Graphics2D graphics2D = (Graphics2D) graphics;

    // DEBUG
    long drawStart = System.nanoTime();

    // TILE
    tileManager.draw(graphics2D);

    // OBJECT
    for (SuperObject object : superObject) {
      if (object != null) {
        object.draw(graphics2D, this);
      }
    }

    // PLAYER
    player.draw(graphics2D);

    // UI
    ui.draw(graphics2D);

    // DEBUG
    if (keyHandler.checkDrawTime) {
      long drawEnd = System.nanoTime();
      long passed = drawEnd - drawStart;
      graphics2D.setColor(Color.WHITE);
      graphics2D.drawString("Draw time: " + passed, 10, 400);
      System.out.println("Draw time: " + passed);
    }


    graphics2D.dispose();
  }

  public void playMusic(int i) {

    music.setFile(i);
    music.play();
    music.loop();

  }

  public void stopMusic() {

    music.stop();

  }

  public void playSoundEffect(int i) {

    soundEffect.setFile(i);
    soundEffect.play();

  }
}
