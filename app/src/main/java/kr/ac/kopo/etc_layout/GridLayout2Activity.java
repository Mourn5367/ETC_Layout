package kr.ac.kopo.etc_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kr.ac.kopo.etc_layout.databinding.Gridlayout2Binding;

public class GridLayout2Activity extends AppCompatActivity {

    private Gridlayout2Binding binding;
    Button[] btnNums = new Button[10];
    int[] btnNumIds = { };
    String num1 = "", num2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Gridlayout2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnNums = new Button[]{binding.btnNumber0,binding.btnNumber1,binding.btnNumber2,
                            binding.btnNumber3, binding.btnNumber4,binding.btnNumber5,
                            binding.btnNumber6,binding.btnNumber7,binding.btnNumber8,
                            binding.btnNumber9};

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnPlus.setOnClickListener(btnListener);
        binding.btnMinus.setOnClickListener(btnListener);
        binding.btnDiv.setOnClickListener(btnListener);
        binding.btnMulti.setOnClickListener(btnListener);

        for (int i = 0; i < btnNums.length; i++)
        {
            // 이거 찾아보기 왜 파이널
            final int index;
            index = i;

            btnNums[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if (binding.edit1.isFocused())
                {
                    num1 = binding.edit1.getText().toString() + btnNums[index].getText().toString();
                    binding.edit1.setText(num1);
                }
                else if (binding.edit2.isFocused())
                {
                    num2 = binding.edit2.getText().toString() + btnNums[index].getText().toString();
                    binding.edit2.setText(num2);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Edit Text 선택하고 누르라고",Toast.LENGTH_SHORT).show();
                }
                }
            });
        }



    }


//    View.OnClickListener btnNumsListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//        }
//    };

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button evenBtn = (Button) v;
            int editNum1 = 0;
            int editNum2 = 0;
            int result = 0;
            int time = 1;
            try {
                editNum1 = Integer.parseInt(binding.edit1.getText().toString());
                time = 2;
                editNum2 = Integer.parseInt(binding.edit2.getText().toString());
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"Edit"+time+"에 숫자 넣으셈" + e,Toast.LENGTH_SHORT).show();
                return ;
            }

            if (evenBtn == binding.btnPlus)
            {
                result = editNum1 + editNum2;
            }
            else if (evenBtn == binding.btnMinus)
            {
                result = editNum1 - editNum2;
            }
            else if (evenBtn == binding.btnDiv)
            {
                if (editNum2 == 0)
                {
                    Toast.makeText(getApplicationContext(),"Edit2에 0 넣지마셈",Toast.LENGTH_SHORT).show();
                    return;
                }
                result = editNum1/editNum2;
            }
            else if (evenBtn == binding.btnMulti)
            {
                result = editNum1 * editNum2;
            }

            binding.textViewResult.setText(String.valueOf(result));
        }
    };
}