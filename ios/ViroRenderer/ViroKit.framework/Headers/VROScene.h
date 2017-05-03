//
//  VROScene.h
//  ViroRenderer
//
//  Created by Raj Advani on 10/19/15.
//  Copyright © 2015 Viro Media. All rights reserved.
//

#ifndef VROScene_h
#define VROScene_h

#include <stdio.h>
#include <vector>
#include <memory>
#include "VROInputPresenter.h"
#include "VROAllocationTracker.h"
#include "VROSortKey.h"
#include "VROQuaternion.h"
#include "VROThreadRestricted.h"

class VRONode;
class VRORenderContext;
class VRODriver;
class VROTexture;
class VROGeometry;
class VROHitTestResult;
class VROVector3f;
class VROVector4f;
class VROAudioPlayer;
class VROInputControllerBase;

class VROScene : public std::enable_shared_from_this<VROScene>, public VROThreadRestricted {
    
public:
    
    VROScene();
    virtual ~VROScene();
    
    void renderBackground(const VRORenderContext &context,
                          std::shared_ptr<VRODriver> &driver);
    void render(const VRORenderContext &context,
                std::shared_ptr<VRODriver> &driver);
    
    void updateSortKeys(const VRORenderContext &context,
                        std::shared_ptr<VRODriver> &driver);

    /*
     Add a new root node to the scene.
     */
    void addNode(std::shared_ptr<VRONode> node);
    std::vector<std::shared_ptr<VRONode>> &getRootNodes() {
        return _nodes;
    }
    
    /*
     Set the background of the scene to a cube-map defined by
     the given cube texture or color, or a sphere defined by the given
     spherical image.
     */
    void setBackgroundCube(std::shared_ptr<VROTexture> textureCube);
    void setBackgroundCube(VROVector4f color);
    void setBackgroundSphere(std::shared_ptr<VROTexture> textureSphere);
    void setBackgroundRotation(VROQuaternion rotation);
    std::shared_ptr<VROGeometry> getBackground() const {
        return _background;
    }
    void attachInputController(std::shared_ptr<VROInputControllerBase> controller);
    void detachInputController(std::shared_ptr<VROInputControllerBase> controller);
    std::shared_ptr<VROInputPresenter> getControllerPresenter();
    
    float getDistanceOfFurthestObjectFromCamera() const {
        return _distanceOfFurthestObjectFromCamera;
    }

private:
    
    /*
     The root nodes of the scene.
     */
    std::vector<std::shared_ptr<VRONode>> _nodes;
    
    /*
     The background visual to display. Rendered before any nodes.
     */
    std::shared_ptr<VROGeometry> _background;

    /*
     UI representation of the underlying controller
     */
    std::shared_ptr<VROInputPresenter> _controllerPresenter;
    
    /*
     The rotation to apply to the background geometry
     */
    VROQuaternion _backgroundRotation;
    
    /*
     The nodes ordered for rendering by their sort keys.
     */
    std::vector<VROSortKey> _keys;
    
    /*
     The distance from the camera of the furthest away object, since the last
     call to updateSortKeys. Distance is from the camera to the bounding
     box of the object.
     */
    float _distanceOfFurthestObjectFromCamera;
};

#endif /* VROScene_h */