/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camerafollow;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rob
 */
public class Game {

  private World world;
  private Camera camera;
  private Player player;
  private Map map;

  public Game(Map map, Camera camera, Player player) {
    this.camera = camera;
    this.player = player;
    this.map = map;

    this.player.setPosition(
            new Vector2(
                    (map.GetMapBounds().right - map.GetMapBounds().left) / 2,
                    (map.GetMapBounds().bottom - map.GetMapBounds().top) / 2));
  }

  public void Play() {

    map.DrawMap(camera.getCameraPosition(), camera.getViewportSize());
    camera.CenterOverObject(player.getPosition());

    Scanner scanner = new Scanner(System.in);

    while (true) {

      /**
       * *******************************************
       * TAKE USER INPUT
       */
      Vector2 movementDirection = Vector2.ZeroVector;

      System.out.print("Enter a command: ");
      String input = scanner.nextLine();

      if (input != null && (input.length() > 0)) {

        char j = input.charAt(0);

        switch (j) {
          case 'i':
            movementDirection = Vector2.Up;
            break;
          case 'j':
            movementDirection = Vector2.Left;
            break;
          case 'k':
            movementDirection = Vector2.Right;
            break;
          case 'm':
            movementDirection = Vector2.Down;
            break;
          default:
            break;
        }
      }

      /**
       * ******************************************************
       * UPDATE ALL ENTITIES
       */
      boolean blockMovement = false;
      // Update position by adding the movement vector to the current
      // player position
      Vector2 currentPlayerPosition = player.getPosition().Add(movementDirection);

      // Clamp player's x location between the left and right world boundary
      currentPlayerPosition.x = Math.max(map.GetMapBounds().left,
              (Math.min(currentPlayerPosition.x, map.GetMapBounds().right - 1)));

      // Clamp player's y location between the top and bottom world boundary
      currentPlayerPosition.y = Math.max(map.GetMapBounds().top,
              (Math.min(currentPlayerPosition.y, map.GetMapBounds().bottom - 1)));

      // Check for collisions with walls
      char mapSymbol = map.GetSymbolAtLocation(currentPlayerPosition.x, currentPlayerPosition.y);

      switch (mapSymbol) {
        case Map.WALL:  // Wall, block movement
          blockMovement = true;
          break;

        case Map.MONEY:  // Money, add to score
          player.addMoney(10);
          System.out.println("Found money!");
          map.ClearSymbolAtLocation(currentPlayerPosition);
          break;

        case Map.GOBLIN:  // Goblin - fight...
          break;

        default:
          break;
      }

      if (!blockMovement) {
        // Set the current player position
        player.setPosition(currentPlayerPosition);
      }

      // Move the camera to follow the player
      camera.CenterOverObject(player.getPosition());

      /**
       * ************************************************
       * RENDER ALL ENTITIES BASED ON NEW STATE
       */
      Vector2 viewportSize = camera.getViewportSize();
      Vector2 cameraPosition = camera.getCameraPosition();

      // Get a "picture" of the currently visible portion of the map
      char[][] visibleMap = map.GetVisibleMap(camera.getCameraPosition(), viewportSize);

      // Add the player to the "picture"
      Vector2 playerVisiblePosition = new Vector2(player.getPosition().x - cameraPosition.x,
              player.getPosition().y - cameraPosition.y);

      map.SetSymbolAtLocation(playerVisiblePosition, mapSymbol);
      visibleMap[playerVisiblePosition.x][playerVisiblePosition.y] = Map.PLAYER;

      for (int y = 0; y < viewportSize.y; y++) {
        for (int x = 0; x < viewportSize.x; x++) {
          System.out.print(visibleMap[x][y]);
        }
        System.out.println();
      }
    }
  }
}
