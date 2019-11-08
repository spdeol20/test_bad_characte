package com.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DataModel implements Parcelable {

    /**
     * char_id : 1
     * name : Walter White
     * birthday : 09-07-1958
     * occupation : ["High School Chemistry Teacher","Meth King Pin"]
     * img : https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg
     * status : Presumed dead
     * nickname : Heisenberg
     * appearance : [1,2,3,4,5]
     * portrayed : Bryan Cranston
     * category : Breaking Bad
     * better_call_saul_appearance : []
     */

    private int char_id;
    private String name;
    private String birthday;
    private String img;
    private String status;
    private String nickname;
    private String portrayed;
    private String category;
    private List<String> occupation;
    private List<Integer> appearance;
    private List<Integer> better_call_saul_appearance;

    public static List<DataModel> instance;

    public static  List<DataModel> getInstance() {
        if (instance == null) {
            instance = new ArrayList<>();
        }
        return instance;
    }

    public static void setInstance( List<DataModel> da) {
       instance = da;
    }

    public int getChar_id() {
        return char_id;
    }

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortrayed() {
        return portrayed;
    }

    public void setPortrayed(String portrayed) {
        this.portrayed = portrayed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getOccupation() {
        return occupation;
    }

    public void setOccupation(List<String> occupation) {
        this.occupation = occupation;
    }

    public List<Integer> getAppearance() {
        return appearance;
    }

    public void setAppearance(List<Integer> appearance) {
        this.appearance = appearance;
    }

    public List<Integer> getBetter_call_saul_appearance() {
        return better_call_saul_appearance;
    }

    public void setBetter_call_saul_appearance(List<Integer> better_call_saul_appearance) {
        this.better_call_saul_appearance = better_call_saul_appearance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.char_id);
        dest.writeString(this.name);
        dest.writeString(this.birthday);
        dest.writeString(this.img);
        dest.writeString(this.status);
        dest.writeString(this.nickname);
        dest.writeString(this.portrayed);
        dest.writeString(this.category);
        dest.writeStringList(this.occupation);
        dest.writeList(this.appearance);
        dest.writeList(this.better_call_saul_appearance);
    }

    public DataModel() {
    }

    protected DataModel(Parcel in) {
        this.char_id = in.readInt();
        this.name = in.readString();
        this.birthday = in.readString();
        this.img = in.readString();
        this.status = in.readString();
        this.nickname = in.readString();
        this.portrayed = in.readString();
        this.category = in.readString();
        this.occupation = in.createStringArrayList();
        this.appearance = new ArrayList<Integer>();
        in.readList(this.appearance, Integer.class.getClassLoader());
        this.better_call_saul_appearance = new ArrayList<Integer>();
        in.readList(this.better_call_saul_appearance, Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<DataModel> CREATOR = new Parcelable.Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel source) {
            return new DataModel(source);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };
}
