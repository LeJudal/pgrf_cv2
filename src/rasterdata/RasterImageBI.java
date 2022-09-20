package rasterdata;

import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.util.Optional;

public class RasterImageBI implements RasterImage<Integer>{

    private final @NotNull BufferedImage bufferedImage;

    public RasterImageBI(final int width, final int height) {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    @Override
    public @NotNull Optional<Integer> getPixel(final int c, final int r) {
        if(getWidth() > c && getHeight() > r && c >= 0 && r >= 0){
            return Optional.of(bufferedImage.getRGB(c, r));
        }
        return Optional.empty();
    }

    @Override
    public void setPixel(final int c, final int r, final @NotNull Integer newValue) {
        if(getWidth() > c && getHeight() > r && c >= 0 && r >= 0){
            bufferedImage.setRGB(c, r, newValue);
        }
    }
}
