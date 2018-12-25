package co.classplus.app.cms.data.model.test;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import co.classplus.app.cms.data.model.base.TestBaseModel;

public class SingleTest extends TestBaseModel {

    @Expose
    @SerializedName("isSectionEnable")
    private boolean isSectionEnable;
    @Expose
    @SerializedName("sections")
    private ArrayList<TestSection> sections;
    @Expose
    @SerializedName("createdBy")
    private String createdBy;

    protected SingleTest(Parcel in) {
        super(in);
    }

    public boolean isSectionEnable() {
        return isSectionEnable;
    }

    public void setSectionEnable(boolean sectionEnable) {
        isSectionEnable = sectionEnable;
    }

    public ArrayList<TestSection> getSections() {
        return sections;
    }

    public void setSections(ArrayList<TestSection> sections) {
        this.sections = sections;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
