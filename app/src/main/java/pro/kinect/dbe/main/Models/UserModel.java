package pro.kinect.dbe.main.Models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by http://kinect.pro on 03.10.16.
 * Developer Andrew.Gahov@gmail.com
 */

@IgnoreExtraProperties
public class UserModel {
    public String name;
    public String email;

    public UserModel() {
        // Default constructor required for calls to DataSnapshot.getValue(GoogleExample_Post.class)
    }

    public UserModel(String email) {
        if (email.contains("@")) this.name = email.split("@")[0];
        else this.name = email;

        this.email = email;
    }
}
