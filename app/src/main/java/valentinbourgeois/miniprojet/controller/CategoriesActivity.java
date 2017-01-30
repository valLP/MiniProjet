package valentinbourgeois.miniprojet.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import valentinbourgeois.miniprojet.R;
import valentinbourgeois.miniprojet.model.Categories;
import valentinbourgeois.miniprojet.view.CategoriesAdapter;

public class CategoriesActivity extends AppCompatActivity {
    public static final String CATEGRORY = "valentinbourgeois.miniprojet.CategoriesActivity.CATEGRORY";
    private Categories _categories;
    private RecyclerView _catRecyclerView;
    private LinearLayoutManager _catLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        _categories = Categories.getInstance();
        showCategories();
    }

    private void showCategories()
    {
        _catRecyclerView = (RecyclerView) findViewById(R.id.catRecyclerView);
        _catRecyclerView.setHasFixedSize(true);

        _catLayout = new LinearLayoutManager(this);
        _catRecyclerView.setLayoutManager(_catLayout);

        CategoriesAdapter adapter = new CategoriesAdapter(_categories);
        _catRecyclerView.setAdapter(adapter);
    }
}
