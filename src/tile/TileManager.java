package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {

  GamePanel gamePanel;
  public Tile[] tiles;
  public int[][] mapTileNum;

  public TileManager(GamePanel gamePanel) {
    this.gamePanel = gamePanel;

    tiles = new Tile[21];
    mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

    getTileImage();
    loadMap("/maps/map01.txt");
  }

  public void getTileImage() {

      setup(0, "grass1", false);
      setup(1, "grass2", false);
      setup(2, "grass_with_wall1", false);
      setup(3, "grass_with_wall2", false);
      setup(4, "wall1", true);
      setup(5, "wall2", true);
      setup(6, "wall_corner", true);
      setup(7, "water1", true);
      setup(8, "water2", true);
      setup(9, "earth", false);
      setup(10, "sand", false);
      setup(11, "tree", true);
      setup(12, "shallow_water", true);
      setup(13, "water_cliffside_D", true);
      setup(14, "water_cliffside_DL", true);
      setup(15, "water_cliffside_DR", true);
      setup(16, "water_cliffside_L", true);
      setup(17, "water_cliffside_R", true);
      setup(18, "water_cliffside_U", true);
      setup(19, "water_cliffside_UL", true);
      setup(20, "water_cliffside_UR", true);

  }

  public void setup(int index, String imageName, boolean collision) {

    UtilityTool utilityTool = new UtilityTool();

    try {

      tiles[index] =new Tile();
      tiles[index].image = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
      tiles[index].image = utilityTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
      tiles[index].collision = collision;

    } catch (IOException exception) {
      exception.printStackTrace();
    }

  }

  public void loadMap(String mapPath) {
    try {

      InputStream inputStream = getClass().getResourceAsStream(mapPath);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

      int col = 0;
      int row = 0;

      while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

        String line = bufferedReader.readLine();

        while (col < gamePanel.maxWorldCol) {

          String[] numbers = line.split(" ");

          int num = Integer.parseInt(numbers[col]);

          mapTileNum[col][row] = num;
          col++;
        }
        if (col == gamePanel.maxWorldCol) {
          col = 0;
          row++;
        }
      }
      bufferedReader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics2D graphics2D) {

    int worldCol = 0;
    int worldRow = 0;

    while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

      int tileNum = mapTileNum[worldCol][worldRow];

      int worldX = worldCol * gamePanel.tileSize;
      int worldY = worldRow * gamePanel.tileSize;
      int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
      int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

      if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
          worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
          worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
          worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

        graphics2D.drawImage(tiles[tileNum].image, screenX, screenY, null);

      }

      worldCol++;

      if (worldCol == gamePanel.maxWorldCol) {
        worldCol = 0;
        worldRow++;
      }

    }
  }
}
