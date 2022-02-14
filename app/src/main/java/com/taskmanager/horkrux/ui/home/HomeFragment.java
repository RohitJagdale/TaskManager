package com.taskmanager.horkrux.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    final String[] taskCategories = {"ALL", "TODO", "IN PROGRESS", "DONE"};
    final int ALL = 0, TODO = 1, IN_PROGRESS = 2, DONE = 3;
    ArrayAdapter taskCategoryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        taskCategoryAdapter = new ArrayAdapter(getContext(), R.layout.home_list_item, taskCategories);
        binding.taskCategory.setAdapter(taskCategoryAdapter);

        binding.taskCategory.setOnItemClickListener(taskCategoryListener);
//        FirebaseAuth.getInstance().signOut();

        return binding.getRoot();
    }


    AdapterView.OnItemClickListener taskCategoryListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            binding.currentTaskCategory.setText(taskCategories[position]);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}