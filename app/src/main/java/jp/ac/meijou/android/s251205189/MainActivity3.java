package jp.ac.meijou.android.s251205189;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205189.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );


        /*
         * MainActivity2から文字が渡されていれば
         * TextViewに表示する
         */
        Intent intent = getIntent();

        String sentText =
                intent.getStringExtra("editText");

        if (sentText != null) {
            binding.textViewVisible.setText(sentText);
        }


        /*
         * 演習3：
         * OKボタン
         *
         * RESULT_OKを設定して
         * MainActivity2へ戻る
         */
        binding.resultOkButton.setOnClickListener(view -> {

            setResult(RESULT_OK);

            finish();
        });


        /*
         * 演習3：
         * Cancelボタン
         *
         * RESULT_CANCELEDを設定して
         * MainActivity2へ戻る
         */
        binding.resultCancelButton.setOnClickListener(view -> {

            setResult(RESULT_CANCELED);

            finish();
        });
    }
}

