package alpha432.oyvey.features.modules.render;

import alpha432.oyvey.features.modules.Module;
import alpha432.oyvey.api.events.RenderItemEvent;
import alpha432.oyvey.features.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public
class ViewModel extends Module {
    private static ViewModel INSTANCE = new ViewModel ( );
    public Setting< Settings > settings = this.register ( new Setting <> ( "Settings", Settings.TRANSLATE) );
    public Setting < Boolean > noEatAnimation = this.register ( new Setting <> ( "NoEatAnimation" , false , v -> settings.getValue ( ) == Settings.TWEAKS ) );
    public Setting < Double > eatX = this.register ( new Setting <> ( "EatX" , 1.0 , - 2.0 , 5.0 , v -> settings.getValue ( ) == Settings.TWEAKS && ! this.noEatAnimation.getValue ( ) ) );
    public Setting < Double > eatY = this.register ( new Setting <> ( "EatY" , 1.0 , - 2.0 , 5.0 , v -> settings.getValue ( ) == Settings.TWEAKS && ! this.noEatAnimation.getValue ( ) ) );
    public Setting < Boolean > doBob = this.register ( new Setting <> ( "ItemBob" , true , v -> settings.getValue ( ) == Settings.TWEAKS ) );
    public Setting < Double > mainX = this.register ( new Setting <> ( "MainX" , 1.2 , - 2.0 , 4.0 , v -> settings.getValue ( ) == Settings.TRANSLATE ) );
    public Setting < Double > mainY = this.register ( new Setting <> ( "MainY" , - 0.95 , - 3.0 , 3.0 , v -> settings.getValue ( ) == Settings.TRANSLATE ) );
    public Setting < Double > mainZ = this.register ( new Setting <> ( "MainZ" , - 1.45 , - 5.0 , 5.0 , v -> settings.getValue ( ) == Settings.TRANSLATE ) );
    public Setting < Double > offX = this.register ( new Setting <> ( "OffX" , 1.2 , - 2.0 , 4.0 , v -> settings.getValue ( ) == Settings.TRANSLATE ) );
    public Setting < Double > offY = this.register ( new Setting <> ( "OffY" , - 0.95 , - 3.0 , 3.0 , v -> settings.getValue ( ) == Settings.TRANSLATE ) );
    public Setting < Double > offZ = this.register ( new Setting <> ( "OffZ" , - 1.45 , - 5.0 , 5.0 , v -> settings.getValue ( ) == Settings.TRANSLATE ) );
    public Setting < Integer > mainRotX = this.register ( new Setting <> ( "MainRotationX" , 0 , - 36 , 36 , v -> settings.getValue ( ) == Settings.ROTATE ) );
    public Setting < Integer > mainRotY = this.register ( new Setting <> ( "MainRotationY" , 0 , - 36 , 36 , v -> settings.getValue ( ) == Settings.ROTATE ) );
    public Setting < Integer > mainRotZ = this.register ( new Setting <> ( "MainRotationZ" , 0 , - 36 , 36 , v -> settings.getValue ( ) == Settings.ROTATE ) );
    public Setting < Integer > offRotX = this.register ( new Setting <> ( "OffRotationX" , 0 , - 36 , 36 , v -> settings.getValue ( ) == Settings.ROTATE ) );
    public Setting < Integer > offRotY = this.register ( new Setting <> ( "OffRotationY" , 0 , - 36 , 36 , v -> settings.getValue ( ) == Settings.ROTATE ) );
    public Setting < Integer > offRotZ = this.register ( new Setting <> ( "OffRotationZ" , 0 , - 36 , 36 , v -> settings.getValue ( ) == Settings.ROTATE ) );
    public Setting < Double > mainScaleX = this.register ( new Setting <> ( "MainScaleX" , 1.0 , 0.1 , 5.0 , v -> settings.getValue ( ) == Settings.SCALE ) );
    public Setting < Double > mainScaleY = this.register ( new Setting <> ( "MainScaleY" , 1.0 , 0.1 , 5.0 , v -> settings.getValue ( ) == Settings.SCALE ) );
    public Setting < Double > mainScaleZ = this.register ( new Setting <> ( "MainScaleZ" , 1.0 , 0.1 , 5.0 , v -> settings.getValue ( ) == Settings.SCALE ) );
    //public Setting < Double > mainItemWidth = this.register ( new Setting <> ( "MainItemWidth" , 1.0 , 0.1 , 5.0 , v -> settings.getValue ( ) == Settings.SCALE ) );
    public Setting < Double > offScaleX = this.register ( new Setting <> ( "OffScaleX" , 1.0 , 0.1 , 5.0 , v -> settings.getValue ( ) == Settings.SCALE ) );
    public Setting < Double > offScaleY = this.register ( new Setting <> ( "OffScaleY" , 1.0 , 0.1 , 5.0 , v -> settings.getValue ( ) == Settings.SCALE ) );
    public Setting < Double > offScaleZ = this.register ( new Setting <> ( "OffScaleZ" , 1.0 , 0.1 , 5.0 , v -> settings.getValue ( ) == Settings.SCALE ) );
    //public Setting < Double > offItemWidth = this.register ( new Setting <> ( "OffItemWidth" , 1.0 , 0.1 , 5.0 , v -> settings.getValue ( ) == Settings.SCALE ) );

    public
    ViewModel ( ) {
        super ( "ViewModel" , "Cool" , Category.RENDER , true , false , false );
        this.setInstance ( );
    }

    public static
    ViewModel getInstance ( ) {
        if ( INSTANCE == null ) {
            INSTANCE = new ViewModel ( );
        }
        return INSTANCE;
    }

    private
    void setInstance ( ) {
        INSTANCE = this;
    }

    @SubscribeEvent
    public
    void onItemRender ( RenderItemEvent event ) {
        event.setMainX ( mainX.getValue ( ) );
        event.setMainY ( mainY.getValue ( ) );
        event.setMainZ ( mainZ.getValue ( ) );

        event.setOffX ( - offX.getValue ( ) );
        event.setOffY ( offY.getValue ( ) );
        event.setOffZ ( offZ.getValue ( ) );

        event.setMainRotX ( mainRotX.getValue ( ) * 5 );
        event.setMainRotY ( mainRotY.getValue ( ) * 5 );
        event.setMainRotZ ( mainRotZ.getValue ( ) * 5 );

        event.setOffRotX ( offRotX.getValue ( ) * 5 );
        event.setOffRotY ( offRotY.getValue ( ) * 5 );
        event.setOffRotZ ( offRotZ.getValue ( ) * 5 );

        event.setOffHandScaleX ( offScaleX.getValue ( ) );
        event.setOffHandScaleY ( offScaleY.getValue ( ) );
        event.setOffHandScaleZ ( offScaleZ.getValue ( ) );

        event.setMainHandScaleX ( mainScaleX.getValue ( ) );
        event.setMainHandScaleY ( mainScaleY.getValue ( ) );
        event.setMainHandScaleZ ( mainScaleZ.getValue ( ) );

        //event.setMainHandItemWidth ( mainItemWidth.getValue ( ) );
        //event.setOffHandItemWidth ( offItemWidth.getValue ( ) );
    }

    private
    enum Settings {
        TRANSLATE,
        ROTATE,
        SCALE,
        TWEAKS
    }
}