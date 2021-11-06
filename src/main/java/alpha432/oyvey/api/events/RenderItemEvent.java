package alpha432.oyvey.api.events;


import alpha432.oyvey.api.EventStage;

public
class RenderItemEvent extends EventStage {
    double mainX, mainY, mainZ,
            offX, offY, offZ,
            mainRotX, mainRotY, mainRotZ,
            offRotX, offRotY, offRotZ,
            mainHandScaleX, mainHandScaleY, mainHandScaleZ, /*mainHandItemWidth,*/
            offHandScaleX, offHandScaleY, offHandScaleZ/*, offHandItemWidth*/;


    public
    RenderItemEvent(double mainX, double mainY, double mainZ,
                    double offX, double offY, double offZ,
                    double mainRotX, double mainRotY, double mainRotZ,
                    double offRotX, double offRotY, double offRotZ,
                    double mainHandScaleX, double mainHandScaleY, double mainHandScaleZ, /*double mainHandItemWidth ,*/
                    double offHandScaleX, double offHandScaleY, double offHandScaleZ /*, double offHandItemWidth*/) {
        this.mainX = mainX;
        this.mainY = mainY;
        this.mainZ = mainZ;
        this.offX = offX;
        this.offY = offY;
        this.offZ = offZ;
        this.mainRotX = mainRotX;
        this.mainRotY = mainRotY;
        this.mainRotZ = mainRotZ;
        this.offRotX = offRotX;
        this.offRotY = offRotY;
        this.offRotZ = offRotZ;
        this.mainHandScaleX = mainHandScaleX;
        this.mainHandScaleY = mainHandScaleY;
        this.mainHandScaleZ = mainHandScaleZ;
        //this.mainHandItemWidth = mainHandItemWidth;
        this.offHandScaleX = offHandScaleX;
        this.offHandScaleY = offHandScaleY;
        this.offHandScaleZ = offHandScaleZ;
        //this.offHandItemWidth = offHandItemWidth;
    }

    public
    double getMainX() {
        return mainX;
    }

    public
    void setMainX(double v) {
        this.mainX = v;
    }

    public
    double getMainY() {
        return mainY;
    }

    public
    void setMainY(double v) {
        this.mainY = v;
    }

    public
    double getMainZ() {
        return mainZ;
    }

    public
    void setMainZ(double v) {
        this.mainZ = v;
    }

    public
    double getOffX() {
        return offX;
    }

    public
    void setOffX(double v) {
        this.offX = v;
    }

    public
    double getOffY() {
        return offY;
    }

    public
    void setOffY(double v) {
        this.offY = v;
    }

    public
    double getOffZ() {
        return offZ;
    }

    public
    void setOffZ(double v) {
        this.offZ = v;
    }

    public
    double getMainRotX() {
        return mainRotX;
    }

    public
    void setMainRotX(double v) {
        this.mainRotX = v;
    }

    public
    double getMainRotY() {
        return mainRotY;
    }

    public
    void setMainRotY(double v) {
        this.mainRotY = v;
    }

    public
    double getMainRotZ() {
        return mainRotZ;
    }

    public
    void setMainRotZ(double v) {
        this.mainRotZ = v;
    }

    public
    double getOffRotX() {
        return offRotX;
    }

    public
    void setOffRotX(double v) {
        this.offRotX = v;
    }

    public
    double getOffRotY() {
        return offRotY;
    }

    public
    void setOffRotY(double v) {
        this.offRotY = v;
    }

    public
    double getOffRotZ() {
        return offRotZ;
    }

    public
    void setOffRotZ(double v) {
        this.offRotZ = v;
    }

    public
    double getMainHandScaleX() {
        return mainHandScaleX;
    }

    public
    void setMainHandScaleX(double v) {
        this.mainHandScaleX = v;
    }

    public
    double getMainHandScaleY() {
        return mainHandScaleY;
    }

    public
    void setMainHandScaleY(double v) {
        this.mainHandScaleY = v;
    }

    public
    double getMainHandScaleZ() {
        return mainHandScaleZ;
    }

    public
    void setMainHandScaleZ(double v) {
        this.mainHandScaleZ = v;
    }

    //public
    //double getMainHandItemWidth ( ) {
    //    return mainHandItemWidth;
    //}

    //public
    //void setMainHandItemWidth ( double v ) {
    //    this.mainHandItemWidth = v;
    //}

    public
    double getOffHandScaleX() {
        return offHandScaleX;
    }

    public
    void setOffHandScaleX(double v) {
        this.offHandScaleX = v;
    }

    public
    double getOffHandScaleY() {
        return offHandScaleY;
    }

    public
    void setOffHandScaleY(double v) {
        this.offHandScaleY = v;
    }

    public
    double getOffHandScaleZ() {
        return offHandScaleZ;
    }

    public
    void setOffHandScaleZ(double v) {
        this.offHandScaleZ = v;
    }

    //public
    //double getOffHandItemWidth ( ) {
    //    return offHandItemWidth;
    //}

    //public
    //void setOffHandItemWidth ( double v ) {
    //    this.offHandItemWidth = v;
    //}
}