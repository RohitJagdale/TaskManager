package com.taskmanager.horkrux.Authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.taskmanager.horkrux.R;

public class LoginTabfragment extends Fragment {

    EditText email, pass;
    TextView forgotpass;
    Button login;
    float v = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_fragment, container, false);

        email = root.findViewById(R.id.loginemail);
        pass = root.findViewById(R.id.loginpass);
        forgotpass = root.findViewById(R.id.forgotpass);
        login = root.findViewById(R.id.loginbutton);


        email.setTranslationY(800);
        pass.setTranslationY(800);
        forgotpass.setTranslationY(800);
        login.setTranslationY(800);


        email.setAlpha(v);
        pass.setAlpha(v);
        forgotpass.setAlpha(v);
        login.setAlpha(v);


        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        pass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        forgotpass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();



        return root;

    }

}
