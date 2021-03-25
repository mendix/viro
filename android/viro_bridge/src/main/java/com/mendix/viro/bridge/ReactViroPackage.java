//  Copyright © 2016 Viro Media. All rights reserved.
//
//  Permission is hereby granted, free of charge, to any person obtaining
//  a copy of this software and associated documentation files (the
//  "Software"), to deal in the Software without restriction, including
//  without limitation the rights to use, copy, modify, merge, publish,
//  distribute, sublicense, and/or sell copies of the Software, and to
//  permit persons to whom the Software is furnished to do so, subject to
//  the following conditions:
//
//  The above copyright notice and this permission notice shall be included
//  in all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
//  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
//  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
//  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
//  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
//  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package com.mendix.viro.bridge;

import android.util.Log;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import com.mendix.viro.bridge.component.VRT3DSceneNavigatorManager;
import com.mendix.viro.bridge.component.VRTAnimatedComponentManager;
import com.mendix.viro.bridge.component.VRTControllerManager;
import com.mendix.viro.bridge.component.VRT360ImageManager;
import com.mendix.viro.bridge.component.VRTAmbientLightManager;
import com.mendix.viro.bridge.component.VRTDirectionalLightManager;
import com.mendix.viro.bridge.component.VRTLightingEnvironmentManager;
import com.mendix.viro.bridge.component.VRTMaterialVideoManager;
import com.mendix.viro.bridge.component.VRTOmniLightManager;
import com.mendix.viro.bridge.component.VRTSkyBoxManager;
import com.mendix.viro.bridge.component.VRTSoundFieldManager;
import com.mendix.viro.bridge.component.VRTSoundManager;
import com.mendix.viro.bridge.component.VRTSpatialSoundManager;
import com.mendix.viro.bridge.component.VRTSpotLightManager;
import com.mendix.viro.bridge.component.VRTVRSceneNavigatorManager;
import com.mendix.viro.bridge.component.node.VRTARImageMarkerManager;
import com.mendix.viro.bridge.component.node.VRTARObjectMarkerManager;
import com.mendix.viro.bridge.component.node.VRTARPlaneManager;
import com.mendix.viro.bridge.component.node.VRTARSceneManager;
import com.mendix.viro.bridge.component.node.VRTSceneManagerImpl;
import com.mendix.viro.bridge.component.node.control.VRT3DObjectManager;
import com.mendix.viro.bridge.component.node.control.VRTAnimatedImageManager;
import com.mendix.viro.bridge.component.node.control.VRTGeometryManager;
import com.mendix.viro.bridge.component.node.control.VRTParticleEmitterManager;
import com.mendix.viro.bridge.component.node.control.VRTPolygonManager;
import com.mendix.viro.bridge.component.node.control.VRTPolylineManager;
import com.mendix.viro.bridge.component.node.control.VRTTextManager;
import com.mendix.viro.bridge.component.VRT360VideoManager;
import com.mendix.viro.bridge.component.node.VRTCameraManager;
import com.mendix.viro.bridge.component.node.VRTOrbitCameraManager;
import com.mendix.viro.bridge.component.node.VRTFlexViewManager;
import com.mendix.viro.bridge.component.node.VRTNodeManagerImpl;
import com.mendix.viro.bridge.component.node.control.VRTBoxManager;
import com.mendix.viro.bridge.component.VRTSceneNavigatorManager;
import com.mendix.viro.bridge.component.VRTARSceneNavigatorManager;
import com.mendix.viro.bridge.component.node.control.VRTSphereManager;
import com.mendix.viro.bridge.component.node.control.VRTImageManager;
import com.mendix.viro.bridge.component.node.control.VRTQuadManager;
import com.mendix.viro.bridge.component.node.control.VRTVideoSurfaceManager;
import com.mendix.viro.bridge.component.node.VRTPortalSceneManager;
import com.mendix.viro.bridge.component.node.VRTPortalManager;


