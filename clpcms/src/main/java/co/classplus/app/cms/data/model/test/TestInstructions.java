package co.classplus.app.cms.data.model.test;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import co.classplus.app.cms.data.model.base.SectionBaseModel;
import co.classplus.app.cms.data.model.base.TestBaseModel;

public class TestInstructions extends TestBaseModel {

    @Expose
    @SerializedName("sections")
    private ArrayList<SectionBaseModel> sections;

    protected TestInstructions(Parcel in) {
        super(in);
    }

    public ArrayList<SectionBaseModel> getSections() {
        return sections;
    }

    public void setSections(ArrayList<SectionBaseModel> sections) {
        this.sections = sections;
    }
}
