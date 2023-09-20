package objects;

import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Key extends SuperObject{

  GamePanel gamePanel;

  public OBJ_Key(GamePanel gamePanel) {

    this.gamePanel = gamePanel;

    name = "Key";
    try {
      bufferedImage = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
      bufferedImage = utilityTool.scaleImage(bufferedImage, gamePanel.tileSize, gamePanel.tileSize);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

}
