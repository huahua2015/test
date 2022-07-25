package com.example.jetpack.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jetpack.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.List;


public class StudentAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {
    public StudentAdapter(@Nullable List<Student> data) {
        super(R.layout.list_item_student,data);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, Student item) {
        helper.setText(R.id.tvId,item.id+"")
                .setText(R.id.tvName,item.name)
                .setText(R.id.tvAge,item.age);
    }
}

