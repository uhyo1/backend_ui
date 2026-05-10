package com.uhyo.backend_ui.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Vue → backend_ui → backend_data_management
 * の中継を行うコントローラ
 */
@RestController
public class FileProxyController {

    // backend_data_management の URL
    private static final String DATA_FILES_URL = "http://localhost:9001/files";
    private static final String DATA_DOWNLOAD_URL = "http://localhost:9001/download";

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * GET /files
     * ファイル一覧の中継
     */
    @GetMapping("/files")
    public List<String> getFiles() {
        return restTemplate.getForObject(DATA_FILES_URL, List.class);
    }

    /**
     * POST /download
     * ZIP ダウンロードの中継
     */
    @PostMapping("/download")
    public ResponseEntity<byte[]> downloadZip(@RequestBody List<String> filenames) {

        // backend_data_management に送る HTTP ヘッダ
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // リクエストエンティティ（body + header）
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(filenames, headers);

        // backend_data_management の /download を呼び出す
        ResponseEntity<byte[]> response = restTemplate.exchange(
                DATA_DOWNLOAD_URL,
                HttpMethod.POST,
                requestEntity,
                byte[].class
        );

        // Vue に返すレスポンス（ZIP のバイト配列）
        HttpHeaders outHeaders = new HttpHeaders();
        outHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"download.zip\"");
        outHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(response.getBody(), outHeaders, HttpStatus.OK);
    }
}
