package objects;

import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Chest extends SuperObject {

  GamePanel gamePanel;

  public OBJ_Chest(GamePanel gamePanel) {

    this.gamePanel = gamePanel;

    name = "Chest";
    try {
      bufferedImage = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("/objects/treasure_chest.png")));
      bufferedImage = utilityTool.scaleImage(bufferedImage, gamePanel.tileSize, gamePanel.tileSize);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

}
