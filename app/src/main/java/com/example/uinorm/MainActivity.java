package com.example.uinorm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity{

    private EditText txtName, txtEmail, txtPassword, txtPassword2;
    private TextView txtWarnName, txtWarnEmail, txtWarnPass, txtWarnPass2;
    private RadioGroup gender;
    private CheckBox checkBoxAgreement;
    private Spinner spinner;
    private Button btnPickView;
    private Button btnRegister;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initRegister();

    }

    public void initRegister() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    if (checkBoxAgreement.isChecked()) {
                        showSnackBar();
                    } else {
                        Toast.makeText(MainActivity.this, "You need to agree to the license agreement", Toast.LENGTH_SHORT).show();
                    }
                }
                
            }
        });
    }

    private void showSnackBar() {
        txtWarnName.setVisibility(View.GONE);
        Snackbar.make(parent, "User registered", Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtName.setText("");
                        txtEmail.setText("");
                        txtPassword.setText("");
                        txtPassword2.setText("");
                        checkBoxAgreement.setChecked(false);
                    }
                }).show();
    }

    private boolean validateData() {
        if (("").equals(txtName.getText().toString())) {
            txtWarnName.setVisibility(View.VISIBLE);
            return false;
        }

        if (("").equals(txtEmail.getText().toString())) {
            txtWarnEmail.setVisibility(View.VISIBLE);
            return false;
        }

        if (("").equals(txtPassword.getText().toString())) {
            txtWarnPass.setVisibility(View.VISIBLE);
            return false;
        }

        if (("").equals(txtPassword2.getText().toString())) {
            txtWarnPass2.setText("Confirm password");
            txtWarnPass2.setVisibility(View.VISIBLE);
            return false;
        }

        if (!txtPassword2.getText().toString().equals(txtPassword.getText().toString())) {
            txtWarnPass2.setText("Password doesn't match!");
            txtWarnPass2.setVisibility(View.VISIBLE);
        }

        return true;
    }

    public void initViews() {
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword2 = findViewById(R.id.txtPassword2);
        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPass = findViewById(R.id.txtWarnPass);
        txtWarnPass2 = findViewById(R.id.txtWarnPass2);
        checkBoxAgreement = findViewById(R.id.checkBoxAgree);
        gender = findViewById(R.id.radioGroupGender);
        parent = findViewById(R.id.parent);

        btnRegister = findViewById(R.id.btnRegister);
        btnPickView = findViewById(R.id.btnPickView);


        spinner = findViewById(R.id.spinnerCountry);
        ArrayAdapter<CharSequence> adapterView = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_list_item_1);
        adapterView.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterView);
    }


}