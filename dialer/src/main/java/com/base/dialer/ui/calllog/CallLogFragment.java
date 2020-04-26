package com.base.dialer.ui.calllog;

import android.database.Cursor;
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
import androidx.recyclerview.widget.RecyclerView;

import com.base.dialer.R;

import java.lang.annotation.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallLogFragment extends Fragment {

    private CallLogViewModel mCallLogViewModel;

    @BindView(R.id.empty_info)
    private TextView mEmptyInfo;
    @BindView(R.id.call_log)
    private RecyclerView mCallLogView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_call_log, container, false);
        ButterKnife.bind(root);
        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCallLogViewModel =
                ViewModelProviders.of(this).get(CallLogViewModel.class);
        mCallLogViewModel.getCallLog().observe(getViewLifecycleOwner(), new Observer<Cursor>() {
            @Override
            public void onChanged(Cursor cursor) {
                if (cursor == null || cursor.getCount() <=0) {
                    mEmptyInfo.setVisibility(View.VISIBLE);
                    mCallLogView.setVisibility(View.GONE);
                } else {
                    mEmptyInfo.setVisibility(View.GONE);
                    mCallLogView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
