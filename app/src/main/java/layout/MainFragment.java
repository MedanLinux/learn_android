package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labsgn.labsgn_learn_android.Adapter;
import com.labsgn.labsgn_learn_android.Information;
import com.labsgn.labsgn_learn_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    //Todo 4. Menambahkan instance RecyclerView milik android.v7
    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    //Todo 20. Membuat Instance variable adapter baru
    private Adapter myAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Todo 5. Menghubungkan instance recyclerView dengan recyclerView yang ada didalam main_fragment
        View layout = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        //Todo 24. Simpan instance myAdapter kemudian set adapter ke recyclerView
        myAdapter = new Adapter(getActivity(), getData());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    //Todo 21. Membuat method untuk mengambil data
    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int icons[] = {R.drawable.ic_looks_one_black_48dp, R.drawable.ic_looks_two_black_48dp, R.drawable.ic_looks_3_black_48dp};
        String titles[] ={"Dji","Sam","Soe"};

        //Todo 22. Melakukan perulangan untuk menyimpan data ke dalam Information
        for (int i = 0; i<titles.length && i<icons.length; i++){
            Information current = new Information();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

    public void setUp(DrawerLayout drawerLayout, final Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //Redraw option menu when the drawer opened
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //Redraw option menu when the drawer closed
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
}
