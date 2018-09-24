package co.classplus.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.classplus.data.network.ApiHelper;
import co.classplus.data.prefs.PreferencesHelper;
import co.classplus.di.ApplicationContext;
import io.reactivex.Observable;

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

    @Override
    public Observable<ListingYoutubeItemModel> getMostPopularVideos(String pageToken, String apiKey) {
        return mApiHelper.getMostPopularVideos(pageToken, apiKey);
    }

    @Override
    public Observable<SearchYoutubeItemModel> getSearchVideos(String searchQuery, String pageToken, String apiKey) {
        return mApiHelper.getSearchVideos(searchQuery, pageToken, apiKey);
    }
}
