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
package playn.sample.cute.robovm;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;

import playn.robovm.RoboPlatform;
import playn.sample.cute.core.CuteGame;

public class CuteGameRoboVM extends UIApplicationDelegateAdapter {

  @Override
  public boolean didFinishLaunching (UIApplication app, UIApplicationLaunchOptions launchOpts) {

    RoboPlatform pf = RoboPlatform.register(app);
    // Retain platform object until the application is deallocated. Prevents Java GC from
    // collecting things too early.
    addStrongRef(pf);

    pf.run(new CuteGame());
    return true;
  }

  public static void main (String[] args) {
    NSAutoreleasePool pool = new NSAutoreleasePool();
    UIApplication.main(args, null, CuteGameRoboVM.class);
    pool.close();
  }
}
