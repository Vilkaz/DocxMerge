package contentPage;

import generalSettings.GeneralSettings;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

/**
 * Created by vkukanauskas on 09/02/2016.
 */
public class ContentPageController {
    public Docx getEmptyContentTemplate(){
        return new Docx(GeneralSettings.TEMPLATE_PATH+"/template_BASO_contentPage.docx");
    }

    public static void setDummyForContent(Docx docx){
        docx.setVariablePattern(new VariablePattern("${", "}"));
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("${set_title}", "Titel "));
        variables.addTextVariable(new TextVariable("${steckbrief_id}", "Steckbrief ID ! !"));
        docx.fillTemplate(variables);
    }

}
