/*
 * Copyright 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.china.btc;

import android.content.Context;
import android.database.CursorJoiner.Result;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private boolean mAlternateTitle = false;
    
    private TextView btcChina;
    private TextView mtgox;
    
    private EditText number;
    private TextView count;
    
    private TextView mtrmb;

    private String btc = "";
    private String mtg = "";
    
    private String sum = "";
    private String rmb = "";
    
    private Context mContext;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        btcChina = (TextView)findViewById(R.id.btchina);
        mtgox = (TextView)findViewById(R.id.mtgox);
        number = (EditText)findViewById(R.id.number);
        count = (TextView)findViewById(R.id.count);
        mtrmb = (TextView)findViewById(R.id.mtgoxrmb);
        
        findViewById(R.id.toggle_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAlternateTitle) {
                    setTitle(R.string.app_name);
                } else {
                    setTitle(R.string.alternate_title);
                }
                mAlternateTitle = !mAlternateTitle;
            }
        });
   
    }
    
    
    
    @Override
	protected void onResume() {
    	handler.sendEmptyMessageDelayed(0, 0);
		super.onResume();
	}



	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
		     
	        GetInfoTast task = new GetInfoTast();
	        task.execute();
	        
	        handler.sendEmptyMessageDelayed(0, 10000);
	        
			super.handleMessage(msg);
		}
    	
    };
    
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        // Calling super after populating the menu is necessary here to ensure that the
        // action bar helpers have a chance to handle this event.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_refresh:
                Toast.makeText(this, "refreshing...", Toast.LENGTH_SHORT).show();
                
                GetInfoTast task = new GetInfoTast();
                task.execute();
                break;

            case R.id.menu_search:
                Toast.makeText(this, "Tapped search", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_share:
                Toast.makeText(this, "Tapped share", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    
    class GetInfoTast extends AsyncTask<String, Integer, Result>
    {

		@Override
		protected Result doInBackground(String... params) {		
			
			double b = Watcher.getBtcChina();
			
			double num = Double.parseDouble(number.getText().toString());
			
			if (b > 0) {
				btc = "гд" + b  ;
				sum = "гд" + b*num;
			}
			else
				btc = "гд0";
	        double m = Watcher.getMtgox();
	        if (m > 0) {
	        	mtg =  "$" + m + "x 6.1837 " ;
	        	rmb = "гд " +  m*6.1837;
	        }
	        else
	        	mtg = "$0";
			return null;
		}

		@Override
		protected void onPostExecute(Result result) {
			getActionBarHelper().setRefreshActionItemState(false);
			
			btcChina.setText(btc);
			mtgox.setText(mtg);
			count.setText(sum);
			mtrmb.setText(rmb);
			
			Toast.makeText(mContext, btc, Toast.LENGTH_SHORT).show();
			
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			getActionBarHelper().setRefreshActionItemState(true);
			super.onPreExecute();
		}
		
		
    	
    }
}
