package main;

import models.Character;

public class CollisionChecker {

  GamePanel gamePanel;
  public CollisionChecker(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public void checkTile(Character character) {
    int characterLeftWorldX = character.worldX + character.solidArea.x;
    int characterRightWorldX = character.worldX + character.solidArea.x + character.solidArea.width;
    int characterTopWorldY = character.worldY + character.solidArea.y;
    int characterBottomWorldY = character.worldY + character.solidArea.y + character.solidArea.height;

    int characterLeftCol = characterLeftWorldX / gamePanel.tileSize;
    int characterRightCol = characterRightWorldX / gamePanel.tileSize;
    int characterTopRow = characterTopWorldY / gamePanel.tileSize;
    int characterBottomRow = characterBottomWorldY / gamePanel.tileSize;

    int tileNum1, tileNum2;

    switch (character.direction) {
      case "up":
        characterTopRow = (characterTopWorldY - character.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tileManager.mapTileNum[characterLeftCol][characterTopRow];
        tileNum2 = gamePanel.tileManager.mapTileNum[characterRightCol][characterTopRow];
        if (gamePanel.tileManager.tiles[tileNum1].collision ||
            gamePanel.tileManager.tiles[tileNum2].collision) {
          character.collisionOn = true;
        }
        break;
      case "down":
        characterBottomRow = (characterBottomWorldY + character.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tileManager.mapTileNum[characterLeftCol][characterBottomRow];
        tileNum2 = gamePanel.tileManager.mapTileNum[characterRightCol][characterBottomRow];
        if (gamePanel.tileManager.tiles[tileNum1].collision ||
            gamePanel.tileManager.tiles[tileNum2].collision) {
          character.collisionOn = true;
        }
        break;
      case "left":
        characterLeftCol = (characterLeftWorldX - character.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tileManager.mapTileNum[characterLeftCol][characterTopRow];
        tileNum2 = gamePanel.tileManager.mapTileNum[characterLeftCol][characterBottomRow];
        if (gamePanel.tileManager.tiles[tileNum1].collision ||
            gamePanel.tileManager.tiles[tileNum2].collision) {
          character.collisionOn = true;
        }
        break;
      case "right":
        characterRightCol = (characterRightWorldX + character.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tileManager.mapTileNum[characterRightCol][characterTopRow];
        tileNum2 = gamePanel.tileManager.mapTileNum[characterRightCol][characterBottomRow];
        if (gamePanel.tileManager.tiles[tileNum1].collision ||
            gamePanel.tileManager.tiles[tileNum2].collision) {
          character.collisionOn = true;
        }
        break;
    }
  }

  public int checkObject(Character character, boolean player) {

    int index = 999;

    for (int i = 0; i < gamePanel.superObject.length; i++) {
      if (gamePanel.superObject[i] != null) {
        // Get character's solid area position
        character.solidArea.x = character.worldX + character.solidArea.x;
        character.solidArea.y = character.worldY + character.solidArea.y;

        // Get the object's solid area position
        gamePanel.superObject[i].solidArea.x = gamePanel.superObject[i].worldX + gamePanel.superObject[i].solidArea.x;
        gamePanel.superObject[i].solidArea.y = gamePanel.superObject[i].worldY + gamePanel.superObject[i].solidArea.y;

        switch (character.direction) {
          case "up":
            character.solidArea.y -= character.speed;
            if (character.solidArea.intersects(gamePanel.superObject[i].solidArea)) {
              if (gamePanel.superObject[i].collision) {
                character.collisionOn = true;
              }
              if (player) {
                index = i;
              }
            }
            break;
          case "down":
            character.solidArea.y += character.speed;
            if (character.solidArea.intersects(gamePanel.superObject[i].solidArea)) {
              if (gamePanel.superObject[i].collision) {
                character.collisionOn = true;
              }
              if (player) {
                index = i;
              }
            }
            break;
          case "left":
            character.solidArea.x -= character.speed;
            if (character.solidArea.intersects(gamePanel.superObject[i].solidArea)) {
              if (gamePanel.superObject[i].collision) {
                character.collisionOn = true;
              }
              if (player) {
                index = i;
              }
            }
            break;
          case "right":
            character.solidArea.x += character.speed;
            if (character.solidArea.intersects(gamePanel.superObject[i].solidArea)) {
              if (gamePanel.superObject[i].collision) {
                character.collisionOn = true;
              }
              if (player) {
                index = i;
              }
            }
            break;
        }
        character.solidArea.x = character.solidAreaDefaultX;
        character.solidArea.y = character.solidAreaDefaultY;
        gamePanel.superObject[i].solidArea.x = gamePanel.superObject[i].solidAreaDefaultX;
        gamePanel.superObject[i].solidArea.y = gamePanel.superObject[i].solidAreaDefaultY;
      }
    }

    return index;
  }
}
