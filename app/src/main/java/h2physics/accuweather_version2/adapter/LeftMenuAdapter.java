package h2physics.accuweather_version2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import h2physics.accuweather_version2.R;
import h2physics.accuweather_version2.model.MenuItem;

/**
 * Created by YukiNoHara on 8/30/2017.
 */

public class LeftMenuAdapter extends BaseListAdapter<MenuItem>{
    private Context mContext;
    private LayoutInflater mInflate;

    public LeftMenuAdapter(Context mContext) {
        this.mContext = mContext;
        mInflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            view = mInflate.inflate(R.layout.menu_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.bind(getItem(i), i);
        return view;
    }

    private class ViewHolder extends BaseViewHolder{
        private ImageView mIcon;
        private TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.imv_menu_icon);
            mTitle = (TextView) itemView.findViewById(R.id.tv_menu_title);
        }

        @Override
        public void bind(MenuItem menuItem, int position) {
            mIcon.setImageResource(menuItem.icon);
            mTitle.setText(menuItem.title);
        }
    }
}
