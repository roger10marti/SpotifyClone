package cat.itb.spotifyclone.ui.search;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;

import cat.itb.spotifyclone.R;

public class SearchingFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchingViewModel viewModel;
    private TextInputEditText searchEt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(SearchingViewModel.class);
        View v = inflater.inflate(R.layout.fragment_searching, container, false);

        searchEt = v.findViewById(R.id.et_searching);

        recyclerView = v.findViewById(R.id.search_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));

        return v;
    }
}