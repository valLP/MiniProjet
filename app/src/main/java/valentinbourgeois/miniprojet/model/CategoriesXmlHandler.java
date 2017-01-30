package valentinbourgeois.miniprojet.model;

import org.xml.sax.helpers.DefaultHandler;


public class CategoriesXmlHandler extends DefaultHandler {
    private Categories _categories;
    private XmlElement _currentElement;
    private Image _currentImage;

    public CategoriesXmlHandler(Categories categories)
    {
        _categories = categories;
        _currentElement = null;
        _currentImage = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes)
            throws org.xml.sax.SAXException
    {

        _currentElement = XmlElement.getElement(qName);

        if(_currentElement == null)
            return;

        switch(_currentElement)
        {
            case IMAGE:
                _currentImage = new Image();
                break;
            case CATEGORY:
                String categoryName = attributes.getValue("label");
                _categories.addImage(categoryName,_currentImage);
                break;
            case LINK:
                String url = attributes.getValue("href");
                _currentImage.setUri(getFileNameFromURL(url));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length){
        String data = createString(ch,start,length);
        if(data.length() == 0)
            return;

        switch (_currentElement)
        {
            case NAME:
                _currentImage.setName(data);
                break;
            case DESCRIPTION:
                _currentImage.setDescription(data);
                break;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws org.xml.sax.SAXException
    {

        _currentElement = null;
    }

    private String createString(char[] ch, int start, int length)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < start+length; i++)
            sb.append(ch[i]);

        return sb.toString().trim();
    }

    private String getFileNameFromURL(String urlImage)
    {
        String [] tokens = urlImage.split("/");
        String fileName = tokens[tokens.length-1];
        return fileName;
    }
}
