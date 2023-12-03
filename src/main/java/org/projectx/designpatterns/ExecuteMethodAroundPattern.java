package org.projectx.designpatterns;

import java.util.function.Consumer;

public class ExecuteMethodAroundPattern {

    public static void main(String[] args) {
        Mailer.execute((mailer) -> mailer.from("mike@gmail.com")
                .to("george@gmail.com")
                .body("This is a valid message"));
    }

    static class Mailer {

        private String from;
        private String to;
        private String body;

        private Mailer() {

        }

        private void print() {
            System.out.println("Sending message and the message is : " + body);
        }

        public Mailer from(String from) {
            this.from = from;
            return this;
        }

        public Mailer to(String to) {
            this.to = to;
            return this;
        }

        public Mailer body(String body) {
            this.body = body;
            return this;
        }

        public static void execute(Consumer<Mailer> mailerConsumer) {
            System.out.println("Perform some initial calls/ validations");
            Mailer mailer = new Mailer();
            mailerConsumer.accept(mailer);
            mailer.print();

            System.out.println("Perform some end/cleanup calls");
        }
    }
}
