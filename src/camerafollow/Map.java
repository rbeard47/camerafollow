/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camerafollow;

import java.util.Random;

/**
 *
 * @author Rob
 */
public class Map {

  private final char[][] map;
  private Vector2 screenSize;

  public static final char PLAYER = '@';
  public static final char MONEY = '$';
  public static final char WALL = '#';
  public static final char FLOOR = '.';
  public static final char GOBLIN = 'G';
  public static final char CHEST = 'C';

  public Map(int mapWidth, int mapHeight) {
    screenSize = new Vector2(mapWidth, mapHeight);
    map = new char[mapWidth][mapHeight];
    Random mapSymbolGenerator = new Random();

    for (int x = 0; x < mapWidth; x++) {
      for (int y = 0; y < mapHeight; y++) {
        
        Vector2 symbolLocation = new Vector2(x,y);
        if (x == 0 || (x + 1) == mapWidth || y == 0 || (y + 1) == mapHeight) {
          this.SetSymbolAtLocation(symbolLocation, WALL);
        } else {
          this.SetSymbolAtLocation(symbolLocation, FLOOR);
        }
      }
    }
  }

  public void DrawMap(Vector2 startingPoint, Vector2 screenSize) {
    for (int y = startingPoint.y; y < startingPoint.y + screenSize.y; y++) {
      for (int x = startingPoint.x; x < startingPoint.x + screenSize.x; x++) {
        System.out.print(map[x][y]);
      }
      System.out.println();
    }
  }

  // Generate a "picture" of the visible portion of the map
  public char[][] GetVisibleMap(Vector2 startingPoint, Vector2 screenSize) {

    char[][] visibleArea = new char[screenSize.x][screenSize.y];

    for (int vy = 0, y = startingPoint.y; y < startingPoint.y + screenSize.y; y++, vy++) {
      for (int vx = 0, x = startingPoint.x; x < startingPoint.x + screenSize.x; x++, vx++) {
        visibleArea[vx][vy] = map[x][y];
      }
    }

    return visibleArea;
  }

  public char GetSymbolAtLocation(int x, int y) {
    return map[x][y];
  }

  public void SetSymbolAtLocation(Vector2 location, char symbol) {
    map[location.x][location.y] = symbol;
  }

  public void ClearSymbolAtLocation(Vector2 location) {
    map[location.x][location.y] = '.';
  }

  public Bound GetMapBounds() {
    return new Bound(0, 0, screenSize.x, screenSize.y);
  }
}
