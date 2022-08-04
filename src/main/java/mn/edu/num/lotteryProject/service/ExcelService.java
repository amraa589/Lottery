package mn.edu.num.lotteryProject.service;

import mn.edu.num.lotteryProject.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelService {

    public void save(MultipartFile file);
    public ByteArrayInputStream load();
    public List<User> getAllTutorials();
}
