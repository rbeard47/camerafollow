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
public class Camera {

  private Vector2 cameraPosition;
  private Vector2 viewportSize;
  private Bound movementLimits;

  public Vector2 getCameraPosition() {
    return cameraPosition;
  }

  public void setCameraPosition(Vector2 cameraPosition) {
    this.cameraPosition = cameraPosition;
  }

  public Vector2 getViewportSize() {
    return viewportSize;
  }

  public void setViewportSize(Vector2 viewportSize) {
    this.viewportSize = viewportSize;
  }

  public Camera(Vector2 initialPosition, Vector2 viewportSize) {
    cameraPosition = initialPosition;
    this.viewportSize = viewportSize;
  }

  public void SetLimits(Bound limits) {
    movementLimits = limits;
  }

  public void CenterOverObject(Vector2 objectLocation) {

    // Clamp the camera location
    cameraPosition.x = Math.max(movementLimits.left,
            Math.min(objectLocation.x - (viewportSize.x / 2), movementLimits.right - viewportSize.x));

    cameraPosition.y = Math.max(movementLimits.top,
            Math.min(objectLocation.y - viewportSize.y / 2, movementLimits.bottom - viewportSize.y));
  }

}
