package pro.kinect.dbe.google_example.fragment.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pro.kinect.dbe.R;
import pro.kinect.dbe.google_example.fragment.models.GoogleExample_Post;

public class GoogleExample_PostViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;

    public GoogleExample_PostViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.post_title);
        authorView = (TextView) itemView.findViewById(R.id.post_author);
        starView = (ImageView) itemView.findViewById(R.id.star);
        numStarsView = (TextView) itemView.findViewById(R.id.post_num_stars);
        bodyView = (TextView) itemView.findViewById(R.id.post_body);
    }

    public void bindToPost(GoogleExample_Post googleExamplePost, View.OnClickListener starClickListener) {
        titleView.setText(googleExamplePost.title);
        authorView.setText(googleExamplePost.author);
        numStarsView.setText(String.valueOf(googleExamplePost.starCount));
        bodyView.setText(googleExamplePost.body);

        starView.setOnClickListener(starClickListener);
    }
}
