package com.github.marlonlom.demos.navigationroutes;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * The type Hello activity.
 *
 * @author marlonlom
 */
public class HelloActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hello);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    final String stringExtra = getIntent().getStringExtra(getString(R.string.text_bundle_hello));
    Greeting parcelableData = getIntent().getParcelableExtra(
        getString(R.string.text_bundle_hello));
    TextView mHelloText = (TextView) findViewById(R.id.textView_hello_world);
    if (TextUtils.isEmpty(stringExtra)) {
      mHelloText.setText(String.format(getString(R.string.text_hello_world), stringExtra));
    } else if (parcelableData != null) {
      mHelloText.setText(
          String.format(getString(R.string.text_hello_world), parcelableData.getMessageText()));
    }
  }

}
