package com.beauty.settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.beauty.common.BeautyGlobal;
import com.beauty.dialer.R;
import com.beauty.common.BeautyDefDialerHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BeautySettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private BeautyDefDialerHelper mDefDialerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        mDefDialerHelper = BeautyGlobal.getInstance().getDefDialerHelper();
        findViewById(R.id.set_self_as_default).setOnClickListener(this);
        findViewById(R.id.reset_to_system_dialer).setOnClickListener(this);

        String curDefDialer = mDefDialerHelper.getDefDialerPkg();
        TextView curDefDialerText = (TextView) findViewById(R.id.current_default_dialer);
        curDefDialerText.setText(TextUtils.isEmpty(curDefDialer)?"system dialer" : curDefDialer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_self_as_default:
                BeautyDefDialerHelper.requestChangeDefDialer(this, this.getPackageName());
                break;
            case R.id.reset_to_system_dialer:
                BeautyDefDialerHelper.requestChangeDefDialer(this, "com.android.dialer");
                break;
            default:
                break;
        }
    }
}
