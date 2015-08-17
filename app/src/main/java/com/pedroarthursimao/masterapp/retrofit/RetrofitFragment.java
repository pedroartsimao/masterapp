package com.pedroarthursimao.masterapp.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.pedroarthursimao.masterapp.MainActivity;
import com.pedroarthursimao.masterapp.R;
import com.pedroarthursimao.masterapp.retrofit.rest.RestClient;
import com.pedroarthursimao.masterapp.retrofit.rest.pojo.weather.OpenWeatherData;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RetrofitFragment extends Fragment {

    public static String TITLE = "Retrofit";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editQuery)
    EditText editQuery;
    @Bind(R.id.fabGetAnswer)
    FloatingActionButton fabGetAnswer;
    @Bind(R.id.tvAnswer)
    TextView tvAnswer;

    private RestClient restClient;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_retrofit, container, false);
        ButterKnife.bind(this, rootView);
        restClient = new RestClient();
        setUpToolbar();
        return rootView;
    }

    @OnClick(R.id.fabGetAnswer)
    public void fabOnClick(View view) {
        String city = editQuery.getText().toString().trim();
        if(city.length() > 0){
            tvAnswer.setText("Searching for " + city + "...");
            restClient.getClient().getWeather(city, new Callback<OpenWeatherData>() {
                @Override
                public void success(OpenWeatherData openWeatherData, Response response) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Country: " + openWeatherData.getSys().getCountry() + "\n");
                    sb.append("Name: " + openWeatherData.getName() + "\n");
                    sb.append("Code: " + openWeatherData.getCod() + "\n");
                    sb.append("Lat/long: " + openWeatherData.getCoord().getLat() + "/" + openWeatherData.getCoord().getLon() + "\n");
                    sb.append("Temp: " + openWeatherData.getMain().getTemp() + "\n");
                    sb.append("Min/max: " + openWeatherData.getMain().getTempMin() + "/" + openWeatherData.getMain().getTempMax() + "\n");
                    tvAnswer.setText(sb.toString());
                }

                @Override
                public void failure(RetrofitError error) {
                    tvAnswer.setText(error.getMessage());
                }
            });
        }

    }

    private void setUpToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_drawer_menu);
        toolbar.setTitle(this.TITLE);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
    }
}
