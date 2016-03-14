package com.TigersIter2.views;


public class Camera {   //Basically the link between the "physical" (Model) and "camera" (Views) worlds.
                        // it takes in some "physical" object and processes this information in a way that is
                        // accessible to various "view" things.

    public Camera(){
        objectOfInterest_X = 0;
        objectOfInterest_Y = 0;
    }

    int objectOfInterest_X; //"View" usable data (derived from some "Model" object of interest)
    int objectOfInterest_Y;

    public int getObjectOfInterest_X() {
        return objectOfInterest_X;
    }

    public void setObjectOfInterest_X(int objectOfInterest_X) {
        this.objectOfInterest_X = objectOfInterest_X;
    }

    public int getObjectOfInterest_Y() {
        return objectOfInterest_Y;
    }

    public void setObjectOfInterest_Y(int objectOfInterest_Y) {
        this.objectOfInterest_Y = objectOfInterest_Y;
    }
}
