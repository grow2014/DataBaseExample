package pro.kinect.dbe.main.Models;

/**
 * Created by http://kinect.pro on 03.10.16.
 * Developer Andrew.Gahov@gmail.com
 */

public class UserModel {
    private String name;
    private String email;

    public UserModel(String email) {
        if (email.contains("@")) this.name = email.split("@")[0];
        else this.name = email;

        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
