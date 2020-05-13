package com.example.booleanmodelsearchengine_irproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

    private int id;
    private String link, title;

    public News(int id, String link, String title) {
        this.id = id;
        this.link = link;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.link);
        dest.writeString(this.title);
    }

    protected News(Parcel in) {
        this.id = in.readInt();
        this.link = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
