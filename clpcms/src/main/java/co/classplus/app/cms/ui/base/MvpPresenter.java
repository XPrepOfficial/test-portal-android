package co.classplus.app.cms.ui.base;

import android.os.Bundle;

import co.classplus.app.cms.data.network.retrofit.RetrofitException;
import rebus.permissionutils.PermissionEnum;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    PermissionEnum[] getPermissionEnums(String... permissions);

    void handleError(RetrofitException error, Bundle bundle, String apiTag);
}
