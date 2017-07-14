package com.scg.document.service;

import com.scg.document.model.DirDTO;
import com.scg.document.model.GetFileDTO;
import com.scg.document.model.UploadFileBody;
import com.scg.document.model.UploadFileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tanatloke on 7/13/2017 AD.
 */
@Service
public class DocumentService {

    @Autowired
    RestTemplate restTemplate;
    @Value("${promis.dirservice.url.base}")
    private String baseDirUrl;
    @Value("${promis.fileservice.url.get}")
    private String getFileURL;
    @Value("${promis.fileservice.url.post}")
    private String postFileURL;

    public DirDTO getDirByDocID(int documentId) throws Exception {
          return restTemplate.getForObject(baseDirUrl.replace("{docid}",String.valueOf(documentId)),DirDTO.class);
    }

    public GetFileDTO getFileContent(String templateLink) throws Exception {
        return restTemplate.getForObject(getFileURL.replace("{url}",templateLink), GetFileDTO.class);
    }

    public UploadFileResponseDTO uploadFileContent(String link, byte[] fileContent, String description) throws Exception {

        UploadFileBody uploadingBody = new UploadFileBody(link.substring( link.lastIndexOf('/')+1, link.length() ),fileContent,description);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UploadFileBody> entity = new HttpEntity<>(uploadingBody,headers);

        return restTemplate.postForObject(postFileURL,entity , UploadFileResponseDTO.class);
    }

    public DirDTO createDirByDirSerice(DirDTO fetchedDir){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DirDTO> entity = new HttpEntity<>(fetchedDir,headers);

        return restTemplate.postForObject(baseDirUrl.replace("/{docid}",""),entity,DirDTO.class);
    }


}
