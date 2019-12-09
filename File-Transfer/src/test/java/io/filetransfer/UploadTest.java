package io.filetransfer;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.filetransfer.sftp.Upload;

import static org.junit.Assert.*;

import java.io.IOException;

public class UploadTest {

	@Test
    public ResponseEntity<Object> upload() throws IOException {
        return new Upload().upload(null);
    }
	
}
