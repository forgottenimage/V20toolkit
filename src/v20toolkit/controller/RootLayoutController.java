package v20toolkit.controller;

import v20toolkit.V20toolkit;

/**
 * Created by baarzul on 19.10.16.
 */
public class RootLayoutController {

    // Reference to the main application
    public V20toolkit v20toolkit;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param v20toolkit
     */
    public void setMainApp(V20toolkit v20toolkit) {

        this.v20toolkit = v20toolkit;
    }
}
