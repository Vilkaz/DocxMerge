package test;

/**
 * Created by vkukanauskas on 07/03/2016.
 */

import cover.CoverController;
import cover.CoverDTO;
import generalSettings.GeneralSettings;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;
import org.docx4j.wml.TrPr;
import sieve.SieveController;


public class VariableReplace {

    public static void main(String[] args) throws Exception {

        String outputfilepath = GeneralSettings.TEMPLATE_PATH + "/OUT_VariableReplace.docx";


        long start = System.currentTimeMillis();

        CoverDTO coverDTO = new CoverDTO("Beste Headline", "projekt name", "dynamischer Projekt type");

        WordprocessingMLPackage wordMLPackage = CoverController.getCover(coverDTO);

        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();



        int writableWidthTwips = wordMLPackage.getDocumentModel().getSections().get(0).getPageDimensions().getWritableWidthTwips();
        int cols = 3;
        int cellWidthTwips = new Double(
                Math.floor( (writableWidthTwips/cols ))
        ).intValue();

        Tbl tbl = SieveController.getSieveTable(wordMLPackage);

        documentPart.addObject(tbl);



        long end = System.currentTimeMillis();
        long total = end - start;
        System.out.println("Time: " + total);

        SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
        saver.save(outputfilepath);
    }

}
