package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.VisitorRepository;
import softuni.exam.service.VisitorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class VisitorServiceImpl implements VisitorService {
    private static final String FILE_PATH = "src/main/resources/files/xml/visitors.xml";
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public boolean areImported() {
        return this.visitorRepository.count() > 0;
    }

    @Override
    public String readVisitorsFileContent() throws IOException {
        String xml = Files.readString(Path.of(FILE_PATH));

        return xml;
    }

    @Override
    public String importVisitors() {
        return null;
    }
}
