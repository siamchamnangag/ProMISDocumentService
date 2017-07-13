package com.scg.document.service;

import com.scg.document.model.DirDTO;
import com.scg.document.model.GetFileDTO;
import com.scg.document.model.UploadFileBody;
import com.scg.document.model.UploadFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        return restTemplate.getForObject(getFileURL.replace("{url}",templateLink), GetFileDTO.class);
    }

    public UploadFileDTO uploadFileContent(String link, byte[] fileContent, String description) throws Exception {

        UploadFileBody uploadingBody = new UploadFileBody(link.substring( link.lastIndexOf('/')+1, link.length() ),fileContent,description);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UploadFileBody> entity = new HttpEntity<>(uploadingBody,headers);

        return restTemplate.postForObject(postFileURL,entity , UploadFileDTO.class);
    }
}
