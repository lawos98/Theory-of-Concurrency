package com.example.demo;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class ColorSeter implements Callable<Integer> {

    private final int x1;
    private final int x2;
    private final int y1;
    private final int y2;
    private final BufferedImage bufferedImage;

    public ColorSeter(int x1, int x2, int y1, int y2,BufferedImage bufferedImage) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.bufferedImage = bufferedImage;
    }

    @Override
    public Integer call() {
        double zx, zy, cX, cY, tmp;

        int halfHeight = Config.getHeight() / 2;
        int halfWidth = Config.getWidth() / 2;

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                zx = 0;
                zy = 0;
                cX = (x - halfWidth) / Config.getZoom();
                cY = (y - halfHeight) / Config.getZoom();
                int iter = Config.getMaxIter();
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                bufferedImage.setRGB(x, y, iter | (iter << 8));
            }
        }
        return 0;
    }
}
