package co.kr.bongjae.web.domain.file.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileUtils {

    /**
     * 허용된 파일 확장자 목록
     */
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".svg");

    /**
     * 파일명 추출
     * @param file 파일
     * @return 파일명
     */
    public static String getFileName(MultipartFile file) {
        return file.getOriginalFilename();
    }

    /**
     * 디렉토리가 존재하지 않으면 생성
     * @param directoryPath 디렉토리 경로
     * @return 생성된 디렉토리 경로
     * @throws IOException 디렉토리 생성 실패
     */
    public static Path ensureDirectoryExists(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        return ensureDirectoryExists(path);
    }

    /**
     * 디렉토리가 존재하지 않으면 생성
     * @param directoryPath 디렉토리 경로
     * @return 생성된 디렉토리 경로
     * @throws IOException 디렉토리 생성 실패
     */
    public static Path ensureDirectoryExists(Path directoryPath) throws IOException {
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        return directoryPath;
    }

    /**
     * 파일 확장자 추출
     * @param file 파일
     * @return 파일 확장자
     */
    public static String getFileExtension(MultipartFile file) {
        String fileName = getFileName(file);
        return getFileExtension(fileName);
    }

    /**
     * 파일 확장자 추출
     * @param fileName 파일명
     * @return 파일 확장자
     */
    public static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex).toLowerCase();
    }

    /**
     * 파일 저장
     * @param uploadPath 업로드 경로
     * @param fileName 파일명
     * @param file 파일
     * @return 저장된 파일 경로
     * @throws IOException 파일 저장 실패
     */
    public static void saveFile(Path uploadPath, String fileName, MultipartFile file) throws IOException {
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());
    }

    /**
     * 랜덤 파일명 생성
     * @return 랜덤 파일명
     */
    public static String generateUniqueFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStamp = dateFormat.format(new Date());

        // UUID for randomness and uniqueness
        String uniqueID = UUID.randomUUID().toString().replace("-", "");

        return timeStamp + "_" + uniqueID;
    }

    /**
     * 이미지 파일 여부 확인
     * @param file 파일
     * @return 이미지 파일 여부
     */
    public static Boolean isImageFile(MultipartFile file) {
        String fileName = getFileName(file);
        String extension = getFileExtension(fileName);
        return extension.matches("\\.(jpg|jpeg|png|gif|bmp|svg)$");
    }

    /**
     * 허용된 확장자인지 확인
     * @param extension 확장자
     * @return 허용된 확장자 여부
     */
    public static boolean isExtensionAllowed(String extension) {
        return ALLOWED_EXTENSIONS.contains(extension);
    }

    /**
     * 허용된 확장자인지 확인
     * @param file 파일
     * @return 허용된 확장자 여부
     */
    public static boolean isExtensionAllowed(MultipartFile file) {
        String extension = getFileExtension(file);
        return isExtensionAllowed(extension);
    }
}
