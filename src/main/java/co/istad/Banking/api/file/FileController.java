package co.istad.Banking.api.file;

import co.istad.Banking.base.BaseRest;
import co.istad.Banking.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {

    @Value("${file.base-url}")
    public String fileBaseUrl;
    private final FileService fileService;

    @PostMapping
    public BaseRest<?> uploadSingle(@RequestPart("file") MultipartFile file) {
        FileDto fileDto = fileService.uploadSingle(file);
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("File have been Uploaded")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @PostMapping("/upload-multiple")
    public BaseRest<?> uploadMultiple(@RequestPart("files") List<MultipartFile> files) {
        List<FileDto> fileDto = fileService.uploadMultiple(files);
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("File have been Uploaded")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }


    @GetMapping
    public BaseRest<?> getListOfFile() {
        List<FileDto> fileDtos = fileService.getAllFiles();
        if (fileDtos.isEmpty()) {
            System.out.println("Files Not Found");
        }
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("File have been Retrieve *")
                .timestamp(LocalDateTime.now())
                .data(fileDtos)
                .build();
    }

    @DeleteMapping("/{fileName}")
    public BaseRest<?> removeFileByName(@PathVariable String fileName) {
        FileDto fileDto = fileService.removeFileByName(fileName);

        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("This FileName have been Removed !")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }


    @DeleteMapping
    public BaseRest<?> removeAllFile() {
        boolean fileDto = fileService.removeAllFile();
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("All File have been Removed !")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileName") String fileName) {
        Resource resource = fileService.getDownloadFileByName(fileName);
        return ResponseEntity
                .ok()
                . contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() +"\"")
                .body(resource);


    }



}
