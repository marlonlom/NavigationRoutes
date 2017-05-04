package com.github.marlonlom.helpers.navigationroutes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * The type Navigation routes.
 *
 * @author marlonlom
 */
public final class NavigationRoutes {

  /**
   * From origin context.
   *
   * @param context the context
   * @return the with context
   */
  public static WithContext from(@NonNull Context context) {
    return new Builder(context);
  }

  /**
   * The interface With context.
   *
   * @author marlonlom
   */
  public interface WithContext {

    /**
     * Using data with context.
     *
     * @param value the value
     * @return the with context
     */
    WithContext usingData(@NonNull Bundle value);

    /**
     * Using data with context.
     *
     * @param key   the key
     * @param value the value
     * @return the with context
     */
    WithContext usingData(@NonNull String key, @NonNull Parcelable value);

    /**
     * To.
     *
     * @param aClass the a class
     */
    void to(@NonNull Class aClass);
  }

  /**
   * The type Builder.
   *
   * @author marlonlom
   */
  public static class Builder implements WithContext {
    /**
     * The context.
     */
    private Context mContext;
    /**
     * The bundle.
     */
    private Bundle mBundle;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(Context context) {
      mContext = context;
    }

    @Override
    public WithContext usingData(@NonNull Bundle value) {
      checkBundle();
      mBundle.putAll(value);
      return this;
    }

    @Override
    public WithContext usingData(@NonNull String key, @NonNull Parcelable value) {
      checkBundle();
      mBundle.putParcelable(key, value);
      return this;
    }

    private void checkBundle() {
      if (mBundle == null) {
        mBundle = new Bundle();
      }
    }

    @Override
    public void to(@NonNull Class aClass) {
      final Intent intent = new Intent(mContext, aClass);
      if (mBundle != null) {
        intent.putExtras(mBundle);
      }
      mContext.startActivity(intent);
    }
  }
}
