package doc4jTools;

import generalSettings.GeneralSettings;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.*;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by vkukanauskas on 08/03/2016.
 */
public class PController {

    private static WordprocessingMLPackage wordMLPackage;
    private static ObjectFactory factory;

   public static P getPWithImage(String imageURL,WordprocessingMLPackage wordMLPackage){
       PController.wordMLPackage = wordMLPackage;
       P paragraphWithImage = null;
       File file = GeneralSettings.getFile(imageURL);
       try {
           paragraphWithImage = addInlineImageToParagraph(createInlineImage(file));
       } catch (Exception e) {
           e.printStackTrace();
       }
       return  paragraphWithImage;
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

    /**
     *  Adds the in-line image to a new paragraph and then returns the paragraph.
     *  Thism method has not changed from the previous example.
     *
     * @param inline
     * @return
     */
    public static P addInlineImageToParagraph(Inline inline) {
        // Now add the in-line image to a paragraph
        ObjectFactory factory = new ObjectFactory();
        P paragraph = factory.createP();
        R run = factory.createR();
        paragraph.getContent().add(run);
        Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return paragraph;
    }

    /**
     * Creates an in-line image of the given file.
     * As in the previous example, we convert the file to a byte array, and then
     * create an inline image object of it.
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static Inline createInlineImage(File file) throws Exception {
        byte[] bytes = convertImageToByteArray(file);

        BinaryPartAbstractImage imagePart =
                BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        int docPrId = 1;
        int cNvPrId = 2;

        return imagePart.createImageInline("Filename hint",
                "Alternative text", docPrId, cNvPrId, false);
    }

    /**
     * Convert the image from the file into an array of bytes.
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static byte[] convertImageToByteArray(File file)
            throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(file );
        long length = file.length();
        // You cannot create an array using a long, it needs to be an int.
        if (length > Integer.MAX_VALUE) {
            System.out.println("File too large!!");
        }
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read
        if (offset < bytes.length) {
            System.out.println("Could not completely read file "+file.getName());
        }
        is.close();
        return bytes;
    }



}