import com.mendix.viro.bridge.module.ARSceneModule;
import com.mendix.viro.bridge.module.ARSceneNavigatorModule;
import com.mendix.viro.bridge.module.ARTrackingTargetsModule;
import com.mendix.viro.bridge.module.AnimationManager;
import com.mendix.viro.bridge.module.CameraModule;
import com.mendix.viro.bridge.module.ControllerModule;
import com.mendix.viro.bridge.module.MaterialManager;
import com.mendix.viro.bridge.module.NodeModule;
import com.mendix.viro.bridge.module.SceneModule;
import com.mendix.viro.bridge.module.SceneNavigatorModule;
import com.mendix.viro.bridge.module.PerfMonitor;
import com.mendix.viro.bridge.module.SoundModule;
import com.mendix.viro.bridge.module.VRT3DSceneNavigatorModule;
import com.mendix.viro.bridge.module.VRTImageModule;

import java.util.Arrays;
import java.util.List;

/**
 * ReactViroPackage class containing an array of all ViroViewManagers to be created.
 */
public class ReactViroPackage implements ReactPackage {
    public static final String ON_EXIT_VIRO_BROADCAST ="com.viromedia.bridge.broadcast.OnExitViro";

    public enum ViroPlatform {
        GVR, OVR_MOBILE, AR
    }

    private final ViroPlatform mViroPlatform;

    public ReactViroPackage(ViroPlatform platform) {
        mViroPlatform = platform;
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        Log.e("Manish", "createNativeModules");
        return Arrays.<NativeModule>asList(
                new MaterialManager(reactContext),
                new AnimationManager(reactContext),
                new CameraModule(reactContext),
                new SoundModule(reactContext),
                new SceneNavigatorModule(reactContext),
                new PerfMonitor(reactContext),
                new ControllerModule(reactContext),
                new NodeModule(reactContext),
                new SceneModule(reactContext),
                new VRTImageModule(reactContext),
                new ARSceneModule(reactContext),
                new ARSceneNavigatorModule(reactContext),
                new ARTrackingTargetsModule(reactContext),
                new VRT3DSceneNavigatorModule(reactContext)
        );
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new VRTSceneNavigatorManager(reactContext, mViroPlatform),
                new VRTVRSceneNavigatorManager(reactContext, mViroPlatform),
                new VRTSceneManagerImpl(reactContext),
                new VRT3DSceneNavigatorManager(reactContext, mViroPlatform),
                new VRTBoxManager(reactContext),
                new VRTGeometryManager(reactContext),
                new VRTVideoSurfaceManager(reactContext),
                new VRT360VideoManager(reactContext),
                new VRTNodeManagerImpl(reactContext),
                new VRTCameraManager(reactContext),
                new VRTOrbitCameraManager(reactContext),
                new VRTSphereManager(reactContext),
                new VRTImageManager(reactContext),
                new VRT360ImageManager(reactContext),
                new VRTSkyBoxManager(reactContext),
                new VRTFlexViewManager(reactContext),
                new VRTAnimatedComponentManager(reactContext),
                new VRTQuadManager(reactContext),
                new VRTAnimatedImageManager(reactContext),
                new VRTPolygonManager(reactContext),
                new VRTFlexViewManager(reactContext),
                new VRTDirectionalLightManager(reactContext),
                new VRTAmbientLightManager(reactContext),
                new VRTSpotLightManager(reactContext),
                new VRTOmniLightManager(reactContext),
                new VRTSoundManager(reactContext),
                new VRTSoundFieldManager(reactContext),
                new VRTSpatialSoundManager(reactContext),
                new VRTOmniLightManager(reactContext),
                new VRTTextManager(reactContext),
                new VRT3DObjectManager(reactContext),
                new VRTControllerManager(reactContext),
                new VRTPolylineManager(reactContext),
                new VRTParticleEmitterManager(reactContext),
                new VRTPortalSceneManager(reactContext),
                new VRTPortalManager(reactContext),
                new VRTLightingEnvironmentManager(reactContext),
                new VRTMaterialVideoManager(reactContext),
                // AR Components
                new VRTARSceneNavigatorManager(reactContext),
                new VRTARSceneManager(reactContext),
                new VRTARPlaneManager(reactContext),
                new VRTARImageMarkerManager(reactContext),
                new VRTARObjectMarkerManager(reactContext)
        );
    }
}
