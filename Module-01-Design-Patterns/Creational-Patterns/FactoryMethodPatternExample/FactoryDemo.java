interface Document {
    void open();
    void close();
    void save();
}

class Word implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word document...");
    }

    @Override
    public void close() {
        System.out.println("Closing Word document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document...");
    }
}

class PDF implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document...");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF document...");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document...");
    }
}

class Excel implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel document...");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel document...");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
    public Document open() {
        Document document = createDocument();
        document.open();
        return document;
    }
}

class WordFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new Word();
    }
}

class PDFFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PDF();
    }
}

class ExcelFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new Excel();
    }
}

public class FactoryDemo {  
    public static void main(String[] args) {
        DocumentFactory[] facts = {
            new WordFactory(),
            new PDFFactory(),
            new ExcelFactory()
        };

        for (DocumentFactory f : facts) {
            Document document = f.open();
            document.save();
            document.close();
        }
    }
}

//Output:
// Opening Word document...
// Saving Word document...
// Closing Word document...
// Opening PDF document...
// Saving PDF document...
// Closing PDF document...
// Opening Excel document...
// Saving Excel document...
// Closing Excel document...