package doc4jTools;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.*;

import java.math.BigInteger;

/**
 * Created by vkukanauskas on 08/03/2016.
 */
public class TableController {
    /**
     * Adds simple black borders to the table
     *
     * @param table
     */
    public static void addBorders(Tbl table) {
        table.setTblPr(new TblPr());
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);

        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);
        table.getTblPr().setTblBorders(borders);
    }

    /**
     * Adds a table cell to the given row with the given paragraph as content.
     *
     * @param tr
     * @param paragraph
     */
    public static void addTableCell(Tr tr, P paragraph) {
        ObjectFactory factory =  Context.getWmlObjectFactory();
        Tc tc1 = factory.createTc();
        tc1.getContent().add(paragraph);
        tr.getContent().add(tc1);
    }
}
