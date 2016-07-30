package tomer.edu.firedemo.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tomer.edu.firedemo.Const;
import tomer.edu.firedemo.R;
import tomer.edu.firedemo.models.ShoppingList;
import tomer.edu.firedemo.models.ShoppingListItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddShoppingListItemFragment extends DialogFragment {


    public AddShoppingListItemFragment() {
        // Required empty public constructor
    }


    public static AddShoppingListItemFragment newInstance(String pid) {

        Bundle args = new Bundle();
        args.putString(Const.EXTRA_PID, pid);
        args.putString(Const.QUANTITY_PID, pid);
        AddShoppingListItemFragment fragment = new AddShoppingListItemFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_item, container, false);

        final EditText etItemList = (EditText) v.findViewById(R.id.etShoppingListName);
        final EditText etQuantity = (EditText) v.findViewById(R.id.etQuantity);
        Button btnSave = (Button) v.findViewById(R.id.btnSaveList);


        final String pid = getArguments().getString(Const.EXTRA_PID);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = currentUser.getUid();
                String email = currentUser.getEmail();


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ShoppingListItems").child(pid);


                String listName = etItemList.getText().toString();
                double quantity=1.0;
                try{
                    quantity = Double.parseDouble(etQuantity.getText().toString());
                }catch (Exception e){}

                ShoppingListItem shoppingListItemModel = new ShoppingListItem(email, uid, listName, quantity, 0);
                ref.push().setValue(shoppingListItemModel);
                dismiss();
            }
        });

        return v;
    }

}
