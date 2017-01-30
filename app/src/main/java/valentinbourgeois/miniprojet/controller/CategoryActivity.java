package valentinbourgeois.miniprojet.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import valentinbourgeois.miniprojet.R;
import valentinbourgeois.miniprojet.model.Categories;
import valentinbourgeois.miniprojet.model.Category;
import valentinbourgeois.miniprojet.view.CategoryAdapter;

public class CategoryActivity extends AppCompatActivity {
    public static final String IMAGE = "valentinbourgeois.miniprojet.CategoriesActivity.IMAGE";
    private Category _category;
    private RecyclerView _imgRecyclerView;
    private LinearLayoutManager _imgLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra(CategoriesActivity.CATEGRORY);
        _category = Categories.getInstance().getCategory(categoryName);

        showImages();
    }

    private void showImages()
    {
        _imgRecyclerView = (RecyclerView) findViewById(R.id.imgRecyclerView);
        _imgRecyclerView.setHasFixedSize(true);

        _imgLayout = new LinearLayoutManager(this);
        _imgRecyclerView.setLayoutManager(_imgLayout);


        CategoryAdapter adapter = new CategoryAdapter(_category);
        _imgRecyclerView.setAdapter(adapter);
    }
}
