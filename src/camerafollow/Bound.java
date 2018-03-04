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
public class Bound {
  public Integer left;
  public Integer right;
  public Integer top;
  public Integer bottom;
  
  public Bound(Integer left, Integer top, Integer right, Integer bottom)  {
    this.left = left;
    this.top = top;
    this.right = right;
    this.bottom = bottom;
  }
}
