package tdc.edu.vn.slider.Slider;

import java.io.Serializable;

public class Photo implements Serializable {
    private int resourceId;

    public Photo() {
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public Photo(int resourceId) {
        this.resourceId = resourceId;
    }
}
