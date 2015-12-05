package it.spinningtop.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import it.spinningtop.lib.Request;
import it.spinningtop.lib.SpinningTop;
import it.spinningtop.lib.events.EventReceiver;


public class MainActivity extends ActionBarActivity implements EventReceiver {

    private static final String TAG = MainActivity.class.getName();

    private SpinningTop spinningTop;

    private TextView helloText;
    private ProgressBar progressBar;

    private Request<Void> requestShort;
    private Request<Void> requestLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloText = (TextView) findViewById(R.id.hello);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        spinningTop = SpinningTop.getInstance();

        if (savedInstanceState != null) {
            helloText.setText(savedInstanceState.getString("text"));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        spinningTop.register(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        //check if LongHelloSpinningTopTask is running... show progress
        showProgress(spinningTop.isRequestRunning(requestLong = new Request<Void>(LongHelloSpinningTopTask.class)));

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", (String) helloText.getText());
    }

    @Override
    public void onStop() {
        super.onStop();
        spinningTop.unregister(this);

    }

    public void onStartHelloTask(View v) {
        resetText();
        showProgress(true);
        spinningTop.submitRequest(requestShort = new Request<Void>(HelloSpinningTopTask.class));
    }

    public void onStartLongTask(View v) {
        resetText();
        showProgress(true);
        spinningTop.submitRequest(requestLong = new Request<Void>(LongHelloSpinningTopTask.class));
    }

    private void resetText() {
        helloText.setText(null);
    }

    private void showProgress(boolean show) {
        if (show) {
            helloText.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            helloText.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    public void onQueryLongTask(View v) {
        boolean isRunning = spinningTop.isTaskRunning(LongHelloSpinningTopTask.class);
        if (isRunning) {
            Toast.makeText(this, "The LongHelloSpinningTopTask is still running", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "The LongHelloSpinningTopTask is not running", Toast.LENGTH_SHORT).show();
        }
    }

    public void onEvent(HelloSuccess helloSuccess) {
        showProgress(false);
        helloText.setText(helloSuccess.getResult());
    }

    public void onEvent(HelloException helloFailure) {
        showProgress(false);
        helloText.setText(helloFailure.getThrowable().getMessage());
    }

}
