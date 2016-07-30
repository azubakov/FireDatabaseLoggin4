package tomer.edu.firedemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import tomer.edu.firedemo.Const;
import tomer.edu.firedemo.R;
import tomer.edu.firedemo.activities.ShareListWithUsersActivity;
import tomer.edu.firedemo.activities.ShoppingListItemsActivity;
import tomer.edu.firedemo.models.ShoppingList;
import tomer.edu.firedemo.utils.FirebaseRecyclerAdapter;

/**
 * Created by stud27 on 24/07/16.
 */
public class UserShoppingListsAdapter extends FirebaseRecyclerAdapter<ShoppingList, UserShoppingListsAdapter.UserShoppingListsViewHolder> {
    private final DatabaseReference mRef;
    boolean isEnabled = true;
    private final Context context;

    public UserShoppingListsAdapter(Context context, DatabaseReference ref) {
        super(ShoppingList.class, R.layout.user_lists_item, UserShoppingListsViewHolder.class, ref);
        this.context = context;
        mRef = ref;
    }

    @Override
    protected void populateViewHolder(final UserShoppingListsViewHolder holder, final ShoppingList model, final int position) {
        holder.tvListTitle.setText(model.getTitle());

        holder.shoppingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShoppingListItemsActivity.class);
                intent.putExtra(Const.EXTRA_PID, holder.key);
                context.startActivity(intent);
            }
        });
        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //key:
                //holder.key;
                //getRef(position).getKey();
                Intent intent = new Intent(context, ShareListWithUsersActivity.class);
                intent.putExtra(Const.EXTRA_USER_LIST, model);
                intent.putExtra(Const.EXTRA_USER_LIST_KEY, holder.key);
                context.startActivity(intent);
            }
        });
    }

    public static class  UserShoppingListsViewHolder extends RecyclerView.ViewHolder{
        TextView tvListTitle;
        RelativeLayout shoppingLayout;
        ImageView ivShare;
        String key; //Filled automatically by the adapter.

        public UserShoppingListsViewHolder(View itemView) {
            super(itemView);
            tvListTitle = (TextView) itemView.findViewById(R.id.tvListTitle);
            shoppingLayout = (RelativeLayout) itemView.findViewById(R.id.listLayout);
            ivShare = (ImageView) itemView.findViewById(R.id.shareListButton);
        }
    }
}
