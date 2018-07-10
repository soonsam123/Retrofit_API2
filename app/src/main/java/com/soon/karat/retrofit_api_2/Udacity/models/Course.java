package com.soon.karat.retrofit_api_2.Udacity.models;

import java.util.List;

public class Course {

    // These names must be exactly the same that is in the Json file
    public String title;
    public String subtitle;

    // Image url
    public String image;

    public List<Instructor> instructors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Instructor> getInstructorList() {
        return instructors;
    }

    public void setInstructorList(List<Instructor> instructorList) {
        this.instructors = instructorList;
    }
}
