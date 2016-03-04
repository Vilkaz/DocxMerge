package test;

import contentPage.ContentPageController;
import cover.CoverController;
import docxMerge.DocxMerge;
import generalSettings.GeneralSettings;
import myTemplate.MyTemplateController;
import pl.jsolve.templ4docx.core.Docx;

import java.util.ArrayList;

/**
 * Created by vkukanauskas on 09/02/2016.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Docx cover = new CoverController().getEmptyCoverTemplate();
        Docx cover2 = new CoverController().getEmptyCoverTemplate();
        Docx content = new ContentPageController().getEmptyContentTemplate();
        Docx content2 = new ContentPageController().getEmptyContentTemplate();

        CoverController.setDummyDataForCover(cover);
        ContentPageController.setDummyForContent(content);

        DocxMerge docxMerge = new DocxMerge();

        Docx result;


//        ArrayList<Docx> documents = new ArrayList<Docx>();
//        documents.add(cover);
//        documents.add(cover2);
//        documents.add(content);
//        documents.add(content2);
//        result =  docxMerge.mergeDocx(documents, true);
//        result.save("C:\\development\\intelliJProjects\\PagingProto2\\templates\\temp\\test4.docx");



        Docx temp1 =  new Docx(GeneralSettings.TEMPLATE_PATH+"/temp1.docx");
        Docx temp2 =  new Docx(GeneralSettings.TEMPLATE_PATH+"/temp2.docx");
        Docx temp3 =  new Docx(GeneralSettings.TEMPLATE_PATH+"/temp1.docx");

        MyTemplateController.setDummyForMyTemplate(temp1);
        MyTemplateController.setDummyForMyTemplate(temp3);


        ArrayList<Docx> documents2 = new ArrayList<Docx>();
        documents2.add(cover);
        documents2.add(temp1);
        //documents2.add(temp2);
        documents2.add(temp3);
        //documents2.add(content);

        result =  docxMerge.mergeDocx(documents2,true);

        result.save(GeneralSettings.TEMPLATE_PATH+"\\temp\\myTemplatesMerged.docx");

    }

}
