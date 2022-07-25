package com.example.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

/**
 * @ClassName FourFragment
 * @Description TODO
 * @Author user
 * @Date 2019/12/4
 * @Version 1.0
 */
public class FourFragment extends Fragment {
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        button = view.findViewById(R.id.btn);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NavHostFragment.findNavController(FourFragment.this).navigate(R.id.action_fourFragment_to_oneFragment);
            }
        });
    }

}
