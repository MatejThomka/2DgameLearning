package objects;

import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Door extends SuperObject {

  GamePanel gamePanel;

  public OBJ_Door(GamePanel gamePanel) {

    this.gamePanel = gamePanel;

    name = "Door";
    try {
      bufferedImage = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
      bufferedImage = utilityTool.scaleImage(bufferedImage, gamePanel.tileSize, gamePanel.tileSize);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    collision = true;
  }

}
