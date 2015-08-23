package com.pedroarthursimao.masterapp.sqlite;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.pedroarthursimao.masterapp.MainActivity;
import com.pedroarthursimao.masterapp.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SQLiteFragment extends Fragment {

    public static String TITLE = "SQLite";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    SQLiteTools sqliteTools;
    @Bind(R.id.etFrom)
    EditText etFrom;
    @Bind(R.id.etTo)
    EditText etTo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sqlite, container, false);
        ButterKnife.bind(this, rootView);
        setUpToolbar();
        SimpleOpenHelper simpleOpenHelper = new SimpleOpenHelper(getActivity()); //Only to create sqlite in first time
        simpleOpenHelper.getWritableDatabase();
        simpleOpenHelper.close();
        sqliteTools = new SQLiteTools(getActivity(), SimpleOpenHelper.DB_NAME);
        etTo.setText(Environment.getExternalStorageDirectory().getAbsolutePath() + "/export/" + SimpleOpenHelper.DB_NAME);
        etFrom.setText(Environment.getExternalStorageDirectory().getAbsolutePath() + "/import/" + SimpleOpenHelper.DB_NAME);
        return rootView;
    }

    private void setUpToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_drawer_menu);
        toolbar.setTitle(this.TITLE);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
    }

    @OnClick(R.id.btnExport)
    public void exportDatabase(){
        String toast = sqliteTools.exportDatabase(new File(etTo.getText().toString().trim())) ? "Success" : "Error";
        Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnImport)
    public void importDatabase(){
        String toast = sqliteTools.importDatabase(new File(etFrom.getText().toString().trim())) ? "Success" : "Error";
        Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
    }
}
