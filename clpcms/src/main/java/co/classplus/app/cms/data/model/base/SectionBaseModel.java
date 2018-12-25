package co.classplus.app.cms.data.model.base;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.classplus.app.cms.data.model.test.TestSection;

public class SectionBaseModel extends CmsNameId implements Comparable<SectionBaseModel> {

    @Expose
    @SerializedName("numberOfQuestions")
    private int numberOfQuestions;
    @Expose
    @SerializedName("order")
    private int order;
    @Expose
    @SerializedName("createdAt")
    private String createdAt;
    @Expose
    @SerializedName("updatedAt")
    private String updatedAt;

    public SectionBaseModel() {
    }

    public SectionBaseModel(TestSection testSection) {
        this.set_id(testSection.get_id());
        this.setName(testSection.getName());
        this.numberOfQuestions = testSection.getNumberOfQuestions();
        this.order = testSection.getOrder();
        this.createdAt = testSection.getCreatedAt();
        this.updatedAt = testSection.getUpdatedAt();
    }

    protected SectionBaseModel(Parcel in) {
        super(in);
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int compareTo(@NonNull SectionBaseModel o) {
        return Integer.compare(this.getOrder(), o.getOrder());
    }
}
