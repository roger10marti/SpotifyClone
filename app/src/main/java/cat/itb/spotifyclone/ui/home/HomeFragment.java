package cat.itb.spotifyclone.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.api.ApiHelper;
import cat.itb.spotifyclone.model.Album;
import cat.itb.spotifyclone.model.AlbumSimple;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    private List<AlbumSimple> lista;
    private List<Album> list;
    private ImageButton buttonSettings;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        buttonSettings = v.findViewById(R.id.settingsButton);

        recyclerView = v.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
       // lista = homeViewModel.getAlbumSimples();
        list = new ArrayList<>();

        int[] albumes4Dani = {303391, 78630952, 78630352, 303656, 78677632, 299180};
        for (int i : albumes4Dani) {
            list.add(ApiHelper.consultarAlbum(i));
        }
        HomeAdapter adapter = new HomeAdapter(list, getContext());
        recyclerView.setAdapter(adapter);

        recyclerView2 = v.findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setAdapter(adapter);

        recyclerView3 = v.findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView3.setAdapter(adapter);

        recyclerView4 = v.findViewById(R.id.recyclerView4);
        recyclerView4.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView4.setAdapter(adapter);

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_settingsFragment);
            }
        });

        return v;
    }
}