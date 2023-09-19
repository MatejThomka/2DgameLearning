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

    tiles = new Tile[51];
    mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

    getTileImage();
    loadMap("/maps/map01.txt");
  }

  public void getTileImage() {

      setup(0, "grass", false);
      setup(1, "flower_grass", false);
      setup(2, "dirt_path_T_D", false);
      setup(3, "dirt_path_T_L", false);
      setup(4, "wall1", true);
      setup(5, "dirt_path_T_R", true);
      setup(6, "dirt_path_T_U", true);
      setup(7, "water1", true);
      setup(8, "water2", true);
      setup(9, "dirt", false);
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
      setup(21, "dirt_grass_D", false);
      setup(22, "dirt_grass_DL", false);
      setup(23, "dirt_grass_DR", false);
      setup(24, "dirt_grass_L", false);
      setup(25, "dirt_grass_LD", false);
      setup(26, "dirt_grass_LU", false);
      setup(27, "dirt_grass_R", false);
      setup(28, "dirt_grass_RD", false);
      setup(29, "dirt_grass_RU", false);
      setup(30, "dirt_grass_U", false);
      setup(31, "dirt_grass_UL", false);
      setup(32, "dirt_grass_UR", false);
      setup(33, "sand_dirt_D", false);
      setup(34, "sand_dirt_L", false);
      setup(35, "sand_dirt_R", false);
      setup(36, "sand_dirt_U", false);
      setup(37, "dirt_path_cross", false);
      setup(38, "dirt_path_horizontal", false);
      setup(39, "dirt_path_vertical", false);
      setup(40, "dirt_path_downToLeft", false);
      setup(41, "dirt_path_downToRight", false);
      setup(42, "dirt_path_upToLeft", false);
      setup(43, "dirt_path_upToRight", false);
      setup(44, "dirt_path_startOfPath_D", false);
      setup(45, "dirt_path_startOfPath_L", false);
      setup(46, "dirt_path_startOfPath_R", false);
      setup(47, "dirt_path_startOfPath_U", false);


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
