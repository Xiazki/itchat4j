package mobi.xiazki.wx.util.enums;

import com.google.common.collect.Maps;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Map;

/**
 * Created by xiang on 2017/7/6.
 */
public class ResultTools {

    public static Map<String, String> processResult(String result) {
        String[] retParams = result.split(";");
        Map<String, String> map = Maps.newHashMap();
        for (String s : retParams) {
            String[] tc = s.split("=", 2);
            map.put(tc[0].trim(), tc[1].trim().replace("\"", ""));
        }
        return map;
    }

    public static Map<String, String> processLoginInfo(String uri) {
        String[] retParams = uri.split("\\?");
        return null;
    }

    public static Document xmlParser(String text) {
        Document doc = null;
        StringReader sr = new StringReader(text);
        InputSource is = new InputSource(sr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

}
