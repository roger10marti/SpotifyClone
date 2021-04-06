package cat.itb.spotifyclone.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;

import cat.itb.spotifyclone.R;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private TextInputLayout searchIl;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        searchIl = v.findViewById(R.id.search_et);
        searchIl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }
}