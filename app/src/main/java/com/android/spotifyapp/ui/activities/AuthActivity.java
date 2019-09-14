package com.android.spotifyapp.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.spotifyapp.data.viewModelPackage.AuthViewModel;
import com.android.spotifyapp.databinding.ActivityAuthBinding;
import com.android.spotifyapp.di.components.DaggerAuthComponent;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.POGO;
import com.android.spotifyapp.utils.SharedPreferencesUtil;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;
import static com.android.spotifyapp.utils.Contracts.SpotifyAuthContract.REDIRECT_URL;
import static com.android.spotifyapp.utils.Contracts.SpotifyAuthContract.URI;
import static com.android.spotifyapp.utils.ProgressBar.progressBarUnvisible;

public class AuthActivity extends AppCompatActivity {

    @Inject AuthViewModel authViewModel;
    @Inject CompositeDisposable disposables;
    @Inject ActivityAuthBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerAuthComponent.builder()
                .viewModelsModule(new ViewModelsModule(null))
                .contextModule(new ContextModule(this))
                .build().inject(this);
        POGO pogo = new POGO("this", "that");
        binding.setPOGO(pogo);
        binding.button2.setOnClickListener(view -> {
            

        });
        binding.button3.setOnClickListener(view -> {

            Toast.makeText(this, "RESULT: "+ pogo.getShit(), Toast.LENGTH_LONG).show();
        });
        RxView.clicks(binding.redirectbtn)
                .throttleFirst(10000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Unit unit) {
                        binding.setVisibility(true);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URI + REDIRECT_URL));
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AuthActivity.this, "Oops! Something went wrong...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // Dispose observable
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBarUnvisible(binding.progressBar);
        binding.setVisibility(false);
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(REDIRECT_URL)) {
            String code = uri.getQueryParameter("code");
            authViewModel.getTokenData(code).observe(this, accessToken -> {
                if(!accessToken.getAccess_token().isEmpty()) {
                    SharedPreferences.Editor editor = SharedPreferencesUtil.getPreferences(shared_preferences_auth, getApplicationContext()).edit();
                    editor.putString(access_token, accessToken.getAccess_token()).apply();
//                    progressBarVisible(binding.progressBar);
                    binding.setVisibility(true);
                    Intent intent = new Intent(getBaseContext(), BaseActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
