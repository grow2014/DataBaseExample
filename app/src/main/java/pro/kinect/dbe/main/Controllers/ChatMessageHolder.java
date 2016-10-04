package pro.kinect.dbe.main.Controllers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pro.kinect.dbe.R;

/**
 * Created by http://kinect.pro on 04.10.16.
 * Developer Andrew.Gahov@gmail.com
 */

public class ChatMessageHolder extends RecyclerView.ViewHolder  {

    public TextView tvTime;
    public TextView tvBody;
    public LinearLayout llContent;

    public ChatMessageHolder(View itemView) {
        super(itemView);
        tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        tvBody = (TextView) itemView.findViewById(R.id.tvBody);
        llContent = (LinearLayout) itemView.findViewById(R.id.llContent);
    }
}
