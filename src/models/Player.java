package models;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Character{
  GamePanel gamePanel;
  KeyHandler keyHandler;

  public final int screenX;
  public final int screenY;
  public int hasKey = 0;
  int standCounter = 0;

  public Player(GamePanel gamePanel, KeyHandler keyHandler) {
    this.gamePanel = gamePanel;
    this.keyHandler = keyHandler;

    screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
    screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

    solidArea = new Rectangle();
    solidArea.x = 16;
    solidArea.y = 26;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    solidArea.width = 10;
    solidArea.height = 12;

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {

    worldX = gamePanel.tileSize * 6;
    worldY = gamePanel.tileSize * 6;
    speed = 4;
    direction = "down";

  }

  public void getPlayerImage() {

    up = setup("up_stay");
    up1 = setup("up1");
    up2 = setup("up2");
    down = setup("down_stay");
    down1 = setup("down1");
    down2 = setup("down2");
    left = setup("left_stay");
    left1 = setup("left1");
    left2 = setup("left2");
    right = setup("right_stay");
    right1 = setup("right1");
    right2 = setup("right2");

  }

  public BufferedImage setup(String imageName) {

    UtilityTool utilityTool = new UtilityTool();
    BufferedImage image = null;

    try {
      image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
      image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return image;
  }

  public void update() {

    if (keyHandler.downPressed || keyHandler.leftPressed ||
        keyHandler.upPressed || keyHandler.rightPressed) {

      if (keyHandler.upPressed) direction = "up";
      else if (keyHandler.downPressed) direction = "down";
      else if (keyHandler.leftPressed) direction = "left";
      else if (keyHandler.rightPressed) direction = "right";


      // CHECK TILE COLLISION
      collisionOn = false;
      gamePanel.collisionChecker.checkTile(this);

      // CHECK OBJECT COLLISION
      int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
      pickUpObject(objectIndex);

      // IF COLLISION IS FALSE, PLAYER CAN MOVE
      if (!collisionOn) {
        switch (direction) {
          case "up": worldY -= speed; break;
          case "down": worldY += speed; break;
          case "left": worldX -= speed; break;
          case "right": worldX += speed; break;
        }
      }

      spriteCounter++;
      if (spriteCounter > 5) {
        if (spriteNum == 1) {
          spriteNum = 2;
        } else if (spriteNum == 2) {
          spriteNum = 3;
        } else if (spriteNum == 3) {
          spriteNum = 1;
        }
        spriteCounter = 0;
      }

    } else {
      standCounter++;

      if (standCounter == 20) {
        spriteNum = 2;
        standCounter = 0;
      }

    }

  }

  public void pickUpObject(int index) {

    if (index != 999) {

      String objectName = gamePanel.superObject[index].name;

      switch (objectName) {
        case "Key": gamePanel.playSoundEffect(1);
                    hasKey++;
                    gamePanel.superObject[index] = null;
                    gamePanel.ui.showMessage("You found a key!");
                    break;

        case "Door": if (hasKey > 0) {
                        gamePanel.playSoundEffect(3);
                        gamePanel.superObject[index] = null;
                        hasKey--;
                        gamePanel.ui.showMessage("You opened the door!");
                     }
                     else gamePanel.ui.showMessage("You need a key!");

                     System.out.println("Key: " + hasKey);
                     break;

        case "Boots": gamePanel.playSoundEffect(2);
                      speed += 2;
                      gamePanel.superObject[index] = null;
                      gamePanel.ui.showMessage("You picked up a boots! You are faster now!");
                      break;

        case "Chest": gamePanel.playSoundEffect(4);
                      gamePanel.superObject[index] = null;
                      gamePanel.ui.gameFinished = true;
                      gamePanel.stopMusic();
                      break;
      }
    }
  }

  public void draw(Graphics2D graphics2D) {

    BufferedImage image = null;

    switch (direction) {
      case "up": if (spriteNum == 1) image = up1;
                 if (spriteNum == 2) image = up;
                 if (spriteNum == 3) image = up2;
                 break;

      case "down": if (spriteNum == 1) image = down1;
                   if (spriteNum == 2) image = down;
                   if (spriteNum == 3) image = down2;
                   break;

      case "left": if (spriteNum == 1) image = left1;
                   if (spriteNum == 2) image = left;
                   if (spriteNum == 3) image = left2;
                   break;

      case "right": if (spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right;
                    if (spriteNum == 3) image = right2;
                    break;

    }

    graphics2D.drawImage(image, screenX, screenY, null);
  }

}