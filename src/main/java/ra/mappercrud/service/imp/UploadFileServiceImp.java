package ra.mappercrud.service.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.mappercrud.service.UploadFileService;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadFileServiceImp implements UploadFileService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File không hợp lệ");
        }

        String upload = file.getOriginalFilename();
        if (upload == null || !upload.contains(".")) {
            throw new IllegalArgumentException("Tên file không hợp lệ");
        }

        upload = upload.substring(0, upload.lastIndexOf("."));
        Map uploadParams = ObjectUtils.asMap(
                "public_id", upload
        );

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);
        return uploadResult.get("url").toString();
    }
}
