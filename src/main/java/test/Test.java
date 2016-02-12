package test;

import contentPage.ContentPage;
import cover.CoverController;
import org.apache.poi.xwpf.usermodel.*;
import pl.jsolve.templ4docx.core.Docx;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;

/**
 * Created by vkukanauskas on 09/02/2016.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Docx cover = new CoverController().getEmptyCoverTemplate();
        Docx cover2 = new CoverController().getEmptyCoverTemplate();
        Docx content = new ContentPage().getEmptyContentTemplate();

        CoverController.setDummyData(cover);

        XWPFParagraph paragraph = cover.getXWPFDocument().createParagraph();
        //paragraph.setPageBreak(true);

        XWPFDocument xw = content.getXWPFDocument();
        Collection<XWPFParagraph> paragraphs = xw.getParagraphs();
        XWPFDocument destDoc = cover.getXWPFDocument();
        for (IBodyElement bodyElement : xw.getBodyElements()) {
            BodyElementType elementType = bodyElement.getElementType();
            if (elementType.name().equals("PARAGRAPH")) {
                XWPFParagraph pr = (XWPFParagraph) bodyElement;
                destDoc.createParagraph();
                int pos = destDoc.getParagraphs().size() - 1;
                destDoc.setParagraph(pr, pos);
            } else if( elementType.name().equals("TABLE") ) {
                XWPFTable table = (XWPFTable) bodyElement;
                destDoc.createTable();
                int pos = destDoc.getTables().size() - 1;
                destDoc.setTable(pos, table);
            }
        }

        OutputStream out = new FileOutputStream("Destination.docx");
        FileOutputStream dest = new FileOutputStream("C:\\development\\intelliJProjects\\PagingProto2\\templates\\temp\\test3.docx");
        destDoc.write(dest);
        //DocxController.saveToTemp(cover, "test1");


    }

}
