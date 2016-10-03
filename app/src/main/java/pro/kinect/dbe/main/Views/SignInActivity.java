package pro.kinect.dbe.main.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import pro.kinect.dbe.R;
import pro.kinect.dbe.main.Controllers.SignInController;

/**
 * Created by http://kinect.pro on 03.10.16.
 * Developer Andrew.Gahov@gmail.com
 */

public class SignInActivity extends BaseActivity {

    public EditText etEmail;
    public EditText etPassword;
    private SignInController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        controller = SignInController.getController(this);
    }

    @Override
    public View getRootView() {
        return findViewById(R.id.rootView);
    }

    @Override
    protected void onDestroy() {
        if (controller != null) controller.setActivity(null);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            goToChatActivity();
        }
    }

    public void goToChatActivity() {
        showProgress(false);
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
        this.finish();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn : {
                //hide keyboard
                View hideView = this.getCurrentFocus();
                if (hideView != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                //check values and sent data to server
                controller.checkValues(String.valueOf(etEmail.getText()),
                        String.valueOf(etPassword.getText()));

                break;
            }
            default: break;
        }
    }



}
