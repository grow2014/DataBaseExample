package pro.kinect.dbe.main.Controllers;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pro.kinect.dbe.R;
import pro.kinect.dbe.main.Views.SignInActivity;

/**
 * Created by http://kinect.pro on 03.10.16.
 * Developer Andrew.Gahov@gmail.com
 */

public class SignInController {
    private static SignInController controller;
    private SignInActivity activity;

    private SignInController() {
        //default constructor
    }

    public static SignInController getController() {
        if (controller == null) {
            controller = new SignInController();
        }
        return controller;
    }

    public void setActivity(SignInActivity act) {
        activity = act;
    }

    public static SignInController getController(SignInActivity activity) {
        getController().setActivity(activity);
        return getController();
    }

    public void checkValues(String email, String password) {
        if (activity != null) {
            //example of check fields
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                //go to server
                activity.showProgress(true);
                sendSignIn(email, password);

            } else {
                if (TextUtils.isEmpty(email)) activity.etEmail.setError(
                        activity.getString(R.string.empty_email));
                if (TextUtils.isEmpty(password)) activity.etPassword.setError(
                        activity.getString(R.string.empty_password)
                );

                activity.showProgress(false);
            }
        }
    }


    ///// --------  THE BLOCK OF SIGN IN WITH EMAIL STARTED ---------- //////
    //the first we will try sign in
    public void sendSignIn(final String email, final String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            sendRegistration(email, password);
                        } else {
                            //completeSignIn;
                            if (activity != null) activity.goToChatActivity();
                        }
                    }
                });
    }

    //if we have error after sign in we will try register
    private void sendRegistration(String email, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (activity != null) {
                                activity.showProgress(false);
                                activity.showMessage("Your password is wrong or " +
                                        task.getException().getMessage());
                            }
                        } else {
                            //completeRegistration;
                            if (activity != null) activity.goToChatActivity();
                        }
                    }
                });
    }
    ///// --------  THE BLOCK OF SIGN IN WITH EMAIL FINISHED ---------- //////
}
