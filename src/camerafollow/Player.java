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
public class Player {

  private Vector2 position;
  private Vector2 velocity;
  private int money;
  private int health;
  boolean dead;

  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }

  public Vector2 getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector2 velocity) {
    this.velocity = velocity;
  }

  public Player() {
    position = Vector2.ZeroVector;
    velocity = Vector2.ZeroVector;
  }

  public Player(Vector2 initialPosition) {
    position = initialPosition;
    velocity = Vector2.ZeroVector;
  }
  
  public void addMoney(int amount) {
    money += amount;
  }
  
  public void takeDamage(int amount) {
    health -= amount;
  }
  
  public boolean isPlayerDead() {
    return dead;
  }
}
