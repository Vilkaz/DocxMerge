package sieve;

import doc4jTools.PController;
import doc4jTools.TableController;
import generalSettings.GeneralSettings;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vkukanauskas on 07/03/2016.
 */
public class SieveController {
    private static WordprocessingMLPackage wordMLPackage;
    private static ObjectFactory factory;

    public static Tbl getSieveTable(WordprocessingMLPackage externWordMLPackage){
        wordMLPackage = externWordMLPackage;
        factory = Context.getWmlObjectFactory();

        MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();




        Tbl table = factory.createTbl();

        TblPr tblPr = factory.createTblPr();
        table.setTblPr(tblPr);
        TblWidth tblW = factory.createTblWidth();
        tblW.setW( BigInteger.valueOf(8* getColumnWidth()));
        tblW.setType( "dxa" );
        tblPr.setTblW( tblW );


        TableController.addBorders(table);

        Tr tr1 = factory.createTr();

        Tc tc1 = factory.createTc();
        P p2 = wordMLPackage.getMainDocumentPart()
                .createParagraphOfText("Sieb-Steckbrief");
        P p3 = wordMLPackage.getMainDocumentPart()
                .createParagraphOfText("");
        tc1.getContent().add(p2);

        Tc tc11 = factory.createTc();

        tc11.getContent().add(p3);

        tr1.getContent().addAll(Arrays.asList(tc1, tc11));
        TblWidth tblWidth = factory.createTblWidth();

        Tr tr2 = factory.createTr();



        P paragraphOfText = wordMLPackage.getMainDocumentPart()
                .createParagraphOfText("Field 1");
        addTableCell(tr2, paragraphOfText);

        addTableCell(tr2, paragraphOfText);

        File file = GeneralSettings.getFile("tecracer.png");
        P paragraphWithImage = null;
        try {
            paragraphWithImage = PController.addInlineImageToParagraph(PController.createInlineImage(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        addTableCell(tr2, paragraphWithImage);

        table.getContent().addAll(Arrays.asList(tr1,tr2));

        return  table;

    }

    private static int getColumnWidth() {
        List<SectionWrapper> sections = wordMLPackage.getDocumentModel().getSections();
        return sections.get( sections.size() - 1 ).getPageDimensions().getWritableWidthTwips();
    }

    private static void addHeaderToTable(Tbl table){
        Tr tr = factory.createTr();

    }

    /**
     * Adds a table cell to the given row with the given paragraph as content.
     *
     * @param tr
     * @param paragraph
     */
    private static void addTableCell(Tr tr, P paragraph) {
        Tc tc1 = factory.createTc();
        tc1.getContent().add(paragraph);
        tr.getContent().add(tc1);
    }


    public static SieveDTO getSieveDTOMockup1(){
        return new SieveDTO(
                1,
                1,
                "Krankenhaus Siloah",
                "Gehirn OP",
                "Sieb Nr 14234",
                "ACI-BBB",
                GeneralSettings.TEMPLATE_PATH+"sieb1.jpg"
        );
    }

    public static SieveDTO getSieveDTOMockup2(){
        return new SieveDTO(
                2,
                2,
                "Schweizer Berge ",
                "Knochenbehandlung",
                "Sieb Nr 99163",
                "ACDC-1337",
                GeneralSettings.TEMPLATE_PATH+"sieb2.jpg"
        );
    }

}
