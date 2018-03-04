/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camerafollow;

import java.util.ArrayList;

/**
 *
 * @author Rob
 */
public class Program {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here

    Player player = new Player();
    Camera camera = new Camera(new Vector2(30, 30), new Vector2(20, 10));
    Vector2 mapSize = new Vector2(60, 60);
    MapGenerator generator = new MapGenerator();
    Map map = generator.GenerateMap(mapSize);

    camera.SetLimits(map.GetMapBounds());

    Game game = new Game(map, camera, player);

    game.Play();
  }

  public void InitializeMap() {

  }

}
