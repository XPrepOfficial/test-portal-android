package co.classplus.cms.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.classplus.cms.data.network.ApiHelper;
import co.classplus.cms.data.prefs.PreferencesHelper;
import co.classplus.cms.di.ApplicationContext;

@Singleton
public class DataManagerImpl implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManagerImpl(@ApplicationContext Context context,
                           PreferencesHelper preferencesHelper,
                           ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }
}
