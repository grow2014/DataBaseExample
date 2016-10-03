package pro.kinect.dbe.google_example.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class GoogleExample_MyPostsFragment extends GoogleExample_PostListFragment {

    public GoogleExample_MyPostsFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("user-posts")
                .child(getUid());
    }
}
