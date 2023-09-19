package main;

import objects.OBJ_Boots;
import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_Key;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
      this.gamePanel = gamePanel;
    }

    public void setObject() {

      gamePanel.superObject[0] = new OBJ_Key(gamePanel);
      gamePanel.superObject[0].worldX = 71 * gamePanel.tileSize;
      gamePanel.superObject[0].worldY = 25 * gamePanel.tileSize;

      gamePanel.superObject[1] = new OBJ_Key(gamePanel);
      gamePanel.superObject[1].worldX = 24 * gamePanel.tileSize;
      gamePanel.superObject[1].worldY = 7 * gamePanel.tileSize;

      gamePanel.superObject[2] = new OBJ_Key(gamePanel);
      gamePanel.superObject[2].worldX = 65 * gamePanel.tileSize;
      gamePanel.superObject[2].worldY = 9 * gamePanel.tileSize;

      gamePanel.superObject[3] = new OBJ_Door(gamePanel);
      gamePanel.superObject[3].worldX = 24 * gamePanel.tileSize;
      gamePanel.superObject[3].worldY = 10 * gamePanel.tileSize;

      gamePanel.superObject[4] = new OBJ_Door(gamePanel);
      gamePanel.superObject[4].worldX = 8 * gamePanel.tileSize;
      gamePanel.superObject[4].worldY = 49 * gamePanel.tileSize;

      gamePanel.superObject[5] = new OBJ_Door(gamePanel);
      gamePanel.superObject[5].worldX = 71 * gamePanel.tileSize;
      gamePanel.superObject[5].worldY = 10 * gamePanel.tileSize;

      gamePanel.superObject[6] = new OBJ_Door(gamePanel);
      gamePanel.superObject[6].worldX = 65 * gamePanel.tileSize;
      gamePanel.superObject[6].worldY = 15 * gamePanel.tileSize;

      gamePanel.superObject[7] = new OBJ_Chest(gamePanel);
      gamePanel.superObject[7].worldX = 6 * gamePanel.tileSize;
      gamePanel.superObject[7].worldY = 49 * gamePanel.tileSize;

      gamePanel.superObject[8] = new OBJ_Boots(gamePanel);
      gamePanel.superObject[8].worldX = 7 * gamePanel.tileSize;
      gamePanel.superObject[8].worldY = 27 * gamePanel.tileSize;
    }
}
