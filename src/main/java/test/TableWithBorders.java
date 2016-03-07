//package test;
//
//import generalSettings.GeneralSettings;
//import org.docx4j.jaxb.Context;
//import org.docx4j.openpackaging.exceptions.Docx4JException;
//import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
//import org.docx4j.wml.*;
//
//import java.math.BigInteger;
//
///**
// * Created by vkukanauskas on 07/03/2016.
// */
//public class TableWithBorders {
//    private static WordprocessingMLPackage wordMLPackage;
//    private static ObjectFactory factory;
//
//    public static void main (String[] args) throws Docx4JException {
//        wordMLPackage = WordprocessingMLPackage.createPackage();
//        factory = Context.getWmlObjectFactory();
//
//        Tbl table = createTableWithContent();
//
//        addBorders(table);
//
//        wordMLPackage.getMainDocumentPart().addObject(table);
//        wordMLPackage.save(GeneralSettings.getFile("HelloWord5.docx"));
//    }
//
//    private static void addBorders(Tbl table) {
//        table.setTblPr(new TblPr());
//        CTBorder border = new CTBorder();
//        border.setColor("auto");
//        border.setSz(new BigInteger("4"));
//        border.setSpace(new BigInteger("0"));
//        border.setVal(STBorder.SINGLE);
//
//        TblBorders borders = new TblBorders();
//        borders.setBottom(border);
//        borders.setLeft(border);
//        borders.setRight(border);
//        borders.setTop(border);
//        borders.setInsideH(border);
//        borders.setInsideV(border);
//        table.getTblPr().setTblBorders(borders);
//    }
//
//    private static Tbl createTableWithContent() {
//        Tbl table = factory.createTbl();
//        Tr tableRow = factory.createTr();
//
//        addTableCell(tableRow, "Field 1");
//        addTableCell(tableRow, "Field 2");
//
//        table.getContent().add(tableRow);
//        return table;
//    }
//
//    private static void addTableCell(Tr tableRow, String content) {
//        Tc tableCell = factory.createTc();
//        tableCell.getContent().add(
//                wordMLPackage.getMainDocumentPart().
//                        createParagraphOfText(content));
//        tableRow.getContent().add(tableCell);
//    }
//}