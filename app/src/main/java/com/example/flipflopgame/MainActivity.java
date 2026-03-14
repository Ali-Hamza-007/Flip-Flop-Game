package com.example.flipflopgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton[] btns = new MaterialButton[64];
    private TextView timerText;
    private CountDownTimer gameTimer;

    private int previousCardIndex = -1;
    private boolean isProcessing = false;

    private boolean isGameOver = false;
    private List<Integer> currentGameValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.timerText);
        MaterialButton btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(v -> {
            isGameOver = false;
            setupBoard();
            startNewTimer();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize board for the first time
        setupBoard();
        startNewTimer();

    }

    private void setupBoard() {
        currentGameValues = generateGameValues();
        previousCardIndex = -1;
        isProcessing = false;
        for (int i = 0; i < 64; i++) {
            String buttonID = "btn" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());

            if (resID != 0) {
                btns[i] = findViewById(resID);
                btns[i].setText("");
                btns[i].setEnabled(true);

                final int index = i;
                btns[i].setOnClickListener(v -> onButtonClick(index));
            }
        }
    }

    private void startNewTimer() {
        if (gameTimer != null) gameTimer.cancel();

        // 60000ms = 60 seconds = 1 min, Here using 2 min = 60000 * 2
        gameTimer = new CountDownTimer(60000*2, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time Left: " + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                isGameOver = true; // LOCK the board
                timerText.setText("Time's Up!");
                Toast.makeText(MainActivity.this, "Time Over! Press Play Again.", Toast.LENGTH_LONG).show();

            }
        }.start();
    }

    private void onButtonClick(int index) {
        if (isGameOver || isProcessing || index == previousCardIndex || !btns[index].isEnabled()) {
            return;
        }


        String value = String.valueOf(currentGameValues.get(index));
        btns[index].setText(value);

        if (previousCardIndex == -1) {
            previousCardIndex = index;
        } else {
            if (btns[index].getText().equals(btns[previousCardIndex].getText())) {
                btns[index].setEnabled(false);
                btns[previousCardIndex].setEnabled(false);
                previousCardIndex = -1;
                checkVictory();
            } else {
                isProcessing = true;
                new Handler().postDelayed(() -> {
                    btns[index].setText("");
                    btns[previousCardIndex].setText("");
                    previousCardIndex = -1;
                    isProcessing = false;
                }, 500);
            }
        }
    }

    private void checkVictory() {
        boolean allMatched = true;
        for (MaterialButton btn : btns) {
            if (btn.isEnabled()) {
                allMatched = false;
                break;
            }
        }
        if (allMatched) {
            isGameOver = true;
            gameTimer.cancel();
            Toast.makeText(this, "Victory! You matched them all!", Toast.LENGTH_LONG).show();
        }
    }

    public List<Integer> generateGameValues() {
        List<Integer> gameList = new ArrayList<>();
        for (int i = 1; i <= 32; i++) {
            gameList.add(i);
            gameList.add(i);
        }
        Collections.shuffle(gameList);
        return gameList;
    }
}