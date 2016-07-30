package tomer.edu.firedemo.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by stud27 on 24/07/16.
 */
public class ShoppingListItem implements Parcelable {
    private String ownerEmail;
    private String ownerId;
    private String title;
    private double quantity;
    private int status;
    enum STATUSES {pending, done, canceled}

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ShoppingListItem() {
    }

    public ShoppingListItem(String ownerEmail, String ownerId, String title, double quantity, int status) {
        this.ownerEmail = ownerEmail;
        this.ownerId = ownerId;
        this.title = title;
        this.quantity = quantity;
        this.status = status;
    }

    public ShoppingListItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.quantity);
        dest.writeString(this.title);
        dest.writeInt(this.status);
    }

    protected ShoppingListItem(Parcel in) {
        this.quantity = in.readDouble();
        this.title = in.readString();
        this.status = in.readInt();
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public static final Parcelable.Creator<ShoppingListItem> CREATOR = new Parcelable.Creator<ShoppingListItem>() {
        @Override
        public ShoppingListItem createFromParcel(Parcel source) {
            return new ShoppingListItem(source);
        }

        @Override
        public ShoppingListItem[] newArray(int size) {
            return new ShoppingListItem[size];
        }
    };
}
