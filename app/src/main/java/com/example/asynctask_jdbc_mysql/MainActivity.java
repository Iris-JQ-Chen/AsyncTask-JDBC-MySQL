package com.example.asynctask_jdbc_mysql;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asynctask_jdbc_mysql.util.DBUtil;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button_1);
        textView = (TextView)findViewById(R.id.text_1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.MySQLConnectAsyncTask task = new MainActivity.MySQLConnectAsyncTask();
                task.execute();
            }
        });
    }

    class MySQLConnectAsyncTask extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... params) {
            return DBUtil.find();
        }

        @Override
        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
            textView.setText(s);
        }
    }
}
