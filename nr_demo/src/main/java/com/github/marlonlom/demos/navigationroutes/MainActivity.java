package com.github.marlonlom.demos.navigationroutes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.marlonlom.helpers.navigationroutes.NavigationRoutes;

/**
 * The type Main activity.
 *
 * @author marlonlom
 */
public class MainActivity extends AppCompatActivity {

  private Button mNavigateTextButton;
  private EditText mEditText;
  private Button mNavigateButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mNavigateButton = (Button) findViewById(R.id.button_navigate_next_window);
    mEditText = (EditText) findViewById(R.id.editText_hello);
    mNavigateTextButton = (Button) findViewById(R.id.button_navigate_with_text);

    setupNavigationButtonNormal();
    setupNavigationButtonExtraText();
    setupNavigationButtonParcelText();
  }

  private void setupNavigationButtonParcelText() {
    final Button mNavigateParcelButton = (Button) findViewById(R.id.button_navigate_with_parcel);
    mNavigateParcelButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (TextUtils.isEmpty(mEditText.getText())) {
          Toast.makeText(MainActivity.this, R.string.text_toast_hello_empty,
              Toast.LENGTH_LONG).show();
        } else {
          Greeting parcelableData = new Greeting();
          parcelableData.setMessageText(mEditText.getText().toString());

          NavigationRoutes.from(MainActivity.this)
              .usingData(getString(R.string.text_bundle_hello), parcelableData)
              .to(HelloActivity.class);
        }
      }
    });
  }

  private void setupNavigationButtonExtraText() {
    mNavigateTextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (TextUtils.isEmpty(mEditText.getText())) {
          Toast.makeText(MainActivity.this, R.string.text_toast_hello_empty,
              Toast.LENGTH_LONG).show();
        } else {
          Bundle bundle = new Bundle();
          bundle.putString(getString(R.string.text_bundle_hello), mEditText.getText().toString());
          NavigationRoutes.from(MainActivity.this).usingData(bundle).to(HelloActivity.class);
        }
      }
    });
  }

  /**
   * Prepare navigate button.
   */
  private void setupNavigationButtonNormal() {
    mNavigateButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NavigationRoutes.from(MainActivity.this).to(NextActivity.class);
      }
    });
  }
}
