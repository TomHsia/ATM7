package com.shia.atm7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private int Login_REQUEST = 123;
    final String[] func = {"Balance","History","News","Finance","Exit"};
    final int icon[] = {R.drawable.func_balance,
            R.drawable.func_history,
            R.drawable.func_news,
            R.drawable.func_finance,
            R.drawable.func_exit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent =new Intent(this,Login.class);
        startActivityForResult(intent,Login_REQUEST);
        GridView gridView = findViewById(R.id.grid);
        IconAdapter adapter =new IconAdapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch ((int) id){
            case R.drawable.func_balance:
                break;
            case R.drawable.func_history:
                startActivity(new Intent(this,TransActivity.class));
                break;
            case R.drawable.func_news:
                startActivity(new Intent(this,NewsActivity.class));
                break;
            case R.drawable.func_finance:
                startActivity(new Intent(this,FinaceActivity.class));
                break;
            case R.drawable.func_exit:
                finish();
                break;
        };
    }

    class IconAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return func.length;
        }

        @Override
        public Object getItem(int i) {
            return func[i];
        }

        @Override
        public long getItemId(int i) {
            return icon[i];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null){
                row = getLayoutInflater().inflate(R.layout.irem_row,null);
                ImageView imageView = row.findViewById(R.id.item_image);
                TextView textView = row.findViewById(R.id.item_text);
                imageView.setImageResource(icon[position]);
                textView.setText(func[position]);
            }
            return row;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Login_REQUEST){
            if (resultCode == RESULT_OK){

            }
        }else {
            finish();
        }
    }
}
