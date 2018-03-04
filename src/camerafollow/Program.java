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

    World world = new World(new Vector2(60, 60));
    Player player = new Player();
    Camera camera = new Camera(new Vector2(30, 30), new Vector2(20, 10));
    camera.SetLimits(world.getWorldBounds());
    MapGenerator generator = new MapGenerator();
    Map map = generator.GenerateMap(60,60);

    Game game = new Game(world, camera, player, map);

    game.Play();
  }

  public void InitializeMap() {

  }

}
