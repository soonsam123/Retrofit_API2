/*
 * Copyright (C) LeafSoon 2018
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 *
 * Written by Soon Sam <karatesoon@gmail.com>
 *
 */

package com.soon.karat.retrofit_api_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.soon.karat.retrofit_api_2.Gerrit.activities.ChangeActivity;
import com.soon.karat.retrofit_api_2.Github.activities.MyReposActivity;
import com.soon.karat.retrofit_api_2.StackOverflow.activities.StackActivity;
import com.soon.karat.retrofit_api_2.Udacity.activities.DegreesActivity;
import com.soon.karat.retrofit_api_2.Udacity.activities.MainActivity;
import com.soon.karat.retrofit_api_2.Udacity.activities.TracksActivity;

public class BaseActivity extends AppCompatActivity {

    /* =========================== Menu =========================== */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_base, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_courses:
                Intent intentMainActivity = new Intent(this, MainActivity.class);
                startActivity(intentMainActivity);
                break; // 1. Navigate to Courses Activity;
            case R.id.menu_tracks:
                Intent intentTracksActivity = new Intent(this, TracksActivity.class);
                startActivity(intentTracksActivity);
                break; // 2. Navigate to Tracks Activity;
            case R.id.menu_degrees:
                Intent intentDegreesActivity = new Intent(this, DegreesActivity.class);
                startActivity(intentDegreesActivity);
                break; // 3. Navigate to Degrees Activity;
            case R.id.menu_changes:
                Intent intentChangeActivity = new Intent(this, ChangeActivity.class);
                startActivity(intentChangeActivity);
                break; // 4. Navigate to Changes Activity;
            case R.id.menu_my_repos:
                Intent intentMyReposActivity = new Intent(this, MyReposActivity.class);
                startActivity(intentMyReposActivity);
                break; // 5. Navigate to My Repos Activity;
            case R.id.menu_stack:
                Intent intentStackActivity = new Intent(this, StackActivity.class);
                startActivity(intentStackActivity);
                break; // 6. Navigate to Stack Activity.
        }

        return super.onOptionsItemSelected(item);
    }

    /* =========================== END OF Menu =========================== */


}
