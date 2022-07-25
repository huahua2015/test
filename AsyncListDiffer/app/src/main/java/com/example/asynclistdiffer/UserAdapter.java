package com.example.asynclistdiffer;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.UserViewHodler> {
    private AsyncListDiffer<User> mDiffer;

    private DiffUtil.ItemCallback<User> diffCallback = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(User oldItem, User newItem) {
            return TextUtils.equals(oldItem.getId(), newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(User oldItem, User newItem) {
            return oldItem.getAge() == newItem.getAge();
        }

        @Nullable
        @Override
        public Object getChangePayload(@NonNull User oldItem, @NonNull User newItem) {
            return super.getChangePayload(oldItem, newItem);
        }
    };

    public UserAdapter() {
        mDiffer = new AsyncListDiffer<>(this, diffCallback);
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public void submitList(List<User> data) {
        mDiffer.submitList(data);
    }

    public User getItem(int position) {
        return mDiffer.getCurrentList().get(position);
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);
        return new UserViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHodler holder, int position) {
        holder.setData(getItem(position));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHodler holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    class UserViewHodler extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvAge;

        public UserViewHodler(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
        }

        public void setData(User data) {
            tvName.setText(data.getName());
            tvAge.setText(String.valueOf(data.getAge()));
        }
    }
}