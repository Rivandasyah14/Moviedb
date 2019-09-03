package com.rane.mov.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Model implements Parcelable {
    private String name;
    private String poster_path;
    private String overview;
    private String first_air_date;
    private Double vote_average;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }


    public Model(JSONObject jsonObject) {

        try {
            String name;
            String release_date;
            StringBuilder path = new StringBuilder("https://image.tmdb.org/t/p/w154/");

            if (jsonObject.isNull("name")){
                name = jsonObject.getString("title");
                release_date = jsonObject.getString("release_date");
            }else{
                name = jsonObject.getString("name");
                release_date = jsonObject.getString("first_air_date");

            }

            Double vote_average = jsonObject.getDouble ("vote_average");
            String poster_path = path + jsonObject.getString("poster_path");
            String overview = jsonObject.getString("overview");

            this.name =  name;
            this.first_air_date = release_date;
            this.vote_average = vote_average;
            this.poster_path = poster_path;
            this.overview = overview;

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
        dest.writeString(this.first_air_date);
        dest.writeDouble(this.vote_average);
    }

    protected Model(Parcel in) {
        this.name = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.first_air_date = in.readString();
        this.vote_average = in.readDouble();
    }

    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

}
