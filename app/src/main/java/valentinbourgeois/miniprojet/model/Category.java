package valentinbourgeois.miniprojet.model;

import java.util.ArrayList;
import java.util.List;


public class Category {
    private String _name;
    private List<Image> _images;

    public Category(String name)
    {
        _name = name;
        _images = new ArrayList<Image>();
    }

    public String getName()
    {
        return _name;
    }

    public Image getImage(int i)
    {
        return _images.get(i);
    }

    public List<String> getImagesUrl()
    {
        List<String> urls = new ArrayList<String>();

        for(Image img : _images)
            urls.add(img.getUri());

        return urls;
    }

    public List<Image> getImages()
    {
        return _images;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public boolean addImage(Image img)
    {
        return _images.add(img);
    }
}
