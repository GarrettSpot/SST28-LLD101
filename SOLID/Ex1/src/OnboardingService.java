import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private final Validator validator;
    private final Parser parser;
    private final Printer printer;

    public OnboardingService(StudentRepository db, Validator validator, Parser parser, Printer printer) {
        this.db = db;
        this.validator = validator;
        this.parser = parser;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        Map<String, String> kv = parser.parseRawInputToMap(raw);

        if (!validator.validate(kv)) {
            return;
        }

        StudentRecord rec = studentFromMap(kv);
        saveStudentInDB(rec);
    }

    private StudentRecord studentFromMap(Map<String, String> kv) {
        String id = IdUtil.nextStudentId(db.count());
        kv.put("id", id);
        StudentRecord rec = new StudentRecord(kv);
        return rec;
    }

    private void saveStudentInDB(StudentRecord rec) {
        db.save(rec);
        printer.printStudentCreated(rec, db.count());
    }
}
