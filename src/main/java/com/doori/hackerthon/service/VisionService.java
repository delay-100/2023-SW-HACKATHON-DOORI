package com.doori.hackerthon.service;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class VisionService {

    //    @Value("${gcp.config.file}")
//    private String gcpConfigFile;
//
//    @Value("${gcp.project.id}")
//    private String gcpProjectId;
//
//    @Value("${gcp.bucket.id}")
//    private String gcpBucketId;
//
//    @Value("${gcp.dir.name}")
//    private String gcpDirectoryName;
//    public static final String STATIC_DIRECTORY = "src/main/resources/data";
//    private ChatgptService chatgptService;
//    public void processGcsFiles() throws Exception {
//        // Read and process GCS files using your credentials and configurations
//        String gcsSourcePath = "gs://" + gcpBucketId + "/" + gcpDirectoryName + "/[붙임 2] 산학협력프로젝트 결과물.pdf";
//        String gcsDestinationPath = "gs://" + gcpBucketId + "/" + gcpDirectoryName + "/output/";
//
////        detectDocumentsGcs(gcsSourcePath, gcsDestinationPath);
//    }

    @SneakyThrows
    public String extractContent(MultipartFile multipartFile) {
        String text;
        String name = multipartFile.getOriginalFilename();
        try (final PDDocument document = PDDocument.load(multipartFile.getInputStream())) {
            final PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (final Exception ex) {
            text = "Error parsing PDF";
        }
//
//        try {
////            String dataPath = new ClassPathResource(STATIC_DIRECTORY).getFile().getAbsolutePath();
//            // 파일 생성 및 내용 쓰기
//            String fileName = "data.txt";
//
//            Files.write(Paths.get(STATIC_DIRECTORY, fileName), text.getBytes());
//            System.out.println("File created successfully.");
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return text;
    }

//    public ResponseEntity<String> getDetectText(String gcsPath) throws IOException {
//        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(gcpConfigFile));
//
//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//        try {
//            // 사용자가 지정한 Google Cloud Storage 경로의 이미지에서 텍스트 추출
//            String extractedText = Detect.detectDocumentTextGcs(gcsPath);
//            return ResponseEntity.ok(extractedText);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().body("Error occurred while extracting text");
//        }
//    }
}
