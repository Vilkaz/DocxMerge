package generalSettings;

import java.io.File;

/**
 * Created by Vilkazzz on 04/03/2016.
 */
public class GeneralSettings {
    //final public static String ROOT = "C:\\code\\DocxMerge";
    final public static String ROOT = "C:\\development\\intelliJProjects\\PagingProto2";
    final public static String TEMPLATE_PATH = ROOT + "\\templates";

    public static File getFile(String fileName){
        File file = new File(TEMPLATE_PATH+"/"+fileName);
        return file;
    }

}
