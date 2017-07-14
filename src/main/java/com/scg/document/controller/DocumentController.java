package com.scg.document.controller;

import com.scg.document.model.DirDTO;
import com.scg.document.model.SAPUser;
import com.scg.document.model.SCGResponseBody;
import com.scg.document.model.UploadFileResponseDTO;
import com.scg.document.service.DocumentService;
import com.scg.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tanatloke on 7/12/2017.
 */
@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("documents")
public class DocumentController {

    @Autowired
    UserService userService;

    @Autowired
    DocumentService documentService;

    @RequestMapping(method = RequestMethod.POST, value = "/{doc_id}")
    ResponseEntity createDocumentFromTemplate(@PathVariable("doc_id") int documentId,
                                                @RequestParam(required = true,name = "xomlanid") String xomLanId) throws Exception {

        SAPUser sapUser;
        try {
            sapUser = userService.getSAPUser(xomLanId);
        } catch (Exception e) {
            return new ResponseEntity(new SCGResponseBody("User not found."),HttpStatus.NOT_FOUND);
        }

        DirDTO fetchedDir;
        try {
            fetchedDir  = documentService.getDirByDocID(documentId);
        } catch (Exception e) {
           return new ResponseEntity(new SCGResponseBody("document is not found"),HttpStatus.NOT_FOUND);
        }

        byte[] fileContent;
        try {
            fileContent = documentService.getFileContent(fetchedDir.getLink()).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new SCGResponseBody("upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UploadFileResponseDTO uploadResponse;
        try {
            uploadResponse = (UploadFileResponseDTO) documentService.uploadFileContent(fetchedDir.getLink(),fileContent,fetchedDir.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new SCGResponseBody("upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // create dir with new link and old dir
        fetchedDir.setLink(uploadResponse.getLink());
        fetchedDir.setUser(sapUser.getSapid());

        DirDTO createdDirResponse = documentService.createDirByDirSerice(fetchedDir);

        return new ResponseEntity(createdDirResponse,HttpStatus.OK);
    }


}