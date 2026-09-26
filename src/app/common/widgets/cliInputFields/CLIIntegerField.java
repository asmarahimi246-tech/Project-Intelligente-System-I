package app.common.widgets.cliInputFields;

import java.util.InputMismatchException;
import java.util.Scanner;

import app.common.widgets.cliInputFields.common.CLIField;

/**
 * CLIIntegerField
 * 
 * takes integer input from the command line interface
 * 
 * A field is made using the builder pattern (explanation v)
 * https://refactoring.guru/design-patterns/builder
 * 
 * (example v)
 * CLIIntegerField field = new CLIIntegerField.Builder(scanner, errorMessage)
 *      .defaultInput(5)                (<- note: these are optional)
 *      .min(1)
 *      .max(10)
 *      .build();                       (<- note: this is not optional)
 */
public class CLIIntegerField extends CLIField<Integer> {
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    /**
     * makes a CLIIntegerField with a builder
     * 
     * @param builder the builder
     */
    private CLIIntegerField(Builder builder) {
        super.scanner = builder.scanner;
        super.errorMessage = builder.errorMessage;

        if (builder.hasDefault) {
            super.defaultInput = builder.defaultInput;
        } else {
            super.defaultInput = -1;
        }

        String readText = "( ";
        if (builder.hasMin) {
            this.min = builder.min;
            readText += this.min + " < ";
        }
        readText += "input";
        if (builder.hasMax) {
            this.max = builder.max;
            readText += " < " + this.max;
        }
        readText += " )";
        super.readText = readText;
    }

    /**
     * Builder
     * 
     * builds the field
     */
    public static class Builder extends CLIField.Builder<Integer, Builder> {
        private int min;
        private int max;
        private boolean hasMin = false;
        private boolean hasMax = false;

        /**
         * constructor with non optional params
         * 
         * @param scanner reference to the scanner
         * @param errorMessage message to print
         */
        public Builder(Scanner scanner, String errorMessage) {
            super(scanner, errorMessage);
        }

        @Override
        protected Builder self() {
            return this;
        }

        /**
         * optional min
         * 
         * @param value the min value
         * @return the builder
         */
        public Builder min(int value) {
            this.min = value;
            this.hasMin = true;
            return this;
        }

        /**
         * optional max
         * 
         * @param value the max value
         * @return the builder
         */
        public Builder max(int value) {
            this.max = value;
            this.hasMax = true;
            return this;
        }

        @Override
        public CLIIntegerField build() {
            return new CLIIntegerField(this);
        }
    }


    @Override 
    public void print() {
        System.out.println(super.readText);
    }

    @Override 
    public Integer getInput(){
        int toReturn = defaultInput;
        try {
            toReturn = scanner.nextInt();
            if(toReturn > this.max || toReturn < this.min){
                System.err.println("input out of range");
                System.err.println("using default: " + defaultInput);
                toReturn = defaultInput;
                System.err.println("press enter to exit error");
                scanner.nextLine();
            }
        } catch (InputMismatchException e) {
            System.err.println(errorMessage);
            System.err.println("using default: " + defaultInput);
            System.err.println("press enter to exit error");
            scanner.nextLine();
        }
        scanner.nextLine();
        return toReturn;
    }
}
