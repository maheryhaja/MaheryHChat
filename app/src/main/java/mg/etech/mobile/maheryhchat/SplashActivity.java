package mg.etech.mobile.maheryhchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 23;

    @AfterViews
    void initAfterView() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            //already signed
        } else {
            signUser();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void signUser() {
        AuthUI.IdpConfig mailIdpConfig = new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build();
        List<AuthUI.IdpConfig> idpConfigs = new ArrayList<>();
        idpConfigs.add(mailIdpConfig);
        startActivityForResult(
                AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(idpConfigs)
                .build()
        , RC_SIGN_IN);
    }

}
