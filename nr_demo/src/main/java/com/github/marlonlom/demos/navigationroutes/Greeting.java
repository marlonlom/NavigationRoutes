package com.github.marlonlom.demos.navigationroutes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Sample message text using parcelable.
 *
 * @author marlonlom
 */
final class Greeting implements Parcelable {
  /**
   * The constant CREATOR.
   */
  public static final Creator<Greeting> CREATOR = new Creator<Greeting>() {
    @Override
    public Greeting createFromParcel(Parcel in) {
      return new Greeting(in);
    }

    @Override
    public Greeting[] newArray(int size) {
      return new Greeting[size];
    }
  };

  /**
   * The Message text.
   */
  private String messageText;

  Greeting() {
  }

  private Greeting(Parcel in) {
    messageText = in.readString();
  }

  /**
   * Gets message text.
   *
   * @return the message text
   */
  public String getMessageText() {
    return messageText;
  }

  /**
   * Sets message text.
   *
   * @param messageText the message text
   */
  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(getMessageText());
  }

  /**
   * Read from parcel.
   *
   * @param in the in
   */
  private void readFromParcel(Parcel in) {
    this.setMessageText(in.readString());
  }
}
