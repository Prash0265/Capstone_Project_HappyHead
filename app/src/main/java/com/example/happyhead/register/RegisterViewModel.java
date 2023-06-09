package com.example.happyhead.register;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.happyhead.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();

    RegisterViewModel() {
    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    public void registerDataChanged(String username, String password, String name,
                                    String DOB,  String gender, String country) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_username, null, null, null, null, null));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_password, null, null, null, null));
        } else if (!isDOBValid((DOB))) {
            registerFormState.setValue(new RegisterFormState(null, null, null, R.string.invalid_DOB, null, null));
        } else if (!isGenderValid(gender)) {
            registerFormState.setValue(new RegisterFormState(null, null, null, null, R.string.invalid_gender, null));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    // A placeholder DOB validation check
    private boolean isDOBValid(String DOB) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(DOB.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    private boolean isGenderValid(String gender) {
        if (gender.equals("Select")) {
            return false;
        }
        return true;
    }
}

