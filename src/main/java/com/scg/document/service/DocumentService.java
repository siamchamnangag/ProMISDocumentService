package com.scg.document.service;

import com.scg.document.model.DirDTO;
import com.scg.document.model.GetFileDTO;
import com.scg.document.model.UploadFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by marcus on 7/13/2017 AD.
 */
@Service
public class DocumentService {

    @Value("${promis.dirservice.url.base}")
    private String getDirURL;

    @Value("${promis.fileservice.url.get}")
    private String getFileURL;

    @Value("${promis.fileservice.url.post}")
    private String postFileURL;

    @Autowired
    RestTemplate restTemplate;

    public DirDTO getDirByDocID(int documentId) throws Exception {
          return restTemplate.getForObject(getDirURL.replace("{docid}",String.valueOf(documentId)),DirDTO.class);
    }

    public GetFileDTO getFileContent(String templateLink) throws Exception {
        return restTemplate.getForObject(getDirURL.replace("{url}",templateLink), GetFileDTO.class);
    }

    public UploadFileDTO uploadFileContent(byte[] fileContent, String description) {
        HttpEntity<byte[]> entity = new HttpEntity<>(fileContent);
        return restTemplate.postForObject(postFileURL.replace("{description}",description),entity ,UploadFileDTO.class);
    }
}
