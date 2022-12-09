package lam.fpoly.adminmanager.manhinhchoAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;

import lam.fpoly.adminmanager.MainActivity;
import lam.fpoly.adminmanager.R;

public class Manhinhcho extends AppCompatActivity {
    RelativeLayout manhinhchao;
    LottieAnimationView loadingmanhinhchao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhcho);
        manhinhchao=findViewById(R.id.manhinhchao);
        loadingmanhinhchao=findViewById(R.id.loadingmanhinhchao);
        loadingmanhinhchao.startAnimation(AnimationUtils.loadAnimation(this,R.anim.loading_plane));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    load();
                    Intent i=new Intent(Manhinhcho.this, ActivityDangNhap.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }).start();

    }
    public void load(){

        manhinhchao.startAnimation(AnimationUtils.loadAnimation(this,R.anim.manloading));
    }
    }