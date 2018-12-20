package co.classplus.cms.ui.taketest;

import android.os.Bundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import co.classplus.cms.data.DataManager;
import co.classplus.cms.data.model.question.QuestionOption;
import co.classplus.cms.data.model.question.SingleQuestion;
import co.classplus.cms.data.model.test.SingleTest;
import co.classplus.cms.data.model.test.TestSection;
import co.classplus.cms.data.network.retrofit.RetrofitException;
import co.classplus.cms.ui.base.BasePresenter;
import co.classplus.cms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class TestTakingPresenterImpl<V extends TestTakingView> extends BasePresenter<V> implements TestTakingPresenter<V> {

    private static final String PARAM_TEST_ID = "PARAM_TEST_ID";
    private static final String API_TEST_DETAILS = "API_TEST_DETAILS";
    private static final String API_SUBMIT_TEST = "API_SUBMIT_TEST";

    @Inject
    public TestTakingPresenterImpl(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void fetchTestDetails(String testId, int studentId) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .startTestApi(getStartTestJson(testId, "batchTestId", "email", "mobile", "name", studentId))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(testGetResponse -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().onTestDetailsFetched(testGetResponse);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    Bundle bundle = new Bundle();
                    bundle.putString(PARAM_TEST_ID, testId);
                    handleError((RetrofitException) throwable, bundle, API_TEST_DETAILS);
                }));
    }

    @Override
    public void submitTest(SingleTest singleTest, String studentTestId, long timeTaken) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .submitTest(getSubmitTestJson(singleTest, studentTestId, timeTaken))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(submitTestResponse -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().onTestSubmitSuccess(submitTestResponse.getData());
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    getMvpView().onTestSubmitError();
                    Bundle bundle = new Bundle();
                    handleError((RetrofitException) throwable, bundle, API_SUBMIT_TEST);
                }));
    }

    private JsonObject getStartTestJson(String testId, String batchTestId, String email, String mobile, String name, int studentId) {
        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("testId", testId);

        JsonObject studentDataObject = new JsonObject();
        studentDataObject.addProperty("batchTestId", batchTestId);
        studentDataObject.addProperty("email", email);
        studentDataObject.addProperty("mobile", mobile);
        studentDataObject.addProperty("name", name);
        studentDataObject.addProperty("studentId", studentId);

        rootObject.add("studentData", studentDataObject);
        return rootObject;
    }

    private JsonObject getSubmitTestJson(SingleTest singleTest, String studentTestId, long timeTaken) {
        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("testId", singleTest.get_id());
        rootObject.addProperty("studentTestId", studentTestId);
        rootObject.addProperty("timeTaken", timeTaken);

        JsonArray questionsArray = new JsonArray();
        for (TestSection section : singleTest.getSections()) {
            for (SingleQuestion singleQuestion : section.getQuestions()) {
                JsonObject questionObject = new JsonObject();
                questionObject.addProperty("_id", singleQuestion.get_id());
                questionObject.addProperty("timeTaken", singleQuestion.getDuration());
                JsonArray optionsArray = new JsonArray();
                for (QuestionOption option : singleQuestion.getOptions()) {
                    if (option.getSelected()) {
                        optionsArray.add(option.get_id());
                    }
                }
                questionObject.add("selectedAnswers", optionsArray);
                questionsArray.add(questionObject);
            }
        }
        rootObject.add("questions", questionsArray);
        return rootObject;
    }
}
