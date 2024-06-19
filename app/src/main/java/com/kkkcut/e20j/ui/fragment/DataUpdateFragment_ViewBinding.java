package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class DataUpdateFragment_ViewBinding implements Unbinder {
    private DataUpdateFragment target;

    public DataUpdateFragment_ViewBinding(DataUpdateFragment dataUpdateFragment, View view) {
        this.target = dataUpdateFragment;
        dataUpdateFragment.tvNameApp = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_name_app, "field 'tvNameApp'", TextView.class);
        dataUpdateFragment.tvNameMainDb = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_name_main_db, "field 'tvNameMainDb'", TextView.class);
        dataUpdateFragment.tvNameRes = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_name_res, "field 'tvNameRes'", TextView.class);
        dataUpdateFragment.tvNameErr = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_name_err, "field 'tvNameErr'", TextView.class);
        dataUpdateFragment.tvNameImgs = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_name_imgs, "field 'tvNameImgs'", TextView.class);
        dataUpdateFragment.tvValueCurrentSoftware = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_current_software, "field 'tvValueCurrentSoftware'", TextView.class);
        dataUpdateFragment.tvValueNewSoftware = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_new_software, "field 'tvValueNewSoftware'", TextView.class);
        dataUpdateFragment.tvValueCurrentMainDb = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_current_main_db, "field 'tvValueCurrentMainDb'", TextView.class);
        dataUpdateFragment.tvValueNewMainDb = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_new_main_db, "field 'tvValueNewMainDb'", TextView.class);
        dataUpdateFragment.tvValueCurrentRes = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_current_res, "field 'tvValueCurrentRes'", TextView.class);
        dataUpdateFragment.tvValueNewRes = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_new_res, "field 'tvValueNewRes'", TextView.class);
        dataUpdateFragment.tvValueCurrentError = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_current_error, "field 'tvValueCurrentError'", TextView.class);
        dataUpdateFragment.tvValueNewError = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_new_error, "field 'tvValueNewError'", TextView.class);
        dataUpdateFragment.tvValueCurrentImg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_current_imgs, "field 'tvValueCurrentImg'", TextView.class);
        dataUpdateFragment.tvValueNewImg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value_new_imgs, "field 'tvValueNewImg'", TextView.class);
        dataUpdateFragment.btSoftwareUpdate = (Button) Utils.findRequiredViewAsType(view, R.id.bt_software_update, "field 'btSoftwareUpdate'", Button.class);
        dataUpdateFragment.btMainDbUpdate = (Button) Utils.findRequiredViewAsType(view, R.id.bt_main_db_update, "field 'btMainDbUpdate'", Button.class);
        dataUpdateFragment.btResUpdate = (Button) Utils.findRequiredViewAsType(view, R.id.bt_res_update, "field 'btResUpdate'", Button.class);
        dataUpdateFragment.btErrorUpdate = (Button) Utils.findRequiredViewAsType(view, R.id.bt_error_update, "field 'btErrorUpdate'", Button.class);
        dataUpdateFragment.btImgUpdate = (Button) Utils.findRequiredViewAsType(view, R.id.bt_imgs_update, "field 'btImgUpdate'", Button.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DataUpdateFragment dataUpdateFragment = this.target;
        if (dataUpdateFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dataUpdateFragment.tvNameApp = null;
        dataUpdateFragment.tvNameMainDb = null;
        dataUpdateFragment.tvNameRes = null;
        dataUpdateFragment.tvNameErr = null;
        dataUpdateFragment.tvNameImgs = null;
        dataUpdateFragment.tvValueCurrentSoftware = null;
        dataUpdateFragment.tvValueNewSoftware = null;
        dataUpdateFragment.tvValueCurrentMainDb = null;
        dataUpdateFragment.tvValueNewMainDb = null;
        dataUpdateFragment.tvValueCurrentRes = null;
        dataUpdateFragment.tvValueNewRes = null;
        dataUpdateFragment.tvValueCurrentError = null;
        dataUpdateFragment.tvValueNewError = null;
        dataUpdateFragment.tvValueCurrentImg = null;
        dataUpdateFragment.tvValueNewImg = null;
        dataUpdateFragment.btSoftwareUpdate = null;
        dataUpdateFragment.btMainDbUpdate = null;
        dataUpdateFragment.btResUpdate = null;
        dataUpdateFragment.btErrorUpdate = null;
        dataUpdateFragment.btImgUpdate = null;
    }
}
