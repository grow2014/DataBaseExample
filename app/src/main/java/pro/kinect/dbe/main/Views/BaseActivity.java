package pro.kinect.dbe.main.Views;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pro.kinect.dbe.R;

/**
 * Created by http://kinect.pro on 03.10.16.
 * Developer Andrew.Gahov@gmail.com
 */

public abstract class BaseActivity  extends AppCompatActivity {

    private ProgressDialog progressDialog;

    public void showProgress(boolean isShow) {
        removeProgressDialog();
        if (isShow) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
        }
    }

    private void removeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void showMessage(String message) {
        Snackbar.make(getRootView(), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

    public abstract View getRootView();
}
