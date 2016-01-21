package com.example.ideanote.toolbarshowandhidesample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ideanote.toolbarshowandhidesample.model.Article;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    private List<Article> mArticles;

    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter(List<Article> articles) {
        mArticles = articles;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card, parent, false);
        return new Holder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mArticles.get(position));
    }

    public static class Holder extends RecyclerView.ViewHolder {

        private View mItemView;

        private TextView mTitleText;

        private TextView mDescriptionText;

        private OnItemClickListener mOnItemClickListener;

        Holder(View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);

            mItemView = itemView;
            mTitleText = (TextView) itemView.findViewById(R.id.title);
            mDescriptionText = (TextView) itemView.findViewById(R.id.description);
            mOnItemClickListener = onItemClickListener;
        }

        public void bind(final Article article) {
            mTitleText.setText(article.getTitle());
            mDescriptionText.setText(article.getDescription());

            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(article);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(Article article);
    }
}
