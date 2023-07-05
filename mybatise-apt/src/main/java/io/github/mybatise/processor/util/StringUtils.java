package io.github.mybatise.processor.util;

/**
 * @author Wes Lin
 */
public class StringUtils {

    private StringUtils() {

    }

    public static boolean hasLength(final String str) {
        return (str != null && !str.isEmpty());
    }

    public static String capitalize(final String str) {
        return changeFirstCharacterCase(str, true);
    }

    public static String uncapitalize(final String str) {
        return changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(final String str, boolean capitalize) {
        if (!hasLength(str)) {
            return str;
        }

        char baseChar = str.charAt(0);
        char updatedChar;
        if (capitalize) {
            updatedChar = Character.toUpperCase(baseChar);
        } else {
            updatedChar = Character.toLowerCase(baseChar);
        }
        if (baseChar == updatedChar) {
            return str;
        }

        char[] chars = str.toCharArray();
        chars[0] = updatedChar;
        return new String(chars, 0, chars.length);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * HelloWorld->HELLO_WORLD
     *
     * @param name
     * @return
     */
    public static String upperUnderscoreName(final String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            result.append(name.substring(0, 1).toUpperCase());
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * HelloWorld->hello_world
     *
     * @param name
     * @return
     */
    public static String lowerUnderscoreName(String name) {
        return upperUnderscoreName(name).toLowerCase();
    }

}
