package cover;

import generalSettings.GeneralSettings;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

/**
 * Created by vkukanauskas on 09/02/2016.
 */
public class CoverController {
    public Docx getEmptyCoverTemplate(){
        return new Docx(GeneralSettings.TEMPLATE_PATH+"/template_BASO_cover.docx");
    }

    public static void setDummyDataForCover(Docx docx){
        docx.setVariablePattern(new VariablePattern("${", "}"));
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("${headline}", "Geile Headline"));
        variables.addTextVariable(new TextVariable("${set_name}", "bester set ever"));
        variables.addTextVariable(new TextVariable("${project_type}", "projekt typ ist sehr wichtig"));
        docx.fillTemplate(variables);
    }

  public static WordprocessingMLPackage getCover(CoverDTO coverDTO){
      org.docx4j.wml.ObjectFactory foo = Context.getWmlObjectFactory();
      String inputfilepath = GeneralSettings.TEMPLATE_PATH + "/template_BASO_cover.docx";
      WordprocessingMLPackage wordMLPackage = null;
      try {
          wordMLPackage = WordprocessingMLPackage
                  .load(new java.io.File(inputfilepath));
      } catch (Docx4JException e) {
          e.printStackTrace();
      }

      MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

      HashMap<String, String> mappings = new HashMap<String, String>();
      mappings.put("headline",coverDTO.getHeadline());
      mappings.put("name", coverDTO.getName());
      mappings.put("project_type", coverDTO.getProject_type());

      try {
          documentPart.variableReplace(mappings);
      } catch (JAXBException e) {
          e.printStackTrace();
      } catch (Docx4JException e) {
          e.printStackTrace();
      }

      return wordMLPackage;
  }
}

