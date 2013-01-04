/**
 * Copyright 2011 The PlayN Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package playn.showcase.core.peas.entities;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import playn.core.Image;
import playn.showcase.core.peas.PeaWorld;

public class Block extends StaticPhysicsEntity {
  public static String TYPE = "Block";

  public Block(final PeaWorld peaWorld, World world, float x, float y, float angle) {
    super(peaWorld, world, x, y, angle);
  }

  @Override
  Body initPhysicsBody(World world, float x, float y, float angle) {
    FixtureDef fixtureDef = new FixtureDef();
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyType.StaticBody;
    bodyDef.position.set(0, 0);
    Body body = world.createBody(bodyDef);

    PolygonShape polygonShape = new PolygonShape();
    Vector2[] polygon = new Vector2[4];
    polygon[0] = new Vector2(-getWidth()/2f, -getHeight()/2f + getTopOffset());
    polygon[1] = new Vector2(getWidth()/2f, -getHeight()/2f + getTopOffset());
    polygon[2] = new Vector2(getWidth()/2f, getHeight()/2f);
    polygon[3] = new Vector2(-getWidth()/2f, getHeight()/2f);
    polygonShape.set(polygon);
    fixtureDef.shape = polygonShape;
    fixtureDef.friction = 0.1f;
    fixtureDef.restitution = 0.8f;
    body.createFixture(fixtureDef);
    body.setTransform(new Vector2(x, y), angle);
    return body;
  }

  @Override
  float getWidth() {
    return 2.0f;
  }

  @Override
  float getHeight() {
    return 2.0f;
  }

  /**
   * Return the size of the offset where the block is slightly lower than where
   * the image is drawn for a depth effect
   */
  public float getTopOffset() {
    return 2.0f / 8f;
  }

  @Override
  public Image getImage() {
    return image;
  }

  private static Image image = loadImage("Block-Normal.png");
}
