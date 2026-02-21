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
        Map<String, String> kv = parser.parseRawInput(raw);

        if (!validator.validate(kv)) {
            return;
        }

        StudentRecord rec = studentFromMap(kv);
        saveStudentInDB(rec);
    }

    private StudentRecord studentFromMap(Map<String, String> kv) {
        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");
        
        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        return rec;
    }

    private void saveStudentInDB(StudentRecord rec) {
        db.save(rec);
        printer.printStudentCreated(rec, db.count());
    }
}
