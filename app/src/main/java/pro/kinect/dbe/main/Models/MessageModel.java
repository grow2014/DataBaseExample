package pro.kinect.dbe.main.Models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by http://kinect.pro on 03.10.16.
 * Developer Andrew.Gahov@gmail.com
 */

@IgnoreExtraProperties
public class MessageModel {

    public MessageModel() {
        // Default constructor required for calls to DataSnapshot.getValue(GoogleExample_Post.class)
    }

    private String author_name;
    private String author_uid;
    private String message_body;
    private long message_time;

}
