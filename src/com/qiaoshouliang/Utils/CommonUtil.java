package com.qiaoshouliang.Utils;

import com.intellij.openapi.project.Project;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by qiaoshouliang on 17/7/31.
 */
public class CommonUtil {

    /**
     * 从AndroidManifest.xml文件中获取当前app的包名
     * @return
     */
    public static String getPackageName(Project project) {
        String package_name = "";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(project.getBasePath() + "/app/src/main/AndroidManifest.xml");

            NodeList nodeList = doc.getElementsByTagName("manifest");
            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                Element element = (Element) node;
                package_name = element.getAttribute("package");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return package_name;
    }
}
