//package com.artlite.ckwidgets.ui.create;
//
//import android.content.Context;
//import android.view.View;
//
//import com.artlite.bslibrary.ui.view.BSView;
//import com.artlite.ckconcept.constants.KitEvent;
//import com.artlite.ckconcept.ui.abs.create.KitBaseCreateView;
//import com.artlite.ckwidgets.R;
//
///**
// * Created by dlernatovich on 5/16/2017.
// */
//
//public final class KitCreateChannelView extends KitBaseCreateView {
//
//    /**
//     * Constructor which provide the create {@link BSView} from
//     *
//     * @param context instance of {@link Context}
//     */
//    public KitCreateChannelView(Context context) {
//        super(context);
//    }
//
//    /**
//     * Method which provide the getting of the layout ID
//     *
//     * @return layout ID
//     */
//    @Override
//    protected int getLayoutId() {
//        return R.layout.view_ck_create_channel;
//    }
//
//    /**
//     * Method which provide the creating of the {@link View}
//     */
//    @Override
//    protected void onCreateView() {
//        setOnClickListeners(R.id.button_create);
//    }
//
//    /**
//     * Method which provide the click interface
//     *
//     * @param view instance of {@link View}
//     */
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.button_create) {
//            sendEvent(KitEvent.CREATE_CHANNEL);
//            dismiss();
//        }
//    }
//}
