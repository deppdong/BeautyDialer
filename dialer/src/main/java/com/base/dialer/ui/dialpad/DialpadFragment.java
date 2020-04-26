package com.base.dialer.ui.dialpad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.base.dialer.R;

public class DialpadFragment extends Fragment {

    private DialpadViewModel dialpadViewMode;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dialpadViewMode =
                ViewModelProviders.of(this).get(DialpadViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dialpad, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        dialpadViewMode.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
