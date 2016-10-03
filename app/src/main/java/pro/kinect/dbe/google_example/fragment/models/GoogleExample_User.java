package pro.kinect.dbe.google_example.fragment.models;

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class GoogleExample_User {

    public String username;
    public String email;

    public GoogleExample_User() {
        // Default constructor required for calls to DataSnapshot.getValue(GoogleExample_User.class)
    }

    public GoogleExample_User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
// [END blog_user_class]
