/**
 * Copyright 2014 The PlayN Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package playn.showcase.robovm;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIDevice;
import org.robovm.apple.uikit.UIInterfaceOrientation;
import org.robovm.apple.uikit.UIInterfaceOrientationMask;

import playn.robovm.RoboPlatform;
import playn.showcase.core.Showcase;

public class ShowcaseRoboVM extends UIApplicationDelegateAdapter {

  @Override
  public boolean didFinishLaunching (UIApplication app, UIApplicationLaunchOptions launchOpts) {

    RoboPlatform.Config config = new RoboPlatform.Config();
    config.orients = UIInterfaceOrientationMask.All;
    RoboPlatform pf = RoboPlatform.register(app, config);
    // Retain platform object until the application is deallocated. Prevents Java GC from
    // collecting things too early.
    addStrongRef(pf);

    final Showcase game = new Showcase(new Showcase.DeviceService() {
                          public String info () {
                            UIDevice device = UIDevice.getCurrentDevice();
                            return "iOS [model=" + device.getModel() +
                              ", os=" + device.getSystemName() + "/" + device.getSystemVersion() +
                              ", name=" + device.getName() +
                              ", orient=" + device.getOrientation() + "]";
                          }
    });
    pf.setListener(new RoboPlatform.OrientationListener() {
      public void willRotate(UIInterfaceOrientation toOrient, double duration) {}
      public void didRotate(UIInterfaceOrientation orientation) {
        game.didRotate();
      }
    });
    pf.run(game);
    return true;
  }

  public static void main (String[] args) {
    NSAutoreleasePool pool = new NSAutoreleasePool();
    UIApplication.main(args, null, ShowcaseRoboVM.class);
    pool.close();
  }
}
