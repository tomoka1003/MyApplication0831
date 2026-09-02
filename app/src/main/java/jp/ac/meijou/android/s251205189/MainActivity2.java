package jp.ac.meijou.android.s251205189;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205189.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    // 演習3：
    // MainActivity3から返ってくる結果を受け取るためのLauncher
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
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
         * 演習3：
         * MainActivity3から戻ってきた結果を受け取る
         */
        activityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {

                            // OKが押された場合
                            if (result.getResultCode() == RESULT_OK) {
                                binding.resultTextView.setText("Result: OK");
                            }

                            // Cancelが押された場合
                            else if (result.getResultCode() == RESULT_CANCELED) {
                                binding.resultTextView.setText("Result: Cancel");
                            }
                        }
                );


        /*
         * 明示的Intent：
         * 電卓画面(MainActivity3)へ遷移
         */
        binding.buttonA.setOnClickListener(view -> {

            Intent intent = new Intent(
                    MainActivity2.this,
                    MainActivity3.class
            );

            startActivity(intent);
        });


        /*
         * 暗黙的Intent：
         * ブラウザでYahoo!を開く
         */
        binding.buttonB.setOnClickListener(view -> {

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.yahoo.co.jp")
            );

            startActivity(intent);
        });


        /*
         * EditTextの文字をIntentに乗せて
         * MainActivity3に渡す
         */
        binding.intentButton.setOnClickListener(view -> {

            String sentText =
                    binding.intentEditText
                            .getText()
                            .toString();

            Intent intent = new Intent(
                    MainActivity2.this,
                    MainActivity3.class
            );

            intent.putExtra("editText", sentText);

            startActivity(intent);
        });


        /*
         * 演習3：
         * MainActivity3を起動し、
         * 戻り値をActivityResultLauncherで受け取る
         */
        binding.resultButton.setOnClickListener(view -> {

            Intent intent = new Intent(
                    MainActivity2.this,
                    MainActivity3.class
            );

            activityResultLauncher.launch(intent);
        });
    }
}




