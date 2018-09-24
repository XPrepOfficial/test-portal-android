package co.classplus.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class ApiHelperImpl implements ApiHelper {

    @Inject
    public ApiHelperImpl() {
    }

    @Override
    public Observable<ListingYoutubeItemModel> getMostPopularVideos(String pageToken, String apiKey) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).getMostPopularVideos(pageToken, apiKey);
    }

    @Override
    public Observable<SearchYoutubeItemModel> getSearchVideos(String searchQuery, String pageToken, String apiKey) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).getSearchVideos(searchQuery, pageToken, apiKey);
    }
}
