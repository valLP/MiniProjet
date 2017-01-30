package valentinbourgeois.miniprojet.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Categories {
    private Map<String,Category> _categories;
    private static Categories instance;

    private Categories()
    {
        _categories = new HashMap<String,Category>();
    }

    public static Categories getInstance()
    {
        if(instance == null)
            instance = new Categories();
        return instance;
    }

    public void addImage(String categoryName, Image image)
    {
        if(_categories.containsKey(categoryName))
        {
            Category category = _categories.get(categoryName);
            category.addImage(image);
        }
        else
        {
            Category category = new Category(categoryName);
            category.addImage(image);
            _categories.put(categoryName,category);
        }
    }


    public List<Category> getOrderedCategories()
    {
        List<Category> orderedCat = new ArrayList<Category>(_categories.values());

        Collections.sort(orderedCat, new Comparator<Category>() {

            public int compare(Category c1, Category c2) {
                String c1Name = c1.getName().toLowerCase();
                String c2Name = c2.getName().toLowerCase();
                return c1Name.compareTo(c2Name);
            }
        });
        return orderedCat;
    }

    public Category getCategory(String category)
    {
        return _categories.get(category);
    }

    public List<String> getImagesUrl()
    {
        List<String> urls = new ArrayList<String>();

        for(Category cat : _categories.values())
            urls.addAll(cat.getImagesUrl());

        return urls;
    }

    public int getSize()
    {
        return _categories.size();
    }
}
