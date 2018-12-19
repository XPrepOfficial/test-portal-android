package co.classplus.cms.utils;

public class AppConstants {
    public static int NULL_INT = -1;

    public enum QUESTION_TYPE {

        MULTIPLE_CHOICE("multiple_choice"),
        COMPREHENSION("comprehension"),
        TRUE_FALSE("true_false");

        private String value;

        QUESTION_TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
