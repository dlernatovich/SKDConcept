//package com.artlite.ckwidgets.definers;
//
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//
//import com.artlite.ckconcept.constants.KitWidgetType;
//import com.artlite.ckconcept.models.define.KitBaseDefiner;
//import com.magnet.mmx.client.api.ChannelDetail;
//
///**
// * Created by dlernatovich on 5/18/2017.
// */
//
//public final class KitChannelDefiner extends KitBaseDefiner {
//    /**
//     * Default constructor for the {@link KitBaseDefiner}
//     *
//     * @param callerClass instance of the {@link Class}
//     */
//    public KitChannelDefiner(@NonNull Class callerClass) {
//        super(callerClass);
//    }
//
//    /**
//     * Method which provide the define functional for the {@link Object}
//     *
//     * @param object instance of the {@link Object}
//     */
//    @Nullable
//    @Override
//    public String define(@Nullable Object object) {
//        if (object instanceof ChannelDetail) {
//            return KitWidgetType.CHANNEL.getValue();
//        }
//        return null;
//    }
//}
