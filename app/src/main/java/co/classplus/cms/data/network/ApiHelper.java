package co.classplus.cms.data.network;

import com.google.gson.JsonObject;

import co.classplus.cms.data.model.solutions.TestSolutionResponse;
import co.classplus.cms.data.model.submit.SubmitTestResponse;
import co.classplus.cms.data.model.test.TestGetResponse;
import co.classplus.cms.data.model.test.TestInstructionsResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiHelper {

    @GET("test/{testId}/instructions")
    Observable<TestInstructionsResponse> getTestInstructions(@Path("testId") String testId);

    @GET("test/{testId}")
    Observable<TestGetResponse> getTestDetails(@Path("testId") String testId);

    @POST("test/start")
    Observable<TestGetResponse> startTestApi(@Body JsonObject jsonObject);

    @POST("test/evaluate")
    Observable<SubmitTestResponse> submitTest(@Body JsonObject jsonObject);

    @GET("test/{testId}/student/{studentTestId}/solutions")
    Observable<TestSolutionResponse> getTestSolutions(@Path("testId") String testId, @Path("studentTestId") String studentTestId);
}
