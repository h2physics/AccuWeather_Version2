package h2physics.accuweather_version2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import h2physics.accuweather_version2.R;
import h2physics.accuweather_version2.model.Weather;

/**
 * Created by YukiNoHara on 9/4/2017.
 */

public class WeatherAdapter extends BaseListAdapter<Weather> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public WeatherAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            view = mLayoutInflater.inflate(R.layout.weather_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.bind(getItem(position), position);
        return view;
    }

    public class ViewHolder extends BaseViewHolder{
        private TextView mTvDay;
        private ImageView mImvSmallDescription;
        private TextView mTvDescription;
        private TextView mTvTemperature;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvDay = itemView.findViewById(R.id.tv_day);
            mImvSmallDescription = itemView.findViewById(R.id.imv_small_description);
            mTvDescription = itemView.findViewById(R.id.tv_description);
            mTvTemperature = itemView.findViewById(R.id.tv_temperature);
        }

        @Override
        public void bind(Weather weather, int position) {
            mTvDescription.setText(weather.getWeatherDescription().getDescription());
            mTvTemperature.setText(weather.getMain().getMaxTemp() + "/" + weather.getMain().getMinTemp());
        }
    }
}
