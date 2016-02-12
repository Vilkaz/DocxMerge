package docxMerge;

import com.sun.xml.internal.ws.api.message.stream.InputStreamMessage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import pl.jsolve.templ4docx.core.Docx;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vkukanauskas on 12/02/2016.
 * <p/>
 * Merges all the Docx to one Document. Document with index 0
 * <p/>
 * gets Document[1] appended, then Document[2] appended and so on.
 * <p/>
 * Works sofar only with Tables and PaAragraphs ! is NOT COMPLETED !
 * <p/>
 * *
 */
public class DocxMerge {

    Docx mainDocument;
    XWPFDocument mainXwpf;
    boolean pageBreak = false;

    public Docx mergeDocx(Docx document1, Docx document2) {
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

        ArrayList<XWPFTableRow> rows = (ArrayList<XWPFTableRow>) table.getRows();

        int pos = this.mainXwpf.getTables().size() - 1;
        this.mainXwpf.setTable(pos, table);

        for (XWPFTableRow row : table.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph cellParagraph : cell.getParagraphs()) {
                    for (XWPFRun cellRun : cellParagraph.getRuns()) {
                        if (cellRun.getEmbeddedPictures().size() > 0) {
                            System.out.println("Bin eine Tabelle und hab ein Bild !");
                        }
                    }
                }
            }
        }


    }

    private void appendParagraphToMainDocument(IBodyElement bodyElement) {
        XWPFParagraph paragraph = (XWPFParagraph) bodyElement;
        XWPFParagraph mainParagraph = this.mainXwpf.createParagraph();
        for (XWPFRun srcRun : paragraph.getRuns()) {
            //if (srcRun.getEmbeddedPictures().size() > 0) {
                addPictureToMainDocument(srcRun, mainParagraph);
            //}
        }


        int pos = this.mainXwpf.getParagraphs().size() - 1;
        this.mainXwpf.setParagraph(paragraph, pos);
    }

    private void addPictureToMainDocument(XWPFRun run, XWPFParagraph mainParagraph) {
        XWPFPictureData pictureData = null;
        XWPFRun mainParagrahpRun = mainParagraph.createRun();

        for (XWPFPicture picture : run.getEmbeddedPictures()) {
            pictureData = picture.getPictureData();
            byte[] img = pictureData.getData();
            String fileName = pictureData.getFileName();
            int imageFormat = pictureData.getPictureType();
            int index = mainParagrahpRun.getEmbeddedPictures().size();
            InputStream pic = null;
            try {
                pic = new FileInputStream("C:\\development\\intelliJProjects\\PagingProto2\\templates\\relax.jpg");
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                mainParagrahpRun.addPicture(pic, XWPFDocument.PICTURE_TYPE_JPEG, "C:\\development\\intelliJProjects\\PagingProto2\\templates\\relax.jpg", Units.toEMU(200), Units.toEMU(200));
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //writeByteArrayToFile( img, fileName );
            //extractedImages.put( fileName, imageFormat );
        }


    }

    private void insertPageBreakIfRequested() {
        if (this.pageBreak) {
            XWPFParagraph paragraph = this.mainXwpf.createParagraph();
            paragraph.setPageBreak(true);
        }
    }


}
