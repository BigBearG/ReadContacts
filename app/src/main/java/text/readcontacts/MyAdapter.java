package text.readcontacts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liuwei on 7/14/17.
 */

public class MyAdapter extends BaseAdapter {
    private List<contact> mcontactlist;
    Context mcontext;
    @Override
    public int getCount() {
        return mcontactlist.size();
    }
    public MyAdapter(List<contact> list,Context context){
        mcontactlist=list;
        mcontext=context;
    }
    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        ViewHolder viewHolder;
        if (view==null){
            viewHolder=new ViewHolder();
            v= View.inflate(mcontext,R.layout.contacts,null);
            viewHolder.name= (TextView) v.findViewById(R.id.tv_name);
            viewHolder.phone= (TextView) v.findViewById(R.id.tv_phone);
            v.setTag(viewHolder);
        }else {
            v=view;
            viewHolder =(ViewHolder)v.getTag();
        }
        viewHolder.name.setText(mcontactlist.get(i).getName());
        viewHolder.phone.setText(mcontactlist.get(i).getPhone());
        return v;
    }
    public class ViewHolder{
        TextView name,phone;
    }
}
