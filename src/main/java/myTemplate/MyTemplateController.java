package myTemplate;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

/**
 * Created by vkukanauskas on 12/02/2016.
 */
public class MyTemplateController {

    public static void setDummyForMyTemplate(Docx docx){
        docx.setVariablePattern(new VariablePattern("${", "}"));
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("${data1}", "Dynamisch eingeseter Datensatz 1"));
        variables.addTextVariable(new TextVariable("${data2}", "Dynamisch eingesetzer Datensatz 2"));
        docx.fillTemplate(variables);
    }

}
