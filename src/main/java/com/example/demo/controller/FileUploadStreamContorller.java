package com.example.demo.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @param <T>
 *
 */
@RestController
@RequestMapping(value = "/file")
public class FileUploadStreamContorller {

    private final static Logger logger = LoggerFactory.getLogger(FileUploadStreamContorller.class);

    @RequestMapping(method = RequestMethod.POST, value = "/chunk", produces = "application/octet-stream")
    public ResponseEntity<HttpStatus> initUpload(@RequestBody(required = false) byte[] body) throws IOException {

        logger.info("ファイル取得リクエストきた");

        if (body == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        FileUtils.writeByteArrayToFile(new File("C:\\logs\\test.jpg"), body);

        ResponseEntity<HttpStatus> res = new ResponseEntity<>(HttpStatus.OK);
        return res;

    }
}
