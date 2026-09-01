package jp.ac.meijou.android.s251205189;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205189.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //TextView textView = findViewById(R.id.text_view);
        //textView.setText("Activity こんにちは");

        //binding.textView.setText(R.string.text);

        prefDataStore = PrefDataStore.getInstance(this);
        prefDataStore.getString("name").ifPresent(name -> binding.textView.setText(name));

        binding.changebutton.setOnClickListener(view -> {
            String text = binding.editTextText.getText().toString();
            binding.textView.setText(text);
        });

        prefDataStore.getString("image").ifPresent(savedText -> {
            if ("a".equals(savedText)) {
                binding.imageView.setImageResource(R.drawable.ic_android);
            } else if ("b".equals(savedText)) {
                binding.imageView.setImageResource(R.drawable.ic_add_loation);
            } else if ("c".equals(savedText)) {
                binding.imageView.setImageResource(R.drawable.outline_123_24);
            }else{
                binding.imageView.setImageResource(R.drawable.outline_16mp_24);
            }
        });

        binding.savebutton.setOnClickListener(view -> {
            String text = binding.editTextText.getText().toString();
            if ("a".equals(text)) {
                binding.imageView.setImageResource(R.drawable.ic_android);
            }else if("b".equals(text)){
                binding.imageView.setImageResource(R.drawable.ic_add_loation);
            }else if("c".equals(text)){
                binding.imageView.setImageResource(R.drawable.outline_123_24);
            }else{
                binding.imageView.setImageResource(R.drawable.outline_16mp_24);
            }

            prefDataStore.setString("name", text);
            prefDataStore.setString("image", text);
        });


    }
}