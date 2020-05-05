package adp.ux;

import java.awt.*;
import java.awt.image.*;

public final class ColorizeImage {
    
    public static BufferedImage Colorize(BufferedImage img, Color c) {

        BufferedImage toReturn = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        if(img.getTransparency() != 3)
            createColorizeOp_NoAlpha(c.getRed(), c.getGreen(), c.getBlue()).filter(img, toReturn);
        else
            createColorizeOp(c.getRed(), c.getGreen(), c.getBlue()).filter(img, toReturn);

        return toReturn;

    }
    
    protected static LookupOp createColorizeOp_NoAlpha(int R, int G, int B) {

        short[]
            r = new short[256],
            g = new short[256],
            b = new short[256];

        for (short i = 0; i < 256; i++) {
            r[i] = (short)((R)*(float)i/255.0);
            g[i] = (short)((G)*(float)i/255.0);
            b[i] = (short)((B)*(float)i/255.0);
        }

        return new LookupOp(new ShortLookupTable(0, new short[][] { r, g, b }), null);

    }
    
    protected static LookupOp createColorizeOp(int R, int G, int B) {

        short[]
            a = new short[256],
            r = new short[256],
            g = new short[256],
            b = new short[256];

        for (short i = 0; i < 256; i++) {
            a[i] = i;
            r[i] = (short)((R)*(float)i/255.0);
            g[i] = (short)((G)*(float)i/255.0);
            b[i] = (short)((B)*(float)i/255.0);
        }

        return new LookupOp(new ShortLookupTable(0, new short[][] { r, g, b, a }), null);

    }
    
}
