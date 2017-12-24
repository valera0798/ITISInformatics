package supports;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Валера on 03.08.2017.
 */
public class Designer {
    public static final double LINE_WIDTH_PERCENT = 0.017;
    public static String hexFontSecondaryColor = "#7C8995";
    public static String hexFontColor = "#4c5d6e";
    public static String hexCrossColor = "#4db6ac";
    public static String hexZeroColor = "#e49091";
    public static Color crossColor = new Color(77, 182, 172);
    public static Color zeroColor = new Color(228, 144, 145);
    public static Color primaryColor = new Color(255, 255, 255);
    public static Color primaryDarkColor = new Color(221, 225, 232);
    public static Color fontSecondaryColor = new Color(137, 150, 162);
    public static Color fontColor = new Color(76, 93, 110);
    public static Color backgroundColorSemiTransparent = new Color(255, 255, 255, 0);

    public static Insets insetsExternal = new Insets(5, 5, 5, 5);
    public static Insets insetsInner = new Insets(0, 5, 0, 5);

    public static String cooperBlackFont = "Cooper Black";  // eng
    public static String dialogFont = "Dialog"; // eng
    public static String lucidaFaxFont = "Lucida Fax";  // eng
    public static String britannicBoldFont = "Britannic Bold";  // eng
    public static String arialNarrowFont = "Arial Narrow";  // eng, ru
    public static String comicSansFont = "Comic Sans MS";  // eng, ru
    public static int fontSize = 16;
    public static int titleFontSizeHTML = 5;
    public static int textFontSizeHTML = 4;
    public static Font font = new Font(comicSansFont, Font.PLAIN, fontSize);

    // обрамление
    public static Border getNextTurnInfoBorder(String playerName, char side) {
        Color borderColor = crossColor;
        switch (side) {
            case Strings.DOT_CROSS:
                playerName = getColoredText(hexCrossColor, playerName);
                borderColor = crossColor;
                break;
            case Strings.DOT_ZERO:
                playerName = getColoredText(hexZeroColor, playerName);
                borderColor = zeroColor;
                break;
        }
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(borderColor, 2, true),
                playerName);
    }
    public static void setCustomBorder(JPanel panel, String titleText, int justification) {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Designer.fontColor),
                Designer.getColoredTitle(Designer.hexFontColor, titleText));
        titledBorder.setTitleJustification(justification);
        panel.setBorder(titledBorder);
        ((TitledBorder) panel.getBorder()).setTitleFont(font);
    }
    // настройка ограничений
    public static void adjustConstraintsTitle(GridBagConstraints c) {
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = insetsExternal;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0;
        c.weighty = 1;
    }
    public static void adjustConstraintsTextField(GridBagConstraints c) {
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = insetsExternal;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1;
        c.weighty = 1;
    }
    public static void adjustConstraintsRadioButtonContainer(GridBagConstraints c) {
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = insetsExternal;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1;
        c.weighty = 1;
    }
    public static void adjustConstraintsRadioButton(GridBagConstraints c) {
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = insetsInner;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1;
        c.weighty = 1;
    }
    // преобразование элементов на основе стилистики
    public static float getLineWidth(int width) {
        return (float) (width*LINE_WIDTH_PERCENT);
    }
    public static String getColoredTitle(String hexColor, String text) {
        return "<html><font color='" + hexColor + "' " +
                "face='" + comicSansFont + "' " +
                "size=" + titleFontSizeHTML + ">" + text + "</font></html>";
    }
    public static String getColoredText(String hexColor, String text) {
        return "<html><font color='" + hexColor + "' " +
                "face='" + comicSansFont + "' " +
                "size=" + textFontSizeHTML + ">" + text + "</font></html>";
    }
    public static String getColoredSecondaryText(String hexColor, String text) {
        return "<html><font color='" + hexColor + "' " +
                "face='" + comicSansFont + "' " +
                "size=" + textFontSizeHTML + "><i>" + text + "</i></font></html>";
    }
    // изображения
    public static ImageIcon getBackgroundImageIcon(String imagePath) {
        try {
            return new ImageIcon(ImageIO.read(new File(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }
}
