/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camerafollow;

import static camerafollow.Map.GOBLIN;
import static camerafollow.Map.MONEY;
import java.util.Random;

/**
 *
 * @author Rob
 */
public class MapGenerator {

  public MapGenerator() {

  }

  public static Map GenerateMap(Vector2 mapSize) {

    Map map = new Map(mapSize.x, mapSize.y);

    AddSymbolToMap(map, Map.MONEY, 10);
    AddSymbolToMap(map, Map.GOBLIN, 5);
    AddSymbolToMap(map, Map.CHEST, 6);
    
    return map;
  }

  public static void AddSymbolToMap(Map map, char symbol, int symbolCount) {
     Random gridLocator = new Random();
     
     for (int i = 0; i < symbolCount; i++) {
      Vector2 treasureLocation = new Vector2(gridLocator.nextInt(map.GetMapBounds().right - 1),
              gridLocator.nextInt(map.GetMapBounds().bottom - 1));

      map.SetSymbolAtLocation(treasureLocation, symbol);
    }
  }
}
