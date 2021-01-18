package com.example.tech4life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tech4life.R;
import com.example.tech4life.recycleritems.Category;
import com.example.tech4life.recycleritems.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private ArrayList<Category> mCategory;
    private Context mContext;
    public static final int KEY_1 = 0;

    public CategoryAdapter(Context mContext, ArrayList<Category> mComment ) {
        this.mCategory = mComment;
        this.mContext = mContext;
    }

    @NonNull
    @Override

    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View categoryItemView = inflater.inflate(R.layout.activity_category_item, parent, false);
        CategoryAdapter.ViewHolder viewHolder = new CategoryAdapter.ViewHolder(categoryItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category category = mCategory.get(position);
        holder.tvCategoryName.setText(category.getName());
        holder.btnFollowButton.setTag( R.string.KEY, category.getId());
        Picasso.get().load("http://10.0.2.2:8000/img/category"+category.getImage()).into(holder.ivCategoryImg);
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView ivCategoryImg;
        public TextView tvCategoryName;
        public Button btnFollowButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoryImg = itemView.findViewById(R.id.category_img);
            tvCategoryName = itemView.findViewById(R.id.category_name);
            btnFollowButton = itemView.findViewById(R.id.btnFollowCategory);
        }
    }
}
