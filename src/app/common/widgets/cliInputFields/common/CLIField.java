package app.common.widgets.cliInputFields.common;

import java.util.Scanner;

/**
 * CLIField
 * A field is made using the builder pattern (explanation v)
 * https://refactoring.guru/design-patterns/builder
 * 
 * @param <T> the datatype of the input
 */
public abstract class CLIField<T> {
    protected Scanner scanner;
    protected String errorMessage;
    protected T defaultInput;
    protected String readText;

    /**
     * Builder
     * 
     * builds the field
     * @param <T> the datatype of the input
     * @param <B> the builder class; B must extend {@code Builder<T, B>}
     */
    protected abstract static class Builder<T, B extends Builder<T, B>> {
        // too lazy to make getters for this
        public final Scanner scanner;
        public final String errorMessage;
        public T defaultInput;
        public boolean hasDefault = false;

        /**
         * constructor with non optional params
         * 
         * @param scanner reference to the scanner
         * @param errorMessage message to print
         */
        protected Builder(Scanner scanner, String errorMessage) {
            this.scanner = scanner;
            this.errorMessage = errorMessage;
        }

        /**
         * @return self
         */
        protected abstract B self();

        /**
         * sets the defauly input
         * 
         * @param value the value to set
         * @return self
         */
        public B defaultInput(T value) {
            this.defaultInput = value;
            this.hasDefault = true;
            return self();
        }

        /**
         * bulds the field
         * 
         * @return the field
         */
        public abstract CLIField<T> build();
    }

    /**
     * prints the field
     */
    protected abstract void print();

    /**
     * gets input for the field
     * 
     * @return the input
     */
    protected abstract T getInput();
}