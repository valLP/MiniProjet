package valentinbourgeois.miniprojet.view;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import valentinbourgeois.miniprojet.R;
import valentinbourgeois.miniprojet.controller.CategoryActivity;
import valentinbourgeois.miniprojet.controller.ImageActivity;
import valentinbourgeois.miniprojet.controller.InitActivity;
import valentinbourgeois.miniprojet.model.Category;
import valentinbourgeois.miniprojet.model.Image;
import valentinbourgeois.miniprojet.model.MyUtils;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private List<Image> _images;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView imageName;

        ViewHolder(View v)
        {
            super(v);
            imageName = (TextView) v.findViewById(R.id.image_name_row);
            image = (ImageView) v.findViewById(R.id.image_row);
        }
    }


    public CategoryAdapter(Category category)
    {
        _images = category.getImages();
    }
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row, parent, false);
        CategoryAdapter.ViewHolder vh = new CategoryAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        Image image = _images.get(position);
        holder.imageName.setText(image.getName());

        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+ InitActivity.TMPDIR+image.getUri();

        Bitmap bit = MyUtils.decodeSampledBitmapFromFile(path,500,500);
        holder.image.setImageBitmap(bit);
        holder.image.setOnClickListener(new CustomOnClickListener(CategoryActivity.IMAGE,image.getUri(),ImageActivity.class));
    }

    @Override
    public int getItemCount() {
        return _images.size();
    }
}
