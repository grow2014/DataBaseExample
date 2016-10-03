package pro.kinect.dbe.google_example.fragment.models;

import com.google.firebase.database.IgnoreExtraProperties;

// [START comment_class]
@IgnoreExtraProperties
public class GoogleExample_Comment {

    public String uid;
    public String author;
    public String text;

    public GoogleExample_Comment() {
        // Default constructor required for calls to DataSnapshot.getValue(GoogleExample_Comment.class)
    }

    public GoogleExample_Comment(String uid, String author, String text) {
        this.uid = uid;
        this.author = author;
        this.text = text;
    }

}
// [END comment_class]
