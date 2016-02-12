package docxMerge;

import org.apache.poi.xwpf.usermodel.*;
import pl.jsolve.templ4docx.core.Docx;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vkukanauskas on 12/02/2016.
 * <p/>
 * Merges all the Docx to one Document. Document with index 0
 * <p/>
 * gets Document[1] appended, then Document[2] appended and so on.
 *
 * Works sofar only with Tables and PaAragraphs ! is NOT COMPLETED !
 * <p/>
 * *
 */
public class DocxMerge {

    Docx mainDocument;
    XWPFDocument mainXwpf;
    boolean pageBreak = false;

    public Docx mergeDocx(Docx document1, Docx document2){
        ArrayList<Docx> documentList = new ArrayList<Docx>(Arrays.asList(document1, document2));
        return mergeDocx(documentList, false);
    }

    public Docx mergeDocx(Docx document1, Docx document2, boolean pageBreak) {
        ArrayList<Docx> documentList = new ArrayList<Docx>(Arrays.asList(document1, document2));
        return mergeDocx(documentList, pageBreak);
    }

    /**
     * Main function, all other functions only redirect to this one.
     *
     * @param documents
     * @param pageBreak
     * @return
     */
    public Docx mergeDocx(ArrayList<Docx> documents, boolean pageBreak) {
        this.mainDocument = documents.get(0);
        this.mainXwpf = this.mainDocument.getXWPFDocument();
        this.pageBreak = pageBreak;

        for (int i = 1; i < documents.size(); i++) {
            insertPageBreakIfRequested();

            appendDocxBodyElementsToMainDocument(documents.get(i));
        }

        return this.mainDocument;
    }

    private void appendDocxBodyElementsToMainDocument(Docx docx) {
        XWPFDocument xwpf = docx.getXWPFDocument();
        for (IBodyElement bodyElement : xwpf.getBodyElements()) {
            appendBodyElementToMainDocument(bodyElement);
        }
    }

    private void appendBodyElementToMainDocument(IBodyElement bodyElement) {
        BodyElementType elementType = bodyElement.getElementType();
        switch (elementType.name()) {
            case ("PARAGRAPH"):
                appendParagraphToMainDocument(bodyElement);
                break;
            case ("TABLE"):
                appendTableToMainDocument(bodyElement);
                break;
        }
    }

    private void appendTableToMainDocument(IBodyElement bodyElement) {
        XWPFTable table = (XWPFTable) bodyElement;
        this.mainXwpf.createTable();
        int pos = this.mainXwpf.getTables().size() - 1;
        this.mainXwpf.setTable(pos, table);
    }

    private void appendParagraphToMainDocument(IBodyElement bodyElement) {
        XWPFParagraph paragraph = (XWPFParagraph) bodyElement;
        this.mainXwpf.createParagraph();
        int pos = this.mainXwpf.getParagraphs().size() - 1;
        this.mainXwpf.setParagraph(paragraph, pos);
    }

    private void insertPageBreakIfRequested() {
        if (this.pageBreak) {
            XWPFParagraph paragraph = this.mainXwpf.createParagraph();
            paragraph.setPageBreak(true);
        }
    }


}
