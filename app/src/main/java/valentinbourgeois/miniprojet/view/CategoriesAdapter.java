package valentinbourgeois.miniprojet.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import valentinbourgeois.miniprojet.R;
import valentinbourgeois.miniprojet.controller.CategoriesActivity;
import valentinbourgeois.miniprojet.controller.CategoryActivity;
import valentinbourgeois.miniprojet.controller.InitActivity;
import valentinbourgeois.miniprojet.model.Categories;
import valentinbourgeois.miniprojet.model.Category;
import valentinbourgeois.miniprojet.model.MyUtils;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>
{
    private List<Category> _categories;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView categoryName;

        ViewHolder(View v)
        {
            super(v);
            categoryName = (TextView) v.findViewById(R.id.cat_image_name_row);
            image = (ImageView) v.findViewById(R.id.cat_image_row);
        }
    }


    public CategoriesAdapter(Categories categories)
    {
        _categories = categories.getOrderedCategories();
    }


    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Category currentCat = _categories.get(position);
        holder.categoryName.setText(currentCat.getName());

        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+InitActivity.TMPDIR+currentCat.getImage(0).getUri();

        Bitmap bit = MyUtils.decodeSampledBitmapFromFile(path,500,500);
        holder.image.setImageBitmap(bit);
        holder.image.setOnClickListener(new CustomOnClickListener(CategoriesActivity.CATEGRORY,currentCat.getName(),CategoryActivity.class));
    }

    @Override
    public int getItemCount()
    {
        return _categories.size();
    }

}
