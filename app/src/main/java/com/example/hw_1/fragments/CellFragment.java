package com.example.hw_1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hw_1.R;

public class CellFragment extends Fragment {

    private int number;
    private String TEXT_NUMBER = "NUMBER";

    public CellFragment() {}

    public CellFragment(int number) {
        this.number = number;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            number = savedInstanceState.getInt(TEXT_NUMBER);
        }
        return inflater.inflate(R.layout.cell_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView textView = view.findViewById(R.id.number);
        textView.setText(Integer.toString(number));
        if (number % 2 == 0) {
            textView.setTextColor(getResources().getColor(R.color.material_red));
        } else {
            textView.setTextColor(getResources().getColor(R.color.material_blue));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(TEXT_NUMBER, number);
        super.onSaveInstanceState(outState);
    }
}
