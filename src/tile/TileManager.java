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

    tiles = new Tile[69];
    mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

    getTileImage();
    loadMap("/maps/map01.txt");
  }

  public void getTileImage() {

      setup(0, "grass", false);
      setup(1, "flowers", false);
      setup(2, "dirt-path-crossT-top", false);
      setup(3, "dirt-path-crossT-right", false);
      setup(4, "wall", true);
      setup(5, "dirt-path-crossT-left", false);
      setup(6, "dirt-path-crossT-bottom", false);
      setup(7, "dirt-grass", false);
      setup(8, "wooden_bridge", false);
      setup(9, "dirt", false);
      setup(10, "sand", false);
      setup(11, "tree-1", true);
      setup(12, "water-1", true);
      setup(13, "waterside-bottom-1", true);
      setup(14, "waterside-bottom-left-1", true);
      setup(15, "waterside-bottom-right-1", true);
      setup(16, "waterside-left-1", true);
      setup(17, "waterside-right-1", true);
      setup(18, "waterside-top-1", true);
      setup(19, "waterside-top-left-1", true);
      setup(20, "waterside-top-right-1", true);
      setup(21, "dirt-grass-bottom", false);
      setup(22, "dirt-grass-left-bottom", false);
      setup(23, "dirt-grass-right-bottom", false);
      setup(24, "dirt-grass-left", false);
      setup(25, "dirt-grass-bottom-left", false);
      setup(26, "dirt-grass-top-left", false);
      setup(27, "dirt-grass-right", false);
      setup(28, "dirt-grass-bottom-right", false);
      setup(29, "dirt-grass-top-right", false);
      setup(30, "dirt-grass-top", false);
      setup(31, "dirt-grass-left-top", false);
      setup(32, "dirt-grass-right-top", false);
      setup(33, "dirt", false);
      setup(34, "dirt", false);
      setup(35, "dirt", false);
      setup(36, "dirt", false);
      setup(37, "dirt-path-cross", false);
      setup(38, "dirt-path-horizontal", false);
      setup(39, "dirt-path-vertical", false);
      setup(40, "dirt-path-bottomToLeft", false);
      setup(41, "dirt-path-bottomToRight", false);
      setup(42, "dirt-path-topToLeft", false);
      setup(43, "dirt-path-topToRight", false);
      setup(44, "dirt-pathStart-bottom", false);
      setup(45, "dirt-pathStart-left", false);
      setup(46, "dirt-pathStart-right", false);
      setup(47, "dirt-pathStart-top", false);
      setup(48, "sand-grass", false);
      setup(49, "sand-grass-bottom", false);
      setup(50, "sand-grass-bottom-left", false);
      setup(51, "sand-grass-bottom-right", false);
      setup(52, "sand-grass-left", false);
      setup(53, "sand-grass-right", false);
      setup(54, "sand-grass-top", false);
      setup(55, "sand-grass-top-left", false);
      setup(56, "sand-grass-top-right", false);
      setup(57, "beach-left-bottom-2", true);
      setup(58, "beach-right-bottom-2", true);
      setup(59, "beach-left-top-2", true);
      setup(60, "beach-right-top-2", true);
      setup(61, "beach-bottom-2", true);
      setup(62, "beach-bottom-left-2", true);
      setup(63, "beach-bottom-right-2", true);
      setup(64, "beach-left-2", true);
      setup(65, "beach-right-2", true);
      setup(66, "beach-top-2", true);
      setup(67, "beach-top-left-2", true);
      setup(68, "beach-top-right-2", true);


  }

  public void setup(int index, String imageName, boolean collision) {

    UtilityTool utilityTool = new UtilityTool();

    try {

      tiles[index] =new Tile();
      tiles[index].image = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("/" + imageName + ".png")));
      tiles[index].image = utilityTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
      tiles[index].collision = collision;

    } catch (IOException exception) {
      exception.printStackTrace();
    }

  }

  public void loadMap(String mapPath) {
    try {

      InputStream inputStream = getClass().getResourceAsStream(mapPath);
      assert inputStream != null;
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
