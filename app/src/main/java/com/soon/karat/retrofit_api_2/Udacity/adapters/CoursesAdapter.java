package com.soon.karat.retrofit_api_2.Udacity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soon.karat.retrofit_api_2.utils.GlideApp;
import com.soon.karat.retrofit_api_2.Udacity.models.Course;
import com.soon.karat.retrofit_api_2.Udacity.models.Instructor;
import com.soon.karat.retrofit_api_2.R;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ItemHolder> {

    private List<Course> courses;
    private Context context;

    public CoursesAdapter(List<Course> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        Course singleCourse = courses.get(position);

        // 1. Thumbnail
        if (singleCourse.getImage().equals("")) {
            Log.i("Debugging", "onBindViewHolder: " + position + " there is no image");
            holder.thumbNail.setImageResource(R.drawable.ic_launcher_background);
        } else {
            GlideApp.with(holder.thumbNail.getContext())
                    .load(singleCourse.getImage())
                    .centerCrop()
                    .into(holder.thumbNail);
        }

        // 2. Title
        holder.title.setText(singleCourse.getTitle());

        // 3. Subtitle
        holder.subTitle.setText(singleCourse.getSubtitle());

        // 4. Instructors
        List<String> listOfInstructorsName = new ArrayList<>();
        if (singleCourse.getInstructorList() != null) {
            for (Instructor instructor: singleCourse.getInstructorList()) {
                listOfInstructorsName.add(instructor.getName());
            }
        }
        holder.instructors.setText(listOfInstructorsName.toString());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {

        public ImageView thumbNail;

        public TextView title;
        public TextView subTitle;
        public TextView instructors;

        public ItemHolder(View itemView) {
            super(itemView);

            thumbNail = itemView.findViewById(R.id.image_course_thumbnail);

            title = itemView.findViewById(R.id.text_title);
            subTitle = itemView.findViewById(R.id.text_sub_title);
            instructors = itemView.findViewById(R.id.text_instructors);


        }

    }


}
