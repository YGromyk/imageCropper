import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This program demonstrates how to resize an image.
 *
 * @author www.codejava.net
 */
public class ImageResizer {

    public static void resize(String inputImagePath,
                              String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        System.out.println("inputImagePath = " + inputImagePath);
        System.out.println("outputImagePath = " + outputImagePath);

        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

    /**
     * Resizes an image by a percentage of original size (proportional).
     *
     * @param inputImagePath  Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent         a double number specifies percentage of the output image
     *                        over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath,
                              String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

    /**
     * Test resizing images
     */
    public static void main(String[] args) {
        System.out.println("Input the path to directory with photos");
        Scanner inputImagePath = new Scanner(System.in);
        String path = inputImagePath.next();
        File[] files = new File(path).listFiles();
        if (files != null)
            for (File image : files) {
                try {
                    String pathImage = image.getAbsolutePath();
                    if (pathImage.contains(".jpg") || pathImage.contains(".png")) {
                        // resize to a fixed width (not proportional)
                        int scaledWidth = 512;
                        int scaledHeight = 512;
                        ImageResizer.resize(
                                image.getPath(),
                                path + "copies/" + image.getName(),
                                scaledWidth,
                                scaledHeight
                        );
                    }
                } catch (IOException ex) {
                    System.out.println("Error resizing the image.");
                    ex.printStackTrace();
                }
            }


    }

}