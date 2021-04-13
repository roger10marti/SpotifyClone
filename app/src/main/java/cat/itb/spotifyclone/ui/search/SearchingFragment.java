package cat.itb.spotifyclone.ui.search;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.api.ApiHelper;
import cat.itb.spotifyclone.model.Datum;

public class SearchingFragment extends Fragment {

    private RecyclerView recyclerView;

    private TextInputEditText searchEt;
    private TextInputLayout searchIl;
    private List<Datum> busqueda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_searching, container, false);

        searchEt = v.findViewById(R.id.et_searching);
        searchEt.requestFocus();
        showKeyboard();

        busqueda=null;

        searchEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 66 && event.getAction() == KeyEvent.ACTION_UP){
                    String text = searchEt.getText().toString();
                    busqueda = ApiHelper.lanzarPeticion(text);
                    actualizarRecycler();
                    closeKeyboard();
                }
                return false;
            }
        });



        recyclerView = v.findViewById(R.id.search_rv);


        return v;
    }

    private void actualizarRecycler(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        SearchingAdapter adapter = new SearchingAdapter(busqueda, getContext());
        recyclerView.setAdapter(adapter);
    }

    public void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}