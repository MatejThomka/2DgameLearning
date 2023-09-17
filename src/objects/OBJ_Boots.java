package objects;

import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Boots extends SuperObject {

  GamePanel gamePanel;

  public OBJ_Boots(GamePanel gamePanel) {

    this.gamePanel = gamePanel;

    name = "Boots";
    try {
      bufferedImage = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("/objects/red_boots.png")));
      bufferedImage = utilityTool.scaleImage(bufferedImage, gamePanel.tileSize, gamePanel.tileSize);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
