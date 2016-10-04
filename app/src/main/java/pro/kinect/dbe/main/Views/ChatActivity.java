package pro.kinect.dbe.main.Views;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pro.kinect.dbe.R;
import pro.kinect.dbe.main.Controllers.ChatMessageHolder;
import pro.kinect.dbe.main.Models.MessageModel;

import static pro.kinect.dbe.R.id.rootView;

/**
 * Created by http://kinect.pro on 03.10.16.
 * Developer Andrew.Gahov@gmail.com
 */

public class ChatActivity extends BaseActivity {

    private String myUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            finish();
        } else {
            myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        createAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public View getRootView() {
        return findViewById(rootView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.signOut:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void createAdapter() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Set up Layout Manager, reverse layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("users_messages").limitToFirst(100);

        //how much pixels in 140 dp
        Resources r = getResources();
        final int px140 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 140, r.getDisplayMetrics());

        final SimpleDateFormat sdf = new SimpleDateFormat("d MMM,yy   h:mm:ss a", Locale.getDefault());

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<MessageModel,
                ChatMessageHolder>(MessageModel.class, R.layout.item_message_once,
                ChatMessageHolder.class, query) {
            @Override
            protected void populateViewHolder(ChatMessageHolder viewHolder, MessageModel model, int position) {

                RelativeLayout.LayoutParams tvTime_params = (RelativeLayout.LayoutParams) viewHolder.tvTime.getLayoutParams();
                RelativeLayout.LayoutParams llContent_params = (RelativeLayout.LayoutParams) viewHolder.llContent.getLayoutParams();
                if (myUid != null && myUid.equals(model.author_uid)) {
                    //выравниваем всё вправо
                    tvTime_params.addRule(RelativeLayout.ALIGN_RIGHT, R.id.llContent);
                    llContent_params.setMargins(px140, 0, 0, 0);
                    viewHolder.llContent.setBackground(getResources().getDrawable(R.drawable.border));
                } else {
                    //выравниваем всё влево
                    tvTime_params.addRule(RelativeLayout.ALIGN_LEFT, R.id.llContent);
                    llContent_params.setMargins(0, 0, px140, 0);
                    viewHolder.llContent.setBackground(getResources().getDrawable(R.drawable.border_white_gray));
                }
                viewHolder.tvTime.setLayoutParams(tvTime_params);
                viewHolder.llContent.setLayoutParams(llContent_params);

                String timestamp = sdf.format(new Date(model.message_time));
                viewHolder.tvTime.setText(timestamp);
                viewHolder.tvBody.setText(model.message_body);

            }
        };

        recyclerView.setAdapter(adapter);
    }
}
