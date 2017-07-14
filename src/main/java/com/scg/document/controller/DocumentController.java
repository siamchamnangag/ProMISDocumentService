package com.scg.document.controller;

import com.scg.document.model.DirDTO;
import com.scg.document.model.SAPUser;
import com.scg.document.model.SCGResponseBody;
import com.scg.document.model.UploadFileDTO;
import com.scg.document.service.DocumentService;
import com.scg.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tanatloke on 7/12/2017.
 */
@RestController()
@RequestMapping("documents")
public class DocumentController {

    @Autowired
    UserService userService;

    @Autowired
    DocumentService documentService;

    @RequestMapping(method = RequestMethod.POST, value = "/{doc_id}")
    ResponseEntity createDocumentFromTemplatev1(@PathVariable("doc_id") int documentId){

        //get dir object {description status user url} by docid
        DirDTO dir;

        try {
           dir  = documentService.getDirByDocID(documentId);
        } catch (Exception e) {
           return new ResponseEntity(new SCGResponseBody("document is not found"),HttpStatus.NOT_FOUND);
        }

        byte[] fileContent;
        try {
            fileContent = documentService.getFileContent(dir.getLink()).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new SCGResponseBody("upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);
        }


        UploadFileDTO uploadFileDTO;

        try {
            uploadFileDTO = (UploadFileDTO) documentService.uploadFileContent(dir.getLink(),fileContent,dir.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new SCGResponseBody("upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // create dir with new link and old dir
        return new ResponseEntity(uploadFileDTO.getLink(),HttpStatus.OK);
    }


}