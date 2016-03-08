package doc4jTools;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

/**
 * Created by vkukanauskas on 08/03/2016.
 */
public class MainDocumentPartController {
    private static ObjectFactory factory;

    private static void addPageBreak(MainDocumentPart documentPart) {
        factory = Context.getWmlObjectFactory();
        P paragraph = factory.createP();
        R run = factory.createR();
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
