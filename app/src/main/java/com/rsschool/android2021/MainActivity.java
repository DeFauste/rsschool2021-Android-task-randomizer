package com.rsschool.android2021;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements FirstFragment.onActionFirstFragment, SecondFragment.onActionSecondFragment {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        // TODO: invoke function which apply changes of the transaction
        transaction.commit();

    }

    private void openSecondFragment(int min, int max) {
        // TODO: implement it
        final Fragment secondFragment = SecondFragment.newInstance(min,max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,secondFragment);
        transaction.commit();

    }


    @Override
    public void onActionPerformed(int min, int max) {
        openSecondFragment(min,max);
    }

    @Override
    public void onActionPerformed(@NotNull int result) {
        openFirstFragment(result);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if(fragment instanceof IOnBackPressed) {
            openFirstFragment(((IOnBackPressed) fragment).onBackPressed());
        } else {
            openQuitDialog();
        }
    }


    //handling random output
    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                this);
        quitDialog.setTitle("Выход: Данные будут потеряны!");

        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }
}
