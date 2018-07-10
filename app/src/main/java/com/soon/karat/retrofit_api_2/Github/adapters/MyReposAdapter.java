package com.soon.karat.retrofit_api_2.Github.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.Github.models.Repo;
import com.soon.karat.retrofit_api_2.utils.GlideApp;

import java.util.List;

public class MyReposAdapter extends RecyclerView.Adapter<MyReposAdapter.ItemHolder> {

    private List<Repo> repos;
    private Context context;

    public MyReposAdapter(List<Repo> repos, Context context) {
        this.repos = repos;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_repos, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        final Repo singleRepo = repos.get(position);

        holder.name.setText(singleRepo.getName());
        holder.url.setText(singleRepo.getHtml_url());
        holder.language.setText(singleRepo.getLanguage());

        // Handle Click in the url
        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = singleRepo.getHtml_url();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });

        // Owner
        GlideApp.with(holder.avatarURL.getContext())
                .load(singleRepo.getOwner().getAvatar_url())
                .centerCrop()
                .into(holder.avatarURL);

        holder.login.setText(singleRepo.getOwner().getLogin());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView url;
        public TextView language;
        public ImageView avatarURL;
        public TextView login;

        public ItemHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtView_name_itemMyRepos);
            url = itemView.findViewById(R.id.txtView_url_itemMyRepos);
            language = itemView.findViewById(R.id.txtView_language_itemMyRepos);
            avatarURL = itemView.findViewById(R.id.imgView_userAvatar_itemMyRepos);
            login = itemView.findViewById(R.id.txtView_login_itemMyRepos);

        }

    }


}
