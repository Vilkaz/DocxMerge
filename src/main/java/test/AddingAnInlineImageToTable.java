package test;

/**
 * Created by vkukanauskas on 07/03/2016.
 */

import doc4jTools.PController;
import doc4jTools.TableController;
import generalSettings.GeneralSettings;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

import java.io.File;

public class AddingAnInlineImageToTable {
    private static WordprocessingMLPackage  wordMLPackage;
    private static ObjectFactory factory;

    /**
     *  First we create the package and the object factory, so we can use them
     *  everywhere in the class. Then we create a table and add borders to it.
     *  Next we create a table row and add a first field with some text.
     *  For the second field, we use the same image file as before and create a
     *  paragraph with an image, that we add to it. Finally we add the row to the
     *  table, and the table to the package, and save the package.
     */
    public static void main (String[] args) throws Exception {
        wordMLPackage = WordprocessingMLPackage.createPackage();
        factory = Context.getWmlObjectFactory();

        Tbl table = factory.createTbl();
        TableController.addBorders(table);

        Tr tr = factory.createTr();

        P paragraphOfText = wordMLPackage.getMainDocumentPart()
                .createParagraphOfText("Field 1");
        TableController.addTableCell(tr, paragraphOfText);

        File file = GeneralSettings.getFile("tecracer.png");
        P paragraphWithImage = PController.getPWithImage(GeneralSettings.TEMPLATE_PATH+"/sieb1.jpg", wordMLPackage );
        TableController.addTableCell(tr, paragraphWithImage);

        table.getContent().add(tr);

        wordMLPackage.getMainDocumentPart().addObject(table);
        wordMLPackage.save(GeneralSettings.getFile("HelloWord8.docx"));
    }



}