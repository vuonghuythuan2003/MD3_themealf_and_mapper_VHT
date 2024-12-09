package ra.mappercrud.config;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration

public class CloundiaryConfig {
    private static final String CLOUND_NAME = "dtesyeewl";
    private static final String API_KEY = "139496741997153";
    private static final String API_SECRET = "HwYxk-fzSRuDpuJ8eDJjIzVRATA";

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUND_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET
        ));
    }
}
