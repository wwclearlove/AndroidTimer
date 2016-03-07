package dev.xesam.android.toolbox.timer.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import dev.xesam.android.toolbox.timer.CountDownTimer;
import dev.xesam.android.toolbox.timer.CountTimer;

/**
 * xesamguo@gmail.com
 * */
public class CountTimerActivity extends AppCompatActivity {

    @InjectView(R.id.count_timer_ts)
    public TextSwitcher vCountSwitcher;
    @InjectView(R.id.count_timer_status)
    public TextView vCountStatus;

    @InjectView(R.id.countdown_timer_ts)
    public TextSwitcher vCountDownSwitcher;
    @InjectView(R.id.countdown_timer_status)
    public TextView vCountDownStatus;


    private CountTimer countTimer;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_timer);
        ButterKnife.inject(this);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        vCountSwitcher.setInAnimation(in);
        vCountSwitcher.setOutAnimation(out);

        countTimer = new CountTimer(100) {
            @Override
            public void onCancel(long millisFly) {
                vCountStatus.setText((millisFly) + ":onCancel");
            }

            @Override
            public void onPause(long millisFly) {
                vCountStatus.setText((millisFly) + ":onPause");
            }

            @Override
            public void onResume(long millisFly) {
                vCountStatus.setText((millisFly) + ":onResume");
            }

            @Override
            public void onTick(long millisFly) {
                vCountSwitcher.setText((millisFly) + "");
                Log.d("onTick", millisFly + "");
            }
        };

        countDownTimer = new CountDownTimer(10000, 100) {
            @Override
            public void onCancel(long millisUntilFinished) {
                vCountDownStatus.setText((millisUntilFinished) + ":onCancel");
            }

            @Override
            public void onPause(long millisUntilFinished) {
                vCountDownStatus.setText((millisUntilFinished) + ":onPause");
            }

            @Override
            public void onResume(long millisUntilFinished) {
                vCountDownStatus.setText((millisUntilFinished) + ":onResume");
            }

            @Override
            public void onTick(long millisUntilFinished) {
                vCountDownSwitcher.setText((millisUntilFinished) + "");
                Log.d("onTick", millisUntilFinished + "");
            }

            @Override
            public void onFinish() {
                vCountDownStatus.setText("onFinish");
            }
        };
    }

    @OnClick(R.id.count_timer_start)
    public void count_start() {
        countTimer.start();
        vCountStatus.setText("0:start");
    }

    @OnClick(R.id.count_timer_pause)
    public void count_pause() {
        countTimer.pause();
    }

    @OnClick(R.id.count_timer_resume)
    public void count_resume() {
        countTimer.resume();
    }

    @OnClick(R.id.count_timer_cancel)
    public void count_cancel() {
        countTimer.cancel();
    }

    ////////////////////////////////////////////////////////

    @OnClick(R.id.countdown_timer_start)
    public void countdown_start() {
        countDownTimer.start();
        vCountDownStatus.setText("start");
    }

    @OnClick(R.id.countdown_timer_pause)
    public void countdown_pause() {
        countDownTimer.pause();
    }

    @OnClick(R.id.countdown_timer_resume)
    public void countdown_resume() {
        countDownTimer.resume();
    }

    @OnClick(R.id.countdown_timer_cancel)
    public void countdown_cancel() {
        countDownTimer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countTimer.cancel();
        countDownTimer.cancel();
    }
}
