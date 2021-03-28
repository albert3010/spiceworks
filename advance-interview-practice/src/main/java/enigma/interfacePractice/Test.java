package enigma.interfacePractice;

public class Test {

    public static void main(String[] args) {

        @Author(first = "chetan", last = "Bhagat")
        Book book = new Book("five point someone");
        Author author = (Author) book.getClass().getFields()[0].getAnnotation(Author.class);
        System.out.println(book.title);
        System.out.println(author.first() + "  " + author.last());
    }

}
