package com.android.spotifyapp.utils.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.android.spotifyapp.R;
import com.android.spotifyapp.ui.fragment.HomeFragment;
import com.android.spotifyapp.utils.ButtonAccess;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Dialog {
    private Context context;
    private View view;
    private StorageReference storageReference;
    private Uri image_uri;
    private Fragment fragment;
    private Uri image_url;
    private AlertDialog alertDialog;
    @BindView(R.id.load_playlist_image_progress) ProgressBar progressBar;
    @BindView(R.id.playlist_image_upload) ImageView playlist_image;
    @BindView(R.id.public_switch) Switch public_switch;
    @BindView(R.id.dialog_title_edittext) EditText dialog_title_editText;
    @BindView(R.id.dialog_description_edittext) EditText dialog_description_editText;
    @BindView(R.id.button_create) Button create;

    public Dialog(Context context, HomeFragment homeFragment) {
        this.context = context;
        storageReference = FirebaseStorage.getInstance().getReference();
        fragment = homeFragment;
        image_url = Uri.parse("null");
    }
    public void dialogshow() {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null, false);
        ButterKnife.bind(this, view);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setView(view);
        alertDialog.show();

    }

    @OnClick(R.id.playlist_image_upload) void OnImagageclick() {
        startActivityFromResult(fragment);

    }

    @OnClick(R.id.button_create) void OnButtonClick() {
            if(dialog_description_editText.getText().toString().isEmpty() || dialog_title_editText.getText().toString().isEmpty()) {
                Toast.makeText(context, "Title or description cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
        ((HomeFragment) fragment).setData(dialog_title_editText.getText().toString(), dialog_description_editText.getText().toString(), image_url.toString(), public_switch.isChecked());
        alertDialog.dismiss();
    }
    public void selectResult(Intent data) {
        com.android.spotifyapp.utils.ProgressBar.progressBarVisible(progressBar);
        ButtonAccess.disable(create);
        image_uri = data.getData();
        storageReference.child("images/" + "profile.jpg").putFile(image_uri)
                .addOnSuccessListener(taskSnapshot -> {

                    Picasso.with(view.getContext())
                            .load(image_uri)
                            .fit()
                            .into(playlist_image, new Callback() {
                                @Override
                                public void onSuccess() {
                                    storageReference.child("images/" + "profile.jpg").getDownloadUrl().addOnSuccessListener(uri -> {
                                        image_url = uri;
                                        ButtonAccess.enable(create);
                                    });
                                    com.android.spotifyapp.utils.ProgressBar.progressBarUnvisible(progressBar);
                                }

                                @Override
                                public void onError() {
                                    ButtonAccess.enable(create);
                                    com.android.spotifyapp.utils.ProgressBar.progressBarUnvisible(progressBar);
                                }
                            });
                })
                .addOnFailureListener(e -> Log.d("REAL", "onFailure: " + e.getMessage()));


    }

    private void startActivityFromResult(Fragment fragment) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        fragment.startActivityForResult(intent, 1);
    }
}
