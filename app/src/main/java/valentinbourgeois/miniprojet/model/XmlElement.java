package valentinbourgeois.miniprojet.model;


public enum XmlElement {
    IMAGE,
    ID,
    NAME,
    DESCRIPTION,
    LINK,
    CATEGORY,
    IMAGES;

    public String toString()
    {
        switch (this){
            case IMAGE:
                return "image";
            case ID:
                return "id";
            case NAME:
                return "name";
            case DESCRIPTION:
                return "description";
            case LINK:
                return "link";
            case CATEGORY:
                return "category";
            case IMAGES:
                return "images";
            default:
                return "";
        }
    }

    public static XmlElement getElement(String str)
    {
        for (XmlElement e : XmlElement.values())
            if(str.equals(e.toString()))
                return e;

        return null;
    }
}
