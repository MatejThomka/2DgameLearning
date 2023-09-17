package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.UtilityTool;

public class SuperObject {

  public BufferedImage bufferedImage;
  public String name;
  public boolean collision = false;
  public int worldX, worldY;
  public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  public int solidAreaDefaultX = 0;
  public int solidAreaDefaultY = 0;
  UtilityTool utilityTool = new UtilityTool();


  public void draw(Graphics2D graphics2D, GamePanel gamePanel) {

    int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
    int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

    if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
        worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
        worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
        worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

      graphics2D.drawImage(bufferedImage, screenX, screenY, null);
    }

  }

}
