package valentinbourgeois.miniprojet;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import valentinbourgeois.miniprojet.model.Categories;
import valentinbourgeois.miniprojet.model.Category;
import valentinbourgeois.miniprojet.model.Image;

import static org.junit.Assert.*;

public class CategoriesTest {
    private Categories _categories;

    @Before
    public void before(){
        _categories = new Categories();
    }

    @Test
    public void unknowCategory() throws Exception {
        assertEquals(_categories.getSize(), 0);
        assertNull(_categories.getCategory("unknow cat 1"));
    }

    @Test
    public void addNewImageInNewCategory() throws Exception {
        _categories.addImage("cat 1",new Image());
        assertEquals(_categories.getSize(), 1);
        assertNotNull(_categories.getCategory("cat 1"));
    }

    @Test
    public void addImagesInSameCategory() throws Exception {
        _categories.addImage("cat 1",new Image());
        _categories.addImage("cat 1",new Image());
        assertEquals(_categories.getSize(), 1);
        assertNotNull(_categories.getCategory("cat 1"));
    }

    @Test
    public void addImagesInDifferentCategories() throws Exception {
        _categories.addImage("cat 1",new Image());
        _categories.addImage("cat 1",new Image());
        _categories.addImage("cat 2",new Image());
        _categories.addImage("cat 2",new Image());

        assertEquals(_categories.getSize(), 2);
        assertNotNull(_categories.getCategory("cat 1"));
        assertNotNull(_categories.getCategory("cat 2"));
    }

    @Test
    public void orderCategories() throws Exception {
        _categories.addImage("bcd",new Image());
        _categories.addImage("abc",new Image());

        List<Category> orderedCat = _categories.getOrderedCategories();
        assertEquals("abc",orderedCat.get(0).getName());
        assertEquals("bcd",orderedCat.get(1).getName());
    }

    @Test
    public void orderCategoriesDifferentCase() throws Exception {
        _categories.addImage("Bcd",new Image());
        _categories.addImage("abc",new Image());

        List<Category> orderedCat = _categories.getOrderedCategories();
        assertEquals("abc",orderedCat.get(0).getName());
        assertEquals("Bcd",orderedCat.get(1).getName());
    }


}