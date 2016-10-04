package pro.kinect.dbe.main.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by http://kinect.pro on 03.10.16.
 * Developer Andrew.Gahov@gmail.com
 */

@IgnoreExtraProperties
public class MessageModel {

    public MessageModel() {
        // Default constructor required for calls to DataSnapshot.getValue(GoogleExample_Post.class)
    }

    public String author_name;
    public String author_uid;
    public String message_body;
    public Long message_time;

    public MessageModel(String author_uid, String author_name, String message_body, Date message_time) {
        this.author_name = author_name;
        this.author_uid = author_uid;
        this.message_body = message_body;
        this.message_time = message_time.getTime();
    }
}
