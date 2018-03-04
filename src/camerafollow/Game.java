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

  public Game(World world, Camera camera, Player player, Map map) {
    this.world = world;
    this.camera = camera;
    this.player = player;
    this.map = map;

    this.player.setPosition(
            new Vector2(
                    (world.getWorldBounds().right - world.getWorldBounds().left) / 2,
                    (world.getWorldBounds().bottom - world.getWorldBounds().top) / 2));
  }

  public void Play() {

    Scanner scanner = new Scanner(System.in);

    map.DrawMap(camera.getCameraPosition(), camera.getViewportSize());

    while (true) {

      // Take user input
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

      boolean blockMovement = false;
      // Update position by adding the movement vector to the current
      // player position
      Vector2 currentPlayerPosition = player.getPosition().Add(movementDirection);

      // Clamp player's x location between the left and right world boundary
      currentPlayerPosition.x = Math.max(world.getWorldBounds().left,
              (Math.min(currentPlayerPosition.x, world.getWorldBounds().right - 1)));

      // Clamp player's y location between the top and bottom world boundary
      currentPlayerPosition.y = Math.max(world.getWorldBounds().top,
              (Math.min(currentPlayerPosition.y, world.getWorldBounds().bottom - 1)));

      // Check for collisions with walls

      char mapSymbol = map.GetSymbolAtLocation(currentPlayerPosition.x, currentPlayerPosition.y);
      
      switch(mapSymbol) {
        case '#':  // Wall, block movement
          blockMovement = true;
          break;
          
        case Map.MONEY:  // Money, add to score
          player.addMoney(10);
          System.out.println("Found money!");
          map.ClearSymbolAtLocation(currentPlayerPosition.x, currentPlayerPosition.y);
          break;
          
        case Map.GOBLIN:  // Goblin - fight...
          break;
          
        default:
          break;
      }
      
      if(!blockMovement) {
      // Set the current player position
      player.setPosition(currentPlayerPosition);
      }

      // Move the camera to follow the player
      camera.CenterOverObject(player.getPosition());

//      System.out.format("Player (%d,%d), Camera (%d, %d)\n",
//              currentPlayerPosition.x, currentPlayerPosition.y,
//              camera.getCameraPosition().x, camera.getCameraPosition().y);

      // Render
      
      Vector2 viewportSize = camera.getViewportSize();
      Vector2 cameraPosition = camera.getCameraPosition();

      char[][] visibleMap = map.GetVisibleMap(camera.getCameraPosition(), viewportSize);

      Vector2 playerVisiblePosition = new Vector2(player.getPosition().x - cameraPosition.x,
              player.getPosition().y - cameraPosition.y);

      visibleMap[playerVisiblePosition.x][playerVisiblePosition.y] = 'K';

      for (int y = 0; y < viewportSize.y; y++) {
        for (int x = 0; x < viewportSize.x; x++) {
          System.out.print(visibleMap[x][y]);
        }
        System.out.println();
      }

      // Draw screen again.
    }
  }
}
