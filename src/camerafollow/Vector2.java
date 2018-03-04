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
public class Vector2 {

  public int x;
  public int y;

  public static Vector2 ZeroVector = new Vector2(0, 0);
  public static Vector2 Up = new Vector2(0, -1);
  public static Vector2 Down = new Vector2(0, 1);
  public static Vector2 Left = new Vector2(-1, 0);
  public static Vector2 Right = new Vector2(1, 0);

  public Vector2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Vector2 Add(Vector2 a) {
    return new Vector2(this.x + a.x, this.y + a.y);
  }
}
