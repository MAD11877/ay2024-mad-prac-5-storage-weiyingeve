package sg.edu.np.mad.madpractical5;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> data;
    private ListActivity activity;
    public UserAdapter(ArrayList<User> data, ListActivity activity) {
        this.data = data;
        this.activity = activity;
    }
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        return new UserViewHolder(item);
    }
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = data.get(position);
        holder.name.setText(user.getName());
        holder.description.setText(user.getDescription());
        holder.smallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Profile");
                builder.setMessage(user.getName());
                builder.setPositiveButton("Close", (dialog, which) -> {
                    dialog.dismiss();
                });
                builder.setNegativeButton("View", (dialog, which) -> {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.putExtra("id", user.getId());
                    intent.putExtra("name", user.getName());
                    intent.putExtra("description", user.getDescription());
                    intent.putExtra("followed", user.getFollowed());
                    v.getContext().startActivity(intent);
                });
                builder.create().show();
            }
        });
        String userId =String.valueOf(user.getId());
        if (userId.contains("7")) {
            holder.bigImage.setVisibility(View.VISIBLE);
        }
    }
    public int getItemCount() {
        return data.size();
    }
}
