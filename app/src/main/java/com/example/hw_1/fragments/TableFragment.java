package com.example.hw_1.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hw_1.R;

public class TableFragment extends Fragment {
    private int cellCounter = 0;
    private RecyclerView table;
    private CellViewAdapter adapter;
    private String CELLS_COUNT = "CELLS";

    public TableFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        adapter = new CellViewAdapter();
        if (savedInstanceState != null) {
            cellCounter = savedInstanceState.getInt(CELLS_COUNT);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.table_fragment, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_cell, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        table = view.findViewById(R.id.table);
        int orientation = getResources().getConfiguration().orientation;
        int span = orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;
        table.setLayoutManager(new GridLayoutManager(getContext(), span));
        table.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            if (cellCounter >= 100) {
                Toast.makeText(getContext(), "NO MORE CELLS!", Toast.LENGTH_LONG).show();
                return true;
            }
            cellCounter++;
            adapter.notifyItemInserted(cellCounter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CELLS_COUNT, cellCounter);
        super.onSaveInstanceState(outState);
    }

    class CellViewHolder extends RecyclerView.ViewHolder {

        private final TextView cellText;

        public CellViewHolder(View v) {
            super(v);
            cellText = v.findViewById(R.id.cell);
        }

    }

    class CellViewAdapter extends RecyclerView.Adapter<CellViewHolder> {
        @NonNull
        @Override
        public CellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.cell_view, parent, false);
            return new CellViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CellViewHolder holder, int position) {
            final int num = position + 1;
            holder.cellText.setText(Integer.toString(num));
            if (num % 2 == 0) {
                holder.cellText.setTextColor(getResources().getColor(R.color.material_red));
            } else {
                holder.cellText.setTextColor(getResources().getColor(R.color.material_blue));
            }
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CellFragment cellFragment = new CellFragment(num);
                            if (getFragmentManager() != null) {
                                getFragmentManager()
                                        .beginTransaction()
                                        .addToBackStack(null)
                                        .replace(R.id.container, cellFragment)
                                        .commit();
                            }
                        }
                    }
            );
        }

        @Override
        public int getItemCount() {
            return cellCounter;
        }
    }
}

