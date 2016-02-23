package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labsgn.labsgn_learn_android.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MainFragment extends Fragment {

    //Todo: 3. (Answer) Buat sebuah instance dari ActionBarDrawerToggle kemudian tentukan activity-nya (MainActivity), objek DrawerLayout, Icon humberger Toolbar, Strings "Open" & "Close"
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    //Todo: 3. (Answer) Buat sebuah instance dari ActionBarDrawerToggle kemudian tentukan activity-nya (MainActivity), objek DrawerLayout, Icon humberger Toolbar, Strings "Open" & "Close"
    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close){

            // Todo: 4. (Answer) Invalidate Menu Option ketika drawer dibuka untuk menandakan jika ada perubahan.
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // Todo: 5. (Answer) Invalidate Menu Activity ketika drawer ditutup.
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
}
