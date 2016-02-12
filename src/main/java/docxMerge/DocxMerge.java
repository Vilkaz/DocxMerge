package docxMerge;

import org.apache.poi.xwpf.usermodel.*;
import pl.jsolve.templ4docx.core.Docx;

import java.util.ArrayList;

/**
 * Created by vkukanauskas on 12/02/2016.
 *
 * Merges all the Docx to one Document. Document with index 0
 *
 * gets Document[1] appended, then Document[2] appended and so on.
 *
 * *
 */
public class DocxMerge {

    Docx mainDocument;
    XWPFDocument mainXwpf;

    public Docx mergeDocx(ArrayList<Docx> documents){
        this.mainDocument = documents.get(0);
        this.mainXwpf = this.mainDocument.getXWPFDocument();
        for(int i = 1; i < documents.size(); i++){
            appendDocxBodyElementsToMainDocument(documents.get(i));
        }

        return mainDocument;
    }

    private void appendDocxBodyElementsToMainDocument(Docx docx) {
        XWPFDocument xwpf = docx.getXWPFDocument();
        for (IBodyElement bodyElement : xwpf.getBodyElements()) {
            appendBodyElementToMainDocument(bodyElement);
        }
    }

    private void appendBodyElementToMainDocument(IBodyElement bodyElement) {
        BodyElementType elementType = bodyElement.getElementType();
        switch (elementType.name()){
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
}
