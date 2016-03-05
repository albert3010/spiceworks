package emoji;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

/**
 * Created by omprakash.yadav on 21/02/16.
 */
public class Emoji {

    private final String description;
    private final String unicode;
    private final String htmlDec;
    private final String htmlHex;
    private final boolean supportsFitzpatrick;
    private final List<String> aliases;
    private final List<String> tags;

    protected Emoji(String description, boolean supportsFitzpatrick, List<String> aliases, List<String> tags, byte... bytes) {
        this.description = description;
        this.supportsFitzpatrick = supportsFitzpatrick;
        this.aliases = Collections.unmodifiableList(aliases);
        this.tags = Collections.unmodifiableList(tags);

        int count = 0;
        try {
            this.unicode = new String(bytes, "UTF-8");
            int stringLength = getUnicode().length();
            String[] pointCodes = new String[stringLength];
            String[] pointCodesHex = new String[stringLength];

            for (int offset = 0; offset < stringLength; ) {
                final int codePoint = getUnicode().codePointAt(offset);

                pointCodes[count] = String.format("&#%d;", codePoint);
                pointCodesHex[count++] = String.format("&#x%x;", codePoint);

                offset += Character.charCount(codePoint);
            }
            this.htmlDec = stringJoin(pointCodes, count);
            this.htmlHex = stringJoin(pointCodesHex, count);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Method to replace String.join, since it was only introduced in java8
     * @param array the array to be concatenated
     * @return concatenated String
     */
    private String stringJoin(String[] array, int count){
        String joined = "";
        for(int i = 0; i < count; i++)
            joined += array[i];
        return joined;
    }

    /**
     * Returns the description of the emoji
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns wether the emoji supports the Fitzpatrick modifiers or not
     *
     * @return true if the emoji supports the Fitzpatrick modifiers
     */
    public boolean supportsFitzpatrick() {
        return this.supportsFitzpatrick;
    }
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Returns the unicode representation of the emoji
     *
     * @return the unicode representation
     */
    public String getUnicode() {
        return this.unicode;
    }
//    public String getUnicode(Fitzpatrick fitzpatrick) {
//        if (!this.supportsFitzpatrick()) {
//            throw new UnsupportedOperationException("Cannot get the unicode with a fitzpatrick modifier, the emoji doesn't support fitzpatrick.");
//        } else if (fitzpatrick == null) {
//            return this.getUnicode();
//        }
//        return this.getUnicode() + fitzpatrick.unicode;
//    }

    /**
     * Returns the HTML decimal representation of the emoji
     *
     * @return the HTML decimal representation
     */
    public String getHtmlDecimal() {
        return this.htmlDec;
    }
    public String getHtmlHexidecimal() {
        return this.htmlHex;
    }

    /**
     * Returns the String representation of the Emoji object.<br>
     * <br>
     * Example:<br>
     * <code>Emoji{description='smiling face with open mouth and smiling eyes', supportsFitzpatrick=false, aliases=[smile], tags=[happy, joy, pleased], unicode='😄', htmlDec='&amp;#128516;', htmlHex='&amp;#x1f604;'}</code>
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Emoji{" +
                "description='" + description + '\'' +
                ", supportsFitzpatrick=" + supportsFitzpatrick +
                ", aliases=" + aliases +
                ", tags=" + tags +
                ", unicode='" + unicode + '\'' +
                ", htmlDec='" + htmlDec + '\'' +
                ", htmlHex='" + htmlHex + '\'' +
                '}';
    }


}
