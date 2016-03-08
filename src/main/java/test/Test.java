package test;

import com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl;
import cover.CoverController;
import cover.CoverDTO;
import doc4jTools.MainDocumentPartController;
import doc4jTools.PController;
import generalSettings.GeneralSettings;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import sieve.SieveController;

import javax.xml.bind.JAXBIntrospector;
import java.math.BigInteger;

/**
 * Created by vkukanauskas on 09/02/2016.
 */
public class Test {
    private static ObjectFactory factory;

    public static void main(String[] args) throws Exception {
        factory = Context.getWmlObjectFactory();

        long start = System.currentTimeMillis();
        CoverDTO coverDTO = new CoverDTO("Beste Headline", "projekt name", "dynamischer Projekt type");

        WordprocessingMLPackage wordMLPackage = CoverController.getCover(coverDTO);
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        addPageBreak(documentPart);

        //region  table aus dem template


        String inputfilepath2 = GeneralSettings.TEMPLATE_PATH + "/template_BASO_contentPage2.docx";
        WordprocessingMLPackage wordMLPackage2 = null;
        try {
            wordMLPackage2 = WordprocessingMLPackage
                    .load(new java.io.File(inputfilepath2));
        } catch (Docx4JException e) {
            e.printStackTrace();
        }

        MainDocumentPart documentPart2 = wordMLPackage2.getMainDocumentPart();


        /**
         * Manuel die Tabele auslesen
         */
        Tbl t2 = (Tbl) JAXBIntrospector.getValue(documentPart2.getContent().get(0));

        TblPr tblPr = factory.createTblPr();
        TblWidth tblWidth = factory.createTblWidth();
        tblWidth.setType(TblWidth.TYPE_DXA);
        tblWidth.setW(BigInteger.valueOf(5500));
        tblPr.setTblW(tblWidth);
        t2.setTblPr(tblPr);

        Tr tr = (Tr) t2.getContent().get(7);
        Tc tc = (Tc)  JAXBIntrospector.getValue(tr.getContent().get(1));

        P imageP = PController.getPWithImage(GeneralSettings.TEMPLATE_PATH+"/sieb1.jpg", wordMLPackage);
        //tc.getContent().add(imageP);

        documentPart.addObject(t2);
        documentPart.addObject(t2);
        documentPart.addObject(t2);
        documentPart.addObject(t2);
        //endregion table template


        long total = System.currentTimeMillis() - start;
        System.out.println("Time: " + total);

        SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
        String outputfilepath = GeneralSettings.TEMPLATE_PATH + "/OUT_VariableReplace.docx";
        saver.save(outputfilepath);
    }

    private static void addPageBreak(MainDocumentPart documentPart) {
//        P paragraph = factory.createP();
//        R run = factory.createR();
        P p = factory.createP();
        // Create object for r
        R r = factory.createR();
        p.getContent().add(r);
        // Create object for br
        Br br = factory.createBr();
        r.getContent().add(br);
        br.setType(STBrType.PAGE);
        documentPart.addObject(p);
    }

}
