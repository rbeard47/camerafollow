/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camerafollow;

/**
 *
 * @author Rob
 */
public class World {

  private Vector2 worldSize;
  private Bound worldBoundaries;
  private char[][] worldMap;

  public World(Vector2 worldSize) {
    this.worldSize = worldSize;
    worldMap = new char[worldSize.x][worldSize.y];
    worldBoundaries = new Bound(0, 0, worldSize.x, worldSize.y);
  }
  
  public Bound getWorldBounds() {
    return worldBoundaries;
  }
}
