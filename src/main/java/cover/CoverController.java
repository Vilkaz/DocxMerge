package cover;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

/**
 * Created by vkukanauskas on 09/02/2016.
 */
public class CoverController {
    public Docx getEmptyCoverTemplate(){
        return new Docx("C:\\development\\intelliJProjects\\PagingProto2\\templates\\template_BASO_cover.docx");
    }

    public static void setDummyDataForCover(Docx docx){
        docx.setVariablePattern(new VariablePattern("${", "}"));
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("${headline}", "Geile Headline"));
        variables.addTextVariable(new TextVariable("${set_name}", "bester set ever"));
        variables.addTextVariable(new TextVariable("${project_type}", "projekt typ ist sehr wichtig"));
        docx.fillTemplate(variables);
    }



}

