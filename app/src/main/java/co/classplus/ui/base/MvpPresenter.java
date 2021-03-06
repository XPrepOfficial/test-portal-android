package co.classplus.ui.base;

import android.os.Bundle;

import co.classplus.data.network.retrofit.RetrofitException;
import rebus.permissionutils.PermissionEnum;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleError(RetrofitException error);

    PermissionEnum[] getPermissionEnums(String... permissions);
}
