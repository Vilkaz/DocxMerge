package DocxController;

import pl.jsolve.templ4docx.core.Docx;

/**
 * Created by vkukanauskas on 09/02/2016.
 */
public class DocxController {
    public static void saveToTemp(Docx docx, String fileName){
        docx.save("C:\\development\\intelliJProjects\\PagingProto2\\templates\\temp\\"+fileName+".docx");
    }


}
