package project.dda.autodoor.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import project.dda.autodoor.BackGround.JSONTaskLogin;
import project.dda.autodoor.Math.MyMath;
import project.dda.autodoor.R;

public class LoginActivity extends AppCompatActivity {
    boolean IsHide = false;
    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonLogin;
    Button buttonVisiable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initProperties();
    }

    void initProperties() {
        editTextUsername = findViewById(R.id.edittextUsername);
        editTextPassword = findViewById(R.id.edittextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonVisiable = findViewById(R.id.buttonVisiable);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mUsername = editTextUsername.getText().toString();
                String mPassword = editTextPassword.getText().toString();

                if (mUsername.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Tài khoản không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                //TODO: create thread to login
                JSONTaskLogin jsonTaskLogin = new JSONTaskLogin(LoginActivity.this);
                jsonTaskLogin.execute("Login", "http://system.aks.vn/api/login", mUsername, "#" + mPassword);

            }
        });

        buttonVisiable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!IsHide) {
                    IsHide = true;
                    editTextPassword.setTransformationMethod(null);

                    if (editTextPassword.getText().length() > 0)
                        editTextPassword.setSelection(editTextPassword.getText().length());

                    buttonVisiable.setBackgroundResource(R.drawable.ic_visibility_black_24dp);
                } else {
                    IsHide = false;
                    editTextPassword.setTransformationMethod(new PasswordTransformationMethod());

                    if (editTextPassword.getText().length() > 0)
                        editTextPassword.setSelection(editTextPassword.getText().length());

                    buttonVisiable.setBackgroundResource(R.drawable.ic_visibility_off_black_24dp);
                }
            }
        });
    }
}
