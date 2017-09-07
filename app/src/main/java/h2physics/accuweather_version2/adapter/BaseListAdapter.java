package h2physics.accuweather_version2.adapter;

import android.view.View;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YukiNoHara on 8/30/2017.
 */

public abstract class BaseListAdapter<T> extends BaseAdapter{
    protected List<T> mList = new ArrayList<>();

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        if (position < 0 || position > getCount()){
            return null;
        }
        return mList.size() == 0 ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(T i){
        mList.add(i);
    }

    public void addAll(List<T> list){
        mList.addAll(list);
    }

    public void resetAll(List<T> list){
        if (list == null){
            return;
        }
        list.clear();
        list.addAll(list);
        notifyDataSetChanged();
    }

    public List<T> getList(){
        return mList;
    }

    public void clear(){
        mList.clear();
    }

    public int getRows(int columns) {
        return getCount() > 0 ? (getCount() - 1) / columns + 1 : 0;
    }

    public abstract class BaseViewHolder {
        protected View itemView;

        public BaseViewHolder(View itemView) {
            this.itemView = itemView;
        }

        public abstract void bind(T t, int position);
    }
}
