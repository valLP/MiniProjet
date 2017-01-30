package valentinbourgeois.miniprojet.model;


public class Image {
    private String _name;
    private String _description;
    private String _uri;

    public Image() {}

    public Image(String nom, String description, String uri)
    {
        _name = nom;
        _description = description;
        _uri = uri;
    }

    public String getName()
    {
        return _name;
    }

    public String getDescription()
    {
        return _description;
    }

    public String getUri()
    {
        return _uri;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public void setDescription(String description)
    {
        _description = description;
    }

    public void setUri(String uri)
    {
        _uri = uri;
    }

}
