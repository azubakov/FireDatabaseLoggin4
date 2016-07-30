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

import tomer.edu.firedemo.R;
import tomer.edu.firedemo.adapters.UserShoppingListsAdapter;
import tomer.edu.firedemo.models.ShoppingList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserListFragment extends DialogFragment {


    public AddUserListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_user_list, container, false);

        final EditText etItemList = (EditText) v.findViewById(R.id.etShoppingListName);
        Button btnSave = (Button) v.findViewById(R.id.btnSaveList);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = currentUser.getUid();
                String email = currentUser.getEmail();


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("userLists").child(uid);

                String listName = etItemList.getText().toString();
                ShoppingList shoppingListModel = new ShoppingList(listName, email, uid);

                ref.push().setValue(shoppingListModel);
                dismiss();
            }
        });

        return v;
    }

}
