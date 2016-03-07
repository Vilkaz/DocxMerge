package test;


import generalSettings.GeneralSettings;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;

/**
 * Created by vkukanauskas on 07/03/2016.
 */
public class AddingATable {
    private static WordprocessingMLPackage wordMLPackage;
    private static ObjectFactory factory;

    public static void main (String[] args) throws Docx4JException {
        wordMLPackage = WordprocessingMLPackage.createPackage();
        factory = Context.getWmlObjectFactory();

        Tbl table = factory.createTbl();
        Tr tableRow = factory.createTr();

        addTableCell(tableRow, "Field 1");
        addTableCell(tableRow, "Field 2");

        table.getContent().add(tableRow);

        wordMLPackage.getMainDocumentPart().addObject(table);

        wordMLPackage.save(GeneralSettings.getFile("HelloWord4.docx"));
    }

    private static void addTableCell(Tr tableRow, String content) {
        Tc tableCell = factory.createTc();
        tableCell.getContent().add(
                wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
        tableRow.getContent().add(tableCell);
    }
}
