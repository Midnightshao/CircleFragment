package project.myapplication;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private RelativeLayout relativeLayout;
    private FragmentTransaction transaction;
    private FrameLayout fragment;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        game();
        init();
    }

    public void game()
    {
        intent=new Intent(MainActivity.this,Main2Activity.class);
        transaction = getSupportFragmentManager().beginTransaction();
       fragment=(FrameLayout)findViewById(R.id.fragment);
    }
    public void init(){
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.WHITE);
                        fragment.setVisibility(View.VISIBLE);
                        button.setVisibility(View.GONE);
                        Animator animator = ViewAnimationUtils.createCircularReveal(
                                relativeLayout,0,
                                relativeLayout.getHeight(),
                                0,
                                relativeLayout.getHeight()+100);
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.setDuration(800);
                        animator.start();
                        transaction.add(R.id.fragment, new BlankFragment());
                        transaction.commit();
//                        MainActivity.this.startActivity(intent,animator);

//                        animator.addListener(new Animator.AnimatorListener() {
//                            @Override
//                            public void onAnimationStart(Animator animation) {
////                                MainActivity.this.startActivity(intent);
//                            }
//                            @Override
//                            public void onAnimationEnd(Animator animation) {
//                            }
//
//                            @Override
//                            public void onAnimationCancel(Animator animation) {
//
//                            }
//
//                            @Override
//                            public void onAnimationRepeat(Animator animation) {
//
//                            }
//                        });
                    }
                });

//                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
            }
        });
    }
}
