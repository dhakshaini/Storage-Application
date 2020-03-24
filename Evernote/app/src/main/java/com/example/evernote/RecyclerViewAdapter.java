package com.example.evernote;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import java.util.List;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ImageViewHolder> {
    private Context context;
    private List<ImageUploadInfo> MainImageUploadInfoList;
    public RecyclerViewAdapter(Context context, List<ImageUploadInfo> TempList) {
        this.MainImageUploadInfoList = TempList;
        this.context = context;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent, false);
        return new ImageViewHolder(view);
    }
    @Override
    public void onBindViewHolder( ImageViewHolder  holder, int position) {
        ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);
        holder.imageNameTextView.setText(UploadInfo.getImageName());
        //Loading image from Glide library.
        Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }
    public class ImageViewHolder extends RecyclerView.ViewHolder {
        // public TextView textViewName;
        public ImageView imageView;
        public TextView imageNameTextView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView);
        }
    }
}
