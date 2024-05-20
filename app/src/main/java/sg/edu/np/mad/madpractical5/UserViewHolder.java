package sg.edu.np.mad.madpractical5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    TextView description;
    ImageView smallImage;
    ImageView bigImage;
    public UserViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tvName);
        description = (TextView) itemView.findViewById(R.id.tvDescription);
        smallImage = (ImageView) itemView.findViewById(R.id.ivSmallImage);
        bigImage = (ImageView) itemView.findViewById(R.id.ivBigImage);
    }
}
