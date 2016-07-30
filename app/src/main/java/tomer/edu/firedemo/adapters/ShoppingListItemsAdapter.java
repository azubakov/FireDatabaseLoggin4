package tomer.edu.firedemo.adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import tomer.edu.firedemo.R;
import tomer.edu.firedemo.models.ShoppingList;
import tomer.edu.firedemo.models.ShoppingListItem;
import tomer.edu.firedemo.utils.FirebaseRecyclerAdapter;

/**
 * Created by stud27 on 24/07/16.
 */
enum STATUSES {pending, done, canceled}

public class ShoppingListItemsAdapter extends FirebaseRecyclerAdapter<ShoppingListItem, ShoppingListItemsAdapter.ShoppingListItemsViewHolder>
implements  GestureDetector.OnGestureListener {
    private final DatabaseReference mRef;
    boolean isEnabled = true;
    public ShoppingListItemsAdapter( DatabaseReference ref) {
        super(ShoppingListItem.class, R.layout.shopping_list_item, ShoppingListItemsViewHolder.class, ref);
        mRef=ref;

    }

    @Override
    protected void populateViewHolder(final ShoppingListItemsViewHolder viewHolder, ShoppingListItem model, int position) {
        viewHolder.tvListItem.setText(model.getTitle());
        String quantity = ""+model.getQuantity();
        // if quantity terminates with ".0" trim it
        if (quantity.charAt(quantity.length()-2)=='.' && quantity.charAt(quantity.length()-1)=='0')
        quantity = quantity.substring(0,quantity.length()-2);
        viewHolder.tvQuantity.setText(quantity);  // since it is double
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });
        viewHolder.fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getRef(position).removeValue();
                mRef.child(viewHolder.key).removeValue();
            }
        });
        viewHolder.fabCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRef.child(viewHolder.key).child("status").setValue(1);
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    public static class ShoppingListItemsViewHolder extends RecyclerView.ViewHolder{
        String key;
        TextView tvListItem;
        TextView tvQuantity;
        RelativeLayout layout;
        FloatingActionButton fabDelete;
        FloatingActionButton fabCheck;

        public ShoppingListItemsViewHolder(View itemView) {
            super(itemView);
            tvListItem = (TextView) itemView.findViewById(R.id.tvShoppingListItem);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
            layout = (RelativeLayout)itemView.findViewById(R.id.shoppingListLayout);
            fabDelete = (FloatingActionButton) itemView.findViewById(R.id.fabDelete);
            fabCheck = (FloatingActionButton) itemView.findViewById(R.id.fabCheck);

        }
    }
}
