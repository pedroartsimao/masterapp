package com.pedroarthursimao.masterapp.maps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedroarthursimao.masterapp.MainActivity;
import com.pedroarthursimao.masterapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 4/3/15.
 */
public class MapsFragment extends Fragment {

    public static String TITLE = "Maps";
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        ButterKnife.bind(this, rootView);
        setUpToolbar();
        return rootView;
    }

    private void setUpToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_drawer_menu);
        toolbar.setTitle(this.TITLE);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
    }
}
