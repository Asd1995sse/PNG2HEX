package main;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.util.Locale;

class ImageWriteTest {

    private BufferedImage originalImage;
    private BufferedImage textImage;
    private BufferedImage differenceImage;
    private BufferedImage bnwImage;

    private JPanel gui;

    private JCheckBox antialiasing;
    private JCheckBox rendering;
    private JCheckBox fractionalMetrics;
    private JCheckBox strokeControl;
    private JCheckBox colorRendering;
    private JCheckBox dithering;

    private JComboBox textAntialiasing;
    private JComboBox textLcdContrast;

    private JLabel label0_1;
    private JLabel label0_4;
    private JLabel label0_7;
    private JLabel label1_0;

    private JTextArea output;

    final static Object[] VALUES_TEXT_ANTIALIASING = {
        RenderingHints.VALUE_TEXT_ANTIALIAS_OFF,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON,
        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP,
        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR,
        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB,
        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR,
        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB
    };

    final static Object[] VALUES_TEXT_LCD_CONTRAST = {
        new Integer(100),
        new Integer(150),
        new Integer(200),
        new Integer(250)
    };

    ImageWriteTest() {
        int width = 280;
        int height = 100;

        gui = new JPanel(new BorderLayout(0,4));

        originalImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        textImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        differenceImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        bnwImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        JPanel controls = new JPanel(new GridLayout(0,2,0,0));
        antialiasing = new JCheckBox("Anti-aliasing", false);
        rendering = new JCheckBox("Rendering - Quality", true);
        fractionalMetrics = new JCheckBox("Fractional Metrics", true);
        strokeControl = new JCheckBox("Stroke Control - Pure", false);
        colorRendering = new JCheckBox("Color Rendering - Quality", true);
        dithering = new JCheckBox("Dithering", false);

        controls.add(antialiasing);
        controls.add(rendering);

        controls.add(fractionalMetrics);
        controls.add(colorRendering);

        textLcdContrast = new JComboBox(VALUES_TEXT_LCD_CONTRAST);
        JPanel lcdContrastPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        lcdContrastPanel.add(textLcdContrast);
        lcdContrastPanel.add(new JLabel("Text LCD Contrast"));
        controls.add(lcdContrastPanel);
        controls.add(strokeControl);

        textAntialiasing = new JComboBox(VALUES_TEXT_ANTIALIASING);
        controls.add(textAntialiasing);
        controls.add(dithering);

        ItemListener itemListener = new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                updateImages();
            }
        };
        antialiasing.addItemListener(itemListener);
        rendering.addItemListener(itemListener);
        fractionalMetrics.addItemListener(itemListener);
        strokeControl.addItemListener(itemListener);
        colorRendering.addItemListener(itemListener);
        dithering.addItemListener(itemListener);

        textAntialiasing.addItemListener(itemListener);
        textLcdContrast.addItemListener(itemListener);

        Graphics2D g2d = originalImage.createGraphics();
        GradientPaint gp = new GradientPaint(
            0f, 0f, Color.red,
            (float)width, (float)height, Color.orange);
        g2d.setPaint(gp);
        g2d.fillRect(0,0, width, height);

        g2d.setColor(Color.blue);
        for (int ii=0; ii<width; ii+=10) {
            g2d.drawLine(ii, 0, ii, height);
        }
        g2d.setColor(Color.green);
        for (int jj=0; jj<height; jj+=10) {
            g2d.drawLine(0, jj, width, jj);
        }

        updateImages();


        gui.add(controls, BorderLayout.NORTH);

        JPanel images = new JPanel(new GridLayout(0,2,0,0));
        images.add(new JLabel(new ImageIcon(originalImage)));
        images.add(new JLabel(new ImageIcon(textImage)));
        images.add(new JLabel(new ImageIcon(differenceImage)));
        images.add(new JLabel(new ImageIcon(bnwImage)));

        try {
            label0_1 = new JLabel(new ImageIcon(getJpegCompressedImage(0.1f, textImage)));
            images.add(label0_1);
            label0_4 = new JLabel(new ImageIcon(getJpegCompressedImage(0.4f, textImage)));
            images.add(label0_4);
            label0_7 = new JLabel(new ImageIcon(getJpegCompressedImage(0.7f, textImage)));
            images.add(label0_7);
            label1_0 = new JLabel(new ImageIcon(getJpegCompressedImage(1.0f, textImage)));
            images.add(label1_0);
        } catch(IOException ioe) {
        }

        gui.add(images, BorderLayout.CENTER);

        StringBuilder sb = new StringBuilder();
        String[] names = {
            "java.vendor",
            "java.version",
            "java.vm.version",
            "os.name",
            "os.version"
        };
        for (String name : names) {
            addProperty(sb, name);
        }
        output = new JTextArea(sb.toString(),6,40);
        gui.add(new JScrollPane(output), BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, gui);
    }

    private static void addProperty(StringBuilder builder, String name) {
        builder.append( name + " \t" + System.getProperty(name) + "\n" );
    }

    /** Adapted from SO post by x4u. */
    private Image getJpegCompressedImage(float quality, BufferedImage image) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        ImageWriter imgWriter = ImageIO.getImageWritersByFormatName( "jpg" ).next();
        ImageOutputStream ioStream = ImageIO.createImageOutputStream( outStream );
        imgWriter.setOutput( ioStream );

        JPEGImageWriteParam jpegParams = new JPEGImageWriteParam( Locale.getDefault() );
        jpegParams.setCompressionMode( ImageWriteParam.MODE_EXPLICIT );
        jpegParams.setCompressionQuality( quality );

        imgWriter.write( null, new IIOImage( image, null, null ), jpegParams );

        ioStream.flush();
        ioStream.close();
        imgWriter.dispose();

        BufferedImage compressedImage = ImageIO.read(new ByteArrayInputStream(outStream.toByteArray()));
        JLabel label = new JLabel("Quality: " + quality);
        label.setBackground(new Color(255,255,255,192));
        label.setOpaque(true);
        label.setSize(label.getPreferredSize());    
        label.paint(compressedImage.getGraphics());

        return compressedImage;
    }

    private void updateImages() {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        Graphics2D g2dText = textImage.createGraphics();

        if (antialiasing.isSelected()) {
            g2dText.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            g2dText.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        }

        if (rendering.isSelected()) {
            g2dText.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        } else {
            g2dText.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_SPEED);
        }

        if (fractionalMetrics.isSelected()) {
            g2dText.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        } else {
            g2dText.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        }

        if (strokeControl.isSelected()) {
            g2dText.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_NORMALIZE);
        } else {
            g2dText.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
        }

        if (dithering.isSelected()) {
            g2dText.setRenderingHint(RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_ENABLE);
        } else {
            g2dText.setRenderingHint(RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_DISABLE);
        }

        if (colorRendering.isSelected()) {
            g2dText.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        } else {
            g2dText.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_SPEED);
        }

        g2dText.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST,
            textLcdContrast.getSelectedItem());

        g2dText.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
            textAntialiasing.getSelectedItem());

        g2dText.drawImage(originalImage, 0,0, null);
        g2dText.setColor(Color.black);
        g2dText.drawString("The quick brown fox jumped over the lazy dog.", 10,50);

        Graphics2D g2dDifference = differenceImage.createGraphics();

        Graphics2D g2dBnW = bnwImage.createGraphics();

        for (int xx=0; xx<width; xx++) {
            for (int yy=0; yy<height; yy++) {
                Color originalColor = new Color(originalImage.getRGB(xx,yy));
                int r1 = originalColor.getRed();
                int g1 = originalColor.getGreen();
                int b1 = originalColor.getBlue();

                Color newColor = new Color(textImage.getRGB(xx,yy));
                int r2 = newColor.getRed();
                int g2 = newColor.getGreen();
                int b2 = newColor.getBlue();

                Color bnw = Color.black;
                if (r1==r2 && g1==g2 && b1==b2) {
                    bnw = Color.white;
                }
                bnwImage.setRGB(xx, yy, bnw.getRGB());

                int rD = Math.abs(r1-r2);
                int gD = Math.abs(g1-g2);
                int bD = Math.abs(b1-b2);

                Color differenceColor = new Color(rD,gD,bD);
                differenceImage.setRGB(xx, yy, differenceColor.getRGB());
            }
        }

        gui.repaint();
    }

    public static void main(String[] args) {
        
                new ImageWriteTest();
            
    }
}
