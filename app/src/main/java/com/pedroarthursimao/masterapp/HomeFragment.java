package com.pedroarthursimao.masterapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 4/3/15.
 */
public class HomeFragment extends Fragment {

    public static String TITLE = "Home";
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);
        setUpToolbar();
        setHasOptionsMenu(true);
        return rootView;
    }

    private void setUpToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_drawer_menu);
        toolbar.setTitle(this.TITLE);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home, menu);
    }
}
