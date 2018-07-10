package com.soon.karat.retrofit_api_2.Udacity.models;

import java.util.List;

public class UdacityCatalog {

    public List<Course> courses;
    public List<Track> tracks;
    public List<Degree> degrees;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }
}
